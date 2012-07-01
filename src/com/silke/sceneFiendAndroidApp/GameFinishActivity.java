package com.silke.sceneFiendAndroidApp;

import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.silke.sceneFiendAndroidApp.asynctasks.FileDownloader;
import com.silke.sceneFiendAndroidApp.asynctasks.IJsonDownloaded;
import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;

public class GameFinishActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener, IJsonDownloaded
{
	//importing xml 
	TextView textviewScore;
	TextView finishName;
	UserFunctions userFunctions;
	static JSONObject jObj = null;
	Button btnLogout;
	Button twitter;
	TextView update_error;
	private GameFinishActivity context;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        context = this;
        
        //the custom title font
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
       
        //CHECK WHETHER THE DEVICE IS ONLINE
        isNetworkAvailable();
        
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		textviewScore = (TextView) findViewById(R.id.textviewScore);
		finishName = (TextView) findViewById(R.id.finishName);
		update_error = (TextView) findViewById(R.id.update_error);
		
		callAndSendScoreInfo();
		
		
		
		
		 //checking whether the user is logged in before adding the logout button
        userFunctions = new UserFunctions();
        if(userFunctions.isUserLoggedIn(getApplicationContext()))
        {
	        //the logout button resets the db tables and moves user back to the login screen
	        btnLogout = (Button) findViewById(R.id.btnLogout);
	        btnLogout.setOnClickListener(new OnClickListener()
	        {
				public void onClick(View v) 
				{
					// TODO Auto-generated method stub
					userFunctions.logoutUser(getApplicationContext());			
					Intent login = new Intent(getApplicationContext(), 
							LoginActivity.class);
					login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(login);
					finish();
				}
	        });
        }
    }
   //return user score and username and send data to the external and local dbs
    public void callAndSendScoreInfo()
    {
    	textviewScore.setText(GAME_PREFERENCES_PLAYER_SCORE.toString());
    	finishName.setText(GAME_PREFERENCES_PLAYER_NAME);
    	//Log.d("DISPLAY USER NAME HERE TOO", GAME_PREFERENCES_PLAYER_NAME);
    	
    	// user successfully logged in
		// Store user score details in SQLite Database
		DBHandler db = new DBHandler(getApplicationContext());
		
		
		long time = (Calendar.getInstance().getTimeInMillis() / 1000L);
		String s = String.valueOf(time);
		
		//db.addScore(GAME_PREFERENCES_PLAYER_SCORE, time);
		String player_id = GAME_PREFERENCES_PLAYER_ID.toString();
		String player_score = GAME_PREFERENCES_PLAYER_SCORE.toString();
		String score_date = s;
		
		UserFunctions userFunction = new UserFunctions();
		FileDownloader fd = userFunction.updateScore(context, player_id, player_score, s);
		
		String json_str = fd.getApiResponse();
			
		Log.d("Testing JSON string for SCORE", json_str);
		
		//parse string to json object
			try 
			{
			jObj = new JSONObject(json_str);
		} 
			catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
       
    //Json has been downloaded now - login with user entries (context) - passed to userfunctions (context)
    //passed to filedownloader (context) on finishing download calls this function and pass the jObj to it
    //interface was to datatype which kind of object is being passed to filedownloader
	public void onJsonDownloaded(JSONObject jObj) 
	{
		successfulUpdate(jObj);
	}
	
	//this function passes data to the sqlite db and if success tag registered pass user to menu activity
    private void successfulUpdate(JSONObject jObj)
    {
    	// check for login response
			try {
				if (jObj.getString("success") != null) 
				{
					update_error.setText("Score Updated!");
					update_error.setTextColor(Color.parseColor("#FFCC66"));
					
					String res = jObj.getString("success"); 
					if(Integer.parseInt(res) == 1)
					{
//						// user successfully logged in
//						// Store user details in SQLite Database
//						DBHandler db = new DBHandler(getApplicationContext());
//						JSONObject json_user = jObj.getJSONObject("user");
//						
//						// Clear all previous data in database
//						UserFunctions userFunction = new UserFunctions();
//						userFunction.logoutUser(getApplicationContext());
//						
//						//add new data to db
//						db.addUser(jObj.getString(DBHandler.KEY_PLAYER_ID), json_user.getString(DBHandler.KEY_PLAYER_NAME), json_user.getString(DBHandler.KEY_PLAYER_EMAIL));						
//						GAME_PREFERENCES_PLAYER_ID = jObj.getString(DBHandler.KEY_PLAYER_ID);
//						
//						Log.d("LOGIN ACTIVITY", "Logged in player_name: " + jObj.getString("user"));
//						//GAME_PREFERENCES_PLAYER_NAME = jObj.getString("player_name");
//						Log.d("LOGIN ACTIVITY", "Logged in player_id: " + GAME_PREFERENCES_PLAYER_ID);
//						//Log.d("LOGIN ACTIVITY", "Logged in player_name: " + GAME_PREFERENCES_PLAYER_NAME);
//						
						DBHandler db = new DBHandler(getApplicationContext());
						JSONObject json_user = jObj.getJSONObject("user");
						
						// Clear all previous data in database
						UserFunctions userFunction = new UserFunctions();
						userFunction.loseScore(getApplicationContext());
		
						//add new data to db
						db.addScore(jObj.getString(DBHandler.KEY_PLAYER_ID), json_user.getString(DBHandler.KEY_PLAYER_SCORE), json_user.getString(DBHandler.KEY_SCORE_DATE));						
						GAME_PREFERENCES_PLAYER_ID = jObj.getString(DBHandler.KEY_PLAYER_ID);
						
						
						Log.d("SENDING SCORE ACTIVITY", "Sending: " + jObj.getString("user"));

							
						//finish();
					}
			
					//if there is an error
					if(jObj.getInt("success") == 0)
					{		
						// Error in login - provide feedback in red
						update_error.setText("Your score could not be sent!");
						update_error.setTextColor(Color.parseColor("#FF0000"));	
					}
				}
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			
    }
	
    
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				GAME_PREFERENCES_PLAYER_SCORE = 0;
				GAME_PREFERENCES_CURRENT_QUESTION = 1;
				Intent i = new Intent(getApplicationContext(),
 						MenuActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}

	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
	}
	//check network availability for the twitter button to appear
	private boolean isNetworkAvailable() 
    {
    	Log.d("NOW IN", "IS NETWORK AVAILABLE");
        ConnectivityManager connectivityManager 
              = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Log.d("WIFI IS ON", "ABOUT TO CALL ADDTWITTER FUNCITON");
        addTwitter(activeNetworkInfo);
         
        
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();    
    }
  
   
  
    private void addTwitter(NetworkInfo activeNetworkInfo)
    {
    	
    	// TODO Auto-generated method stub
		if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting())
        {	
			
        	GAME_PREFERENCES_WIFI = true;
        	
        	//Log.d("GAME WIFI", "ON");
        	
        	twitter = (Button) findViewById(R.id.twitter);
        	twitter.setText(R.string.tweetMyScore);
            
        	Log.d("TWITTER CALL", "twitter button called");
        	twitter.setOnClickListener(new OnClickListener()
        	{
				public void onClick(View v) 
				{	
					// TODO Auto-generated method stub
					 startActivity(new Intent(GameFinishActivity.this, TwitterLoginActivity.class));
				}       		
        	});       	
        }
        else
        {
        	//Log.d("GAME WIFI", "Nope");
        	GAME_PREFERENCES_WIFI = false;
        	
        	//Log.d("GAME WIFI", "ON");
        	
        	twitter = (Button) findViewById(R.id.twitter);
        	twitter.setText(R.string.goBack);
            
        	Log.d("TWITTER NO WIFI CALL", "twitter button called");
        	twitter.setOnClickListener(new OnClickListener()
        	{
				public void onClick(View v) 
				{	
					// TODO Auto-generated method stub
					 startActivity(new Intent(GameFinishActivity.this, MenuActivity.class));
				}       		
        	});       	
        }
		   	
    }

	
}
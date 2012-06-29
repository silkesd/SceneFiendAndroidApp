package com.silke.sceneFiendAndroidApp;

import java.util.Calendar;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
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

import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;

public class GameFinishActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener
{
	
	TextView textviewScore;
	TextView finishName;
	UserFunctions userFunctions;
	Button btnLogout;
	Button twitter;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        
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
   
    public void callAndSendScoreInfo()
    {
    	textviewScore.setText(GAME_PREFERENCES_PLAYER_SCORE.toString());
    	finishName.setText(GAME_PREFERENCES_PLAYER_NAME);
    	Log.d("DISPLAY USER NAME HERE TOO", GAME_PREFERENCES_PLAYER_NAME);
    	
    	// user successfully logged in
		// Store user score details in SQLite Database
		DBHandler db = new DBHandler(getApplicationContext());
		
		long time = (Calendar.getInstance().getTimeInMillis() / 1000L);
		
		db.addScore(GAME_PREFERENCES_PLAYER_ID, GAME_PREFERENCES_PLAYER_SCORE, time);						
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
        	
        	Log.d("GAME WIFI", "ON");
        	
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
        	Log.d("GAME WIFI", "Nope");
        }
		   	
    }
}
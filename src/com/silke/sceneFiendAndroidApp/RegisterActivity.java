package com.silke.sceneFiendAndroidApp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.silke.sceneFiendAndroidApp.asynctasks.FileDownloader;
import com.silke.sceneFiendAndroidApp.asynctasks.IJsonDownloaded;
import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;


public class RegisterActivity extends SceneFiendAndroidAppActivity implements IJsonDownloaded
{
	Button btnRegister;
	Button btnLinkToLogin;
	static JSONObject jObj = null;
	ImageButton btnBack;
	EditText inputUsername;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;
	private RegisterActivity context;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        context = this;
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
       
        //actionbar
  		ActionBar ab = getActionBar();
  		ab.setDisplayHomeAsUpEnabled(true);
      		
        // Importing all assets like buttons, text fields
 		inputUsername = (EditText) findViewById(R.id.registerName);
 		inputEmail = (EditText) findViewById(R.id.registerEmail);
 		inputPassword = (EditText) findViewById(R.id.registerPassword);
 		btnRegister = (Button) findViewById(R.id.btnRegister);
 		btnLinkToLogin = (Button) findViewById(R.id.btnLinkToLoginScreen);
 		registerErrorMsg = (TextView) findViewById(R.id.register_error);
 		
 		
 		
 		// Register Button Click event
 		btnRegister.setOnClickListener(new View.OnClickListener() 
 		{			
			public void onClick(View view) 
			{
				String player_name = inputUsername.getText().toString();
				String player_email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				FileDownloader fd = userFunction.registerUser(context, player_name, player_email, password);
				//userFunction.registerUser(player_name, player_email, password);
				
				String json_str = fd.getApiResponse();
				
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
				
				/**
				 * You need to get what the user has written in the text fields,
				 * 
				 * attach the data as a list value pair list, then call file downloader ...
				 * 
				 */
				
				
			}
		});

		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view) 
			{
				Intent i = new Intent(getApplicationContext(),
						SceneFiendLoginActivity.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}
    
    //this function passes data to the sqlite db and if success tag registered pass user to menu activity
    private void successfulReg(JSONObject jObj)
    {
    	// check for register response			
		try 
		{
			if (jObj.getString("success") != null) 
			{
				registerErrorMsg.setText("You're Registered and Logged In!");
				registerErrorMsg.setTextColor(Color.parseColor("#FFCC66"));
				String res = jObj.getString("success"); 
				if(Integer.parseInt(res) == 1)
				{
					GAME_PREFERENCES_LOGGED_IN = true;
					// user successfully registered
					// Store user details in SQLite Database
					DBHandler db = new DBHandler(getApplicationContext());
					JSONObject json_user = jObj.getJSONObject("user");
					
					// Clear all previous data in database
					UserFunctions userFunction = new UserFunctions();
					userFunction.logoutUser(getApplicationContext());
					
					db.addUser(jObj.getString(DBHandler.KEY_PLAYER_ID), json_user.getString(DBHandler.KEY_PLAYER_NAME), json_user.getString(DBHandler.KEY_PLAYER_EMAIL));						
					GAME_PREFERENCES_PLAYER_ID = jObj.getString(DBHandler.KEY_PLAYER_ID);
					
					Log.d("REGISTER ACTIVITY", "Registered player_name: " + jObj.getString("user"));
					//GAME_PREFERENCES_PLAYER_NAME = jObj.getString("player_name");
					Log.d("REGISTER ACTIVITY", "Registered player_id: " + GAME_PREFERENCES_PLAYER_ID);
					//Log.d("LOGIN ACTIVITY", "Logged in player_name: " + GAME_PREFERENCES_PLAYER_NAME);
					
					
					// Launch Menu Screen
					Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
					// Close all views before launching Menu
					menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(menu);
					// Close Registration Screen
					finish();
				}
				//if the username inputted already exists
				if(jObj.getInt("error") == 2)
				{	
					// Error in registration - reset the textfields to empty
					inputUsername.setText("");
					inputEmail.setText("");
					inputPassword.setText("");
					// Error in registration - provide feedback in red
					registerErrorMsg.setText("That user already exists! Please try another");
					registerErrorMsg.setTextColor(Color.parseColor("#FF0000"));	
				}
				//if the username inputted already exists
				if(jObj.getInt("error") == 1)
				{	
					// Error in registration - reset the textfields to empty
					inputUsername.setText("");
					inputEmail.setText("");
					inputPassword.setText("");
					// Error in registration - provide feedback in red
					registerErrorMsg.setText("Something went wrong! Please try again");
					registerErrorMsg.setTextColor(Color.parseColor("#FF0000"));	
				}
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}

    }
    
  //Json has been downloaded now - register and login with user entries (context) - passed to userfunctions (context)
    //passed to filedownloader (context) on finishing download calls this function and pass the jObj to it
    //interface was to datatype which kind of object is being passed to filedownloader
	public void onJsonDownloaded(JSONObject jObj) 
	{
		successfulReg(jObj);
	}
    
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent i = new Intent(getApplicationContext(),
 						LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				Log.d("RegisterAct", "activity started");
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}
    
} 
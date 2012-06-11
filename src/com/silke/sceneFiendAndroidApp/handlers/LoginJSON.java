package com.silke.sceneFiendAndroidApp.handlers;

import org.json.JSONException;
import org.json.JSONObject;

import com.silke.sceneFiendAndroidApp.*;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginJSON extends Activity
{
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputUsername;
	EditText inputPassword;
	TextView loginErrorMsg;
	static JSONObject jObj = null;

	// JSON Response node names
	private static String KEY_SUCCESS = "success";
	private static String KEY_ERROR = "error";
	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_ID = "player_id";
	private static String KEY_NAME = "player_name";
	private static String KEY_EMAIL = "player_email";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	super.onCreate(savedInstanceState);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
    	
    	// Importing all assets like buttons, text fields
     	inputUsername = (EditText) findViewById(R.id.loginUsername);
     	inputPassword = (EditText) findViewById(R.id.loginPassword);
     	btnLogin = (Button) findViewById(R.id.btnLogin);
     	btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
     	loginErrorMsg = (TextView) findViewById(R.id.login_error);
     	
	// check for login response
		try {
			if (jObj.get(KEY_SUCCESS) != null) 
			{
				loginErrorMsg.setText("");
				String res = jObj.getString(KEY_SUCCESS); 
				if(Integer.parseInt(res) == 1)
				{
					// user successfully logged in
					// Store user details in SQLite Database
					DBHandler db = new DBHandler(getApplicationContext());
					JSONObject json_user = jObj.getJSONObject("user");
					
					// Clear all previous data in database
					UserFunctions userFunction = new UserFunctions();
					userFunction.logoutUser(getApplicationContext());
					db.addUser(jObj.getString(KEY_ID), json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL));						
					
					// Launch Menu Screen
				Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
				
				// Close all views before launching Menu
				menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(menu);
					
					// Close Login Screen
					finish();
				}
				else
				{
					// Error in login
					loginErrorMsg.setText("Incorrect username/password");
				}
			}
		} 
		catch (JSONException e) 
		{
			e.printStackTrace();
		}
    }
}

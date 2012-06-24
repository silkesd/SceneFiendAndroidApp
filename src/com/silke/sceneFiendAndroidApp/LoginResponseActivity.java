package com.silke.sceneFiendAndroidApp;

import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;
import android.content.Intent;
import android.util.Log;

public class LoginResponseActivity extends SceneFiendLoginActivity 
{
	// JSON Response node names
	private static String KEY_SUCCESS = "success";
//	private static String KEY_ERROR = "error";
//	private static String KEY_ERROR_MSG = "error_msg";
	private static String KEY_PLAYER_ID = "player_id";
	private static String KEY_PLAYER_NAME = "player_name";
	private static String KEY_PLAYER_EMAIL = "player_email";
		
	public LoginResponseActivity(String apiResponse) 
	{
		Log.d("LoginReponseActivity: ", "in main function");
		Log.d("LoginReponseActivity: ", "in try");
			
			if (KEY_SUCCESS != null) 
			{
				Log.d("LoginReponseActivity: ", "in if success");
				
				loginErrorMsg.setText("");
				String res = KEY_SUCCESS; 
				
				if(Integer.parseInt(res) == 1)
				{
					// user successfully logged in
					// Store user details in SQLite Database
					DBHandler db = new DBHandler(getApplicationContext());
					//JSONObject json_user = apiResponse.get("user");
					
					// Clear all previous data in database
					UserFunctions userFunction = new UserFunctions();
					userFunction.logoutUser(getApplicationContext());
					//db.addUser(apiResponse.getString(KEY_PLAYER_ID), json_user.getString(KEY_PLAYER_NAME), json_user.getString(KEY_PLAYER_EMAIL));						
					
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
}
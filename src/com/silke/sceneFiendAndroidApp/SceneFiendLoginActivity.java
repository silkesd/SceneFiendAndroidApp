package com.silke.sceneFiendAndroidApp;

import org.json.JSONException;
import org.json.JSONObject;

import com.silke.sceneFiendAndroidApp.R;
import com.silke.sceneFiendAndroidApp.asynctasks.FileDownloader;
import com.silke.sceneFiendAndroidApp.asynctasks.IJsonDownloaded;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;
import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SceneFiendLoginActivity extends SceneFiendAndroidAppActivity implements IJsonDownloaded
{
	Button btnLogin;
	Button btnLinkToRegister;
	static JSONObject jObj = null;
	EditText inputUsername;
	EditText inputPassword;
	TextView loginErrorMsg;
	private SceneFiendLoginActivity context;
	
	// JSON Response node names
//	private static String KEY_SUCCESS = "success";
//	private static String KEY_ERROR = "error";
//	private static String KEY_ERROR_MSG = "error_msg";
//	private static String KEY_ID = "player_id";
//	private static String KEY_NAME = "player_name";
//	private static String KEY_EMAIL = "player_email";
//   
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        context = this;
        
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
     	
     	//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
   
     	// Login button Click Event
 		btnLogin.setOnClickListener(new View.OnClickListener() 
 		{

 			public void onClick(View view) 
 			{
 				String player_name = inputUsername.getText().toString();
 				String password = inputPassword.getText().toString();
 				Log.d("Button", player_name);
 				UserFunctions userFunction = new UserFunctions();
 				FileDownloader fd = userFunction.loginUser(context, player_name, password);
 				GAME_PREFERENCES_PLAYER_NAME = player_name;
 				String json_str = fd.getApiResponse();
 				Log.d("Testing JSON string for Login", fd.getApiResponse());
 				Log.d("LOGGED IN USER PREFERENCE", GAME_PREFERENCES_PLAYER_NAME);

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
 			
 				
 				// check for login response
// 				try {
// 					if (jObj.getString("success") != null) 
// 					{
// 						loginErrorMsg.setText("");
// 						String res = jObj.getString("success"); 
// 						if(Integer.parseInt(res) == 1)
// 						{
// 							// user successfully logged in
// 							// Store user details in SQLite Database
// 							DBHandler db = new DBHandler(getApplicationContext());
// 							JSONObject json_user = jObj.getJSONObject("user");
// 							
// 							// Clear all previous data in database
// 							UserFunctions userFunction = new UserFunctions();
// 							userFunction.logoutUser(getApplicationContext());
// 							db.addUser(jObj.getString(KEY_ID), json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL));						
// 							
// 						// Launch Menu Screen
// 						Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
// 						
// 						// Close all views before launching Menu
// 						menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
// 						startActivity(menu);
// 							
// 							// Close Login Screen
// 							finish();
// 						}
// 						else
// 						{
// 							// Error in login
// 							loginErrorMsg.setText("Incorrect username/password");
// 						}
// 					}
// 				} 
// 				catch (JSONException e) 
// 				{
// 					e.printStackTrace();
// 				}
 				
 			}
 		});

 		// Link to Register Screen
 		btnLinkToRegister.setOnClickListener(new View.OnClickListener() 
 		{
 			public void onClick(View view) 
 			{
 				Intent i = new Intent(getApplicationContext(),
 						RegisterActivity.class);
 				startActivity(i);
 				finish();
 			}
 		});
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
 				Log.d("SFLoginAct", "activity started");
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}


	public void onJsonDownloaded(JSONObject jObj) 
	{
		try 
		{
			
			GAME_PREFERENCES_PLAYER_ID = jObj.getString("player_id");
			Log.d("LOGIN ACTIVITY", "Logged in player_name: " + jObj.getString("user"));
			//GAME_PREFERENCES_PLAYER_NAME = jObj.getString("player_name");
			Log.d("LOGIN ACTIVITY", "Logged in player_id: " + GAME_PREFERENCES_PLAYER_ID);
			//Log.d("LOGIN ACTIVITY", "Logged in player_name: " + GAME_PREFERENCES_PLAYER_NAME);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 }

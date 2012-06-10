package com.silke.sceneFiendAndroidApp;

import com.silke.sceneFiendAndroidApp.R;
import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends SceneFiendAndroidAppActivity
{
	Button btnRegister;
	Button btnLinkToLogin;
	EditText inputFullName;
	EditText inputEmail;
	EditText inputPassword;
	TextView registerErrorMsg;
   
	
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
        setContentView(R.layout.register);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
       
        // Importing all assets like buttons, text fields
 		inputFullName = (EditText) findViewById(R.id.registerName);
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
				String player_name = inputFullName.getText().toString();
				String player_email = inputEmail.getText().toString();
				String password = inputPassword.getText().toString();
				UserFunctions userFunction = new UserFunctions();
				JSONObject json = userFunction.registerUser(player_name, player_email, password);
				
				// check for login response
				try {
					if (json.getString(KEY_SUCCESS) != null) {
						registerErrorMsg.setText("");
						String res = json.getString(KEY_SUCCESS); 
						if(Integer.parseInt(res) == 1){
							// user successfully registered
							// Store user details in SQLite Database
							DBHandler db = new DBHandler(getApplicationContext());
							JSONObject json_user = json.getJSONObject("user");
							
							// Clear all previous data in database
							userFunction.logoutUser(getApplicationContext());
							// Launch Menu Screen
							Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
							// Close all views before launching Menu
							menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							startActivity(menu);
							// Close Registration Screen
							finish();
						}else{
							// Error in registration
							registerErrorMsg.setText("Error occured in registration");
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});

		// Link to Login Screen
		btnLinkToLogin.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent i = new Intent(getApplicationContext(),
						SceneFiendLoginActivity.class);
				startActivity(i);
				// Close Registration View
				finish();
			}
		});
	}
    }
   
   
package com.silke.sceneFiendAndroidApp;

import com.silke.sceneFiendAndroidApp.R;
import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;

import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;

import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SceneFiendLoginActivity extends SceneFiendAndroidAppActivity
{
	Button btnLogin;
	Button btnLinkToRegister;
	EditText inputUsername;
	EditText inputPassword;
	TextView loginErrorMsg;
	
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
        setContentView(R.layout.login);
        
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
   
     	// Login button Click Event
 		btnLogin.setOnClickListener(new View.OnClickListener() 
 		{

 			public void onClick(View view) 
 			{
 				String player_name = inputUsername.getText().toString();
 				String password = inputPassword.getText().toString();
 				UserFunctions userFunction = new UserFunctions();
 				Log.d("Button", "Login is here");
 				userFunction.loginUser(player_name, password);

 				
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
 }

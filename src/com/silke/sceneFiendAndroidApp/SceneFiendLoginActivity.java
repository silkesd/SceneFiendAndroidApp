package com.silke.sceneFiendAndroidApp;
import com.silke.sceneFiendAndroidApp.R;
import com.silke.sceneFiendAndroidApp.handlers.DBHandler;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;

import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Typeface;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
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
 				String email = inputUsername.getText().toString();
 				String password = inputPassword.getText().toString();
 				UserFunctions userFunction = new UserFunctions();
 				Log.d("Button", "Login");
 				JSONObject json = userFunction.loginUser(email, password);

 				// check for login response
 				try {
 					if (json.getString(KEY_SUCCESS) != null) 
 					{
 						loginErrorMsg.setText("");
 						String res = json.getString(KEY_SUCCESS); 
 						if(Integer.parseInt(res) == 1)
 						{
 							// user successfully logged in
 							// Store user details in SQLite Database
 							DBHandler db = new DBHandler(getApplicationContext());
 							JSONObject json_user = json.getJSONObject("user");
 							
 							// Clear all previous data in database
 							userFunction.logoutUser(getApplicationContext());
 							db.addUser(json_user.getString(KEY_NAME), json_user.getString(KEY_EMAIL), json.getString(KEY_ID));						
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

package com.silke.sceneFiendAndroidApp;
import com.silke.sceneFiendAndroidApp.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwitterLoginActivity extends SceneFiendAndroidAppActivity
{
	Button btnBack;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_login);
        btnBack = (Button) findViewById(R.id.btnBack);
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        
        // Listening to register new account link
        registerScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// Switching to Register screen
				Intent i = new Intent(getApplicationContext(), TwitterRegisterActivity.class);
				startActivity(i);
			}
		});
        
     // Link to Login Menu Screen
		btnBack.setOnClickListener(new View.OnClickListener() 
 		{
 			public void onClick(View view) 
 			{
 				Intent i = new Intent(getApplicationContext(),
 						LoginActivity.class);
 				startActivity(i);
 				finish();
 			}
 		});
    }
}

package com.silke.sceneFiendAndroidApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TwitterRegisterActivity extends Activity {
	Button btnBack;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.twitter_register);
        btnBack = (Button) findViewById(R.id.btnBack);
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// Switching to Login Screen/closing register screen
				finish();
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
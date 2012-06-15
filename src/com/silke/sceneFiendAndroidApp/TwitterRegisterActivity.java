package com.silke.sceneFiendAndroidApp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class TwitterRegisterActivity extends Activity 
{
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.twitter_register);
        
        TextView loginScreen = (TextView) findViewById(R.id.link_to_login);
        
    	//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
        
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
        	
			public void onClick(View arg0) 
			{
				// Switching to Login Screen/closing register screen
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
 						TwitterLoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				Log.d("TwitterRegAct", "activity started");
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}
}
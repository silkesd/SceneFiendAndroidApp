package com.silke.sceneFiendAndroidApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class SettingsActivity extends SceneFiendAndroidAppActivity 
{
	ImageButton btnBack;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        // Link to Score Menu Screen
       	btnBack.setOnClickListener(new View.OnClickListener() 
       	{
       		public void onClick(View view) 
       		{
       			Intent i = new Intent(getApplicationContext(),
       					ScoreMenuActivity.class);
       			startActivity(i);
       			finish();
       		}
       	});
    }
}
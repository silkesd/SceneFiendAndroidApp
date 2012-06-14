package com.silke.sceneFiendAndroidApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GameActivity extends SceneFiendAndroidAppActivity 
{
	Button btnBack;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        btnBack = (Button) findViewById(R.id.btnBack);
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
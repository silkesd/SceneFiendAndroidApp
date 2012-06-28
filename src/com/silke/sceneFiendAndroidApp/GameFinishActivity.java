package com.silke.sceneFiendAndroidApp;

import java.util.Calendar;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.silke.sceneFiendAndroidApp.handlers.DBHandler;

public class GameFinishActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener
{
	
	TextView textviewScore;
	TextView finishName;
	Button btnLinkToTwitterLogin;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        
        //the custom title font
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
       
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		textviewScore = (TextView) findViewById(R.id.textviewScore);
		finishName = (TextView) findViewById(R.id.finishName);
		
		callAndSendScoreInfo();
		
		
		if(GAME_PREFERENCES_WIFI == true || GAME_PREFERENCES_MOBILE == true)
		{
			btnLinkToTwitterLogin = (Buttons) findViewById(R.id.btnLinkToTwitterLogin);
			
		    // Link to Twitter Login
			btnLinkToTwitterLogin.setOnClickListener(new View.OnClickListener() 
			{
				public void onClick(View view) 
				{
					Intent i = new Intent(getApplicationContext(),
							TwitterLoginActivity.class);
					startActivity(i);
					finish();
				}
			});
		}
    }
   
    public void callAndSendScoreInfo()
    {
    	textviewScore.setText(GAME_PREFERENCES_PLAYER_SCORE.toString());
    	finishName.setText(GAME_PREFERENCES_PLAYER_NAME);
    	Log.d("DISPLAY USER NAME HERE TOO", GAME_PREFERENCES_PLAYER_NAME);
    	
    	// user successfully logged in
		// Store user score details in SQLite Database
		DBHandler db = new DBHandler(getApplicationContext());
		
		long time = (Calendar.getInstance().getTimeInMillis() / 1000L);
		
		db.addScore(GAME_PREFERENCES_PLAYER_ID, GAME_PREFERENCES_PLAYER_SCORE, time);						
    }
    
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				GAME_PREFERENCES_PLAYER_SCORE = 0;
				GAME_PREFERENCES_CURRENT_QUESTION = 1;
				Intent i = new Intent(getApplicationContext(),
 						MenuActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}

	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
	}

}
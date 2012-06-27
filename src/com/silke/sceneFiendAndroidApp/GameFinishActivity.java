package com.silke.sceneFiendAndroidApp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class GameFinishActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener
{
	
	TextView textviewScore;
	TextView finishName;
	
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
		
		callScoreInfo();
    }
   
    public void callScoreInfo()
    {
    	textviewScore.setText(GAME_PREFERENCES_PLAYER_SCORE.toString());
    	finishName.setText(GAME_PREFERENCES_PLAYER_NAME);
    	Log.d("DISPLAY USER NAME HERE TOO", GAME_PREFERENCES_PLAYER_NAME);
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
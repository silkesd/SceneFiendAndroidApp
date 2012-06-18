package com.silke.sceneFiendAndroidApp;

import java.util.HashMap;

import com.silke.sceneFiendAndroidApp.handlers.DBHandler;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener
{
	SharedPreferences mGameSettings;
	TextView textviewQu;
	Button buttonviewAns1;
	Button buttonviewAns2;
	Button buttonviewAns3;
	Button buttonviewAns4;
//	Button[] answers;
//	Button[] questions;
	Button next;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
       
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		DBHandler db = new DBHandler(this);
		
//		// Retrieve the shared preferences
//        mGameSettings = getSharedPreferences(GAME_PREFERENCES, Context.MODE_PRIVATE);
//
//        // Get our progress through the questions
//        int startingQuestionNumber = mGameSettings.getInt(GAME_PREFERENCES_CURRENT_QUESTION, 0);
//
//        // If we're at the beginning of the quiz, initialize the Shared preferences
//        if (startingQuestionNumber == 0) 
//        {
//            startingQuestionNumber = 1;
//            android.content.SharedPreferences.Editor editor = mGameSettings.edit();
//            editor.putInt(GAME_PREFERENCES_CURRENT_QUESTION, startingQuestionNumber);
//            editor.commit();
//        }
        
//       Log.d("GameActivity: " + startingQuestionNumber, "starting qu number");
        
        textviewQu = (TextView) findViewById(R.id.textviewQu);
			HashMap<String,String> gameList = db.getQuestionAnswers();
				String question_id = gameList.get("question_id");
				String question = gameList.get("question_text");
				
				textviewQu.setText(question);
		
				
				
				
		HashMap<String,String> gameAns1List = db.getFourAnswers(Integer.parseInt(question_id));	
		Log.d("shit", gameAns1List.toString());
		
		buttonviewAns1 = (Button) findViewById(R.id.buttonviewAns1);	
				String answers1 = gameAns1List.get("answer_text1");
				buttonviewAns1.setText(answers1);
				Log.d("Game", answers1);
				
		buttonviewAns2 = (Button) findViewById(R.id.buttonviewAns2);
				
				//DO HIDDEN STRINGS FOR THE ID HERE :)
				String answers2 = gameAns1List.get("answer_text2");
				buttonviewAns2.setText(answers2);	
				Log.d("Game", answers2);
					
		buttonviewAns3 = (Button) findViewById(R.id.buttonviewAns3);
			
				String answers3 = gameAns1List.get("answer_text3");
				buttonviewAns3.setText(answers3);
				Log.d("Game", answers3);
				
		buttonviewAns4 = (Button) findViewById(R.id.buttonviewAns4);
			
				String answers4 = gameAns1List.get("answer_text4");
				buttonviewAns4.setText(answers4);
		
//		Log.d("Game", answers1.toString());
//		Log.d("Game", answers2.toString());
//		Log.d("Game", answers3.toString());		
//		Log.d("Game", answers4.toString());
		
		//next = (Button) findViewById(R.id.next);
		
		//next.setOnClickListener((OnClickListener) this);
		buttonviewAns4.setOnClickListener((OnClickListener) this);
		buttonviewAns3.setOnClickListener((OnClickListener) this);
		buttonviewAns2.setOnClickListener((OnClickListener) this);
		buttonviewAns1.setOnClickListener((OnClickListener) this);
    }
    
    public void onClick(View view)
    {
    	if(buttonviewAns4 != null)
    	{
    		//find correct answer here on click - get correct answer id
    		// EXTEND BUTTON CLASS TO ACTIVITY - can get id this way :)
    		//make button mybutton to read id
    		Log.d("Game", buttonviewAns4.toString());
    	}
    	else if(buttonviewAns3 != null)
    	{
    		Log.d("Game", buttonviewAns3.toString());
    	}
    	else if(buttonviewAns2 != null)
    	{
    		Log.d("Game", buttonviewAns2.toString());
    	}
    	else if(buttonviewAns1 != null)
    	{
    		Log.d("Game", buttonviewAns1.toString());
    	}
    	Log.d("game", "clicked");
    	//currentQuestion++;
    }
    
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent i = new Intent(getApplicationContext(),
 						MenuActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				Log.d("GameAct", "activity started");
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}
	
	
}
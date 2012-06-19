package com.silke.sceneFiendAndroidApp;

import java.util.HashMap;

import com.silke.sceneFiendAndroidApp.handlers.DBQuizHandler;

import android.app.ActionBar;
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
	Button next;
	//MyButtons myButtons = new MyButtons();
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
       
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		DBQuizHandler db = new DBQuizHandler(this);
		
        textviewQu = (TextView) findViewById(R.id.textviewQu);
			HashMap<String,String> gameList = db.getQuestionAnswers();
				String question_id = gameList.get("question_id");
				String question = gameList.get("question_text");
				
				textviewQu.setText(question);
			
		HashMap<String,String> gameAns1List = db.getFourAnswers(Integer.parseInt(question_id));	
		Log.d("shit", gameAns1List.toString());
		
		Buttons buttonviewAns1 = (Buttons) findViewById(R.id.buttonviewAns1);	
				String answers1 = gameAns1List.get("answer_text1");
				//String answers1id = gameAns1List.get("answer_id");
				buttonviewAns1.setText(answers1);
				Log.d("Game", answers1);
				
		Buttons buttonviewAns2 = (Buttons) findViewById(R.id.buttonviewAns2);
				
				//DO HIDDEN STRINGS FOR THE ID HERE :)
				String answers2 = gameAns1List.get("answer_text2");
				buttonviewAns2.setText(answers2);	
				Log.d("Game", answers2);
					
		Buttons buttonviewAns3 = (Buttons) findViewById(R.id.buttonviewAns3);
			
				String answers3 = gameAns1List.get("answer_text3");
				buttonviewAns3.setText(answers3);
				Log.d("Game", answers3);
				
		Buttons buttonviewAns4 = (Buttons) findViewById(R.id.buttonviewAns4);
			
				String answers4 = gameAns1List.get("answer_text4");
				buttonviewAns4.setText(answers4);

		next = (Button) findViewById(R.id.next);
		
		buttonviewAns4.setOnClickListener((OnClickListener) this);
		buttonviewAns3.setOnClickListener((OnClickListener) this);
		buttonviewAns2.setOnClickListener((OnClickListener) this);
		buttonviewAns1.setOnClickListener((OnClickListener) this);
		next.setOnClickListener((OnClickListener) this);
    }
    
    public void onClick(View view)
    {
    	if(buttonviewAns4 != null)
    	{
    		//find correct answer here on click - get correct answer id
    		// EXTEND BUTTON CLASS TO ACTIVITY - can get id this way :)
    		//make button mybutton to read id
    		Log.d("button4", buttonviewAns4.toString());
    	}
    	else if(buttonviewAns3 != null)
    	{
    		Log.d("button3", buttonviewAns3.toString());
    	}
    	else if(buttonviewAns2 != null)
    	{
    		Log.d("button2", buttonviewAns2.toString());
    	}
    	else if(buttonviewAns1 != null)
    	{
    		Log.d("button1", buttonviewAns1.toString());
    	}
    	else if(next != null)
    	{
    		Log.d("Next", next.toString());
    		DBQuizHandler db = new DBQuizHandler(this);
    		//HashMap<String,String> gameList = db.getFourAnswers(Integer.parseInt(question_id++));
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
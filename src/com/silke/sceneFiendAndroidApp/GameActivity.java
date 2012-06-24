package com.silke.sceneFiendAndroidApp;

import java.util.HashMap;

import com.silke.sceneFiendAndroidApp.handlers.DBQuizHandler;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener
{
	//
	TextView textviewQu;
	Button buttonviewAns1;
	Button buttonviewAns2;
	Button buttonviewAns3;
	Button buttonviewAns4;
	Button next;
	HashMap<String,String>  gameAns1List;
	HashMap<String,String>  gameCorrectAns1List;
	HashMap<String,String>  gameCorrectAns2List;
	HashMap<String,String>  gameCorrectAns3List;
	HashMap<String,String>  gameCorrectAns4List;

	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(final Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
       
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		
        textviewQu = (TextView) findViewById(R.id.textviewQu);
		
        callQuestionInfo();
    }
    
    public void callQuestionInfo()
    {
    	final DBQuizHandler db = new DBQuizHandler(this);
    	
    	Log.d("GAME ACTIVITY", "question_id BEFORE CALL TO DB: " + GAME_PREFERENCES_CURRENT_QUESTION);	
    	
        final HashMap<String,String> gameList = db.getNextQuestion(GAME_PREFERENCES_CURRENT_QUESTION);
				String question_id = gameList.get("question_id");
				String question = gameList.get("question_text");
				textviewQu.setText(question);
		
		GAME_PREFERENCES_CURRENT_QUESTION = Integer.parseInt(gameList.get("question_id"));		
		
		Log.d("GAME ACTIVITY", "question_id AFTER CALL TO DB: " + GAME_PREFERENCES_CURRENT_QUESTION);		
		
		HashMap<String,String> gameAns1List = db.getFourAnswers(Integer.parseInt(question_id));	
		
		buttonviewAns1 = (Buttons) findViewById(R.id.buttonviewAns1);	
				String answers1 = gameAns1List.get("answer_text1");	
				buttonviewAns1.setText(answers1);
				buttonviewAns1.setId(Integer.parseInt(gameAns1List.get("answer_id1")));
						
		buttonviewAns2 = (Buttons) findViewById(R.id.buttonviewAns2);
				String answers2 = gameAns1List.get("answer_text2");
				buttonviewAns2.setText(answers2);	
				buttonviewAns2.setId(Integer.parseInt(gameAns1List.get("answer_id2")));
				
		buttonviewAns3 = (Buttons) findViewById(R.id.buttonviewAns3);
				String answers3 = gameAns1List.get("answer_text3");
				buttonviewAns3.setText(answers3);
				buttonviewAns3.setId(Integer.parseInt(gameAns1List.get("answer_id3")));
				
		buttonviewAns4 = (Buttons) findViewById(R.id.buttonviewAns4);
				String answers4 = gameAns1List.get("answer_text4");	
				buttonviewAns4.setText(answers4);
				buttonviewAns4.setId(Integer.parseInt(gameAns1List.get("answer_id4")));
				
		next = (Button) findViewById(R.id.next);
		
		//Getting the id attached to each button
		buttonviewAns1.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns1.getId());
				
				HashMap<String,String> gameCorrectAns1List = db.getCorrectAnswer(buttonviewAns1.getId());
				String correct_answers1 = gameCorrectAns1List.get("correct_answer");
	
				//if the answer attached to this button is correct (1) log correct, else log incorrect
				if(Integer.parseInt(gameCorrectAns1List.get("correct_answer"))==1)
				{
					Log.d("Game 1 IS THE CORRECT ANSWER", correct_answers1);
				}
				else
				{
					Log.d("Game 1 IS THE WRONG ANSWER", correct_answers1);
					//go to next qu / show negative response
				}
			}			
		});
		buttonviewAns2.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns2.getId());
				
				/*setting a hashmap that contains the returned values from getCorrectAnswer and
				* and passing the id returned from the button
				*/
				HashMap<String,String> gameCorrectAns2List = db.getCorrectAnswer(buttonviewAns2.getId());
				String correct_answers2 = gameCorrectAns2List.get("correct_answer");
				
				//if the answer attached to this button is correct (1) log correct, else log incorrect
				if(Integer.parseInt(gameCorrectAns2List.get("correct_answer"))==1)
				{
					Log.d("Game 2 IS THE CORRECT ANSWER", correct_answers2);
				}
				else
				{
					Log.d("Game 2 IS THE WRONG ANSWER", correct_answers2);
					//go to next qu / show negative response
				}
			}
		});
		buttonviewAns3.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns3.getId());
				
				HashMap<String,String> gameCorrectAns3List = db.getCorrectAnswer(buttonviewAns3.getId());
				String correct_answers3 = gameCorrectAns3List.get("correct_answer");
	
				//if the answer attached to this button is correct (1) log correct, else log incorrect
				if(Integer.parseInt(gameCorrectAns3List.get("correct_answer"))==1)
				{
					Log.d("Game 3 IS THE CORRECT ANSWER", correct_answers3);
				}
				else
				{
					Log.d("Game 3 IS THE WRONG ANSWER", correct_answers3);
					//go to next qu / show negative response
				}
			}			
		});
		buttonviewAns4.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns4.getId());
				
				HashMap<String,String> gameCorrectAns4List = db.getCorrectAnswer(buttonviewAns4.getId());
				String correct_answers4 = gameCorrectAns4List.get("correct_answer");
				
				//if the answer attached to this button is correct (1) log correct, else log incorrect
				if(Integer.parseInt(gameCorrectAns4List.get("correct_answer"))==1)
				{
					Log.d("Game 4 IS THE CORRECT ANSWER", correct_answers4);
				}
				else
				{
					//go to next qu / show negative response
				}
			}			
		});
		
		next.setOnClickListener( new OnClickListener()
		{
			public void onClick(View v) 
			{				
				//increment shared preference instead and call here and pass to sqlite
				GAME_PREFERENCES_CURRENT_QUESTION++;
				
				Log.d("GAME ACTIVITY", "NEXT BUTTON CLICKED: " + GAME_PREFERENCES_CURRENT_QUESTION);			
				
				//call activity again
				Intent i = new Intent(getApplicationContext(),
 						GameActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			
				startActivity(i);
				finish();
 				Log.d("GameAct", "activity started again");
			}
			
		});
	
//		//if next is clicked - qu_id++
//		final Timer task = new Timer();
//		final long seconds = 5;
//		timer.schedule(task,seconds*1000L);
//		clicker.setOnClickListener(new View.OnClickListener() 
//		{
//		   public void onClick(View v) 
//		   {                                                                   
//		      counter++;
//		      task.cancel();
//		      task = new Timer();
//		      timer.schedule(task,seconds*1000L);
//		   }
//		});		
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

	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
	}
}
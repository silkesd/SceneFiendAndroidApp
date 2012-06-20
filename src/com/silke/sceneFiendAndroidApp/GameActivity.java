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
	HashMap<String,String>  gameAns1List;
	HashMap<String,String>  gameCorrectAns1List;
	HashMap<String,String>  gameCorrectAns2List;
	HashMap<String,String>  gameCorrectAns3List;
	HashMap<String,String>  gameCorrectAns4List;
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
		
		final DBQuizHandler db = new DBQuizHandler(this);
		
        textviewQu = (TextView) findViewById(R.id.textviewQu);
			HashMap<String,String> gameList = db.getQuestionAnswers();
				String question_id = gameList.get("question_id");
				String question = gameList.get("question_text");
				textviewQu.setText(question);
		
		HashMap<String,String> gameCorrectAnsList = db.getCorrectAnswers();		
				String answer_id = gameCorrectAnsList.get("answer_id");	
				String correct_answer = gameCorrectAnsList.get("correct_answer");	
		Log.d("GAME CORRECT: ", gameCorrectAnsList.toString());
				
				
		HashMap<String,String> gameAns1List = db.getFourAnswers(Integer.parseInt(question_id));	
		
		
		Log.d("Game Answer List", gameAns1List.toString());
		
		buttonviewAns1 = (Buttons) findViewById(R.id.buttonviewAns1);	
				String answers1 = gameAns1List.get("answer_text1");	
				buttonviewAns1.setText(answers1);
				buttonviewAns1.setId(Integer.parseInt(gameAns1List.get("answer_id1")));
				
				Log.d("GAME TEST ANSWERID1:", gameAns1List.get("answer_id1"));
				
						
		buttonviewAns2 = (Buttons) findViewById(R.id.buttonviewAns2);
				String answers2 = gameAns1List.get("answer_text2");
				buttonviewAns2.setText(answers2);	
				buttonviewAns2.setId(Integer.parseInt(gameAns1List.get("answer_id2")));
				
				Log.d("GAME TEST ANSWERID2:", gameAns1List.get("answer_id2"));
				
//				HashMap<String,String> gameCorrectAns2List = db.getCorrectAnswer(Integer.parseInt(gameAns1List.get("answer_id2")));
//				String correct_answer2 = gameCorrectAns2List.get("correct_answer");
//				
//				Log.d("Game 2", answers2);
//				Log.d("Game 2 CORRECT", correct_answer2);
				
		buttonviewAns3 = (Buttons) findViewById(R.id.buttonviewAns3);
				String answers3 = gameAns1List.get("answer_text3");
				buttonviewAns3.setText(answers3);
				buttonviewAns3.setId(Integer.parseInt(gameAns1List.get("answer_id3")));
//				
//				HashMap<String,String> gameCorrectAns3List = db.getCorrectAnswer(Integer.parseInt(gameAns1List.get("answer_id3")));
//				String correct_answer3 = gameCorrectAns3List.get("correct_answer");
//				
//				Log.d("Game 3", answers3);
//				Log.d("Game 3 CORRECT", correct_answer3);
				
		buttonviewAns4 = (Buttons) findViewById(R.id.buttonviewAns4);
				String answers4 = gameAns1List.get("answer_text4");	
				buttonviewAns4.setText(answers4);
				buttonviewAns4.setId(Integer.parseInt(gameAns1List.get("answer_id4")));
//				
//				HashMap<String,String> gameCorrectAns4List = db.getCorrectAnswer(Integer.parseInt(gameAns1List.get("answer_id4")));
//				String correct_answer4 = gameCorrectAns4List.get("correct_answer");
//				
//				Log.d("Game 4", answers4);
//				Log.d("Game 4 CORRECT", correct_answer4);
				
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
	
				Log.d("Game 1 CORRECT", correct_answers1);
			}
			
		});
		buttonviewAns2.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns2.getId());
				
				HashMap<String,String> gameCorrectAns2List = db.getCorrectAnswer(buttonviewAns2.getId());
				String correct_answers2 = gameCorrectAns2List.get("correct_answer");
	
				Log.d("Game 2 CORRECT", correct_answers2);
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
	
				Log.d("Game 3 CORRECT", correct_answers3);
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
				if(Integer.parseInt(correct_answers4)==0)
				{
					Log.d("Game 4 CORRECT", correct_answers4);
				}
			}
			
		});
		
		next.setOnClickListener((OnClickListener) this);
		
		//if next is clicked - qu_id++
		
    }
    
    
//    if(correct_answer == 1)
//    {
//    	score++;
//    }
//    else
//    {
//    	//do nothing
//    }


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

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	
}
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
		Log.d("Game Answer List", gameAns1List.toString());
		
		buttonviewAns1 = (Buttons) findViewById(R.id.buttonviewAns1);	
				String answers1 = gameAns1List.get("answer_text1");	
				buttonviewAns1.setText(answers1);
				buttonviewAns1.setId(Integer.parseInt(gameAns1List.get("answer_id1")));
//				Log.d("Game 1", answers1);
//				Log.d("Game 1id", gameAns1List.get("answer_id1"));
				
		buttonviewAns2 = (Buttons) findViewById(R.id.buttonviewAns2);
				String answers2 = gameAns1List.get("answer_text2");
				buttonviewAns2.setText(answers2);	
				buttonviewAns2.setId(Integer.parseInt(gameAns1List.get("answer_id2")));
//				Log.d("Game 2id", gameAns1List.get("answer_id2"));
//				Log.d("Game 2", answers2);
					
		buttonviewAns3 = (Buttons) findViewById(R.id.buttonviewAns3);
				String answers3 = gameAns1List.get("answer_text3");
				buttonviewAns3.setText(answers3);
				buttonviewAns3.setId(Integer.parseInt(gameAns1List.get("answer_id3")));
//				Log.d("Game 3", answers3);
//				Log.d("Game 3", gameAns1List.get("answer_id3"));
				
		buttonviewAns4 = (Buttons) findViewById(R.id.buttonviewAns4);
				String answers4 = gameAns1List.get("answer_text4");	
				buttonviewAns4.setText(answers4);
				buttonviewAns4.setId(Integer.parseInt(gameAns1List.get("answer_id4")));
//				//Log.d("Game 4id", gameAns1List.get("answer_id4"));
				//Log.d("Game 4", gameAns1List.get("answer_id4"));

		next = (Button) findViewById(R.id.next);
		
		//Getting the id attached to each button
		buttonviewAns1.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns1.getId());
			}
			
		});
		buttonviewAns2.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns2.getId());
			}
			
		});
		buttonviewAns3.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns3.getId());
			}
			
		});
		buttonviewAns4.setOnClickListener(new OnClickListener()
		{

			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				System.out.println(buttonviewAns4.getId());
			}
			
		});
		
		next.setOnClickListener((OnClickListener) this);
    }
    
//    public void onClick(View view)
//    {
//	    switch(view.getId())
//	    {
//	    	case R.id.buttonviewAns1:    		
//	    		Log.d("IN BUTTON VIEW CLICK ANS 1: ", buttonviewAns1.toString());
//	    		break;
//	    		
//	    	case R.id.buttonviewAns2:
//	    		Log.d("IN BUTTON VIEW CLICK ANS 1: ", buttonviewAns2.toString());
//	    		break;
//	    		
//	    	case R.id.buttonviewAns3:
//	    		Log.d("IN BUTTON VIEW CLICK ANS 1: ", buttonviewAns3.toString());
//	    		break;
//	    		
//	    	case R.id.buttonviewAns4:
//	    		Log.d("IN BUTTON VIEW CLICK ANS 1: ", buttonviewAns4.toString());
//	    		break;
//	    }	
//    	if(this.buttonviewAns4 != null)
//    	{
//    		//find correct answer here on click - get correct answer id
//    		// EXTEND BUTTON CLASS TO ACTIVITY - can get id this way :)
//    		//make button mybutton to read id
//    		Log.d("Game 4id before try", gameAns1List.get("answer_id4"));
//    		try
//    		{
//    			
//				attach_id4((gameAns1List).toString());
//    		}
//    		finally
//    		{
//    			
//    		}
//    		
//    	}
//    	else if(buttonviewAns3 != null)
//    	{
//    		Log.d("button3", buttonviewAns3.toString());
//    	}
//    	else if(buttonviewAns2 != null)
//    	{
//    		Log.d("button2", buttonviewAns2.toString());
//    	}
//    	else if(buttonviewAns1 != null)
//    	{
//    		Log.d("button1", buttonviewAns1.toString());
//    	}
//    	else if(next != null)
//    	{
//    		Log.d("Next", next.toString());
//    		DBQuizHandler db = new DBQuizHandler(this);
//    		//HashMap<String,String> gameList = db.getFourAnswers(Integer.parseInt(question_id++));
//    	}
    	
    	//currentQuestion++;
    //}

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
package com.silke.sceneFiendAndroidApp;

import java.util.HashMap;

import com.silke.sceneFiendAndroidApp.handlers.DBHandler;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity 
{
	TextView textviewQu;
	Button buttonviewAns1;
	Button buttonviewAns2;
	Button buttonviewAns3;
	Button buttonviewAns4;
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
			
		textviewQu = (TextView) findViewById(R.id.textviewQu);
			HashMap<String,String> gameList = db.getQuestionAnswers();
				String question = gameList.get("question_text");
				textviewQu.setText(question);
			
		buttonviewAns1 = (Button) findViewById(R.id.buttonviewAns1);
			HashMap<String,String> gameAns1List = db.getQuestionAnswers();
				String answers1 = gameAns1List.get("answer_text");
				buttonviewAns1.setText(answers1);
		
		buttonviewAns2 = (Button) findViewById(R.id.buttonviewAns2);
			HashMap<String,String> gameAnsList2 = db.getQuestionAnswers();
				String answers2 = gameAnsList2.get("answer_text");
				buttonviewAns2.setText(answers2);	
		
		buttonviewAns3 = (Button) findViewById(R.id.buttonviewAns3);
			HashMap<String,String> gameAnsList3 = db.getQuestionAnswers();
				String answers3 = gameAnsList3.get("answer_text");
				buttonviewAns3.setText(answers3);
				
		buttonviewAns4 = (Button) findViewById(R.id.buttonviewAns4);
			HashMap<String,String> gameAnsList4 = db.getQuestionAnswers();
					String answers4 = gameAnsList4.get("answer_text");
					buttonviewAns4.setText(answers4);
					
		next = (Button) findViewById(R.id.next);
			
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
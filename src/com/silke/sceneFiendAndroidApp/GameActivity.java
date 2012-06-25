package com.silke.sceneFiendAndroidApp;

import java.util.HashMap;

import com.silke.sceneFiendAndroidApp.handlers.DBQuizHandler;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends SceneFiendAndroidAppActivity implements View.OnClickListener
{
	private ProgressDialog pDialog;
	TextView textviewQu;
	TextView textviewScore;
	TextView tv;
	
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
        textviewScore = (TextView) findViewById(R.id.textviewScore);
		
        textviewScore.setText("Your score is: " + GAME_PREFERENCES_PLAYER_SCORE.toString());
        
        callQuestionInfo();
        
        //set a text view to show response from count down timer
        tv = (TextView) findViewById(R.id.tv);
  		
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
		
		//initialise a counter
  		MyCount counter = new MyCount(8000,1000);
  		counter.start();
		
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
					GAME_PREFERENCES_PLAYER_SCORE++;
					Log.d("Game 1 IS THE CORRECT ANSWER", correct_answers1);
					Log.d("RIGHT", GAME_PREFERENCES_PLAYER_SCORE.toString());
					right();
				}
				else
				{	
					GAME_PREFERENCES_PLAYER_SCORE--;
					Log.d("Game 1 IS THE WRONG ANSWER", correct_answers1);
					Log.d("WRONG", GAME_PREFERENCES_PLAYER_SCORE.toString());
					//go to next qu / show negative response
					wrong();
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
					
					GAME_PREFERENCES_PLAYER_SCORE++;
					Log.d("Game 2 IS THE CORRECT ANSWER", correct_answers2);
					Log.d("RIGHT", GAME_PREFERENCES_PLAYER_SCORE.toString());
					right();
				}
				else
				{
					
					GAME_PREFERENCES_PLAYER_SCORE--;
					Log.d("Game 2 IS THE WRONG ANSWER", correct_answers2);
					Log.d("WRONG", GAME_PREFERENCES_PLAYER_SCORE.toString());
					//go to next qu / show negative response
					wrong();
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
					GAME_PREFERENCES_PLAYER_SCORE++;
					Log.d("Game 3 IS THE CORRECT ANSWER", correct_answers3);
					Log.d("RIGHT", GAME_PREFERENCES_PLAYER_SCORE.toString());
					right();
				}
				else
				{
					
					GAME_PREFERENCES_PLAYER_SCORE--;
					Log.d("Game 3 IS THE WRONG ANSWER", correct_answers3);
					Log.d("WRONG", GAME_PREFERENCES_PLAYER_SCORE.toString());
					//go to next qu / show negative response
					wrong();
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
					GAME_PREFERENCES_PLAYER_SCORE++;
					Log.d("Game 4 IS THE CORRECT ANSWER", correct_answers4);
					Log.d("RIGHT", GAME_PREFERENCES_PLAYER_SCORE.toString());
					right();
				}
				else
				{
					GAME_PREFERENCES_PLAYER_SCORE--;
					Log.d("Game 4 IS THE WRONG ANSWER", correct_answers4);
					Log.d("WRONG", GAME_PREFERENCES_PLAYER_SCORE.toString());
					//go to next qu / show negative response
					wrong();
				}
				
			}			
		});
		next.setOnClickListener( new OnClickListener()
		{
			public void onClick(View v) 
			{	
				moveOn();
			}
			
		});	
    }
    
	public class MyCount extends CountDownTimer
	{

		//the timer countdown info
		public MyCount(long millisInFuture, long countDownInterval) 
		{
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onFinish() 
		{
			tv.setText("Time\'s Up!");
			moveOn();
		}

		@Override
		public void onTick(long millisUntilFinished) 
		{
			tv.setText("Time Left: " + millisUntilFinished/1000);
		}
		
	}  
    
	private void right() 
	{
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
		                               (ViewGroup) findViewById(R.id.toast_layout_root));

		ImageView image = (ImageView) layout.findViewById(R.id.icon);
		image.setImageResource(R.drawable.happy);
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("That's right!");

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 147);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
		
		//Toast.makeText(GameActivity.this, "That's right :)", Toast.LENGTH_SHORT).show();
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() 
		{
		  public void run() 
		  {
			  moveOn();
		  }
		}, 100);
		
			
	}
	
	private void wrong() 
	{
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toast_layout,
		                               (ViewGroup) findViewById(R.id.toast_layout_root));

		ImageView image = (ImageView) layout.findViewById(R.id.icon);
		image.setImageResource(R.drawable.sad);
		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setText("Nope, that's not right!");

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.CENTER_VERTICAL, 0, 147);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
		
		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() 
		{
		  public void run() 
		  {
			  moveOn();
		  }
		}, 100);
	}
    
    public void moveOn()
    {
    	//increment shared preference instead and call here and pass to sqlite
		GAME_PREFERENCES_CURRENT_QUESTION++;
		
		Log.d("GAME ACTIVITY", "MOVE TO NEXT QU: " + GAME_PREFERENCES_CURRENT_QUESTION);			
		
		
		if(GAME_PREFERENCES_CURRENT_QUESTION < 15)
		{
			new LoadNextQuestion().execute();
		}
		else if (GAME_PREFERENCES_CURRENT_QUESTION == 15)
	    {
			Log.d("GAME ACTIVITY", "About to load end screen intent ");			
			new LoadEndQuiz().execute();
	    }
    }
    
    
    class LoadNextQuestion extends AsyncTask<String, String, String>
    {
    	/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(GameActivity.this);
			pDialog.setMessage("Loading next question. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... arg0) 
		{
			return null;
		}
    	
		protected void onPostExecute(String file_url) 
		{
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			//call activity again
			Intent i = new Intent(getApplicationContext(),
						GameActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
			startActivity(i);
			finish();
				Log.d("GameAct", "activity started again");
			
				
		}
    }
    
    class LoadEndQuiz extends AsyncTask<String, String, String>
    {
    	/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(GameActivity.this);
			pDialog.setMessage("Loading Score Screen. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		@Override
		protected String doInBackground(String... arg0) 
		{
			return null;
		}
    	
		protected void onPostExecute(String file_url) 
		{
			Log.d("GameEndAct", "Game Over activity about to be called");
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			//call activity again
			Intent i = new Intent(GameActivity.this,
						GameFinishActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		
			startActivity(i);
			finish();
					
		}
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
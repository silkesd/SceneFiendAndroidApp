package com.silke.sceneFiendAndroidApp.handlers;

import java.util.HashMap;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBQuizHandler extends SQLiteOpenHelper
{
	SharedPreferences mGameSettings;
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	
	// Database Name
	private static final String DATABASE_NAME = "scenefiendlocaldb";

	// Table names
	private static final String TABLE_QUESTIONS = "scene__questions";
	private static final String TABLE_QUIZ = "scene_quiz";
	private static final String TABLE_ANSWERS = "scene__answers";
	private static final String TABLE_QUESTION_HAS_IMAGE = "question_has_image";
	private static final String TABLE_QUESTION_HAS_CLIP = "question_has_clip";

	//Scene Questions Table
	private static final String KEY_QUESTION_ID = "question_id";
	private static final String KEY_QUESTION_TEXT = "question_text";
	
	//Scene Quiz Table
	private static final String KEY_CORRECT_ANSWER = "correct_answer";
	
	//Scene Answers Table
	private static final String KEY_ANSWER_ID = "answer_id";
	private static final String KEY_ANSWER_TEXT = "answer_text";
	
	//question had clip table
	private static final String KEY_QUESTION_HAS_CLIP_ID = "id";
	private static final String KEY_PRE_CLIP = "pre_clip";
	private static final String KEY_POST_CLIP = "post_clip";
	
	
	public DBQuizHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		
		//Scene Questions Table
		String CREATE_QUESTIONS_TABLE = "CREATE TABLE " + TABLE_QUESTIONS + "("
				+ KEY_QUESTION_ID + " CHAR(10) NOT NULL PRIMARY KEY," 
				+ KEY_QUESTION_TEXT + " VARCHAR(255)" + ")";
		db.execSQL(CREATE_QUESTIONS_TABLE);
		
		final String InsertQuestionsTable="INSERT INTO " + TABLE_QUESTIONS + " SELECT " + "'1'" + " AS " + KEY_QUESTION_ID  
				+ ", " + "'What does Happy say next?'" + " AS " + KEY_QUESTION_TEXT 
				+ " UNION SELECT " + "'2'" + ", " + "'Bruce Willis plays which character in The fifth Element?'"
				+ " UNION SELECT " + "'3'" + ", " + "'What does Derek from Spinal Tap have hidden in his trousers?'"
				+ " UNION SELECT " + "'4'" + ", " + "'Who plays Margaret Thatcher in The Iron Lady?'"
				+ " UNION SELECT " + "'5'" + ", " + "'Who plays Lionel Logue, the speech therapist, in The Kings Speech?'"
				+ " UNION SELECT " + "'6'" + ", " + "'What is the name of the rebel base ice planet in The Empire Strikes Back?'"
				+ " UNION SELECT " + "'7'" + ", " + "'Who is Grus arch nemesis in Despicable Me?'"
				+ " UNION SELECT " + "'8'" + ", " + "'Which brothers created the Matrix movies?'"
				+ " UNION SELECT " + "'9'" + ", " + "'Where did Thomas Schell, played by Tom Hanks in Extremely Loud and Incredibly Close, die?'"
				+ " UNION SELECT " + "'10'" + ", " + "'Which of these superheroes did not appear in The Avengers?'"
				+ " UNION SELECT " + "'11'" + ", " + "'What does Gru do next?'"
				+ " UNION SELECT " + "'12'" + ", " + "'How is Kopi Luwak, the coffee Edward loves in The Bucket List, made?'"
				+ " UNION SELECT " + "'13'" + ", " + "'What is the fifth element?'"
				+ " UNION SELECT " + "'14'" + ", " + "'Which planet do Wookies come from?'"
				+ " UNION SELECT " + "'15'" + ", " + "'What does Gru try to steal in Despicable Me?'";		
		db.execSQL(InsertQuestionsTable);
		
		//+ "FOREIGN KEY" + "(" + KEY_PLAYER_ID + ")" + " REFERENCES " + TABLE_PLAYER + "( " + KEY_PLAYER_ID + " )" + ")";
		
		//Scene Quiz Table
		String CREATE_QUIZ_TABLE = "CREATE TABLE " + TABLE_QUIZ + "("
				+ KEY_QUESTION_ID + " CHAR(10)," 
				+ KEY_ANSWER_ID + " CHAR(10)," 
				+ KEY_CORRECT_ANSWER + " TINYINT(1)," 
				+ "FOREIGN KEY" + "(" + KEY_QUESTION_ID + ")" + " REFERENCES " + TABLE_QUESTIONS + "( " + KEY_QUESTION_ID + " )" 
				+ "FOREIGN KEY" + "(" + KEY_ANSWER_ID + ")" + " REFERENCES " + TABLE_ANSWERS + "( " + KEY_ANSWER_ID + " )"+ ")";
		db.execSQL(CREATE_QUIZ_TABLE);
		
		final String InsertQuizTable="INSERT INTO " + TABLE_QUIZ + " SELECT " + "'1'" + " AS " + KEY_QUESTION_ID  
				+ ", " + "'1'" + " AS " + KEY_ANSWER_ID 
				+ ", " + "'1'" + " AS " + KEY_CORRECT_ANSWER
				+ " UNION SELECT " + "1" + ", " + "2" + ", " + "0"
				+ " UNION SELECT " + "1" + ", " + "3" + ", " + "0"
				+ " UNION SELECT " + "1" + ", " + "4" + ", " + "0"
				+ " UNION SELECT " + "2" + ", " + "9" + ", " + "1"
				+ " UNION SELECT " + "2" + ", " + "10" + ", " + "0"
				+ " UNION SELECT " + "2" + ", " + "11" + ", " + "0"
				+ " UNION SELECT " + "2" + ", " + "12" + ", " + "0"
				+ " UNION SELECT " + "3" + ", " + "13" + ", " + "1"
				+ " UNION SELECT " + "3" + ", " + "14" + ", " + "0"
				+ " UNION SELECT " + "3" + ", " + "15" + ", " + "0"
				+ " UNION SELECT " + "3" + ", " + "16" + ", " + "0"
				+ " UNION SELECT " + "4" + ", " + "17" + ", " + "0"
				+ " UNION SELECT " + "4" + ", " + "18" + ", " + "1"
				+ " UNION SELECT " + "4" + ", " + "19" + ", " + "0"
				+ " UNION SELECT " + "4" + ", " + "20" + ", " + "0"
				+ " UNION SELECT " + "5" + ", " + "21" + ", " + "1"
				+ " UNION SELECT " + "5" + ", " + "22" + ", " + "0"
				+ " UNION SELECT " + "5" + ", " + "23" + ", " + "0"
				+ " UNION SELECT " + "5" + ", " + "24" + ", " + "0"
				+ " UNION SELECT " + "6" + ", " + "25" + ", " + "1"
				+ " UNION SELECT " + "6" + ", " + "26" + ", " + "0"
				+ " UNION SELECT " + "6" + ", " + "27" + ", " + "0"
				+ " UNION SELECT " + "6" + ", " + "28" + ", " + "0"
				+ " UNION SELECT " + "7" + ", " + "29" + ", " + "1"
				+ " UNION SELECT " + "7" + ", " + "30" + ", " + "0"
				+ " UNION SELECT " + "7" + ", " + "31" + ", " + "0"
				+ " UNION SELECT " + "7" + ", " + "32" + ", " + "0"
				+ " UNION SELECT " + "8" + ", " + "33" + ", " + "0"
				+ " UNION SELECT " + "8" + ", " + "34" + ", " + "0"
				+ " UNION SELECT " + "8" + ", " + "35" + ", " + "0"
				+ " UNION SELECT " + "8" + ", " + "36" + ", " + "1"
				+ " UNION SELECT " + "9" + ", " + "37" + ", " + "1"
				+ " UNION SELECT " + "9" + ", " + "38" + ", " + "0"
				+ " UNION SELECT " + "9" + ", " + "39" + ", " + "0"
				+ " UNION SELECT " + "9" + ", " + "40" + ", " + "0"
				+ " UNION SELECT " + "10" + ", " + "41" + ", " + "0"
				+ " UNION SELECT " + "10" + ", " + "42" + ", " + "0"
				+ " UNION SELECT " + "10" + ", " + "43" + ", " + "0"
				+ " UNION SELECT " + "10" + ", " + "44" + ", " + "1"
				+ " UNION SELECT " + "11" + ", " + "45" + ", " + "0"
				+ " UNION SELECT " + "11" + ", " + "46" + ", " + "0"
				+ " UNION SELECT " + "11" + ", " + "47" + ", " + "0"
				+ " UNION SELECT " + "11" + ", " + "48" + ", " + "1"
				+ " UNION SELECT " + "12" + ", " + "49" + ", " + "1"
				+ " UNION SELECT " + "12" + ", " + "50" + ", " + "0"
				+ " UNION SELECT " + "12" + ", " + "51" + ", " + "0"
				+ " UNION SELECT " + "12" + ", " + "52" + ", " + "0"
				+ " UNION SELECT " + "13" + ", " + "53" + ", " + "0"
				+ " UNION SELECT " + "13" + ", " + "54" + ", " + "0"
				+ " UNION SELECT " + "13" + ", " + "55" + ", " + "0"
				+ " UNION SELECT " + "13" + ", " + "56" + ", " + "1"
				+ " UNION SELECT " + "14" + ", " + "57" + ", " + "0"
				+ " UNION SELECT " + "14" + ", " + "58" + ", " + "0"
				+ " UNION SELECT " + "14" + ", " + "59" + ", " + "1"
				+ " UNION SELECT " + "14" + ", " + "60" + ", " + "0"
				+ " UNION SELECT " + "15" + ", " + "61" + ", " + "1"
				+ " UNION SELECT " + "15" + ", " + "62" + ", " + "0"
				+ " UNION SELECT " + "15" + ", " + "63" + ", " + "0"
				+ " UNION SELECT " + "15" + ", " + "64" + ", " + "0";
		db.execSQL(InsertQuizTable);		
							
		
		//Scene Answers Table
		String CREATE_ANSWERS_TABLE = "CREATE TABLE " + TABLE_ANSWERS + "("
				+ KEY_ANSWER_ID + " CHAR(10) NOT NULL PRIMARY KEY," 
				+ KEY_ANSWER_TEXT + " VARCHAR(255)" + ")";
		db.execSQL(CREATE_ANSWERS_TABLE);
		
		final String InsertAnswersTable="INSERT INTO " + TABLE_ANSWERS + " SELECT " + "'1'" + " AS " + KEY_ANSWER_ID  
				+ ", " + "'Youre going to die clown!'" + " AS " + KEY_ANSWER_TEXT
				+ " UNION SELECT " + "'2'" + ", " + "'Now youre going to get it clown!'"
				+ " UNION SELECT " + "'3'" + ", " + "'Jackass clown!'"
				+ " UNION SELECT " + "'4'" + ", " + "'Is that clown regulation size or what?'"
				+ " UNION SELECT " + "'9'" + ", " + "'Korben Dallas'"
				+ " UNION SELECT " + "'10'" + ", " + "'Cordially Texas'"
				+ " UNION SELECT " + "'11'" + ", " + "'Corn Pops for breakfast'"
				+ " UNION SELECT " + "'12'" + ", " + "'Bob'"
				+ " UNION SELECT " + "'13'" + ", " + "'A courgette'"
				+ " UNION SELECT " + "'14'" + ", " + "'A bong'"
				+ " UNION SELECT " + "'15'" + ", " + "'A woodland animal'"
				+ " UNION SELECT " + "'16'" + ", " + "'A can of baked beans'"
				+ " UNION SELECT " + "'17'" + ", " + "'Jim Broadbent'"
				+ " UNION SELECT " + "'18'" + ", " + "'Meryl Streep'"
				+ " UNION SELECT " + "'19'" + ", " + "'Margaret Thatcher'"
				+ " UNION SELECT " + "'20'" + ", " + "'Helena Bonham Carter'"
				+ " UNION SELECT " + "'21'" + ", " + "'Geoffrey Rush'"
				+ " UNION SELECT " + "'22'" + ", " + "'Colin Firth'"
				+ " UNION SELECT " + "'23'" + ", " + "'Mr Darcy'"
				+ " UNION SELECT " + "'24'" + ", " + "'King George VI'"
				+ " UNION SELECT " + "'25'" + ", " + "'Hoth'"
				+ " UNION SELECT " + "'26'" + ", " + "'Coruscant'"
				+ " UNION SELECT " + "'27'" + ", " + "'Dagobah'"
				+ " UNION SELECT " + "'28'" + ", " + "'Endor'"
				+ " UNION SELECT " + "'29'" + ", " + "'Vector'"
				+ " UNION SELECT " + "'30'" + ", " + "'Square'"
				+ " UNION SELECT " + "'31'" + ", " + "'Polygon'"
				+ " UNION SELECT " + "'32'" + ", " + "'The moon'"
				+ " UNION SELECT " + "'33'" + ", " + "'Coen brothers'"
				+ " UNION SELECT " + "'34'" + ", " + "'Weinstein brothers'"
				+ " UNION SELECT " + "'35'" + ", " + "'Weitz brothers'"
				+ " UNION SELECT " + "'36'" + ", " + "'Wachowski brothers'"
				+ " UNION SELECT " + "'37'" + ", " + "'World Trade Center'"
				+ " UNION SELECT " + "'38'" + ", " + "'Iraq war'"
				+ " UNION SELECT " + "'39'" + ", " + "'Vietnam war'"
				+ " UNION SELECT " + "'40'" + ", " + "'The hospital'"
				+ " UNION SELECT " + "'41'" + ", " + "'Iron Man'"
				+ " UNION SELECT " + "'42'" + ", " + "'The Hulk'"
				+ " UNION SELECT " + "'43'" + ", " + "'Thor'"
				+ " UNION SELECT " + "'44'" + ", " + "'Superman'"
				+ " UNION SELECT " + "'45'" + ", " + "'Buys the boy another ice cream'"
				+ " UNION SELECT " + "'46'" + ", " + "'Skips away'"
				+ " UNION SELECT " + "'47'" + ", " + "'Shoots the boy with a lazer gun'"
				+ " UNION SELECT " + "'48'" + ", " + "'Pops the balloon animal'"
				+ " UNION SELECT " + "'49'" + ", " + "'From jungle cat poo'"
				+ " UNION SELECT " + "'50'" + ", " + "'From elephant sweat'"
				+ " UNION SELECT " + "'51'" + ", " + "'From a poisonous coffee plant'"
				+ " UNION SELECT " + "'52'" + ", " + "'From tasty mud'"
				+ " UNION SELECT " + "'53'" + ", " + "'Water'"
				+ " UNION SELECT " + "'54'" + ", " + "'Fire'"
				+ " UNION SELECT " + "'55'" + ", " + "'Fog'"
				+ " UNION SELECT " + "'56'" + ", " + "'Leelu'"
				+ " UNION SELECT " + "'57'" + ", " + "'Wookieland'"
				+ " UNION SELECT " + "'58'" + ", " + "'Earth'"
				+ " UNION SELECT " + "'59'" + ", " + "'Kashyyyk'"
				+ " UNION SELECT " + "'60'" + ", " + "'Geonosis'"
				+ " UNION SELECT " + "'61'" + ", " + "'The moon'"
				+ " UNION SELECT " + "'62'" + ", " + "'The sun'"
				+ " UNION SELECT " + "'63'" + ", " + "'The pyramids'"
				+ " UNION SELECT " + "'64'" + ", " + "'The mona lisa'";
		db.execSQL(InsertAnswersTable);				
		
		//question had clip table
		String CREATE_QUESTION_HAS_CLIP_TABLE = "CREATE TABLE " + TABLE_QUESTION_HAS_CLIP + "("
				+ KEY_QUESTION_HAS_CLIP_ID + " CHAR(10) NOT NULL PRIMARY KEY," 
				+ KEY_QUESTION_ID + " CHAR(10),"
				+ KEY_PRE_CLIP + " VARCHAR(200),"
				+ KEY_POST_CLIP + " VARCHAR(200),"
				+ "FOREIGN KEY" + "(" + KEY_QUESTION_ID + ")" + " REFERENCES " + TABLE_QUESTIONS + "( " + KEY_QUESTION_ID + " )" + ")";
		db.execSQL(CREATE_QUESTION_HAS_CLIP_TABLE);
		
		final String InsertClipTable="INSERT INTO " + TABLE_QUESTION_HAS_CLIP + " SELECT " + "'1'" + " AS " + KEY_QUESTION_HAS_CLIP_ID  
				+ ", " + "'1'" + " AS " + KEY_QUESTION_ID
				+ ", " + "'happy_gilmore_pre.mp4'" + " AS " + KEY_PRE_CLIP
				+ ", " + "'happy_gilmore_post.mp4'" + " AS " + KEY_POST_CLIP	
				+ " UNION SELECT " + "2" + ", " + "3" + ", " + "'spinal_tap_pre.mp4'" + ", " + "'spinal_tap_post.mp4'"
				+ " UNION SELECT " + "3" + ", " + "11" + ", " + "'despicable_me_pre.mp4'" + ", " + "'despicable_me_post.mp4'";
		db.execSQL(InsertClipTable);		
	}
	
	// Updating database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// Drop older tables if they exist
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTIONS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANSWERS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_HAS_IMAGE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION_HAS_CLIP);

		// Create tables again
		onCreate(db);
	}

	
	/*
	 * Get four game answers
	 */
	public HashMap<String, String>getFourAnswers(int qu_id)
	{
		HashMap<String,String> answers = new HashMap<String,String>();
		String selectQuery = "SELECT  " + TABLE_ANSWERS + "." + KEY_ANSWER_ID + ", " 
				+ TABLE_ANSWERS + "." + KEY_ANSWER_TEXT +  " FROM " + TABLE_ANSWERS + ", " 
				+ TABLE_QUESTIONS + ", " + TABLE_QUIZ 
				+ " WHERE " + TABLE_QUESTIONS + "." + KEY_QUESTION_ID + "= " + TABLE_QUIZ 
				+ "." + KEY_QUESTION_ID + " AND " + TABLE_ANSWERS + "." 
				+ KEY_ANSWER_ID  + "= " + TABLE_QUIZ + "." + KEY_ANSWER_ID
				+ " AND " + TABLE_QUESTIONS + "." + KEY_QUESTION_ID + "=" + qu_id;
		
//		/Log.d("TESTING QUERY: ", selectQuery);
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		int i = 0;
	    while(cursor.moveToNext())
	    {
		    	i++;
		    	answers.put("answer_id"+i, cursor.getString(0));
		    	answers.put("answer_text"+i, cursor.getString(1));
		    	Log.d("answer_id"+i, cursor.getString(0));
		    	Log.d("answer_text"+i, cursor.getString(1));
	    }
	    cursor.close();
	    db.close();
	    return answers;
	}
	
	/*
	 * getcorrect answer - get answer id for question id where correct answer...
	 */
	public HashMap<String, String>getCorrectAnswer(int ans_id)
	{
		HashMap<String,String> correct_answer = new HashMap<String,String>();
		String selectQuery = "SELECT  " + TABLE_ANSWERS + "." + KEY_ANSWER_ID + ", " 
				+ TABLE_QUIZ + "." + KEY_ANSWER_ID + ", " 
				+ TABLE_QUIZ + "." + KEY_CORRECT_ANSWER 
				+  " FROM " + TABLE_ANSWERS + ", " + TABLE_QUIZ 
				+ " WHERE " + TABLE_ANSWERS + "." + KEY_ANSWER_ID + 
				"= " + TABLE_QUIZ + "." + KEY_ANSWER_ID 
				+ " AND " + TABLE_QUIZ + "." + KEY_ANSWER_ID + "=" + ans_id;
		
		//Log.d("TESTING QUERY: ", selectQuery);
		
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		cursor.moveToFirst();
		if(cursor.getCount() > -1)
		{
		    	correct_answer.put("correct_answer", cursor.getString(2));    	
		}
	    //Log.d("GET CORRECT ANSWER: ", correct_answer.toString());
	    cursor.close();
	    db.close();
	    return correct_answer;
	}
	
	/*
	 * Get following game questions with their four answers
	 */
	public HashMap<String, String>getNextQuestion(int current_qu)
	{
		int current_question = current_qu;
		
		HashMap<String,String> questionanswers = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_ANSWERS + ", " 
							+ TABLE_QUESTIONS + ", " + TABLE_QUIZ + ", " 
				+ TABLE_QUESTION_HAS_CLIP
				+ " WHERE " + TABLE_QUESTIONS + "." + KEY_QUESTION_ID 
				+ "= " + TABLE_QUIZ + "." + KEY_QUESTION_ID 
				+ " AND " + TABLE_ANSWERS + "." + KEY_ANSWER_ID  
				+ "= " + TABLE_QUIZ + "." + KEY_ANSWER_ID
				+ " AND " + TABLE_QUESTIONS + "." + KEY_QUESTION_ID  
				+ "= " + current_question;
		
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
	    
	    if(cursor.moveToLast())
	    {
	    	 // Retrieve the shared preferences
    		questionanswers.put("question_text", cursor.getString(3));
    		questionanswers.put("question_id", cursor.getString(4));	    	
	    }
	    
	    cursor.close();
	    db.close();
	    getClips(current_question);
		return questionanswers;
	}	
	
	/*
	 * Get all the game clips
	 */
	public HashMap<String, String>getClips(int current_qu)
	{
		int current_question = current_qu;
		
		HashMap<String,String> clips = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_QUESTION_HAS_CLIP 
				+ ", " + TABLE_QUESTIONS 
		+ " WHERE " + TABLE_QUESTIONS + "." + KEY_QUESTION_ID 
		+ "= " + TABLE_QUESTION_HAS_CLIP + "." + KEY_QUESTION_ID
		+ " AND " + TABLE_QUESTIONS + "." + KEY_QUESTION_ID  
		+ "= " + current_question;
	
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
	    if(cursor.moveToLast())
	    {
		    clips.put("question_id", cursor.getString(1));
		   	clips.put("pre_clip", cursor.getString(2));
    		clips.put("post_clip", cursor.getString(3));
		    //Log.d("DBHandler", clips.toString());
	    }
	    cursor.close();
	    
	    db.close();
		// return clips
	    return clips;
	}
	
		
	
	
	/**
	 * Re-create database
	 * Delete all tables and create them again
	 * */
	public void resetTables()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_QUESTIONS, null, null);
		db.delete(TABLE_QUIZ, null, null);
		db.delete(TABLE_ANSWERS, null, null);
		db.delete(TABLE_QUESTION_HAS_IMAGE, null, null);
		db.delete(TABLE_QUESTION_HAS_CLIP, null, null);
		
		db.close();
	}
}
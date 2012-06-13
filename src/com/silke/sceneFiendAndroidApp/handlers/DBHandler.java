package com.silke.sceneFiendAndroidApp.handlers;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper
{
	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "scenefiendlocaldb";

	// Table names
	private static final String TABLE_PLAYER= "scene__player";
	private static final String TABLE_SCORES = "scene_scores";
	private static final String TABLE_QUESTIONS = "scene__questions";
	private static final String TABLE_QUIZ = "scene_quiz";
	private static final String TABLE_ANSWERS = "scene__answers";
	private static final String TABLE_QUESTION_HAS_IMAGE = "question_has_image";
	private static final String TABLE_QUESTION_HAS_CLIP = "question_has_clip";
	
	
	// Player Table Columns names
	private static final String KEY_PLAYER_ID = "player_id";
	private static final String KEY_PLAYER_NAME = "player_name";
	private static final String KEY_PLAYER_EMAIL = "player_email";
	
	// Scores Table Columns names
	private static final String KEY_SCORES_ID = "id";
	private static final String KEY_PLAYER_SCORE = "player_score";
	private static final String KEY_SCORE_DATE = "score_date";
	
	//Scene Questions Table
	private static final String KEY_QUESTION_ID = "question_id";
	private static final String KEY_QUESTION_TEXT = "question_text";
	
	//Scene Quiz Table
	private static final String KEY_CORRECT_ANSWER = "correct_answer";
	
	//Scene Answers Table
	private static final String KEY_ANSWER_ID = "answer_id";
	private static final String KEY_ANSWER_TEXT = "answer_text";
	
	//Question_has_image table
	private static final String KEY_QUESTION_HAS_IMAGE_ID = "id";
	private static final String KEY_IMAGE = "image";
	
	//question had clip table
	private static final String KEY_QUESTION_HAS_CLIP_ID = "id";
	private static final String KEY_PRE_CLIP = "pre_clip";
	private static final String KEY_POST_CLIP = "post_clip";
	
	
	public DBHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		//Player table
		String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYER + "("
				+ KEY_PLAYER_ID + " CHAR(10) NOT NULL PRIMARY KEY," 
				+ KEY_PLAYER_NAME + " VARCHAR(100),"
				+ KEY_PLAYER_EMAIL + " VARCHAR(100)," + ")";
		db.execSQL(CREATE_PLAYER_TABLE);
		
		//NO PRIMARY KEY IS IN THE SCORES TABLE!!!!!!
		//Scores table
		//LINKS TO TABLE PLAYER TO PASS PLAYER_ID VALUE TO SCORES TABLE
		String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
				+ KEY_SCORES_ID + " CHAR(10) NOT NULL PRIMARY KEY," 
				+ KEY_PLAYER_ID + " CHAR(10)," 
				+ KEY_PLAYER_SCORE + " INTEGER,"
				+ KEY_SCORE_DATE + " DATETIME," 
				+ "FOREIGN KEY" + "(" + KEY_PLAYER_ID + "REFERENCES" + TABLE_PLAYER + "(" + KEY_PLAYER_ID + ")" + ")";
		db.execSQL(CREATE_SCORES_TABLE);
		
		//Scene Questions Table
		
		//Scene Quiz Table
		
		//Scene Answers Table
		
		//Question_has_image table
		
		//question had clip table
		
	}
	
	// Updating database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// Drop older tables if they exist
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);

		// Create tables again
		onCreate(db);
	}

	/**
	 * Storing user details in local database
	 * */
	public void addUser(String player_id, String player_name, String player_email) 
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_PLAYER_ID, player_id); // Email
		values.put(KEY_PLAYER_NAME, player_name); // Name
		values.put(KEY_PLAYER_EMAIL, player_email); // Email

		// Inserting Row
		db.insert(TABLE_PLAYER, null, values);
		db.close(); // Closing database connection
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails()
	{
		HashMap<String,String> user = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_PLAYER;
		 
	    SQLiteDatabase db = this.getReadableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    // Move to first row
	    cursor.moveToFirst();
	    if(cursor.getCount() > 0)
	    {
	    	user.put("player_id", cursor.getString(1));
	    	user.put("player_name", cursor.getString(2));
	    	user.put("player_email", cursor.getString(3));
	    }
	    cursor.close();
	    db.close();
		// return user
		return user;
	}

	/**
	 * Getting user login status
	 * return true if rows are in table
	 * */
	public int getRowCount() 
	{
		String countQuery = "SELECT  * FROM " + TABLE_PLAYER;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		int rowCount = cursor.getCount();
		db.close();
		cursor.close();
		
		// return row count
		return rowCount;
	}

	/**
	 * Re-create database
	 * Delete all tables and create them again
	 * */
	public void resetTables()
	{
		SQLiteDatabase db = this.getWritableDatabase();
		// Delete All Rows
		db.delete(TABLE_PLAYER, null, null);
		db.close();
	}

}
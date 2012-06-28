package com.silke.sceneFiendAndroidApp.handlers;

import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper
{
	SharedPreferences mGameSettings;

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "scenefiendlocaldb";

	// Table names
	private static final String TABLE_PLAYER= "scene__player";
	private static final String TABLE_SCORES = "scene_scores";

	// Player Table Columns names
	public static final String KEY_PLAYER_ID = "player_id";
	public static final String KEY_PLAYER_NAME = "player_name";
	public static final String KEY_PLAYER_EMAIL = "player_email";
	
	// Scores Table Columns names
	private static final String KEY_SCORES_ID = "id";
	private static final String KEY_PLAYER_SCORE = "player_score";
	private static final String KEY_SCORE_DATE = "score_date";

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
				+ KEY_PLAYER_EMAIL + " VARCHAR(100)" + ")";
		db.execSQL(CREATE_PLAYER_TABLE);
		
		
		//Scores table
		String CREATE_SCORES_TABLE = "CREATE TABLE " + TABLE_SCORES + "("
				+ KEY_SCORES_ID + " CHAR(10) NOT NULL PRIMARY KEY, " 
				+ KEY_PLAYER_ID + " CHAR(10), " 
				+ KEY_PLAYER_SCORE + " INTEGER, "
				+ KEY_SCORE_DATE + " INTEGER, " 
				+ "FOREIGN KEY" + "(" + KEY_PLAYER_ID + ")" + " REFERENCES " + TABLE_PLAYER + "( " + KEY_PLAYER_ID + " )" + ")";
		db.execSQL(CREATE_SCORES_TABLE);
			
	}
	
	/**
	 * Storing user details in local database
	 * */
	public void addScore(String player_id, Integer player_score, long score_date) 
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_SCORES_ID, 1);
		values.put(KEY_PLAYER_ID, player_id); // Email
		values.put(KEY_PLAYER_SCORE, player_score); // Name
		values.put(KEY_SCORE_DATE, score_date); // Email

		// Inserting Row
		db.insert(TABLE_SCORES, null, values);
		db.close(); // Closing database connection
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
		db.delete(TABLE_SCORES, null, null);

		db.close();
	}
}
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

	// Player Login table name
	private static final String TABLE_LOGIN = "login";

	// Player Login Table Columns names
	private static final String KEY_ID = "player_id";
	private static final String KEY_NAME = "player_name";
	private static final String KEY_EMAIL = "player_email";


	public DBHandler(Context context) 
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// Creating Player Table
	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," 
				+ KEY_NAME + " TEXT,"
				+ KEY_EMAIL + " TEXT," + ")";
		db.execSQL(CREATE_LOGIN_TABLE);
	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// Drop older table if exists
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);

		// Create table again
		onCreate(db);
	}

	/**
	 * Storing user details in local database
	 * */
	public void addUser(String player_id, String player_name, String player_email) 
	{
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ID, player_id); // Email
		values.put(KEY_NAME, player_name); // Name
		values.put(KEY_EMAIL, player_email); // Email

		// Inserting Row
		db.insert(TABLE_LOGIN, null, values);
		db.close(); // Closing database connection
	}

	/**
	 * Getting user data from database
	 * */
	public HashMap<String, String> getUserDetails()
	{
		HashMap<String,String> user = new HashMap<String,String>();
		String selectQuery = "SELECT  * FROM " + TABLE_LOGIN;
		 
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
		String countQuery = "SELECT  * FROM " + TABLE_LOGIN;
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
		db.delete(TABLE_LOGIN, null, null);
		db.close();
	}

}
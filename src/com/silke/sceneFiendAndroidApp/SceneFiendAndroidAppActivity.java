package com.silke.sceneFiendAndroidApp;

import android.app.Activity;

public class SceneFiendAndroidAppActivity extends Activity 
{
	// Game preference values
	public static final String GAME_PREFERENCES = "GamePrefs";
	public static String GAME_PREFERENCES_PLAYER_NAME = "Name"; // String
	public static final String GAME_PREFERENCES_PLAYER_EMAIL = "Email"; // String
	public static final String GAME_PREFERENCES_PLAYER_PASSWORD = "Password"; // String
	public static Integer GAME_PREFERENCES_PLAYER_SCORE = 0; // Integer
	public static Integer GAME_PREFERENCES_CURRENT_QUESTION = 1; // Integer
	public static final String GAME_PREFERENCES_PLAYER_AVATAR = "Avatar"; // String URL to image
	
	public static String GAME_PREFERENCES_PLAYER_ID = "ServerId"; // Integer
	
	// XML tags for questions in the game
    public static final String XML_TAG_QUESTION_BLOCK = "questions";
    public static final String XML_TAG_QUESTION = "question";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_NUMBER = "number";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_TEXT = "text";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_VIDEO = "video";    
    public static final String XML_TAG_QUESTION_ATTRIBUTE_IMAGEURL = "imageUrl";
    public static final int QUESTION_BATCH_SIZE = 5; // 15;
	
	protected static final String DEBUG_TAG = "Scene Fiend Log";

	//save user id in a preference
}


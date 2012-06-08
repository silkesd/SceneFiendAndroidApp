package com.silke.sceneFiendAndroidApp;

import android.app.Activity;

public class SceneFiendAndroidAppActivity extends Activity 
{
	// Game preference values
	public static final String GAME_PREFERENCES = "GamePrefs";
	public static final String GAME_PREFERENCES_NICKNAME = "Nickname"; // String
	public static final String GAME_PREFERENCES_EMAIL = "Email"; // String
	public static final String GAME_PREFERENCES_PASSWORD = "Password"; // String
	public static final String GAME_PREFERENCES_GENDER = "Gender";  // Integer, in array order: Male (1), Female (2), and Undisclosed (0)
	public static final String GAME_PREFERENCES_SCORE = "Score"; // Integer
	public static final String GAME_PREFERENCES_CURRENT_QUESTION = "CurQuestion"; // Integer
	public static final String GAME_PREFERENCES_AVATAR = "Avatar"; // String URL to image
	
	public static final String GAME_PREFERENCES_PLAYER_ID = "ServerId"; // Integer
	
	// XML tags for questions in the game
    public static final String XML_TAG_QUESTION_BLOCK = "questions";
    public static final String XML_TAG_QUESTION = "question";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_NUMBER = "number";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_TEXT = "text";
    public static final String XML_TAG_QUESTION_ATTRIBUTE_VIDEO = "video";    
    public static final String XML_TAG_QUESTION_ATTRIBUTE_IMAGEURL = "imageUrl";
    public static final int QUESTION_BATCH_SIZE = 5; // 15;
	
	protected static final String DEBUG_TAG = "Scene Fiend Log";
}

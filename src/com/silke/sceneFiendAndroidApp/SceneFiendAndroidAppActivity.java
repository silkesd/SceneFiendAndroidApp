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
	
	protected static final String DEBUG_TAG = "Scene Fiend Log";
}

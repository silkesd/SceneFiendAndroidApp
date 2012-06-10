package com.silke.sceneFiendAndroidApp.handlers;


public class UserFunctions 
{
	private JSONParser jsonParser;
	
	//localhost strings to connect with php files for online db connectivity
	private static String loginURL = "http://10.0.2.2/SceneFiendDatabasing/";
	private static String registerURL = "http://10.0.2.2/SceneFiendDatabasing/";
	
	private static String login_tag = "login";
	private static String register_tag = "register";
	
	// constructor
	public UserFunctions()
	{
		jsonParser = new JSONParser();
	}
	
}

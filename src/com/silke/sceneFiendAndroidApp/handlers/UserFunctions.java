package com.silke.sceneFiendAndroidApp.handlers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


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
	
	/**
	 * function make Login Request with email and password
	 * @param player_email
	 * @param player_password
	 * */
	public JSONObject loginUser(String player_email, String player_password)
	{
		// Building Parameters and returning json string
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("player_email", player_email));
		params.add(new BasicNameValuePair("player_password", player_password));
		//parse data from the login url stated above with params
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		return json;
	}
	
	/**
	 * function make Login Request
	 * @param player_name
	 * @param player_email
	 * @param password
	 * @param player_avatar
	 * */
	public JSONObject registerUser(String player_name, String player_email, String password, String player_avatar){
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("player_name", player_name));
		params.add(new BasicNameValuePair("player_email", player_email));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("player_avatar", player_avatar));
		
		// getting JSON Object from register url with params
		//password is prior to encryption in php files
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		return json;
	}
}

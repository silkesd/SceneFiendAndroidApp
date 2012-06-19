package com.silke.sceneFiendAndroidApp.handlers;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import com.silke.sceneFiendAndroidApp.asynctasks.FileDownloader;
import android.content.Context;
import android.util.Log;


public class UserFunctions 
{
	private FileDownloader fileDownloader;
	
	//localhost strings to connect with php files for online db connectivity
	private static String loginURL = "http://10.0.2.2:8888/SceneFiendDatabasing/index.php";
	private static String registerURL = "http://10.0.2.2:8888/SceneFiendDatabasing/index.php";
	
	private static String login_tag = "login";
	private static String register_tag = "register";
	
	private Context c;
	
	// constructor
	public UserFunctions() 
	{
		
		fileDownloader = new FileDownloader(null, null);
	}
	
	/**
	 * function make Login Request with username and password
	 * @param player_name
	 * @param player_password
	 * @return 
	 * */
	public FileDownloader loginUser(String player_name, String password)
	{
		// Building Parameters and returning json string
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", this.login_tag));
		params.add(new BasicNameValuePair("player_name", player_name));
		params.add(new BasicNameValuePair("password", password));
		
		Log.d("UserFunctions", "sending login to file downloader");
		fileDownloader = new FileDownloader(c, params);
		fileDownloader.execute(loginURL);
		return fileDownloader;
		
		//parse data from the login url stated above with params
		//JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
		// return json
		// Log.e("JSON", json.toString());
		//return json;
	}
	
	/**
	 * function make Login Request
	 * @param player_name
	 * @param player_email
	 * @param password
	 * @param player_avatar
	 * */
	public void registerUser(String player_name, String player_email, String password)
	{
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", this.register_tag));
		params.add(new BasicNameValuePair("player_name", player_name));
		params.add(new BasicNameValuePair("player_email", player_email));
		params.add(new BasicNameValuePair("password", password));
		
		// getting JSON Object from register url with params
		//password is prior to encryption in php files
		//JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
		// return json
		//return json;
		Log.d("UserFunctions", "sending register to file downloader");
		fileDownloader = new FileDownloader(c, params);
		fileDownloader.execute(registerURL);
		
	}
	
	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context)
	{
		DBHandler db = new DBHandler(context);
		int count = db.getRowCount();
		if(count > 0){
			// user is logged in
			return true;
		}
		return false;
	}
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
	public boolean logoutUser(Context context)
	{
		DBHandler db = new DBHandler(context);
		db.resetTables();
		return true;
	}
}
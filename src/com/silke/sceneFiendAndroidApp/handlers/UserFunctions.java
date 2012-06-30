package com.silke.sceneFiendAndroidApp.handlers;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

import com.silke.sceneFiendAndroidApp.SceneFiendAndroidAppActivity;
import com.silke.sceneFiendAndroidApp.asynctasks.FileDownloader;
import com.silke.sceneFiendAndroidApp.asynctasks.IJsonDownloaded;

public class UserFunctions extends SceneFiendAndroidAppActivity
{
	private FileDownloader fileDownloader;
	
	//localhost strings to connect with php files for online db connectivity
	private static String loginURL = "http://www.scenefiend.silkesd.com/index.php";
	private static String registerURL = "http://www.scenefiend.silkesd.com/index.php";
	private static String scoreURL = "http://www.scenefiend.silkesd.com/update_scores.php";
	
	private static String login_tag = "login";
	private static String register_tag = "register";
	private static String score_tag = "score";
	
	// constructor
	public UserFunctions() 
	{
		
	}
	
	/**
	 * function make Login Request with username and password
	 * @param player_name
	 * @param player_password
	 * @return 
	 * */
	public FileDownloader loginUser(IJsonDownloaded context, String player_name, String password)
	{
		// Building Parameters and returning json string
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", this.login_tag));
		params.add(new BasicNameValuePair("player_name", player_name));
		params.add(new BasicNameValuePair("password", password));
		
		//Log.d("UserFunctions", "sending login to file downloader");
		fileDownloader = new FileDownloader(context, params);
		fileDownloader.execute(loginURL);
		return fileDownloader;
	}
	
	
	/**
	 * function make Login Request
	 * @param player_name
	 * @param player_email
	 * @param password
	 * @param player_avatar
	 * @return 
	 * */
	public FileDownloader registerUser(IJsonDownloaded context, String player_name, String player_email, String password)
	{
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", this.register_tag));
		params.add(new BasicNameValuePair("player_name", player_name));
		params.add(new BasicNameValuePair("player_email", player_email));
		params.add(new BasicNameValuePair("password", password));
		
		Log.d("UserFunctions", "sending register to file downloader");
		fileDownloader = new FileDownloader(context, params);
		fileDownloader.execute(registerURL);
		return fileDownloader;
	}
	
	
	/**
	 * function make Login Request with username and password
	 * @param player_name
	 * @param player_password
	 * @return 
	 * */
//	public FileDownloader updateScore(IJsonDownloaded context, String player_id, Integer player_score, Long score_date)
//	{
//		// Building Parameters and returning json string
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		params.add(new BasicNameValuePair("tag", this.login_tag));
//		params.add(new BasicNameValuePair("player_name", player_name));
//		params.add(new BasicNameValuePair("password", password));
//		
//		//Log.d("UserFunctions", "sending login to file downloader");
//		fileDownloader = new FileDownloader(context, params);
//		fileDownloader.execute(loginURL);
//		return fileDownloader;
//	}
	
	/**
	 * Function get Login status
	 * */
	public boolean isUserLoggedIn(Context context)
	{
		DBHandler db = new DBHandler(context);
		int count = db.getRowCount();
		if(count > 0)
		{
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
	
	/**
	 * Function to logout user
	 * Reset Database
	 * */
	public boolean loseScore(Context context)
	{
		DBHandler db = new DBHandler(context);
		db.resetTables();
		return true;
	}

	public FileDownloader updateScore(IJsonDownloaded context, String player_id, String player_score, String score_date) 
	{
		//Log.d("THIS IS THE PLAYER_ID IN USERFUNCTIONS", player_id);
		// Building Parameters and returning json string
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("tag", this.score_tag));
		params.add(new BasicNameValuePair("player_id", player_id));
		params.add(new BasicNameValuePair("player_score", player_score));
		params.add(new BasicNameValuePair("score_date", score_date));
		//Log.d("PLAYER_ID IN FILEDOWNLOADER AFTER BUILD PARAMS", player_id);
		
		Log.d("UserFunctions", "sending update score to file downloader");
		fileDownloader = new FileDownloader(context, params);
		fileDownloader.execute(scoreURL);
		//Log.d("PLAYER_ID EXECUTE AFTER BUILD PARAMS", player_id);
		return fileDownloader;
	}
}
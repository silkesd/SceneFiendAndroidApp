package com.silke.sceneFiendAndroidApp.handlers;

import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.silke.sceneFiendAndroidApp.GameActivity;
import com.silke.sceneFiendAndroidApp.MenuActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class JSONParser extends Activity
{
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	private static String data;
	private Context c;

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        //Toast.makeText(c, data, Toast.LENGTH_SHORT).show();
        
    }
        
	// constructor
	public JSONParser(String data)
	{
		Log.d("JSONParser", data + "tisthoiajfo;iej");
		//this.data = data;
  	
		handleData(data);
	}
	
	//returning a json object
	private JSONObject handleData(String data)
	{
		try 
		{
			JSONObject jObj = new JSONObject(data);
			
			Log.d("JSONPARSER", jObj.get("tag").toString());
			String testing = jObj.get("tag").toString();
			//
			//upon logging in: email and password will be returned
			if(testing == "login")
			{
				Intent i = new Intent(this, LoginJSON.class);
				this.startActivity(i);
			}
			else
			{
				Intent i = new Intent(this, RegisterJSON.class);
				this.startActivity(i);
				//new Intent(this, RegisterJSON.class);
			}
			
			//upon signing up: player id will be returned.
			Log.d("JSONParser", data);
		
			
			
			
			//jObj.getJSONObject("success");
//				try {
//					
//					
//					//String jData = jObj.getString(i);
//					
//					//int player_id = Integer.parseInt(jObj.getString(i));
//				}
				
				Log.d("JSONParser", jObj.get("success") + " : hello");
				
				//Toast.makeText(this.c, jObj + " we are connected to the API!", Toast.LENGTH_LONG).show();	
		} 
		catch(JSONException e)
		{
			e.printStackTrace();
		}
		return jObj;	
	}
}
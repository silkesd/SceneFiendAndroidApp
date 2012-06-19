//package com.silke.sceneFiendAndroidApp.handlers;
//
//import java.io.InputStream;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import com.silke.sceneFiendAndroidApp.GameActivity;
//import com.silke.sceneFiendAndroidApp.MenuActivity;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//public class JSONParser extends Activity
//{
//	static InputStream is = null;
//	static JSONObject jObj = null;
//	static String json = "";
//	private static String data;
//	private Context c;
//
//	/** Called when the activity is first created. */
//    @Override
//    public void onCreate(Bundle savedInstanceState) 
//    {
//        super.onCreate(savedInstanceState);
//        //Toast.makeText(c, data, Toast.LENGTH_SHORT).show();
//        
//    }
//        
//	// constructor
//	public JSONParser(String data)
//	{
//		//this.data = data;
//		//Log.d("JSONParser", data);
//		Log.d("JSONParser", data + "ABOUT TO SEND TO HANDLE");
//		handleData(data);
//	}
//	
//	//returning a json object
//	private JSONObject handleData(String data)
//	{
//		Log.d("JSONParser", "in HANDLE");
//		try 
//		{
//			
//			jObj = new JSONObject().put("JSON", data);	
////			JSONObject jObj = new JSONObject(data);
////
////			//upon logging in: email and password will be returned
////			if((jObj.get("tag")).toString() == "login")
////			{
////				Intent i = new Intent(this, LoginJSON.class);
////				this.startActivity(i);
////			}
////			else
////			{
////				Intent i = new Intent(this, RegisterJSON.class);
////				this.startActivity(i);
//////				//new Intent(this, RegisterJSON.class);
////			}			
//			//upon signing up: player id will be returned.
//			Log.d("JSONParser", data);
//		
//			
//			jObj.getJSONObject("success");
////			try {					
////					
////					//String jData = jObj.getString(i);
////					
////					//int player_id = Integer.parseInt(jObj.getString(i));
////				}
////				
//				Log.d("JSONParser", jObj.get("success") + " : hello");
////				
//				//Toast.makeText(this.c, jObj + " we are connected to the API!", Toast.LENGTH_LONG).show();	
//		} 
//		catch(JSONException e)
//		{
//			e.printStackTrace();
//		}
//		return jObj;	
//	}
//}


package com.silke.sceneFiendAndroidApp.handlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

public class JSONParser extends Activity
{
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	
	// constructor
		public JSONParser(String data) 
		{
			Log.d("JSONParser", data);
		}

		// function get json from url
		// by making HTTP POST or GET mehtod
		public JSONObject makeHttpRequest(String url, String method,
				List<NameValuePair> params) 
		{

			// Making HTTP request
			try {
				
				// check for request method
				if(method == "POST"){
					// request method is POST
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(url);
					httpPost.setEntity(new UrlEncodedFormEntity(params));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
					
				}
				else if(method == "GET")
				{
					// request method is GET
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String paramString = URLEncodedUtils.format(params, "utf-8");
					url += "?" + paramString;
					HttpGet httpGet = new HttpGet(url);

					HttpResponse httpResponse = httpClient.execute(httpGet);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
				}			
				

			} 
			catch (UnsupportedEncodingException e) 
			{
				e.printStackTrace();
			} 
			catch (ClientProtocolException e) 
			{
				e.printStackTrace();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				json = sb.toString();
				Log.e("JSON", json);
			} 
			catch (Exception e) 
			{
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}

			// try parse the string to a JSON object
			try 
			{
				jObj = new JSONObject(json);
			} 
			catch (JSONException e) 
			{
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			
			// return JSON String
			return jObj;
			
		}
}
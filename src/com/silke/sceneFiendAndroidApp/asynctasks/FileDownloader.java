package com.silke.sceneFiendAndroidApp.asynctasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.silke.sceneFiendAndroidApp.LoginResponseActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class FileDownloader extends AsyncTask<String, Integer, String>
{
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	protected Context c;
	protected List<NameValuePair> params;
	
	public FileDownloader(Context c, List<NameValuePair> params)
	{
		this.c = c;
		this.params = params;
	}
	
	protected String doInBackground(String... url) 
	{
		String apiResponse = "";
		
		for(String urlToLoad : url)
		{
			apiResponse = addParams(urlToLoad, params);
			
			Log.d("FileDownloader", apiResponse.toString());
		}
		
		return apiResponse;
		
	}
	
	protected String addParams(
			String urlToLoad, List<NameValuePair> params)
	{
		String apiResponse = "";
		
		
		Log.d("FileDownloader", urlToLoad);
		try 
		{
			Log.d("FileDownloader", "in try");
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(urlToLoad);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			
			Log.d("FileDownloader", params.toString() + ": are the params");
			
			//PROBLEM IS HERE!!
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			Log.d("FileDownloader", httpResponse.toString() + ": is the httpResponse");
			
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			
			Log.d("FileDownloader", is.toString() + ": is inputstream for http entity");
			//InputStream content = httpResponse.getEntity().getContent();
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			String s = "";
			
			//loop through lines and append apiresponse
			while((s = buffer.readLine()) != null)
			{
				apiResponse += s;
			}
		} 
		catch (Exception e)
		{
			Log.d("FILEDOWNLOADER", "catch exception in add params");
		}
		return apiResponse;
	}
	
	protected void onPostExecute(String apiResponse)
	{
		
		Log.d("ASYNCTASK", apiResponse + ": is the response from the api");
		//Toast.makeText(this.c, apiResponse, Toast.LENGTH_SHORT).show();
		//pass the data to the jsonparser here. 
		//Log.d("ASYNCTASK", apiResponse);
		//JSONParser jp = new JSONParser(apiResponse);
		//apiResponse to json parser.
		//LoginResponseActivity login = new LoginResponseActivity(apiResponse);
	}
}


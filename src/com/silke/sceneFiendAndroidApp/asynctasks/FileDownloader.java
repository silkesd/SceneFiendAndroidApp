package com.silke.sceneFiendAndroidApp.asynctasks;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import com.silke.sceneFiendAndroidApp.handlers.JSONParser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

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
			// defaultHttpClient
			DefaultHttpClient httpClient = new DefaultHttpClient();
			apiResponse = addParams(httpClient, urlToLoad, params);
			
			
		}
		return apiResponse;
		
	}

	protected String addParams(DefaultHttpClient httpClient, String url, List<NameValuePair> params)
	{
		String apiResponse = "";
		
		HttpPost httpPost = new HttpPost(url);
		
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			//HttpEntity httpEntity = httpResponse.getEntity();
			//is = httpEntity.getContent();
			InputStream content = httpResponse.getEntity().getContent();
			BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
			String s = "";
			
			while((s = buffer.readLine()) != null)
			{
				apiResponse += s;
			}
			
		} catch (Exception e)
		{
			
		}
		
		return apiResponse;
		
	}
	
	protected void onPostExecute(String apiResponse)
	{
		//System.out.println(apiResponse);
		//Log.d("ASYNCTASK", apiResponse + ": is the response from the api");
		//Toast.makeText(this.c, apiResponse, Toast.LENGTH_SHORT).show();
		//pass the data to the jsonparser here. 
		
		JSONParser jp = new JSONParser(apiResponse);
		//apiResponse to json parser.
	}
	
}

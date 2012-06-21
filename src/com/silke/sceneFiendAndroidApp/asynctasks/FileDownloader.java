package com.silke.sceneFiendAndroidApp.asynctasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.util.Log;

public class FileDownloader extends AsyncTask<String, Integer, String>
{
	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	protected List<NameValuePair> params;
	private String apiResponse = "";
	private IJsonDownloaded context;

	public FileDownloader(IJsonDownloaded context, List<NameValuePair> params)
	{
		this.params = params;
		this.context = context;
	}
	
	@Override
	protected String doInBackground(String... urls) 
	{
		Log.d("FileDownloader", urls[0]);
		try 
		{
			Log.d("FileDownloader", "in try");
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(urls[0]);
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			
			Log.d("FileDownloader", params.toString() + ": are the params");
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			Log.d("FileDownloader", httpResponse.toString() + ": is the httpResponse");
			
			HttpEntity httpEntity = httpResponse.getEntity();
			is = httpEntity.getContent();
			
			Log.d("FileDownloader", is.toString() + ": is inputstream for http entity");
			//InputStream content = httpResponse.getEntity().getContent();
			
			BufferedReader buffer = new BufferedReader(new InputStreamReader(is));
			String s = "";
			
			//loop through lines and append api response
			while((s = buffer.readLine()) != null)
			{
				apiResponse += s;
			}
		} 
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log.d("FILEDOWNLOADER", "catch exception in add params");
		}
		
		
		return apiResponse;
		
	}
	
	public String getApiResponse()
	{
		return apiResponse;
	}
	
	protected void onPostExecute(String apiResponse)
	{
		try {
			jObj = new JSONObject(apiResponse);
			context.onJsonDownloaded(jObj);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("ASYNCTASK", apiResponse + ": is the response from the api");
		
	}

	
}


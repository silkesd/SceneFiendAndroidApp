package com.silke.sceneFiendAndroidApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.silke.sceneFiendAndroidApp.AllScoresActivity.LoadAllScores;
import com.silke.sceneFiendAndroidApp.handlers.JSONScoreParser;

public class MyScoresActivity extends ListActivity
{
	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONScoreParser jParser = new JSONScoreParser();

	ArrayList<HashMap<String, String>> scoresList;
	
	// url to get all scores list
	private static String url_my_scores = "http://10.0.2.2:8888/SceneFiendDatabasing/my_scores.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SCORES = "scores";
	private static final String TAG_PLAYER_ID = "player_id";
	private static final String TAG_PLAYER_NAME = "player_name";

	// scores JSONArray
	JSONArray scores = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.score_layout);
		
		// Hashmap for ListView
		scoresList = new ArrayList<HashMap<String, String>>();

		// Loading scores in Background Thread
		new LoadMyScores().execute();
	}
	

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadMyScores extends AsyncTask<String, String, String> 
	{

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(MyScoresActivity.this);
			pDialog.setMessage("Loading scoress. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			//pDialog.show();
		}
		
		/**
		 * getting user scores from url
		 * */
		protected String doInBackground(String... args) 
		{
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_my_scores, "GET", params);
			
			// Check your log cat for JSON response
			Log.d("My Scores: ", json.toString());

			try 
			{
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) 
				{
					// products found
					// Getting Array of Products
					scores = json.getJSONArray(TAG_SCORES);

					// looping through All Products
					for (int i = 0; i < scores.length(); i++) 
					{
						JSONObject c = scores.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_PLAYER_ID);
						String name = c.getString(TAG_PLAYER_NAME);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PLAYER_ID, id);
						map.put(TAG_PLAYER_NAME, name);

						// adding HashList to ArrayList
						scoresList.add(map);
					}
				} 
				else 
				{
					// no products found
					// Launch Add New product Activity
//						Intent i = new Intent(getApplicationContext(),
//								NewProductActivity.class);
//						// Closing all previous activities
//						i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						startActivity(i);
				}
			} 
			catch (JSONException e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) 
		{
			// dismiss the dialog after getting all products
			pDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() 
			{
				public void run() 
				{
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							MyScoresActivity.this, scoresList,
							R.layout.score_list_item, new String[] { TAG_PLAYER_ID,
									TAG_PLAYER_NAME},
							new int[] { R.id.player_id, R.id.player_name });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}
	}
}

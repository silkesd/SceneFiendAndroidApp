package com.silke.sceneFiendAndroidApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.silke.sceneFiendAndroidApp.handlers.JSONScoreParser;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;


public class AllScoresActivity extends ListActivity 
{

	// Progress Dialog
	private ProgressDialog pDialog;

	// Creating JSON Parser object
	JSONScoreParser jParser = new JSONScoreParser();

	ArrayList<HashMap<String, String>> scoresList;
	
	ImageButton btnBack;
	
	// url to get all scores list
	private static String url_all_scores = "http://10.0.2.2:8888/SceneFiendDatabasing/all_scores.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_SCORES = "scores";
	private static final String TAG_PLAYER_ID = "player_id";
	private static final String TAG_PLAYER_NAME = "player_name";
	private static final String TAG_PLAYER_SCORE = "player_score";
	private static final String TAG_SCORE_DATE = "score_date";

	// scores JSONArray
	JSONArray scores = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.all_score_layout);
		
		Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
        
		// Hashmap for ListView
		scoresList = new ArrayList<HashMap<String, String>>();

		// Loading scores in Background Thread
		new LoadAllScores().execute();
		
		btnBack = (ImageButton) findViewById(R.id.btnBack);
	}
	
	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllScores extends AsyncTask<String, String, String> 
	{

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() 
		{
			super.onPreExecute();
			pDialog = new ProgressDialog(AllScoresActivity.this);
			pDialog.setMessage("Loading all scores. Please wait...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
		
		/**
		 * getting All scores from url
		 * */
		protected String doInBackground(String... args) 
		{
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_all_scores, "GET", params);
			
			// Check your log cat for JSON response
			Log.d("All Scores: ", json.toString());

			try {
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
						String score = c.getString(TAG_PLAYER_SCORE);
						String date = c.getString(TAG_SCORE_DATE);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_PLAYER_ID, id);
						map.put(TAG_PLAYER_NAME, name);
						map.put(TAG_PLAYER_SCORE, score);
						map.put(TAG_SCORE_DATE, date);

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
							AllScoresActivity.this, scoresList,
							R.layout.score_list_item, new String[] { TAG_PLAYER_ID,
									TAG_PLAYER_NAME, TAG_PLAYER_SCORE, TAG_SCORE_DATE},
							new int[] { R.id.player_id, R.id.player_name, R.id.player_score, R.id.score_date });
					// updating listview
					setListAdapter(adapter);
				}
			});
			
			// Link to Score Menu Screen
			btnBack.setOnClickListener(new View.OnClickListener() 
	 		{
	 			public void onClick(View view) 
	 			{
	 				Intent i = new Intent(getApplicationContext(),
	 						ScoreMenuActivity.class);
	 				startActivity(i);
	 				finish();
	 			}
	 		});
		}
	}
}


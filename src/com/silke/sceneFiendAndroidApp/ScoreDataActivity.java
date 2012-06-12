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
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TabHost.TabSpec;

public class ScoreDataActivity extends ListActivity
{
	// Progress Dialog
		private ProgressDialog pDialog;

		// Creating JSON Parser object
		JSONScoreParser jParser = new JSONScoreParser();

		//ArrayList<HashMap<String, String>> scoresList;
		
		// url to get all products list
		private static String url_all_scores = "http://10.0.2.2:8888/SceneFiendDatabasing/allscores.php";
		private static String url_my_scores = "http://10.0.2.2:8888/SceneFiendDatabasing/myscores.php";
		private static String url_high_scores = "http://10.0.2.2:8888/SceneFiendDatabasing/highscores.php";

		JSONObject json;
		
		// JSON Node names
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_PLAYER_ID = "player_id";
		private static final String TAG_PLAYER_NAME = "player_name";
		private static final String TAG_PLAYER_SCORES = "player_scores";
		
		// scores JSONArray
		JSONArray scores = null;
		
		// scroll views in layout
		ScrollView ScrollViewMyScores;
		ScrollView ScrollViewAllScores;
		ScrollView ScrollViewHighScores;
		
		TableLayout scoresList;
		TableLayout allScoresTable;
        TableLayout myScoresTable;
        TableLayout highScoresTable;

		@Override
		public void onCreate(Bundle savedInstanceState) 
		{
			super.onCreate(savedInstanceState);
			setContentView(R.layout.scores);
			
			//title typeface
			Typeface tf = Typeface.createFromAsset(getAssets(),
	                "fonts/lucindablack.ttf");
	        TextView tv = (TextView) findViewById(R.id.CustomFont);
	        tv.setTypeface(tf);
	        
//	        // Importing all assets like buttons, text fields
//	  		ScrollViewMyScores = (ScrollView) findViewById(R.id.ScrollViewMyScores);
//	  		ScrollViewAllScores = (ScrollView) findViewById(R.id.ScrollViewAllScores);
//	  		ScrollViewHighScores = (ScrollView) findViewById(R.id.ScrollViewHighScores);
//	        
//	        
//	        // Setting up the tabs in the layout id field
//	        TabHost host = (TabHost) findViewById(R.id.TabHost1);
//	        host.setup();
//	        
//	        // All Scores tab - adding a star by the tab title 
//	        TabSpec allScoresTab = host.newTabSpec("allTab");
//	        allScoresTab.setIndicator(getResources().getString(R.string.all_scores), getResources().getDrawable(
//	                android.R.drawable.star_on));
//	        allScoresTab.setContent(R.id.ScrollViewAllScores);
//	        host.addTab(allScoresTab);
//	        
//	        // My Scores tab - showing user scores
//	        TabSpec myScoresTab = host.newTabSpec("myTab");
//	        myScoresTab.setIndicator(getResources().getString(R.string.my_scores), getResources().getDrawable(
//	                android.R.drawable.star_on));
//	        myScoresTab.setContent(R.id.ScrollViewMyScores);
//	        host.addTab(myScoresTab);
//	        
//	        // High scores tab - the top scores
//	        TabSpec highScoresTab = host.newTabSpec("highscoresTab");
//	        highScoresTab.setIndicator(getResources().getString(R.string.high_scores), getResources().getDrawable(
//	                android.R.drawable.star_big_on));
//	        highScoresTab.setContent(R.id.ScrollViewHighScores);
//	        host.addTab(highScoresTab);
//	        
//	        // Set the default tab - the all scores tab
//	        host.setCurrentTabByTag("highscoresTab");
//	        
//	        // Retrieve the TableLayout references
//	        TableLayout allScoresTable = (TableLayout) findViewById(R.id.TableLayout_AllScores);
//	        TableLayout myScoresTable = (TableLayout) findViewById(R.id.TableLayout_MyScores);
//	        TableLayout highScoresTable = (TableLayout) findViewById(R.id.TableLayout_HighScores);
//	        
//	        // Give each TableLayout a yellow header row with the column names
//	        initializeHeaderRow(allScoresTable);
//	        initializeHeaderRow(myScoresTable);
//	        initializeHeaderRow(highScoresTable);
//	        
//	        new LoadScores().execute();
//	           
//		} 
//		
//	    
//		/**
//		 * Background Async Task to Load all product by making HTTP Request
//		 * */
//		class LoadScores extends AsyncTask<String, String, String> 
//		{
//
//			/**
//			 * Before starting background thread Show Progress Dialog
//			 * */
//			@Override
//			protected void onPreExecute() 
//			{
//				super.onPreExecute();
//				pDialog = new ProgressDialog(ScoreDataActivity.this);
//				pDialog.setMessage("Loading scores. Please wait...");
//				pDialog.setIndeterminate(false);
//				pDialog.setCancelable(false);
//				//pDialog.show();
//			}
//		
//			/**
//			 * getting All products from url
//			 * */
//			protected String doInBackground(String... args) 
//			{
//				// Building Parameters
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				
//				// Check your log cat for JSON response
//				Log.d("Scores: ", json.toString());
//
//				try 
//				{
//						if(allScoresTable != null)
//						{
//							// getting JSON string from URL
//							JSONObject json = jParser.makeHttpRequest(url_all_scores, "GET", params);
//							
//							// Checking for SUCCESS TAG
//							int success = json.getInt(TAG_SUCCESS);
//
//							if (success == 1) 
//							{
//								// products found
//								// Getting Array of Products
//								scores = json.getJSONArray(TAG_PLAYER_SCORES);
//								
//								// looping through All Products
//								for (int i = 0; i < scores.length(); i++) 
//								{
//									JSONObject c = scores.getJSONObject(i);
//	
//									// Storing each json item in variable
//									String id = c.getString(TAG_PLAYER_ID);
//									String name = c.getString(TAG_PLAYER_NAME);
//									String score = c.getString(TAG_PLAYER_SCORES);
//	
//									// creating new HashMap
//									HashMap<String, String> map = new HashMap<String, String>();
//	
//									// adding each child node to HashMap key => value
//									map.put(TAG_PLAYER_ID, id);
//									map.put(TAG_PLAYER_NAME, name);
//	
//									// adding HashList to ArrayList
//									scoresList.add(map);
//								}
//							}
//						}
//						else if(myScoresTable != null)
//						{
//							// getting JSON string from URL
//							JSONObject json = jParser.makeHttpRequest(url_my_scores, "GET", params);
//							
//							// Checking for SUCCESS TAG
//							int success = json.getInt(TAG_SUCCESS);
//
//							if (success == 1) 
//							{
//								// products found
//								// Getting Array of Products
//								scores = json.getJSONArray(TAG_PLAYER_SCORES);
//								
//								// looping through All Products
//								for (int i = 0; i < scores.length(); i++) 
//								{
//									JSONObject c = scores.getJSONObject(i);
//	
//									// Storing each json item in variable
//									String id = c.getString(TAG_PLAYER_ID);
//									String name = c.getString(TAG_PLAYER_NAME);
//									String score = c.getString(TAG_PLAYER_SCORES);
//	
//									// creating new HashMap
//									HashMap<String, String> map = new HashMap<String, String>();
//	
//									// adding each child node to HashMap key => value
//									map.put(TAG_PLAYER_ID, id);
//									map.put(TAG_PLAYER_NAME, name);
//	
//									// adding HashList to ArrayList
//									productsList.add(map);
//								}
//							}
//						}
//						else if (highScoresTable != null)
//						{
//							// getting JSON string from URL
//							JSONObject json = jParser.makeHttpRequest(url_high_scores, "GET", params);
//							
//							// Checking for SUCCESS TAG
//							int success = json.getInt(TAG_SUCCESS);
//
//							if (success == 1) 
//							{
//								// products found
//								// Getting Array of Products
//								scores = json.getJSONArray(TAG_PLAYER_SCORES);
//								
//								// looping through All Products
//								for (int i = 0; i < scores.length(); i++) 
//								{
//									JSONObject c = scores.getJSONObject(i);
//	
//									// Storing each json item in variable
//									String id = c.getString(TAG_PLAYER_ID);
//									String name = c.getString(TAG_PLAYER_NAME);
//									String score = c.getString(TAG_PLAYER_SCORES);
//	
//									// creating new HashMap
//									HashMap<String, String> map = new HashMap<String, String>();
//	
//									// adding each child node to HashMap key => value
//									map.put(TAG_PLAYER_ID, id);
//									map.put(TAG_PLAYER_NAME, name);
//	
//									// adding HashList to ArrayList
//									productsList.add(map);
//								}
//							}
//						} 
//					} 
//					catch (JSONException e) 
//					{
//						e.printStackTrace();
//					}
//	
//					return null;
//			
//				}		
//			}
//		
//		/**
//	     * Add a header {@code TableRow} to the {@code TableLayout} (styled)
//	     * 
//	     * @param scoreTable
//	     *            the {@code TableLayout} that the header row will be added to
//	     */
//	    private void initializeHeaderRow(TableLayout scoreTable) 
//	    {
//	        // Create the Table header row
//	        TableRow headerRow = new TableRow(this);
//	        int textColor = getResources().getColor(R.color.logo_color);
//	        float textSize = getResources().getDimension(R.dimen.score_heading_text_size);
//	        addTextToRowWithValues(headerRow, getResources().getString(R.string.username), textColor, textSize);
//	        addTextToRowWithValues(headerRow, getResources().getString(R.string.score), textColor, textSize);
//	        addTextToRowWithValues(headerRow, getResources().getString(R.string.rank), textColor, textSize);
//	        scoreTable.addView(headerRow);
//	    }
//	    
//	    /**
//	     * {@code insertScoreRow()} helper method -- Populate a {@code TableRow} with
//	     * three columns of {@code TextView} data (styled)
//	     * 
//	     * @param tableRow
//	     *            The {@code TableRow} the text is being added to
//	     * @param text
//	     *            The text to add
//	     * @param textColor
//	     *            The color to make the text
//	     * @param textSize
//	     *            The size to make the text
//	     */
//	    private void addTextToRowWithValues(final TableRow tableRow, String text, int textColor, float textSize) 
//	    {
//	        TextView textView = new TextView(this);
//	        textView.setTextSize(textSize);
//	        textView.setTextColor(textColor);
//	        textView.setText(text);
//	        tableRow.addView(textView);
//	    }
//  
//	
//		/**
//		 * After completing background task Dismiss the progress dialog
//		 * **/
//		protected void onPostExecute(String file_url) 
//		{
//			// dismiss the dialog after getting all products
//			pDialog.dismiss();
//			// updating UI from Background Thread
//			runOnUiThread(new Runnable() 
//			{
//				public void run() 
//				{
//					/**
//					 * Updating parsed JSON data into ListView
//					 * */
//					ListAdapter adapter = new SimpleAdapter(
//							ScoreDataActivity.this, scoresList,
//							R.layout.list_item, new String[] { TAG_PLAYER_ID,
//									TAG_PLAYER_NAME},
//							new int[] { R.id.player_id, R.id.player_name });
//					// updating listview
//					setListAdapter(adapter);
//				}
//			});		
//		}
	}}
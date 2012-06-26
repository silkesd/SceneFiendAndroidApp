package com.silke.sceneFiendAndroidApp;
import com.silke.sceneFiendAndroidApp.handlers.UserFunctions;


import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class MenuActivity extends SceneFiendAndroidAppActivity 
{
	UserFunctions userFunctions;
	Button btnLogout;
	
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        //the custom title font
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
           
        //the menu items 
        ListView menuList = (ListView) findViewById(R.id.ListView_Menu);
        String[] items = { getResources().getString(R.string.menu_item_play),
                getResources().getString(R.string.menu_item_scores),
                getResources().getString(R.string.menu_item_settings),
                getResources().getString(R.string.menu_item_help),
                getResources().getString(R.string.menu_item_login)};
        
    	//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
        
        //data adapter maps data to the layout templates specified via the array that stores the menu items
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.menu_item, items);
        menuList.setAdapter(adapt);
        menuList.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {        
        	//when an item is clicked a specified activity is launched
        	public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) 
        	{
        		//getting the text view and passing the text to that view
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                
                //if the menu item selected is menu_item_play launch the game activity
                if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_play))) 
                {
                    // Launch the Game Activity
                    startActivity(new Intent(MenuActivity.this, PlayVideo.class));
                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_help))) 
                {
                    // Launch the Help Activity
                    startActivity(new Intent(MenuActivity.this, HelpActivity.class));
                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_settings))) 
                {
                    // Launch the Settings Activity
                    startActivity(new Intent(MenuActivity.this, SettingsActivity.class));
                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_scores))) 
                {
                    // Launch the Scores Activity
                    startActivity(new Intent(MenuActivity.this, ScoreMenuActivity.class));
                }
//                else if (strText.equalsIgnoreCase(getResources().getString(R.string.menu_item_login))) 
//                {
//                    // Launch the Login Activity
//                    startActivity(new Intent(MenuActivity.this, LoginActivity.class));
//                }
			}
        });
        
        
        
        //userFunctions = new UserFunctions();
        //if(userFunctions.isUserLoggedIn(getApplicationContext()))
     //   {        	
        //	btnLogout = (Button) findViewById(R.id.btnLogout);
        //	btnLogout.setOnClickListener(new View.OnClickListener() 
        //	{
    			
    	//		public void onClick(View arg0) 
    	//		{
    				// TODO Auto-generated method stub
    	//			userFunctions.logoutUser(getApplicationContext());
    	//			Intent login = new Intent(getApplicationContext(), SceneFiendLoginActivity.class);
    	//        	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    	//        	startActivity(login);
    	        	// Closing menu screen
    	//        	finish();
    	//		}
    	//	});
        	
        //}
        //else
        //{
        	// user is not logged in show login screen
        //	Intent login = new Intent(getApplicationContext(), SceneFiendLoginActivity.class);
        //	login.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //	startActivity(login);
        	// Closing menu screen
        //	finish();
        //}  
    }  
    public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent i = new Intent(getApplicationContext(),
 						LoginActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				Log.d("MenuAct", "activity started");
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}
}
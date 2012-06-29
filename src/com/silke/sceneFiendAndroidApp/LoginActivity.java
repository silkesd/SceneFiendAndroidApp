package com.silke.sceneFiendAndroidApp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LoginActivity extends SceneFiendAndroidAppActivity
{
	
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_menu);
        
        
        //the custom title font
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
        
        
        isNetworkAvailable();
        
        //the login type menu items
        ListView loginList = (ListView) findViewById(R.id.ListView_Login);
        String[] loginItems = { 
                getResources().getString(R.string.login_item_scenefiendlogin),
        		getResources().getString(R.string.login_item_register)};
        
        //data adapter maps data to the layout templates specified via the array that stores the menu items
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.login_item, loginItems);
        loginList.setAdapter(adapt);
        loginList.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {        
        	//when an item is clicked a specified activity is launched
        	public void onItemClick(AdapterView<?> parent, View loginItemClicked, int position, long id) 
        	{
        		//getting the text view and passing the text to that view
                TextView textView = (TextView) loginItemClicked;
                String strText = textView.getText().toString();
                 
                //if the menu item selected is menu_item_play launch the game activity
                if (strText.equalsIgnoreCase(getResources().getString(R.string.login_item_scenefiendlogin))) 
                {
                    // Launch the Scene Fiend Login Activity
                    startActivity(new Intent(LoginActivity.this, SceneFiendLoginActivity.class));
                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.login_item_register))) 
                {
                    // Launch the Register Activity
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                } 
                
                
                
                
               
                   
                
//                if(GAME_PREFERENCES_WIFI == true || GAME_PREFERENCES_MOBILE == true)
//                { 
//                	Log.d("CHECKING NET ACCESS", "ARE TURNED ON");
//                	if (strText.equalsIgnoreCase(getResources().getString(R.string.login_item_twitterlogin))) 
//	                {
//	                    // Launch the Twitter Login Activity
//	                    startActivity(new Intent(LoginActivity.this, TwitterLoginActivity.class));
//	                } 
//                }
			}      	
        });       
    }  

    private boolean isNetworkAvailable() 
    {
    	Log.d("NOW IN", "IS NETWORK AVAILABLE");
        ConnectivityManager connectivityManager 
              = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        Log.d("WIFI IS ON", "ABOUT TO CALL ADDTWITTER FUNCITON");
        //Toast.makeText(this, "WIFI IS ON :)", Toast.LENGTH_LONG).show();
        
        activeNetworkInfo = GAME_PREFERENCES_WIFI_ON_OFF;
        return false;

    }
    
    
}

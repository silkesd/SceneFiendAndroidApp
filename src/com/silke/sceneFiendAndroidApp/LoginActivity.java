package com.silke.sceneFiendAndroidApp;

import com.silke.sceneFiendAndroidApp.R;
import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MenuItem;
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
        
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
       
        //the login type menu items
        ListView loginList = (ListView) findViewById(R.id.ListView_Login);
        String[] loginItems = { 
                getResources().getString(R.string.login_item_scenefiendlogin),
                getResources().getString(R.string.login_item_twitterlogin),
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
//                else if (strText.equalsIgnoreCase(getResources().getString(R.string.login_item_twitterlogin))) 
//                {
//                    // Launch the Twitter Login Activity
//                    startActivity(new Intent(LoginActivity.this, TwitterLoginActivity.class));
//                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.login_item_register))) 
                {
                    // Launch the Register Activity
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                } 
			}
        	
        });
        
    }  
    
    public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
				Intent i = new Intent(getApplicationContext(),
 						MenuActivity.class);
				i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 				startActivity(i);
 				Log.d("LoginAct", "activity started");
 				return true;
 			default:
 				return super.onOptionsItemSelected(item);
		}
	}
}

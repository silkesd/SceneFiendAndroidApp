package com.silke.sceneFiendAndroidApp;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ScoreMenuActivity extends SceneFiendAndroidAppActivity
{

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scores);
		
		//the custom title font
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
        
      //the menu items 
        ListView scoreMenuList = (ListView) findViewById(R.id.ListView_Menu);
        String[] items = { getResources().getString(R.string.high_scores),
                getResources().getString(R.string.all_scores),
                getResources().getString(R.string.my_scores)};
	
      //data adapter maps data to the layout templates specified via the array that stores the menu items
        ArrayAdapter<String> adapt = new ArrayAdapter<String>(this, R.layout.menu_item, items);
        scoreMenuList.setAdapter(adapt);
        scoreMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {        
        	//when an item is clicked a specified activity is launched
        	public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) 
        	{
        		//getting the text view and passing the text to that view
                TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString();
                
                //if the menu item selected is menu_item_play launch the game activity
                if (strText.equalsIgnoreCase(getResources().getString(R.string.all_scores))) 
                {
                    // Launch the Game Activity
                    startActivity(new Intent(ScoreMenuActivity.this, AllScoresActivity.class));
                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.my_scores))) 
                {
                    // Launch the Help Activity
                    startActivity(new Intent(ScoreMenuActivity.this, MyScoresActivity.class));
                } 
                else if (strText.equalsIgnoreCase(getResources().getString(R.string.high_scores))) 
                {
                    // Launch the Settings Activity
                    startActivity(new Intent(ScoreMenuActivity.this, HighScoresActivity.class));
                } 
        	}
        });	
    }	
}
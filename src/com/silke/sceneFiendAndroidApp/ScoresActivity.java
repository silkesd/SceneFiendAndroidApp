package com.silke.sceneFiendAndroidApp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class ScoresActivity extends SceneFiendAndroidAppActivity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scores);
        
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
        
        // Setting up the tabs in the layout id field
        TabHost host = (TabHost) findViewById(R.id.TabHost1);
        host.setup();
        
        // All Scores tab - adding a star by the tab title 
        TabSpec allScoresTab = host.newTabSpec("allTab");
        allScoresTab.setIndicator(getResources().getString(R.string.all_scores), getResources().getDrawable(
                android.R.drawable.star_on));
        allScoresTab.setContent(R.id.ScrollViewAllScores);
        host.addTab(allScoresTab);
        
        // My Scores tab - showing user scores
        TabSpec myScoresTab = host.newTabSpec("myTab");
        myScoresTab.setIndicator(getResources().getString(R.string.my_scores), getResources().getDrawable(
                android.R.drawable.star_on));
        myScoresTab.setContent(R.id.ScrollViewMyScores);
        host.addTab(myScoresTab);
        
        // High scores tab - the top scores
        TabSpec highScoresTab = host.newTabSpec("highscoresTab");
        highScoresTab.setIndicator(getResources().getString(R.string.high_scores), getResources().getDrawable(
                android.R.drawable.star_big_on));
        highScoresTab.setContent(R.id.ScrollViewHighScores);
        host.addTab(highScoresTab);
        
        // Set the default tab - the all scores tab
        host.setCurrentTabByTag("allTab");
    }
}
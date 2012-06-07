package com.silke.sceneFiendAndroidApp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


public class HelpActivity extends SceneFiendAndroidAppActivity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        
        Typeface tf = Typeface.createFromAsset(getAssets(),
                "fonts/lucindablack.ttf");
        TextView tv = (TextView) findViewById(R.id.CustomFont);
        tv.setTypeface(tf);
    }
}
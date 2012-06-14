package com.silke.sceneFiendAndroidApp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.silke.sceneFiendAndroidApp.R;

public class HelpActivity extends SceneFiendAndroidAppActivity 
{
	Button btnBack;

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
        
        btnBack = (Button) findViewById(R.id.btnBack);
        
        //reading string data from the text help file - if it does not work, send to debug list, if it does work should convert the raw
        //data input stream to a sting and output in the layout id field
        InputStream iFile = getResources().openRawResource(R.raw.help);
        try 
        {
            TextView helpText = (TextView) findViewById(R.id.TextView_HelpText);
            String strFile = inputStreamToString(iFile);
            helpText.setText(strFile);
        } catch (Exception e) {
            Log.e(DEBUG_TAG, "InputStreamToString failure", e);
        }
        
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
    
    @SuppressWarnings("deprecation")
	public String inputStreamToString(InputStream is) throws IOException 
    {
        StringBuffer sBuffer = new StringBuffer();
        DataInputStream dataIO = new DataInputStream(is);
        String strLine = null;
        while ((strLine = dataIO.readLine()) != null) 
        {
            sBuffer.append(strLine + "\n");
        }
        dataIO.close();
        is.close();
        return sBuffer.toString();
    }
}
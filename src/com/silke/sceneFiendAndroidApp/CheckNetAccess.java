package com.silke.sceneFiendAndroidApp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class CheckNetAccess extends SceneFiendAndroidAppActivity
{
	

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checknet);
        Button netB = (Button) findViewById(R.id.netbutton);
        netB.setOnClickListener(new OnClickListener() 
        {

            public void onClick(View v) 
            {
                // TODO Auto-generated method stub
                haveNetworkConnection();
                if ((GAME_PREFERENCES_WIFI)|| (GAME_PREFERENCES_MOBILE))
                {
                    Toast.makeText(CheckNetAccess.this, "Internet is on!", 3000).show();
                    Intent i = new Intent(CheckNetAccess.this, TwitterLoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(CheckNetAccess.this, "No access to Internet..please try again", 3000).show();
                }
            }
        });
    }

    boolean haveNetworkConnection() 
    {
    	GAME_PREFERENCES_WIFI = false;
        GAME_PREFERENCES_MOBILE = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) 
        {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                	GAME_PREFERENCES_WIFI = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                	GAME_PREFERENCES_MOBILE = true;
        }
        return GAME_PREFERENCES_WIFI || GAME_PREFERENCES_MOBILE;
    }
    @Override
    protected void onResume() 
    {
        // TODO Auto-generated method stub
        super.onResume();
    }

}

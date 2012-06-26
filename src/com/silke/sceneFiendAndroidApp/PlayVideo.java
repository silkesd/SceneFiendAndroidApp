package com.silke.sceneFiendAndroidApp;

import java.io.IOException;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


public class PlayVideo extends GameActivity implements SurfaceHolder.Callback 
{
    private MediaPlayer mp = null;
    //...
    SurfaceView mSurfaceView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);

        mp = new MediaPlayer();
        mSurfaceView = (SurfaceView) findViewById(R.id.surface);
        //...
    }

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) 
	{
		// TODO Auto-generated method stub
		
	}

	public void surfaceCreated(SurfaceHolder holder) 
	{

	    try {
			mp.setDataSource("android.resource://com.silke.sceneFiendAndroidApp/raw/despicable_me_pre.mp4");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			mp.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    //Get the dimensions of the video
	    int videoWidth = mp.getVideoHeight();
	    int videoHeight = mp.getVideoWidth();

	    //Get the width of the screen
	    int screenWidth = getWindowManager().getDefaultDisplay().getWidth();

	    //Get the SurfaceView layout parameters
	    android.view.ViewGroup.LayoutParams lp = mSurfaceView.getLayoutParams();

	    //Set the width of the SurfaceView to the width of the screen
	    lp.width = screenWidth;

	    //Set the height of the SurfaceView to match the aspect ratio of the video 
	    //be sure to cast these as floats otherwise the calculation will likely be 0
	    lp.height = (int) (((float)videoHeight / (float)videoWidth) * (float)screenWidth);

	    //Commit the layout parameters
	    mSurfaceView.setLayoutParams(lp);        

	    //Start video
	    mp.start();
	}

	public void surfaceDestroyed(SurfaceHolder holder) 
	{
		// TODO Auto-generated method stub
		
	}
}
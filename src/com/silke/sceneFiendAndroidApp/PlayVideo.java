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




//// Create a new shared preference object to remember if the user has
//// already given us permission
//mPrefs = getSharedPreferences("twitterPrefs", MODE_PRIVATE);
//Log.i(TAG, "GOT PREFS");
//
//// Load the twitter4j helper
//mTwitter = new TwitterFactory().getInstance();
//Log.i(TAG, "GOT TWITTER4J");
//
//// Tell twitter4j that we want to use it with our app
//mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
//Log.i(TAG, "Inflated Twitter4j");
//
//btnLogin = (Button) findViewById(R.id.btnLogin);
//
//
////// Listening to register new account link
////registerScreen.setOnClickListener(new View.OnClickListener() {
////	
////	public void onClick(View v) {
////		// Switching to Register screen
////		Intent i = new Intent(getApplicationContext(), TwitterRegisterActivity.class);
////		startActivity(i);
////	}
////});
//}

//public void buttonTwitterLogin(View v) 
//{
//Log.i(TAG, "Login Pressed");
//if (mPrefs.contains(PREF_ACCESS_TOKEN)) 
//{
//      Log.i(TAG, "Repeat User");
//      loginAuthorisedUser();
//} 
//else 
//{
//      Log.i(TAG, "New User");
//      loginNewTwitterUser();
//}
//}
//
//public void buttonTweet(View v) 
//{
//Log.i(TAG, "Tweet Pressed");
//tweetMessage();
//}

/**
* Create a request that is sent to Twitter asking 'can our app have permission to use Twitter for this user'</br>
* We are given back the {@link mReqToken}
* that is a unique indetifier to this request</br>
* The browser then pops up on the twitter website and the user logins in ( we never see this informaton
* )</br> Twitter then redirects us to {@link CALLBACK_URL} if the login was a success</br>
*
*/
//private void loginNewTwitterUser() 
//{
//this.runOnUiThread(new Runnable() 
//{
//	public void run()
//	{
//		try 
//  	{
//      	Log.i(TAG, "Request App Authentication");
//      	mReqToken = mTwitter.getOAuthRequestToken(CALLBACK_URL);
//
//      	Log.i(TAG, "Starting Webview to login to twitter");
//      	WebView twitterSite = new WebView(this);
//      	twitterSite.requestFocus(View.FOCUS_DOWN);
//      	twitterSite.setOnTouchListener(new View.OnTouchListener() 
//      	{
//	        	public boolean onTouch(View v, MotionEvent event) 
//	        	{
//		        	switch (event.getAction()) 
//		        	{
//		        		case MotionEvent.ACTION_DOWN:
//		        		case MotionEvent.ACTION_UP:
//		        		if (!v.hasFocus()) 
//		        		{
//				        	v.requestFocus();
//			        	}
//		        			break;
//			        }
//		        	return false;
//	        	}
//      	});
//      	twitterSite.loadUrl(mReqToken.getAuthenticationURL());
//      	setContentView(twitterSite);
//  	} 
//		catch (TwitterException e) 
//		{
////			Toast.makeText(this, "Twitter Login error, try again later",
////			Toast.LENGTH_SHORT).show();
//		}
//	} 
//}; runOnUiThread(runnable); 
//}
//
/**
* The user had previously given our app permission to use Twitter</br>
* Therefore we retrieve these credentials and fill out the Twitter4j helper
*/
//private void loginAuthorisedUser() 
//{
//String token = mPrefs.getString(PREF_ACCESS_TOKEN, null);
//String secret = mPrefs.getString(PREF_ACCESS_TOKEN_SECRET, null);
//
//// Create the twitter access token from the credentials we got previously
//AccessToken at = new AccessToken(token, secret);
//
//mTwitter.setOAuthAccessToken(at);
//      
//Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
//
//enableTweetButton();
//}

/**
* Catch when Twitter redirects back to our {@link CALLBACK_URL}</br>
* We use onNewIntent as in our manifest we have singleInstance="true" if we did not the
* getOAuthAccessToken() call would fail
*/
//@Override
//protected void onNewIntent(Intent intent) 
//{
//super.onNewIntent(intent);
//Log.i(TAG, "New Intent Arrived");
//dealWithTwitterResponse(intent);
//}
//
//@Override
//protected void onResume() 
//{
//super.onResume();
//Log.i(TAG, "Arrived at onResume");
//}

/**
* Twitter has sent us back into our app</br>
* Within the intent it set back we have a 'key' we can use to authenticate the user
*
* @param intent
*/
//private void dealWithTwitterResponse(Intent intent) 
//{
//Uri uri = intent.getData();
//if (uri != null && uri.toString().startsWith(CALLBACK_URL)) 
//{ 
//	// If the user has just logged in
//  String oauthVerifier = uri.getQueryParameter("oauth_verifier"); 
//  authoriseNewUser(oauthVerifier);
//}
//}

/**
* Create an access token for this new user</br>
* Fill out the Twitter4j helper</br>
* And save these credentials so we can log the user straight in next time
*
* @param oauthVerifier
*/
//private void authoriseNewUser(String oauthVerifier) 
//{
//try 
//{
//  AccessToken at = mTwitter.getOAuthAccessToken(mReqToken, oauthVerifier);
//  mTwitter.setOAuthAccessToken(at);
//
//  saveAccessToken(at);
//
//  // Set the content view back after we changed to a webview
//  setContentView(R.layout.twitter_login);
//          
//  enableTweetButton();
//} 
//catch (TwitterException e) 
//{
//  Toast.makeText(this, "Twitter auth error x01, try again later", Toast.LENGTH_SHORT).show();
//}
//}

/**
* Allow the user to Tweet
*/
//private void enableTweetButton() 
//{
//Log.i(TAG, "User logged in - allowing to tweet");
//btnLogin.setEnabled(false);
//tweet.setEnabled(true);
//}

/**
* Send a tweet on your timeline, with a Toast msg for success or failure
*/
//private void tweetMessage() 
//{
//try 
//{
//  mTwitter.updateStatus("Test - Tweeting with @Blundell_apps #AndroidDev Tutorial using #Twitter4j http://blog.blundell-apps.com/sending-a-tweet");
//
//  Toast.makeText(this, "Tweet Successful!", Toast.LENGTH_SHORT).show();
//} 
//catch (TwitterException e) 
//{
//  Toast.makeText(this, "Tweet error, try again later", Toast.LENGTH_SHORT).show();
//}
//}
//
//private void saveAccessToken(AccessToken at) 
//{
//String token = at.getToken();
//String secret = at.getTokenSecret();
//Editor editor = mPrefs.edit();
//editor.putString(PREF_ACCESS_TOKEN, token);
//editor.putString(PREF_ACCESS_TOKEN_SECRET, secret);
//editor.commit();
//}
//
//

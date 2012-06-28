package com.silke.sceneFiendAndroidApp;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class TwitterLoginActivity extends SceneFiendAndroidAppActivity
{
	
	private int TWITTER_AUTH;
    private Twitter mTwitter;
    private RequestToken mRequestToken;

    private String accessToken;
    private String accessTokenSecret;

    private boolean haveConnectedWifi;
    private boolean haveConnectedMobile;
    private double x;
	    
//	private static final String TAG = "Blundell.TwitterLoginActivity";
//	
//	/** Name to store the users access token */
//	private static final String PREF_ACCESS_TOKEN = "accessToken";
//	/** Name to store the users access token secret */
//	private static final String PREF_ACCESS_TOKEN_SECRET = "accessTokenSecret";
//	/** Consumer Key generated when you registered your app at https://dev.twitter.com/apps/ */
//	private static final String CONSUMER_KEY = "0hNpshvKYcuNMVadMHQA";
//	/** Consumer Secret generated when you registered your app at https://dev.twitter.com/apps/  */
//	private static final String CONSUMER_SECRET = "cmGBxiMLl3UKH5vUD7ekcjdLrheSFHf0ziKBehxiyQ"; // XXX Encode in your app
//	/** The url that Twitter will redirect to after a user log's in - this will be picked up by your app manifest and redirected into this activity */
//	private static final String CALLBACK_URL = "tweet-to-twitter-blundell-01-android";
//	private SharedPreferences mPrefs;
//	
//	
//	/** The request token signifies the unique ID of the request you are sending to twitter  */
//	private RequestToken mReqToken;
//	
//	
//	
	private Button btnLogin;
//	private Button tweet;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twitter_login);
       // TextView tweet = (TextView) findViewById(R.id.tweet);
        
		//actionbar
		ActionBar ab = getActionBar();
		ab.setDisplayHomeAsUpEnabled(true);
		
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        accessToken = settings.getString( "twitter_access_token", null );
        accessTokenSecret = settings.getString( "twitter_access_token_secret", null );
       
        //CHANGED FROM BTWEET BUTTON
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new OnClickListener() 
        {
        	 public void onClick(View v) 
        	 {
                 // TODO Auto-generated method stub
                 updateStatus();
             }
        });     
	}

	private boolean haveNetworkConnection() 
	{
	    haveConnectedWifi = false;
	    haveConnectedMobile = false;
	
	    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo[] netInfo = cm.getAllNetworkInfo();
	    for (NetworkInfo ni : netInfo) 
	    {
	        if (ni.getTypeName().equalsIgnoreCase("WIFI"))
	            if (ni.isConnected())
	                haveConnectedWifi = true;
	        if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
	            if (ni.isConnected())
	                haveConnectedMobile = true;
	    }
	    return haveConnectedWifi || haveConnectedMobile;
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
        // TODO Auto-generated method stub

        if (requestCode == TWITTER_AUTH)
        {

            if (resultCode == Activity.RESULT_OK)
            {
                Toast.makeText(this, "Hey Ho", 300).show();
                String oauthVerifier = (String) data.getExtras().get("oauth_verifier");

                AccessToken at = null;

                try
                {
                    // Pair up our request with the response
                    at = mTwitter.getOAuthAccessToken(oauthVerifier);
                    String theToken = at.getToken();
                    // Toast.makeText(this, theToken, 10000).show();
                    String theTokenSecret = at.getTokenSecret();
                    // Toast.makeText(this, theTokenSecret, 10000).show();
                    SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
                    settings = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("twitter_access_token",theToken);
                    editor.putString("twitter_access_token_secret", theTokenSecret);
                    editor.commit();

                    GAME_PREFERENCES_TWITTER_LOGGED_IN = true;
                    updateStatus();
                }
                catch (TwitterException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            Toast.makeText(this, "uh oh, Spaghetti Os", 300).show();
        
        }

    }
    private void updateStatus() 
    {
        // TODO Auto-generated method stub
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        accessToken = settings.getString( "twitter_access_token", null );
        accessTokenSecret = settings.getString( "twitter_access_token_secret", null );
        // Toast.makeText(this, accessToken, 10000).show();
        // Toast.makeText(this, accessTokenSecret, 10000).show();
        haveNetworkConnection();
        if ((haveConnectedWifi == true)|| (haveConnectedMobile == true))
        {
            ConfigurationBuilder builder = new ConfigurationBuilder();
            builder.setOAuthConsumerKey(CONSUMER_KEY);
            builder.setOAuthConsumerSecret(CONSUMER_SECRET);
            builder.setOAuthAccessToken(accessToken);
            builder.setOAuthAccessTokenSecret(accessTokenSecret);
            Configuration conf = builder.build();
            Twitter t = new TwitterFactory(conf).getInstance();

            try 
            {
                x = Math.random();
                t.updateStatus( "Yo ho ho and a bottle of rum " + x);
            }
            catch (TwitterException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            Toast.makeText(this, "No access to Internet..please try again", 3000).show();
        }
    }
    @Override
    protected void onResume() 
    {
        // TODO Auto-generated method stub
        super.onResume();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);

        accessToken = settings.getString( "twitter_access_token", null );
        accessTokenSecret = settings.getString( "twitter_access_token_secret", null );
        haveNetworkConnection();
        if ((haveConnectedWifi == true)|| (haveConnectedMobile == true))
        {
            // Toast.makeText(this, "Internet is go!", 3000).show();
            // Intent i = new Intent(this, AndroidBasicOAuthActivity.class);
            // startActivity(i);

            if ((accessToken==null)||(accessTokenSecret==null)){
                mTwitter = new TwitterFactory().getInstance();
                mRequestToken = null;
                mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET );
                String callbackURL = getResources().getString(R.string.twitter_callback);
                try
                {
                	StrictMode.enableDefaults();
                    mRequestToken = mTwitter.getOAuthRequestToken(callbackURL);
                }
                catch (TwitterException e)
                {
                    e.printStackTrace();
                }
                Intent i = new Intent(this, TwitterWebviewActivity.class);
                i.putExtra("URL", mRequestToken.getAuthenticationURL());
                startActivityForResult(i, TWITTER_AUTH);
            }
            else
            {
                GAME_PREFERENCES_TWITTER_LOGGED_IN = true;
                Toast.makeText(this, "Hello Again..You're logged in", 3000).show();
                // updateStatusLoggedIn();
            }
        }
        else
        {	
            Toast.makeText(this, "No access to Internet..please try again", 3000).show();
            Intent i = new Intent(this, CheckNetAccess.class);
            startActivity(i);
        }
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
 				Log.d("TwitterLoginAct", "activity started");
 				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}       
        
//		// Create a new shared preference object to remember if the user has
//        // already given us permission
//		mPrefs = getSharedPreferences("twitterPrefs", MODE_PRIVATE);
//		Log.i(TAG, "GOT PREFS");
//		
//		// Load the twitter4j helper
//		mTwitter = new TwitterFactory().getInstance();
//		Log.i(TAG, "GOT TWITTER4J");
//		
//		// Tell twitter4j that we want to use it with our app
//        mTwitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
//        Log.i(TAG, "Inflated Twitter4j");
//        
//        btnLogin = (Button) findViewById(R.id.btnLogin);
//        tweet = (Button) findViewById(R.id.tweet);
//		
////        // Listening to register new account link
////        registerScreen.setOnClickListener(new View.OnClickListener() {
////			
////			public void onClick(View v) {
////				// Switching to Register screen
////				Intent i = new Intent(getApplicationContext(), TwitterRegisterActivity.class);
////				startActivity(i);
////			}
////		});
	//}
    
//    public void buttonTwitterLogin(View v) 
//    {
//        Log.i(TAG, "Login Pressed");
//        if (mPrefs.contains(PREF_ACCESS_TOKEN)) 
//        {
//                Log.i(TAG, "Repeat User");
//                loginAuthorisedUser();
//        } 
//        else 
//        {
//                Log.i(TAG, "New User");
//                loginNewTwitterUser();
//        }
//    }
//    
//    public void buttonTweet(View v) 
//    {
//        Log.i(TAG, "Tweet Pressed");
//        tweetMessage();
//    }
    
    /**
     * Create a request that is sent to Twitter asking 'can our app have permission to use Twitter for this user'</br>
     * We are given back the {@link mReqToken}
     * that is a unique indetifier to this request</br>
     * The browser then pops up on the twitter website and the user logins in ( we never see this informaton
     * )</br> Twitter then redirects us to {@link CALLBACK_URL} if the login was a success</br>
     *
     */
//    private void loginNewTwitterUser() 
//    {
//    	this.runOnUiThread(new Runnable() 
//		{
//			public void run()
//	    	{
//	    		try 
//		    	{
//		        	Log.i(TAG, "Request App Authentication");
//		        	mReqToken = mTwitter.getOAuthRequestToken(CALLBACK_URL);
//		
//		        	Log.i(TAG, "Starting Webview to login to twitter");
//		        	WebView twitterSite = new WebView(this);
//		        	twitterSite.requestFocus(View.FOCUS_DOWN);
//		        	twitterSite.setOnTouchListener(new View.OnTouchListener() 
//		        	{
//			        	public boolean onTouch(View v, MotionEvent event) 
//			        	{
//				        	switch (event.getAction()) 
//				        	{
//				        		case MotionEvent.ACTION_DOWN:
//				        		case MotionEvent.ACTION_UP:
//				        		if (!v.hasFocus()) 
//				        		{
//						        	v.requestFocus();
//					        	}
//				        			break;
//					        }
//				        	return false;
//			        	}
//		        	});
//		        	twitterSite.loadUrl(mReqToken.getAuthenticationURL());
//		        	setContentView(twitterSite);
//		    	} 
//				catch (TwitterException e) 
//				{
////					Toast.makeText(this, "Twitter Login error, try again later",
////					Toast.LENGTH_SHORT).show();
//				}
//	    	} 
//    	}; runOnUiThread(runnable); 
//	}
//  
        /**
     * The user had previously given our app permission to use Twitter</br>
     * Therefore we retrieve these credentials and fill out the Twitter4j helper
     */
//    private void loginAuthorisedUser() 
//    {
//        String token = mPrefs.getString(PREF_ACCESS_TOKEN, null);
//        String secret = mPrefs.getString(PREF_ACCESS_TOKEN_SECRET, null);
//  
//        // Create the twitter access token from the credentials we got previously
//        AccessToken at = new AccessToken(token, secret);
//  
//        mTwitter.setOAuthAccessToken(at);
//                
//        Toast.makeText(this, "Welcome back", Toast.LENGTH_SHORT).show();
//        
//        enableTweetButton();
//    }
  
        /**
     * Catch when Twitter redirects back to our {@link CALLBACK_URL}</br>
     * We use onNewIntent as in our manifest we have singleInstance="true" if we did not the
     * getOAuthAccessToken() call would fail
     */
//    @Override
//    protected void onNewIntent(Intent intent) 
//    {
//        super.onNewIntent(intent);
//        Log.i(TAG, "New Intent Arrived");
//        dealWithTwitterResponse(intent);
//    }
//  
//    @Override
//    protected void onResume() 
//    {
//        super.onResume();
//        Log.i(TAG, "Arrived at onResume");
//    }
    
    /**
     * Twitter has sent us back into our app</br>
     * Within the intent it set back we have a 'key' we can use to authenticate the user
     *
     * @param intent
     */
//    private void dealWithTwitterResponse(Intent intent) 
//    {
//        Uri uri = intent.getData();
//        if (uri != null && uri.toString().startsWith(CALLBACK_URL)) 
//        { 
//        	// If the user has just logged in
//            String oauthVerifier = uri.getQueryParameter("oauth_verifier"); 
//            authoriseNewUser(oauthVerifier);
//        }
//    }
  
    /**
     * Create an access token for this new user</br>
     * Fill out the Twitter4j helper</br>
     * And save these credentials so we can log the user straight in next time
     *
     * @param oauthVerifier
     */
//    private void authoriseNewUser(String oauthVerifier) 
//    {
//        try 
//        {
//            AccessToken at = mTwitter.getOAuthAccessToken(mReqToken, oauthVerifier);
//            mTwitter.setOAuthAccessToken(at);
//  
//            saveAccessToken(at);
//  
//            // Set the content view back after we changed to a webview
//            setContentView(R.layout.twitter_login);
//                    
//            enableTweetButton();
//        } 
//        catch (TwitterException e) 
//        {
//            Toast.makeText(this, "Twitter auth error x01, try again later", Toast.LENGTH_SHORT).show();
//        }
//    }
  
        /**
     * Allow the user to Tweet
     */
//    private void enableTweetButton() 
//    {
//        Log.i(TAG, "User logged in - allowing to tweet");
//        btnLogin.setEnabled(false);
//        tweet.setEnabled(true);
//    }
  
        /**
     * Send a tweet on your timeline, with a Toast msg for success or failure
     */
//    private void tweetMessage() 
//    {
//        try 
//        {
//            mTwitter.updateStatus("Test - Tweeting with @Blundell_apps #AndroidDev Tutorial using #Twitter4j http://blog.blundell-apps.com/sending-a-tweet");
//  
//            Toast.makeText(this, "Tweet Successful!", Toast.LENGTH_SHORT).show();
//        } 
//        catch (TwitterException e) 
//        {
//            Toast.makeText(this, "Tweet error, try again later", Toast.LENGTH_SHORT).show();
//        }
//    }
//  
//    private void saveAccessToken(AccessToken at) 
//    {
//        String token = at.getToken();
//        String secret = at.getTokenSecret();
//        Editor editor = mPrefs.edit();
//        editor.putString(PREF_ACCESS_TOKEN, token);
//        editor.putString(PREF_ACCESS_TOKEN_SECRET, secret);
//        editor.commit();
//    }
//    
//	



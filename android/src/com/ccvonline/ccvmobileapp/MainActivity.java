package com.ccvonline.ccvmobileapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import org.apache.cordova.*;
import com.localytics.android.*;

public class MainActivity extends DroidGap {

	private LocalyticsSession localyticsSession;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.appView.getSettings().setPluginsEnabled(true);
        //
        super.setIntegerProperty("splashscreen", R.drawable.splash);
        //super.appView.getSettings().setPluginsEnabled(true);
        super.loadUrl("file:///android_asset/www/index.html", 5000);
        
        

        
        // setup localytics object
        this.localyticsSession = new LocalyticsSession(
        		this.getApplicationContext(),  
                "d7089dd03cf23289031ecd7-e6933f0a-011b-11e2-54de-00ef75f32667");       

        this.localyticsSession.open();               
        this.localyticsSession.upload(); 
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    public void onResume()
    {
        super.onResume();
        this.localyticsSession.open();
    }
    
    @Override
    public void onPause()
    {
        this.localyticsSession.close();
        this.localyticsSession.upload();
        super.onPause();
    }
}

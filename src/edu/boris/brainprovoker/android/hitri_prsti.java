package edu.boris.brainprovoker.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.Window;

public class hitri_prsti extends Activity {
    /** Called when the activity is first created. */
	public Brain_provoker_app app;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        app = (Brain_provoker_app) getApplication();
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Panel igra=new Panel(this, metrics.widthPixels, metrics.heightPixels);
        setContentView(igra);
    }
    
	public void gotoscores() {
		Intent nov2 = new Intent(this, ScoreListActivity.class);
		this.startActivity(nov2);
	}
	public String getName()
	{
		SharedPreferences settings =  PreferenceManager.getDefaultSharedPreferences(app); 
		return settings.getString("PREF_NAME_DEFAULT","");
	}
}
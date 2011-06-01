package edu.boris.brainprovoker.android;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class MenuPreferences extends PreferenceActivity {
	public static final String TAG = "MenuPreferences";
	SharedPreferences prefs;
	Brain_provoker_app app;
	public static final String PREF_ISOK="ISOK";
	public static final String PREF_MAX_UGIBANJ = "MAX_STEVILO_UGIBANJ";
	public static final String PREF_NAME_DEFAULT = "PREF_NAME_DEFAULT";
	public static final String PREF_TEZAVNOST = "PREF_TEZAVNOST";
	public static boolean isOK=true;
	public static int max_ugibanj=5;
	public static String default_name="name";
	public static String default_tezavnost="0-10";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (Brain_provoker_app) this.getApplication();
		addPreferencesFromResource(R.xml.menu_preferences);
	}
	@Override
	public void onPause() {
		super.onPause();
		SharedPreferences settings =  PreferenceManager.getDefaultSharedPreferences(app); 
		app.player.ime=settings.getString(PREF_NAME_DEFAULT,"");
		//Toast.makeText(this, app.player.ime, Toast.LENGTH_LONG).show();
	}
}
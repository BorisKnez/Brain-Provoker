package edu.boris.brainprovoker.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;


public class Brain_provoker_main extends Activity implements OnClickListener
{
	//globalne deklaracije spremenljivk
	private static final int SPOMIN = 1;  //za nov activity
	public static final String PREF_NAME="SETTINGS";  //za ime nastavitev
	public static final String NAME="PLAYER_NAME"; //za ime spremenljivke v nastavitvah
	public static final String SCORE="PLAYER_SCORE"; //-||-
	Brain_provoker_app app;
	private AlphaAnimation alphaDown,alphaUp;
	ImageButton new_game;
	Menu mMenu;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        app=(Brain_provoker_app) getApplication();
        new_game=(ImageButton)findViewById(R.id.btn_new_game);
        
        new_game.setOnClickListener(this);
    }
    
    public void onClick(View arg0){
    	alphaDown = new AlphaAnimation(1.0f, 0.3f);
        alphaUp = new AlphaAnimation(0.3f, 1.0f);
        alphaDown.setDuration(1000);
        alphaUp.setDuration(1000);
        alphaDown.setFillAfter(true);
        alphaUp.setFillAfter(true);
        /////////////////////nova igra////////////////////////////////
    	if (arg0.getId()==R.id.btn_new_game)
    	{
    		new_game.startAnimation(alphaDown);
    		Intent newgame=new Intent(this,spomin.class);
    		new_game.startAnimation(alphaUp);
    		this.startActivityForResult(newgame, SPOMIN);
    	}
    	
    	alphaDown.reset();
    	alphaUp.reset();
    }
    
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	SharedPreferences settings=getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    	app.player_name=settings.getString(NAME, "player1");
    	app.player_score=settings.getInt(SCORE, 0);
    }
    
    public void onPause()
    {
    	super.onPause();
    	SharedPreferences settings=getSharedPreferences(PREF_NAME, 0);
    	SharedPreferences.Editor editor=settings.edit();
    	editor.putString(NAME, app.player_name);
    	editor.putInt(SCORE, app.player_score);
    	editor.commit();
    	
    }
    
    
  ////////////////////////////////menu///////////////////////////////
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mMenu = menu;
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, mMenu);
		return true;
	}
	/*@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogExit:
			showDialog(EXIT_DIALOG);
			return true;
		case R.id.itemSettings:
			Intent i = new Intent();
			i.setClass(this, MenuPreferences.class);
			startActivityForResult(i, R.id.itemSettings);
			return true;
		case R.id.dialogNewGame:
			NovaIgra();
			return true;
		case R.id.dialogRezultati:
			Intent nov2=new Intent(this,RezultatListActivity.class);
			this.startActivityForResult(nov2, TEST_LIST_ACTIVITY_ID);
			return true;

		default:// Generic catch all for all the other menu resources
			if (!item.hasSubMenu()) {
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT)
				.show();
				return true;
			}
			break;
		}

		return false;
	}*/
	////menu^^^//////
    
    
    
    
    
    
}
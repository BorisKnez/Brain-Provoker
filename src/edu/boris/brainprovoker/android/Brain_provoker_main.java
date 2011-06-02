package edu.boris.brainprovoker.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.Toast;


public class Brain_provoker_main extends Activity implements OnClickListener
{
	//globalne deklaracije spremenljivk
	private static final int SPOMIN = 1;  //za nov activity
	//private static final int BARVE = 2;  //za nov activity
	public static final String PREF_NAME="SETTINGS";  //za ime nastavitev
	public static final String NAME="PLAYER_NAME"; //za ime spremenljivke v nastavitvah
	public static final String SCORE="PLAYER_SCORE"; //-||-
	private static final int EXIT_DIALOG = 0;
	Brain_provoker_app app;
	private AlphaAnimation alphaDown,alphaUp;
	ImageButton new_game, settings, instructions, exit_game;
	Menu mMenu;
	//int sirina_zaslona, visina_zaslona;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //DisplayMetrics metrics = new DisplayMetrics();
        //getWindowManager().getDefaultDisplay().getMetrics(metrics);
        //sirina_zaslona=metrics.widthPixels;
        //visina_zaslona=metrics.heightPixels;
        
        app=(Brain_provoker_app) getApplication();
        new_game=(ImageButton)findViewById(R.id.btn_new_game);
        settings=(ImageButton)findViewById(R.id.btn_settings);
        instructions=(ImageButton)findViewById(R.id.btn_instructions);
        exit_game=(ImageButton)findViewById(R.id.btn_quit);
        
        new_game.setOnClickListener(this);
        settings.setOnClickListener(this);
        instructions.setOnClickListener(this);
        exit_game.setOnClickListener(this);

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
    		//Intent newgame=new Intent(this,ujemanje_barv.class);
    		//this.startActivityForResult(newgame, BARVE);
    	}
    	else
    	if (arg0.getId()==R.id.btn_quit)
    	{
    		exit_game.startAnimation(alphaDown);
    		showDialog(EXIT_DIALOG);
    		exit_game.startAnimation(alphaUp);
    	}
    	else
        	if (arg0.getId()==R.id.btn_settings)
        	{
        		settings.startAnimation(alphaDown);
        		Intent i = new Intent();
    			i.setClass(this, MenuPreferences.class);
    			startActivity(i);
    			settings.startAnimation(alphaUp);
        	}
        	else
            	if (arg0.getId()==R.id.btn_instructions)
            	{
            		instructions.startAnimation(alphaDown);
            		/*Intent i = new Intent();
        			i.setClass(this, Instructions.class);
        			startActivity(i);*/

            		Intent i = new Intent();
        			i.setClass(this, OnlineBaza.class);
        			startActivity(i);
        			instructions.startAnimation(alphaUp);
            	}
    	
    	alphaDown.reset();
    	alphaUp.reset();
    }
    
    
    @Override
    public void onResume()
    {
    	super.onResume();
    	SharedPreferences settings=getSharedPreferences(PREF_NAME, MODE_PRIVATE);
    	app.player.ime=settings.getString(NAME, "player1");
    	app.player.score=settings.getInt(SCORE, 0);
    }
    
    public void onPause()
    {
    	super.onPause();
    	SharedPreferences settings=getSharedPreferences(PREF_NAME, 0);
    	SharedPreferences.Editor editor=settings.edit();
    	editor.putString(NAME, app.player.ime);
    	editor.putInt(SCORE, app.player.score);
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
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.dialogExit:
			showDialog(EXIT_DIALOG);
			return true;
		case R.id.itemSettings:
			Intent i = new Intent();
			i.setClass(this, MenuPreferences.class);
			startActivity(i);
			return true;
		case R.id.dialogRezultati:
			Intent nov2=new Intent(this,ScoreListActivity.class);
			this.startActivity(nov2);
			return true;

		default:// Generic catch all for all the other menu resources
			if (!item.hasSubMenu()) {
				Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
				return true;
			}
			break;
		}

		return false;
	}
	////menu^^^//////
    
    
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	
//////dialogi////////////////
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		switch (id) {
		case EXIT_DIALOG:
			builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you sure you want to quit?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					finish();
				}
				
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
				}
			});
			return builder.create();
		}
		return null;
	}
   ////////////////////////////////
    
    
    
}
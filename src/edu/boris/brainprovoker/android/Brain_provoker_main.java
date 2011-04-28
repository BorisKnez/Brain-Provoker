package edu.boris.brainprovoker.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Brain_provoker_main extends Activity 
{
	//globalne deklaracije spremenljivk
	private static final int SPOMIN = 1;  //za nov activity
	Brain_provoker_app app;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        app=(Brain_provoker_app) getApplication();
    }
    
    public void NovaIgra(View v)
    {
    	Intent newgame=new Intent(this,spomin.class);
		this.startActivityForResult(newgame, SPOMIN);
    }
    
    
    
    
    
}
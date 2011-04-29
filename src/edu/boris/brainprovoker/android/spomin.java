package edu.boris.brainprovoker.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;
import android.widget.TextView;


public class spomin extends Activity implements OnClickListener {
	
	enum gumbi{
		rdec, //0
		moder, //1
		rumen, //2
		zelen  //3
	}
	///status igre koncan ali poteka
	enum status_igre{
		poteka, 
		koncan, 
	}
	
	Random rand=new Random();
	private AlphaAnimation alphaDown,alphaUp;        		//animacija za gumbke
	private static final int START_DIALOG = 1;              //start dialog
	Brain_provoker_app app;									//skupen app vseh
	ImageButton rdec,moder,rumen,zelen;
	TextView prikaz_stopnje,txtspodaj;
	
	public List<gumbi> zaporedje=new ArrayList<gumbi>();           //polje zaporedja
	status_igre status;				//status igre
	int korak=0;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spomin);
        
        app=(Brain_provoker_app) getApplication();
        status=status_igre.poteka;
        //povezemo gumbe
        rdec = (ImageButton)findViewById(R.id.btnRdec);
        moder = (ImageButton)findViewById(R.id.btnModer);
        rumen = (ImageButton)findViewById(R.id.btnRumen);
        zelen = (ImageButton)findViewById(R.id.btnZelen);
        prikaz_stopnje=(TextView)findViewById(R.id.txtPrikazZgoraj);
        txtspodaj=(TextView)findViewById(R.id.txtPrikazSpodaj);
        
        rdec.setOnClickListener(this);
        moder.setOnClickListener(this);
        rumen.setOnClickListener(this);
        zelen.setOnClickListener(this);
        
        onemogocigumbe();
        showDialog(START_DIALOG);
    }
    
    @Override
    public void onClick(View arg0)
    {
    	alphaDown = new AlphaAnimation(1.0f, 0.3f);
        alphaUp = new AlphaAnimation(0.3f, 1.0f);
        alphaDown.setDuration(1000);
        alphaUp.setDuration(1000);
        alphaDown.setFillAfter(true);
        alphaUp.setFillAfter(true);
        ///////////////////////////////zelen/////////////////////////////
    	if (arg0.getId()==R.id.btnZelen)
    	{
    		if(status==status_igre.poteka && korak<zaporedje.size())
    		{
    			if(zaporedje.get(korak)==gumbi.zelen)
    			{
		    		zelen.startAnimation(alphaDown);
		    		MediaPlayer mp = MediaPlayer.create(this, R.raw.green);
		    		mp.start();
		    		zelen.startAnimation(alphaUp);
		    		while(mp.isPlaying()){}
		    		mp.release();
		    		if(korak==(zaporedje.size()-1)){ igraj(); onemogocigumbe(); prikaz_stopnje.setText("Level: "+zaporedje.size()); return;}
		    		korak++;
    			}
    			else{ status=status_igre.koncan; }
    		}
    	}
    	///////////////////////////////////////////////////////////////////
    	//////////////////////////moder////////////////////////////////////
    	else
	    	if (arg0.getId()==R.id.btnModer)
	    	{
	    		if(status==status_igre.poteka && korak<zaporedje.size())
	    		{
	    			if(zaporedje.get(korak)==gumbi.moder)
	    			{
			    		moder.startAnimation(alphaDown);
			    		MediaPlayer mp = MediaPlayer.create(this, R.raw.blue);
			    		mp.start();
			    		moder.startAnimation(alphaUp);
			    		while(mp.isPlaying()){}
			    		mp.release();
			    		if(korak==(zaporedje.size()-1)){ igraj(); onemogocigumbe(); prikaz_stopnje.setText("Level: "+zaporedje.size()); return;}
			    		korak++;
	    			}
	    			else{ status=status_igre.koncan; }
	    		}
	    	}
    	///////////////////////////////////////////////////////////////////////
    	////////////////////////rdec/////////////////////////////////////////////
	    	else
		    	if (arg0.getId()==R.id.btnRdec)
		    	{
		    		if(status==status_igre.poteka && korak<zaporedje.size())
		    		{
		    			if(zaporedje.get(korak)==gumbi.rdec)
		    			{
				    		rdec.startAnimation(alphaDown);
				    		MediaPlayer mp = MediaPlayer.create(this, R.raw.red);
				    		mp.start();
				    		rdec.startAnimation(alphaUp);
				    		while(mp.isPlaying()){}
				    		mp.release();
				    		if(korak==(zaporedje.size()-1)){ igraj(); onemogocigumbe(); prikaz_stopnje.setText("Level: "+zaporedje.size()); return;}
				    		korak++;
		    			}
		    			else{ status=status_igre.koncan; }
		    		}
		    	}
    	////////////////////////////////////////////////////////////////////
    	///////////////////rumen/////////////////////////////////////////
		    	else
			    	if (arg0.getId()==R.id.btnRumen)
			    	{
			    		if(status==status_igre.poteka && korak<zaporedje.size())
			    		{
			    			if(zaporedje.get(korak)==gumbi.rumen)
			    			{
					    		rumen.startAnimation(alphaDown);
					    		MediaPlayer mp = MediaPlayer.create(this, R.raw.yellow);
					    		mp.start();
					    		rumen.startAnimation(alphaUp);
					    		while(mp.isPlaying()){}
					    		mp.release();
					    		if(korak==(zaporedje.size()-1)){ igraj(); onemogocigumbe(); prikaz_stopnje.setText("Level: "+zaporedje.size()); return;}
					    		korak++;
			    			}
			    			else{ status=status_igre.koncan; }
			    		}
			    	}
    	alphaDown.reset();
    	alphaUp.reset();
    	preveriigro();
    }
    //doda nakljucen zvok v listo in poklice predvajaj
    public void igraj()
    {
    	txtspodaj.setText("Computers turn");
    	korak=0;
    	int r=rand.nextInt(4);
    	switch(r){
    	case 0: 
    		zaporedje.add(gumbi.rdec);
    		break;
    	case 1: 
    		zaporedje.add(gumbi.moder);
    		break;
    	case 2: 
    		zaporedje.add(gumbi.rumen);
    		break;
    	case 3: 
    		zaporedje.add(gumbi.zelen);
    		break;
    	}
    	predvajaj pred=new predvajaj();
    	pred.execute();
    }
    
    //prdevaja zvoke iz zaporedja v asinhronem tasku
    public class predvajaj extends AsyncTask<String, Integer, String>
    {	
    	public List<gumbi> lista=new ArrayList<spomin.gumbi>();
    	
    	
    	@Override
		protected void onPreExecute() 
    	{
    		lista=zaporedje;
    		try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	
    	@Override
        protected String doInBackground(String... url) 
        {
            try {
            	for(int i=0;i<lista.size();i++)
    	    	{
            		Thread.sleep(500);
            		switch(lista.get(i)){
            		case rdec:
            			publishProgress(0);
            			if(i==lista.size()-1)publishProgress(5);
            			Thread.sleep(500);
            			break;
            		case moder:
            			publishProgress(1);
            			if(i==lista.size()-1)publishProgress(5);
            			Thread.sleep(500);
            			break;
            		case rumen:
            			publishProgress(2);
            			if(i==lista.size()-1)publishProgress(5);
            			Thread.sleep(500);
            			break;
            		case zelen:
            			publishProgress(3);
            			if(i==lista.size()-1)publishProgress(5);
            			Thread.sleep(500);
            			break;
            		}
    	    	}
            } catch (Exception e) {}
            return null;
        }
    	
    	protected void onProgressUpdate(Integer... args)
        {
	    		alphaDown = new AlphaAnimation(1.0f, 0.3f);
	            alphaUp = new AlphaAnimation(0.3f, 1.0f);
	            alphaDown.setDuration(1000);
	            alphaUp.setDuration(1000);
	            alphaDown.setFillAfter(true);
	            alphaUp.setFillAfter(true);
	    		switch(args[0]){
	    		case 0:
	    			rdec.startAnimation(alphaDown);
		    		MediaPlayer mp1 = MediaPlayer.create(spomin.this, R.raw.red);
		    		mp1.start();
		    		rdec.startAnimation(alphaUp);
		    		while(mp1.isPlaying()){}
		    		mp1.release();
	    			break;
	    		case 1:
	    			moder.startAnimation(alphaDown);
		    		MediaPlayer mp2 = MediaPlayer.create(spomin.this, R.raw.blue);
		    		mp2.start();
		    		moder.startAnimation(alphaUp);
		    		while(mp2.isPlaying()){}
		    		mp2.release();
	    			break;
	    		case 2:
	    			rumen.startAnimation(alphaDown);
		    		MediaPlayer mp3 = MediaPlayer.create(spomin.this, R.raw.yellow);
		    		mp3.start();
		    		rumen.startAnimation(alphaUp);
		    		while(mp3.isPlaying()){}
		    		mp3.release();
	    			break;
	    		case 3:
	    			zelen.startAnimation(alphaDown);
	        		MediaPlayer mp4 = MediaPlayer.create(spomin.this, R.raw.green);
	        		mp4.start();
	        		zelen.startAnimation(alphaUp);
	        		while(mp4.isPlaying()){}
	        		mp4.release();
	    			break;
	    		case 5:
	    			omogocigumbe();
	    			txtspodaj.setText("Your turn");
	    			break;
	    		}
	    		alphaDown.reset();
	        	alphaUp.reset();
        }
    	
    }
    
    
    //////dialogi////////////////
	@Override
	protected Dialog onCreateDialog(int id) {
		AlertDialog.Builder builder;
		switch (id) {
		case START_DIALOG:
			builder = new AlertDialog.Builder(this);
			builder.setMessage("Are you ready to start?")
			.setCancelable(false)
			.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					igraj();
				}
				
			})
			.setNegativeButton("No", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int id) {
					dialog.cancel();
					finish();
				}
			});
			return builder.create();
		}
		return null;
	}
   ////////////////////////////////
	
	
	
	public void onemogocigumbe()
	{
		rdec.setEnabled(false);
		moder.setEnabled(false);
		rumen.setEnabled(false);
		zelen.setEnabled(false);
	}
	
	public void omogocigumbe()
	{
		rdec.setEnabled(true);
		moder.setEnabled(true);
		rumen.setEnabled(true);
		zelen.setEnabled(true);
		status=status_igre.poteka;
	}
	
	//koncamo trenutno igro(ponovimo igro ali nadaljevanje na 2 del igre)
	public void preveriigro()
	{
		if(status==status_igre.koncan)
		{
			MediaPlayer mp = MediaPlayer.create(this, R.raw.gameover);
    		mp.start();
    		while(mp.isPlaying()){}
    		mp.release();
    		txtspodaj.setText("Game over");
    		onemogocigumbe();
		}
	}
}

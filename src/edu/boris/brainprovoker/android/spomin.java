package edu.boris.brainprovoker.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.widget.ImageButton;


public class spomin extends Activity implements OnClickListener {
	
	enum gumbi{
		rdec, //0
		moder, //1
		rumen, //2
		zelen  //3
	}
	
	enum status_igre{
		poteka, 
		koncan, 
	}
	
	Random rand=new Random();
	private AlphaAnimation alphaDown,alphaUp;        		//animacija za gumbke
	private static final int START_DIALOG = 1;              //start dialog
	Brain_provoker_app app;									//skupen app vseh
	ImageButton rdec,moder,rumen,zelen;
	
	List<gumbi> zaporedje=new ArrayList<gumbi>();           //polje zaporedja
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
		    		if(korak==(zaporedje.size()-1)){ igraj(); return;}
		    		korak++;
    			}else{ status=status_igre.koncan; }
    		}
    	}
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
			    		if(korak==(zaporedje.size()-1)){ igraj(); return;}
			    		korak++;
	    			}else{ status=status_igre.koncan; }
	    		}
	    	}
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
				    		if(korak==(zaporedje.size()-1)){ igraj(); return;}
				    		korak++;
		    			}else{ status=status_igre.koncan; }
		    		}
		    	}
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
					    		if(korak==(zaporedje.size()-1)){ igraj(); return;}
					    		korak++;
			    			}else{ status=status_igre.koncan; }
			    		}
			    	}
    	alphaDown.reset();
    	alphaUp.reset();
    }
    //doda nakljucen zvok v listo in poklice predvajaj
    public void igraj()
    {
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
    	predvajaj();
    	omogocigumbe();
    }
    
    //prdevaja zvoke iz zaporedja
    public void predvajaj()
    {		
    	for(int i=0;i<zaporedje.size();i++)
    	{
    		alphaDown = new AlphaAnimation(1.0f, 0.3f);
            alphaUp = new AlphaAnimation(0.3f, 1.0f);
            alphaDown.setDuration(1000);
            alphaUp.setDuration(1000);
            alphaDown.setFillAfter(true);
            alphaUp.setFillAfter(true);
    		switch(zaporedje.get(i)){
    		case rdec:
    			rdec.startAnimation(alphaDown);
	    		MediaPlayer mp1 = MediaPlayer.create(this, R.raw.red);
	    		mp1.start();
	    		rdec.startAnimation(alphaUp);
	    		while(mp1.isPlaying()){}
	    		mp1.release();
    			break;
    		case moder:
    			moder.startAnimation(alphaDown);
	    		MediaPlayer mp2 = MediaPlayer.create(this, R.raw.blue);
	    		mp2.start();
	    		moder.startAnimation(alphaUp);
	    		while(mp2.isPlaying()){}
	    		mp2.release();
    			break;
    		case rumen:
    			rumen.startAnimation(alphaDown);
	    		MediaPlayer mp3 = MediaPlayer.create(this, R.raw.yellow);
	    		mp3.start();
	    		rumen.startAnimation(alphaUp);
	    		while(mp3.isPlaying()){}
	    		mp3.release();
    			break;
    		case zelen:
    			zelen.startAnimation(alphaDown);
        		MediaPlayer mp4 = MediaPlayer.create(this, R.raw.green);
        		mp4.start();
        		zelen.startAnimation(alphaUp);
        		while(mp4.isPlaying()){}
        		mp4.release();
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
	
}

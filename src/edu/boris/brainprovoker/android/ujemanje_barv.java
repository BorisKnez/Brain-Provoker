package edu.boris.brainprovoker.android;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class ujemanje_barv extends Activity implements OnClickListener {
	private static final int START_DIALOG = 1;              //start dialog
	private static final int FINISH_DIALOG = 2;              //nadaljuj dialog
	public static final int RDECA=0;
	public static final int MODRA=1;
	public static final int RUMENA=2;
	public static final int ORANZNA=3;
	public static final int ZELENA=4;
	
	public static final String RED="Red";
	public static final String BLUE="Blue";
	public static final String YELLOW="Yellow";
	public static final String ORANGE="Orange";
	public static final String GREEN="Green";
	static ProgressBar cas;
	LinearLayout layout;
	List<Beseda> seznam_besed;
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tvscore;
	ImageButton da,ne;
	Brain_provoker_app app;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ujemanje_barv);
        
        app=(Brain_provoker_app) getApplication();
        da=(ImageButton)findViewById(R.id.imbDa);
        ne=(ImageButton)findViewById(R.id.imbNe);
        cas=(ProgressBar)findViewById(R.id.progressbar_Horizontal);
        layout=(LinearLayout)findViewById(R.id.linearParent);
        layout.setWillNotDraw(false);
        seznam_besed=new ArrayList<Beseda>(300);
        NapolniSeznamBesed();
        tv1=(TextView)findViewById(R.id.tvSpodaj1);
        tv2=(TextView)findViewById(R.id.tvSpodaj2);
        tv3=(TextView)findViewById(R.id.tvSpodaj3);
        tv4=(TextView)findViewById(R.id.tvSpodaj4);
        tv5=(TextView)findViewById(R.id.tvSpodaj5);
        tv6=(TextView)findViewById(R.id.tvSpodaj6);
        tvscore=(TextView)findViewById(R.id.tvScoreUJemanjeBarv);
        tvscore.setText(Integer.toString(app.player.score));
        vpisiBesede();       //vpisemo v textboxe
        da.setOnClickListener(this);
        ne.setOnClickListener(this);
        showDialog(START_DIALOG);
	}

	
	@Override
    public void onClick(View arg0)
    {
		if(arg0.getId()==R.id.imbDa)
		{
			if(seznam_besed.get(0).enako)
			{
				app.player.score+=20;
			}
			else
			{app.player.score-=30;}
				
			seznam_besed.remove(0);
			vpisiBesede();
			tvscore.setText(Integer.toString(app.player.score));
		}
		else
		if(arg0.getId()==R.id.imbNe)
		{
			if(!seznam_besed.get(0).enako)
			{
				app.player.score+=20;
			}
			else
			{app.player.score-=30;}
				
			seznam_besed.remove(0);
			vpisiBesede();
			tvscore.setText(Integer.toString(app.player.score));
		}
    }
	 
	 
	 private void NapolniSeznamBesed()
	 {
		 Random rand=new Random();
		 Beseda nova;
		 int stevilo,enako;
		 Paint paint;
		 for(int i=0;i<300;i++)
		 {
			 stevilo=rand.nextInt(5);
			 enako=rand.nextInt(2);
			 switch(stevilo)
			 {
			 case RDECA:
				 nova=new Beseda();
				 paint=new Paint();
				 nova.ime=RED;
				 if(enako==1){paint.setColor(Color.RED); nova.enako=true; nova.barva=paint;}
				 else { paint.setColor(Color.BLUE); nova.barva=paint; }
				 seznam_besed.add(nova);
				 break;
			 case MODRA:
				 nova=new Beseda();
				 paint=new Paint();
				 nova.ime=BLUE;
				 if(enako==1){paint.setColor(Color.BLUE); nova.enako=true; nova.barva=paint;}
				 else { paint.setColor(Color.RED); nova.barva=paint; }
				 seznam_besed.add(nova);
				 break;
			 case RUMENA:
				 nova=new Beseda();
				 paint=new Paint();
				 nova.ime=YELLOW;
				 if(enako==1){paint.setColor(Color.YELLOW); nova.enako=true; nova.barva=paint;}
				 else { paint.setARGB(255, 255, 140, 0); nova.barva=paint; }
				 seznam_besed.add(nova);
				 break;
			 case ORANZNA:
				 nova=new Beseda();
				 paint=new Paint();
				 nova.ime=ORANGE;
				 if(enako==1){paint.setARGB(255, 255, 140, 0); nova.enako=true; nova.barva=paint;}
				 else { paint.setColor(Color.YELLOW); nova.barva=paint; }
				 seznam_besed.add(nova);
				 break;
			 case ZELENA:
				 nova=new Beseda();
				 paint=new Paint();
				 nova.ime=GREEN;
				 if(enako==1){paint.setColor(Color.GREEN); nova.enako=true; nova.barva=paint;}
				 else { paint.setColor(Color.RED); nova.barva=paint; }
				 seznam_besed.add(nova);
				 break;
			 }
		 }
	 }
	
	 
	 private void vpisiBesede()
	 {
		 tv1.setText(seznam_besed.get(0).ime);
		 tv1.setTextColor(seznam_besed.get(0).barva.getColor());
		 tv2.setText(seznam_besed.get(1).ime);
		 tv2.setTextColor(seznam_besed.get(1).barva.getColor());
		 tv3.setText(seznam_besed.get(2).ime);
		 tv3.setTextColor(seznam_besed.get(2).barva.getColor());
		 tv4.setText(seznam_besed.get(3).ime);
		 tv4.setTextColor(seznam_besed.get(3).barva.getColor());
		 tv5.setText(seznam_besed.get(4).ime);
		 tv5.setTextColor(seznam_besed.get(4).barva.getColor());
		 tv6.setText(seznam_besed.get(5).ime);
		 tv6.setTextColor(seznam_besed.get(5).barva.getColor());
	 }
	 
	 
	 private void Odstevaj()
	 {		
		Odstevanje stej= new Odstevanje(60000,250,this);
		stej.start();
	 }
	 
	 public static void posodobi(int msg, int time, ujemanje_barv c) {
	    		switch(msg)
	    		{
	    		case 0:
	    			cas.setProgress(time);
	    			break;
	    		case 1:
	    			c.koncaj();
	    			break;
	    		}
	  }
	 public void koncaj()
	 {
		 showDialog(FINISH_DIALOG);
	 }
	 
	 
//////dialogi////////////////
		@Override
		protected Dialog onCreateDialog(int id) {
			AlertDialog.Builder builder;
			switch (id) {
			case START_DIALOG:
				builder = new AlertDialog.Builder(this);
				builder.setMessage("Your goal si to match words with colors names. If the color of the word and meaning of the word are the same than press thumbs up else thumbs down. \n Are you ready to start?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
						Odstevaj();
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
			case FINISH_DIALOG:
				builder = new AlertDialog.Builder(this);
				builder.setMessage("Game is over: Your score: "+app.player.score)
				.setCancelable(false)
				.setPositiveButton("Go to scores", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						app.rezultati.add(app.player);
						dialog.cancel();
						gotoscores();
		    			finish();
					}
					
				})
				.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
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
	
	 
	 
	 
	 
	 public class Odstevanje extends CountDownTimer{
		 	public ujemanje_barv con;
		    public Odstevanje(long millisInFuture, long countDownInterval, ujemanje_barv c) {
		    super(millisInFuture, countDownInterval);
		    this.con=c;
		    }
		    @Override
		    public void onFinish() {
		    	ujemanje_barv.posodobi(1, 0, con);
		    }

		    @Override
		    public void onTick(long millisUntilFinished) {
		    	ujemanje_barv.posodobi(0, (int)millisUntilFinished, null);
		    }

		}
	 
	 
	 public void gotoscores()
	 {
		 Intent nov2=new Intent(this,ScoreListActivity.class);
		 this.startActivity(nov2);
	 }
	 
	 
}
		 
	 
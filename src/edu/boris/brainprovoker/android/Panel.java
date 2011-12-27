package edu.boris.brainprovoker.android;

import java.util.ArrayList;
import java.util.Random;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Panel extends SurfaceView implements SurfaceHolder.Callback {

    public static float mWidth;
    public static float mHeight;
    private boolean trening;
    
    int sirina, visina;
    public static final String PREF_NAME = "SETTINGS"; 
	public static final String NAME = "PLAYER_NAME";
    private String[] moznosti={
    		"Odstranite vse like OKROGLE oblike!",   	//1
    		"Odstranite vse like TRIKOTNE oblike!",  	//2
    		"Odstranite vse like KVADRATNE oblike!",	//3
    		"Odstranite vse like ZVEZDNE oblike!",		//4
    		"Odstranite vse like MODRE barve!",			//1
    		"Odstranite vse like ZELENE barve!",		//2
    		"Odstranite vse like RDEÈE barve!",			//3
    		"Odstranite vse like RUMENE barve!"};		//4
    private int pravilnabarva=-1, pravilnaoblika=-1;
    
    private ViewThread mThread;
    private ArrayList<Element> mElements = new ArrayList<Element>();
    private int napacni = 0;
    private int pravilni = 0;
    private boolean barva;
    int kvadrati=10, krogi=10, trikotniki=10, zvezde=10;
    int rdecih=0, modrih=0, rumenih=0, zelenih=0;
    int rezultat_iz_app=0;
    Bitmap slikaPravilni=BitmapFactory.decodeResource(getResources(), R.drawable.pravilni);
    Bitmap slikaNapacni=BitmapFactory.decodeResource(getResources(), R.drawable.napacni);
    

    private Paint mPaint = new Paint();
    private Paint mPaint2 = new Paint();
    
    public Panel(Context context, int x, int y, boolean t) {
        super(context);
        getHolder().addCallback(this);
        mThread = new ViewThread(this);
        trening=t;
        sirina=x;
        visina=y;
        generirajpolje();
        mPaint.setColor(Color.GREEN);
        mPaint.setTextSize(30);
        mPaint2.setColor(Color.RED);
        mPaint2.setTextSize(30);
        
        Random ra=new Random();
        int randommoznost=ra.nextInt(8);
        if(randommoznost<4)
        {
        	pravilnaoblika=randommoznost+1;	//oblika
        	barva=false;
        }
        else
        {
        	pravilnabarva=randommoznost-3;   //barva
        	barva=true;
        }
        
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(moznosti[randommoznost]);
        builder.setPositiveButton("Razumem!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            }
        });
        Dialog d = builder.create();
        d.show();

        
    }
    
    public void doDraw(long elapsed, Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        synchronized (mElements) {
            for (Element element : mElements) {
                element.doDraw(canvas);
            }
        }
        canvas.drawBitmap(slikaPravilni, 5, 0, null);
        canvas.drawText(""+pravilni, 5+slikaPravilni.getWidth()+5, 40, mPaint);
        canvas.drawBitmap(slikaNapacni, sirina-slikaNapacni.getWidth()-35, 0, null);
        canvas.drawText(""+napacni, sirina-35, 40, mPaint2);
    }
    
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        mWidth = width;
        sirina=width;
        visina=height;
        mHeight = height;
    }
    
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!mThread.isAlive()) {
            mThread = new ViewThread(this);
            mThread.setRunning(true);
            mThread.start();
        }
    }
    
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if (mThread.isAlive()) {
            mThread.setRunning(false);
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
    	synchronized (mElements) {
	    	if(event.getAction()==MotionEvent.ACTION_DOWN)
	    	{
	    		int x=(int)event.getX();
	    		int y=(int)event.getY();
	                for (int i=0;i<mElements.size();i++) {
	                    if(((Math.abs(x-(mElements.get(i).mX+25)))<25) &&  ((Math.abs(y-(mElements.get(i).mY+25)))<25))
	                    {
	                    	if(barva)
	                    	{
	                    		if(mElements.get(i).barva==pravilnabarva)
	                    		{
	                    			pravilni++;
	                    			if(!trening)((hitri_prsti)getContext()).app.player.score+=50;
	                    		}else{napacni++;if(!trening)((hitri_prsti)getContext()).app.player.score-=100;}
	                    	}
	                    	else
		                    	{
		                    		if(mElements.get(i).oblika==pravilnaoblika)
		                    		{
		                    			pravilni++;
		                    			if(!trening)((hitri_prsti)getContext()).app.player.score+=50;
		                    		}else{napacni++;if(!trening)((hitri_prsti)getContext()).app.player.score-=100;}
		                    	}
	                    	mElements.remove(i);
	                    }
	                }
	    	}
    	}
    	//poklikali vse možne, pravilne
    	if(pravilni==10)
    	{
    		if(!trening)rezultat_iz_app=((hitri_prsti)getContext()).app.player.score;
        	
    		AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Koncali ste! Napake:  "+napacni);
            if(!trening)
            {
            builder.setMessage("Skupni rezultat: "+rezultat_iz_app);
            builder.setPositiveButton("Pojdi na rezultate",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int id) {
							((hitri_prsti)getContext()).app.player.ime=((hitri_prsti)getContext()).getName();
							((hitri_prsti)getContext()).app.addIgralec(((hitri_prsti)getContext()).app.player);
							dialog.cancel();
							((hitri_prsti)getContext()).gotoscores();
							((hitri_prsti)getContext()).finish();
						}

					});
            }
            builder.setNegativeButton("Izhod",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog,
								int id) {
			
							if(!trening)((hitri_prsti)getContext()).app.addIgralec(((hitri_prsti)getContext()).app.player);
							dialog.cancel();
							if(!trening)
							{
								((hitri_prsti)getContext()).finish();
							}else{((hitri_prsti_trening)getContext()).finish();}
						}
					});
            Dialog d = builder.create();
            d.show();
    	}
    	
    	
        return super.onTouchEvent(event);
    }
    
    
    
    public void animate(long elapsedTime) {
        synchronized (mElements) {
            for (Element element : mElements) {
                element.animate(elapsedTime);
            }
        }
    }
    
    
    
    public void generirajpolje()
    {
    	Random r = new Random();
    	int nakljucna;
    	synchronized (mElements) {
    		
    		///////////krogi///////
    		for(int i=0;i<10;i++)
    		{
    			nakljucna=r.nextInt(4)+1;
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			switch(nakljucna){
    			case 1: 
    				mElements.add(new Element(getResources(), nak_x, nak_y, 1, 1));
    				modrih++;
    				break;
    			case 2: 
    				mElements.add(new Element(getResources(), nak_x, nak_y, 1, 2));
    				zelenih++;
    				break;
    			case 3: 
    				mElements.add(new Element(getResources(), nak_x, nak_y, 1, 3));
    				rdecih++;
    				break;
    			case 4: 
    				mElements.add(new Element(getResources(), nak_x, nak_y, 1, 4));
    				rumenih++;
    				break;
    			}
    		}
    		///////////trikotniki///////
    		for(int i=0;i<10;i++)
    		{
    			nakljucna=r.nextInt(4)+1;
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			switch(nakljucna){
    			case 1: 
    				if(modrih>=5){i--;break;}
    				else
    				{
    					mElements.add(new Element(getResources(), nak_x, nak_y, 2, 1));
    					modrih++;
    					break;
    				}
    			case 2: 
    				if(zelenih>=5){i--;break;}
    				else
    				{
    					mElements.add(new Element(getResources(), nak_x, nak_y, 2, 2));
    					zelenih++;
    					break;
    				}
    			case 3: 
    				if(rdecih>=5){i--;break;}
    				else
    				{
	    				mElements.add(new Element(getResources(), nak_x, nak_y, 2, 3));
	    				rdecih++;
	    				break;
    				}
    			case 4: 
    				if(rumenih>=5){i--;break;}
    				else
    				{
	    				mElements.add(new Element(getResources(), nak_x, nak_y, 2, 4));
	    				rumenih++;
	    				break;
    				}
    			}
    		}
    		
    		////////kvadrati/////////
    		for(int i=0;i<10;i++)
    		{
    			nakljucna=r.nextInt(4)+1;
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			switch(nakljucna){
    			case 1: 
    				if(modrih==8){i--;break;}
    				else
    				{
    					mElements.add(new Element(getResources(), nak_x, nak_y, 3, 1));
    					modrih++;
    					break;
    				}
    			case 2: 
    				if(zelenih==8){i--;break;}
    				else
    				{
    					mElements.add(new Element(getResources(), nak_x, nak_y, 3, 2));
    					zelenih++;
    					break;
    				}
    			case 3: 
    				if(rdecih==8){i--;break;}
    				else
    				{
	    				mElements.add(new Element(getResources(), nak_x, nak_y, 3, 3));
	    				rdecih++;
	    				break;
    				}
    			case 4: 
    				if(rumenih==8){i--;break;}
    				else
    				{
	    				mElements.add(new Element(getResources(), nak_x, nak_y, 3, 4));
	    				rumenih++;
	    				break;
    				}
    			}
    		} 		
    		
    		while(modrih<10)
    		{
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			mElements.add(new Element(getResources(), nak_x, nak_y, 4, 1));
				modrih++;
    		}
    		while(zelenih<10)
    		{
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			mElements.add(new Element(getResources(), nak_x, nak_y, 4, 2));
				zelenih++;
    		}
    		while(rdecih<10)
    		{
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			mElements.add(new Element(getResources(), nak_x, nak_y, 4, 3));
    			rdecih++;
    		}
    		while(rumenih<10)
    		{
    			int nak_x=r.nextInt(sirina);
    			int nak_y=r.nextInt(visina);
    			mElements.add(new Element(getResources(), nak_x, nak_y, 4, 4));
    			rumenih++;
    		}
    			
    		
    	}
    }
    
    
    
}

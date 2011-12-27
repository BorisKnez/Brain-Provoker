package edu.boris.brainprovoker.android;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Element {
	
	
    public float mX;
    public float mY;
    public int oblika;
    public int barva;
    
    private int mSpeedX;
    private int mSpeedY;
    
    private Bitmap lik;
    
    public Element(Resources res, int x, int y, int oblika, int barva) {
        Random rand = new Random();
        switch(oblika){
        ///krog///
        case 1:
        	switch(barva){
        	case 1: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.krog_moder);
        		break;
        	case 2: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.krog_zelen);
        		break;
        	case 3: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.krog_rdec);
        		break;
        	case 4: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.krog_rumen);
        		break;
			default:
				break;
        	}	
        	break;
        ///trikotnik///
        case 2:
        	switch(barva){
        	case 1: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.trikotnik_moder);
        		break;
        	case 2: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.trikotnik_zelen);
        		break;
        	case 3: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.trikotnik_rdec);
        		break;
        	case 4: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.trikotnik_rumen);
        		break;
			default:
				break;
        	}	
        	
        	break;
        ///kvadrat///	
        case 3:
        	switch(barva){
        	case 1: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.kvadrat_moder);
        		break;
        	case 2: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.kvadrat_zelen);
        		break;
        	case 3: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.kvadrat_rdec);
        		break;
        	case 4: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.kvadrat_rumen);
        		break;
			default:
				break;
        	}	
        	break; 
        ///zvezda///	
        case 4: 
        	switch(barva){
        	case 1: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.zvezda_modra);
        		break;
        	case 2: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.zvezda_zelena);
        		break;
        	case 3: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.zvezda_rdeca);
        		break;
        	case 4: 
        		lik = BitmapFactory.decodeResource(res, R.drawable.zvezda_rumena);
        		break;
			default:
				break;
        	}	
        	break;
		default:
			break; 
        }
        
        this.barva=barva;
        this.oblika=oblika;
        mX = x - lik.getWidth() / 2;
        mY = y - lik.getHeight() / 2;
        mSpeedX = rand.nextInt(9) - 3;
        mSpeedY = rand.nextInt(9) - 3;
    }
    
    public void doDraw(Canvas canvas) {
        canvas.drawBitmap(lik, mX, mY, null);
    }
    
    /**
     * @param elapsedTime in ms.
     */
    public void animate(long elapsedTime) {
        mX += mSpeedX * (elapsedTime / 20f);
        mY += mSpeedY * (elapsedTime / 20f);
        checkBorders();
    }

    private void checkBorders() {
        if (mX <= 0) {
            mSpeedX = -mSpeedX;
            mX = 0;
        } else if (mX + lik.getWidth() >= Panel.mWidth) {
            mSpeedX = -mSpeedX;
            mX = Panel.mWidth - lik.getWidth();
        }
        if (mY <= 0) {
            mY = 0;
            mSpeedY = -mSpeedY;
        }
        if (mY + lik.getHeight() >= Panel.mHeight) {
            mSpeedY = -mSpeedY;
            mY = Panel.mHeight - lik.getHeight();
        }
    }
}

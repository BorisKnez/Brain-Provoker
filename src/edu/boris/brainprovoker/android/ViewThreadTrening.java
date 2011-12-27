package edu.boris.brainprovoker.android;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class ViewThreadTrening extends Thread {
    private Panel_trening mPanel;
    private SurfaceHolder mHolder;
    private boolean mRun = false;
private long mStartTime;
private long mElapsed;
    
    public ViewThreadTrening(Panel_trening panel) {
        mPanel = panel;
        mHolder = mPanel.getHolder();
    }
    
    public void setRunning(boolean run) {
        mRun = run;
    }
    
    @Override
    public void run() {
        Canvas canvas = null;
        mStartTime = System.currentTimeMillis();
        while (mRun) {
            canvas = mHolder.lockCanvas();
            if (canvas != null) {
                mPanel.animate(mElapsed);
                mPanel.doDraw(mElapsed, canvas);
                mElapsed = System.currentTimeMillis() - mStartTime;
                mHolder.unlockCanvasAndPost(canvas);
            }
            mStartTime = System.currentTimeMillis();
        }
    }
}
package com.thedespite.warwarrior;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Despite on 29.1.2014.
 */
public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {

    class CanvasThread extends Thread {

        private Game mGame;

        private SurfaceHolder mSurfaceHolder;
        private Context mContext;

        private boolean mRun = false;
        private final Object mRunLock = new Object();


        public CanvasThread(SurfaceHolder surfaceHolder, Context context)
        {
            // Get handles
            mSurfaceHolder = surfaceHolder;
            mContext = context;
        }

        public void initialize()
        {
            synchronized (mSurfaceHolder)
            {
                mGame = new Game();

                mGame.LoadData(mContext);
                mGame.Start();
            }
        }

        public void Pause()
        {
            synchronized (mSurfaceHolder)
            {
                // TODO
            }
        }

        @Override
        public void run()
        {
            while (mRun)
            {
                Canvas c = null;
                try {
                    {
                        c = mSurfaceHolder.lockCanvas(null);
                        synchronized (mSurfaceHolder)
                        {
                            mGame.Update();

                            synchronized (mRunLock)
                            {
                                if (mRun) doDraw(c);
                            }
                        }
                    }
                } finally {
                    if (c != null)
                    {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        public void SetRunning(boolean b)
        {
            synchronized (mRunLock)
            {
                mRun = b;
            }
        }

        public void SetSurfaceSize(int width, int height)
        {
            synchronized (mSurfaceHolder)
            {
                Game.screenDimensions = new vect2(width, height);
            }
        }

        private void doDraw(Canvas canvas)
        {
            mGame.Draw(canvas);
        }
    }

    // ================================================================
    // ==================== END OF CANVASTHREAD =======================
    // ================================================================
    // ==================== BACK TO CANVASVIEW  =======================
    // ================================================================

    private Context mContext;
    private CanvasThread thread;

    public CanvasView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        // "Register our interest in hearing about changes to our surface"
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);

        // Create thread. !! Started in surfaceCreated !!
        thread = new CanvasThread(holder, context);

        setFocusable(true);
    }

    public CanvasThread GetThread()
    {
        return thread;
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus)
    {
        if (!hasWindowFocus) thread.Pause();
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        thread.SetSurfaceSize(width, height);
    }

    public void surfaceCreated(SurfaceHolder holder)
    {
        thread.SetSurfaceSize(holder.getSurfaceFrame().width(), holder.getSurfaceFrame().height());

        thread.initialize();
        thread.SetRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        thread.SetRunning(false);

        while (retry)
        {
            try{
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent)
    {
        InputMgr.SetFingerPosRelativeToCamera(new vect2(motionEvent.getX(), motionEvent.getY()));

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN)
            InputMgr.newFinger = true;

        return true;
    }
}

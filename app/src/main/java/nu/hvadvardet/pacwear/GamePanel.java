package nu.hvadvardet.pacwear;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private SceneManager sceneManager;
    public static Context CONTEXT;
    public static Vibrator VIBRATOR;

    public GamePanel(Context context) {
        super(context);
        Constants.CONTEXT = context;
        Constants.SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        Constants.SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        Constants.SCREEN_CENTER_WIDTH = Constants.SCREEN_WIDTH / 2;
        Constants.SCREEN_CENTER_HEIGHT = Constants.SCREEN_HEIGHT / 2;
        getHolder().addCallback(this);
        setFocusable(true);
        this.sceneManager = new SceneManager();
        this.VIBRATOR = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if(thread != null) {
            boolean retry = true;
            while (retry) {
                try {
                    thread.setRunning(false);
                    thread.join();
                    retry = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.sceneManager.receiveTouch(event);
        return true;
    }

    public void update() {
        this.sceneManager.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        this.sceneManager.draw(canvas);
    }
}

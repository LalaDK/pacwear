package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

/**
 * Created by mads on 1/21/18.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 40;
    private double averageFps;
    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;
            try {
                canvas = this.surfaceHolder.lockCanvas();
                if (canvas != null) {
                    synchronized (canvas) {
                        this.gamePanel.update();
                        this.gamePanel.draw(canvas);
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - startTime;
            frameCount++;
            averageFps = 1000 / ((totalTime / frameCount) / 1000000);
            totalTime = 0;
            if (frameCount == MAX_FPS) {
                System.out.println(averageFps);
            }
            frameCount = 0;
        }
    }
}

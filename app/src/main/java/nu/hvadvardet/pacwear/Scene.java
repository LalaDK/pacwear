package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by mads on 1/26/18.
 */

public interface Scene {
    void draw(Canvas canvas);
    void update();
    void receiveTouch(MotionEvent event);
    void terminate();
}

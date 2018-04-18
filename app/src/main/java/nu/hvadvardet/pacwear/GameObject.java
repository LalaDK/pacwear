package nu.hvadvardet.pacwear;

import android.graphics.Canvas;

public interface GameObject {
    public void draw(Canvas canvas, Camera camera);
    public void update();
}

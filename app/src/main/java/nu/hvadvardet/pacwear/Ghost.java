package nu.hvadvardet.pacwear;

import android.graphics.Canvas;

public class Ghost extends Moveable {

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Arc arc = new Arc(
                Constants.BLOCK_SIZE,
                Constants.BLOCK_SIZE,
                Constants.BLOCK_SIZE,
                Constants.BLOCK_SIZE,
                0, 360);
        arc.draw(canvas, camera);

    }

    @Override
    public void update() {

    }
}

package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Rectangle implements GameObject {
    private Point position;
    private int width;
    private int height;
    private int color;

    public Rectangle(int x, int y, int width, int height) {
        this.position = new Point(x,y);
        this.width = width;
        this.height = height;
        this.color = Color.WHITE;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
            Paint paint = new Paint();
            paint.setColor(this.color);
            canvas.drawRect((this.position.x + -camera.position.x) * (int) camera.getScaleX(),
                    (this.position.y + -camera.position.y) * (int) camera.getScaleY(),
                    (this.position.x + -camera.position.x + this.width) * (int) camera.getScaleX(),
                    (this.position.y + -camera.position.y + this.height) * (int) camera.getScaleY(),
                    paint);
    }

    @Override
    public void update() {

    }

    public void setColor(int color) {
        this.color = color;
    }
}

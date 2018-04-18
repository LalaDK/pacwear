package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Arc implements GameObject {
    private Point position;
    private int width;
    private int height;
    private int color;
    private int startAngle;
    private int sweepAngle;

    public Arc(int x, int y, int width, int height, int startAngle, int sweepAngle) {
        this.position = new Point(x,y);
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.color = Color.GREEN;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
            Paint paint = new Paint();
            paint.setColor(this.color);
            canvas.drawArc(
                    (this.position.x + -camera.position.x) * (int) camera.getScaleX(),
                    (this.position.y + -camera.position.y) * (int) camera.getScaleY(),
                    (this.position.x + -camera.position.x + this.width) * (int) camera.getScaleX(),
                    (this.position.y + -camera.position.y + this.height) * (int) camera.getScaleY(),
                    this.startAngle,
                    this.sweepAngle,
                    false,
                    paint);
    }

    @Override
    public void update() {

    }

    public void setColor(int color) {
        this.color = color;
    }
}

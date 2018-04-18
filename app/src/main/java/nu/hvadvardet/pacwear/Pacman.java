package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class Pacman extends Moveable {
    public boolean stopAnimation = false;
    private int frameIndex = 0;
    private int framecurrentDirection = 1;
    private int mountAngle = 90;

    public Pacman() {
        super();
    }


    public void randomDirection() {
        Point[] points = new Point[]{
                Constants.DIRECTION_DOWN,
                Constants.DIRECTION_UP,
                Constants.DIRECTION_LEFT,
                Constants.DIRECTION_RIGHT,
        };
        Point selected = points[(int )(Math.random() * points.length)];
        this.setDesiredDirection(selected);
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        Paint paint = new Paint();
        paint.setColor(this.color);
        RectF rect = new RectF(
                (float) (this.position.x + -camera.position.x) * (int) camera.getScaleX(),
                (float) (this.position.y + -camera.position.y) * (int) camera.getScaleY(),
                (float) (this.position.x + this.size + -camera.position.x) * (int) camera.getScaleX(),
                (float) (this.position.y + this.size + -camera.position.y) * (int) camera.getScaleY()
        );
        float currentDirectionRotation = 0; // currentDirection_RIGHT
        if (this.currentDirection == Constants.DIRECTION_UP) {
            currentDirectionRotation = 90;
        } else if (this.currentDirection == Constants.DIRECTION_LEFT) {
            currentDirectionRotation = 180;
        } else if (this.currentDirection == Constants.DIRECTION_DOWN) {
            currentDirectionRotation = 270;
        }
        double frameProgress = ((double) this.frameIndex / (double) MainThread.MAX_FPS);
        canvas.drawArc(rect,
                currentDirectionRotation + (float) +(frameProgress * this.mountAngle),
                (360 - (float) (frameProgress * this.mountAngle) * 2),
                true,
                paint);
    }

    @Override
    public void update() {
        if (!this.stopAnimation) {
            this.frameIndex += this.framecurrentDirection * 5;
            if (this.frameIndex >= MainThread.MAX_FPS || this.frameIndex <= 0) {
                this.framecurrentDirection *= -1;
            }
        }
    }
}

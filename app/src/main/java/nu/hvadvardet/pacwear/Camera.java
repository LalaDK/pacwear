package nu.hvadvardet.pacwear;

/**
 * Created by mads on 1/22/18.
 */

public class Camera {
    public Point position;
    public int width;
    public int height;

    public Camera() {
        this.position = new Point(0, 0);
        this.width = Constants.SCREEN_WIDTH / 2;
        this.height = Constants.SCREEN_HEIGHT / 2;
    }

    public double getScaleX() {
        return Constants.SCREEN_WIDTH / this.width;
    }

    public double getScaleY() {
        return Constants.SCREEN_HEIGHT / this.height;
    }
}

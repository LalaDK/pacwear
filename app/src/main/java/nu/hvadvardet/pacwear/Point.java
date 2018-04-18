package nu.hvadvardet.pacwear;

/**
 * Created by mads on 1/22/18.
 */

public class Point {
    public int x;
    public int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void substract(int x, int y) {
        this.x -= x;
        this.y -= y;
    }

    public void subtractPoint(Point point) {
        this.x -= point.x;
        this.y -= point.y;
    }

    public void addPoint(Point point) {
        this.x += point.x;
        this.y += point.y;
    }
}

package nu.hvadvardet.pacwear;

import android.graphics.Color;

/**
 * Created by mads on 2/1/18.
 */

public abstract class Moveable implements GameObject {
    protected Point position;
    protected Point currentDirection;
    protected Point desiredDirection;
    protected int size = Constants.BLOCK_SIZE;
    protected int color;

    public Moveable() {
        this.position = new Point(Constants.BLOCK_SIZE * 1, Constants.BLOCK_SIZE * 2);
        this.currentDirection = Constants.DIRECTION_STILL;
        this.desiredDirection = Constants.DIRECTION_RIGHT;
        this.color = Color.YELLOW;
    }

    public void move() {
        this.position.add(this.currentDirection.x * 2, this.currentDirection.y * 2);
    }

    public Point getPosition() {
        return this.position;
    }

    public void setCurrentDirection(Point direction) {
        this.currentDirection = direction;
    }

    public void setDesiredDirection(Point direction) {
        if (this.currentDirection == Constants.DIRECTION_UP && direction == Constants.DIRECTION_DOWN) {
            this.currentDirection = direction;
        }
        if (this.currentDirection == Constants.DIRECTION_DOWN && direction == Constants.DIRECTION_UP) {
            this.currentDirection = direction;
        }
        if (this.currentDirection == Constants.DIRECTION_LEFT && direction == Constants.DIRECTION_RIGHT) {
            this.currentDirection = direction;
        }
        if (this.currentDirection == Constants.DIRECTION_RIGHT && direction == Constants.DIRECTION_LEFT) {
            this.currentDirection = direction;
        }
        this.desiredDirection = direction;
    }

    public Point getCurrentDirection() {
        return this.currentDirection;
    }

    public Point getDesiredDirection() {
        return this.desiredDirection;
    }
}

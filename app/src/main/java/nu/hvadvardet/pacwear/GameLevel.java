package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.graphics.Color;


public class GameLevel implements GameObject {
    private int[][] level;
    private int blockSize = Constants.BLOCK_SIZE;
    private int levelWidth;
    private int levelHeight;

    public GameLevel() {
        /*
        this.level = new int[][]{
                {0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

                */

        this.level = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        };
        this.levelHeight = this.level.length;
        this.levelWidth = this.level[0].length;
    }

    public boolean canMove(Point currentPosition, Point desiredDirection) {
        if (desiredDirection == Constants.DIRECTION_STILL) {
            return true;
        }

        int halfBlock = (int) ((double) Constants.BLOCK_SIZE / 2d);
        int centerX = (currentPosition.x + desiredDirection.x + halfBlock);
        int centerY = (currentPosition.y + desiredDirection.y + halfBlock);


        if (desiredDirection == Constants.DIRECTION_UP) {
            if (centerX % halfBlock != 0) {
                return false;
            }
            int x = (centerX - halfBlock) / Constants.BLOCK_SIZE;
            int x2 = ((centerX + halfBlock - 1) / Constants.BLOCK_SIZE);
            int y = (centerY + halfBlock) / Constants.BLOCK_SIZE;
            return this.level[y][x] == 0 && this.level[y][x2] == 0;
        }

        if (desiredDirection == Constants.DIRECTION_DOWN) {
            if (centerX % halfBlock != 0) {
                return false;
            }
            int x = ((centerX - halfBlock) / Constants.BLOCK_SIZE);
            int x2 = ((centerX + halfBlock - 1) / Constants.BLOCK_SIZE);
            int y = ((centerY - halfBlock) / Constants.BLOCK_SIZE);

            return this.level[y][x] == 0 && this.level[y][x2] == 0;
        }

        if (desiredDirection == Constants.DIRECTION_RIGHT) {
            if (centerY % halfBlock != 0) {
                return false;
            }
            int x = (centerX + halfBlock) / Constants.BLOCK_SIZE;
            int y = (centerY - halfBlock) / Constants.BLOCK_SIZE;
            int y2 = ((centerY + halfBlock - 1) / Constants.BLOCK_SIZE);
            return this.level[y][x] == 0 && this.level[y2][x] == 0;
        }

        if (desiredDirection == Constants.DIRECTION_LEFT) {
            if (centerY % halfBlock != 0) {
                return false;
            }
            int x = (centerX - halfBlock) / Constants.BLOCK_SIZE;
            int y = (centerY - halfBlock) / Constants.BLOCK_SIZE;
            int y2 = ((centerY + halfBlock - 1) / Constants.BLOCK_SIZE);

            return this.level[y][x] == 0 && this.level[y2][x] == 0;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas, Camera camera) {
        for (int y = 0; y < this.levelHeight; y++) {
            for (int x = 0; x < this.levelWidth; x++) {
                if (this.level[y][x] == 1) {
                    Rectangle rect = new Rectangle(x * this.blockSize, y * this.blockSize, this.blockSize, this.blockSize);
                    rect.setColor(Color.BLUE);
                    rect.draw(canvas, camera);
                }
            }
        }

    }

    @Override
    public void update() {

    }
}

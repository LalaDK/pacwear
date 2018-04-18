package nu.hvadvardet.pacwear;

import android.content.Context;

/**
 * Created by mads on 1/26/18.
 */

public class Constants {
    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;
    public static int SCREEN_CENTER_WIDTH;
    public static int SCREEN_CENTER_HEIGHT;
    public static Context CONTEXT;
    public static int BLOCK_SIZE = 22;

    public static Point DIRECTION_UP = new Point(0,1);
    public static Point DIRECTION_RIGHT = new Point(1,0);
    public static Point DIRECTION_DOWN = new Point(0,-1);
    public static Point DIRECTION_LEFT = new Point(-1,0);
    public static Point DIRECTION_STILL = new Point(0,0);
}

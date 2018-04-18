package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;

    public SceneManager() {
        this.scenes.add(new GameplayScene());
        ACTIVE_SCENE = 0;
    }

    public void receiveTouch(MotionEvent event) {
        this.scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        this.scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        this.scenes.get(ACTIVE_SCENE).draw(canvas);
    }

}

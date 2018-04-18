package nu.hvadvardet.pacwear;

import android.graphics.Canvas;
import android.os.VibrationEffect;
import android.view.GestureDetector;
import android.view.MotionEvent;

import java.util.ArrayList;


public class GameplayScene implements Scene {
    private ArrayList<GameObject> objects = new ArrayList<>();
    private Camera camera;
    private Pacman player;
    private GameLevel level;
    private Ghost ghost;

    final GestureDetector gestureDetector = new GestureDetector(GamePanel.CONTEXT, new GestureDetector.SimpleOnGestureListener() {
        public void onLongPress(MotionEvent event) {
            System.exit(0);
        }

        public boolean onDown(MotionEvent event) {
            GamePanel.VIBRATOR.vibrate(VibrationEffect.createOneShot(25, 5));
            int touchX = (int) event.getX();
            int touchY = (int) event.getY();
            int screenCenterX = (int) ((double) Constants.SCREEN_WIDTH / 2.0);
            int screenCenterY = (int) ((double) Constants.SCREEN_HEIGHT / 2.0);
            Point p = new Point(screenCenterX - touchX, screenCenterY - touchY);

            if (Math.abs(p.x) > Math.abs(p.y)) {
                if (p.x < 0) {
                    player.setDesiredDirection(Constants.DIRECTION_RIGHT);
                } else {
                    player.setDesiredDirection(Constants.DIRECTION_LEFT);
                }
            } else {
                if (p.y < 0) {
                    player.setDesiredDirection(Constants.DIRECTION_UP);
                } else {
                    player.setDesiredDirection(Constants.DIRECTION_DOWN);
                }
            }
            return true;
        }
    });

    public GameplayScene() {
        this.camera = new Camera();
        this.level = new GameLevel();
        this.player = new Pacman();
        this.ghost = new Ghost();
        //this.objects.add(new Arc(20, 20, 20, 20, 0, 180));
    }

    public void movePlayer() {
            if (this.player.getCurrentDirection() != Constants.DIRECTION_STILL) {
                if(this.level.canMove(this.player.getPosition(), this.player.getDesiredDirection())) {
                    this.player.stopAnimation = false;
                    this.player.setCurrentDirection(this.player.getDesiredDirection());
                    this.player.move();
                } else {
                    if(this.level.canMove(this.player.getPosition(), this.player.getCurrentDirection())) {
                        this.player.stopAnimation = false;
                        this.player.move();
                    } else {
                        this.player.stopAnimation = true;
                    }
                }
            } else {
                this.player.stopAnimation = true;
                this.player.setCurrentDirection(this.player.getDesiredDirection());
            }
    }

    @Override
    public void draw(Canvas canvas) {
        for (GameObject object : this.objects) {
            object.draw(canvas, this.camera);
        }
        this.level.draw(canvas, this.camera);
        this.player.draw(canvas, this.camera);
        this.ghost.draw(canvas, this.camera);

    }

    @Override
    public void update() {
        for (GameObject object : this.objects) {
            object.update();
        }
        movePlayer();
        this.player.update();
        this.ghost.update();

        Point cameraPosition = new Point(
                this.player.getPosition().x - (this.camera.width / 2) + (Constants.BLOCK_SIZE / 2),
                this.player.getPosition().y - (this.camera.height / 2) +  (Constants.BLOCK_SIZE / 2)
        );
        this.camera.position = cameraPosition;

    }

    @Override
    public void receiveTouch(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
    }

    @Override
    public void terminate() {

    }
}

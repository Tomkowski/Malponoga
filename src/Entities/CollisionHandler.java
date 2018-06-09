package Entities;

import java.util.List;

public class CollisionHandler {

    List <GameObject> entities;
    Ball ball;
    public CollisionHandler(List<GameObject> entities,Ball ball){
        this.entities = entities;
        this.ball = ball;
    }

    public void checkForCollisions() {
        for (GameObject e : entities) {
            if (e.collidesWith(ball)) {
                System.out.println("Collides with: " + e.getName());
                if (e.getX() > ball.getX()) ball.callBack(1);
                else ball.callBack(-1);

                return;
            }
        }

        ball.callBack(0);
        return;

    }


}

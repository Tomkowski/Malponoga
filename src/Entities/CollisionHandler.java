package Entities;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import java.util.List;

public class CollisionHandler {

    List <GameObject> entities;
    Rectangle rightGoal;
    Rectangle leftGoal;
    Ball ball;
    Line leftBar;
    Line rightBar;
    public CollisionHandler(List<GameObject> entities, Rectangle leftGoal, Rectangle rightGoal,Line leftBar,Line rightBar, Ball ball){
        this.entities = entities;
        this.leftGoal = leftGoal;
        this.rightGoal = rightGoal;
        this.leftBar = leftBar;
        this.rightBar = rightBar;
        this.ball = ball;
    }

    public void checkForCollisions() {

        for (GameObject e : entities) {
            if (e.collidesWith(ball)) {
                 if (e.getX() > ball.getX()) ball.callBack(1);
                else ball.callBack(-1);

                return;
            }
        }

        ball.callBack(0);
        return;

    }

    public int checkForGoal(){

        if(leftGoal.intersects(ball.collisionBox)) return 1;

        if(rightGoal.intersects(ball.collisionBox)) return -1;

        return 0;

    }

    public void checkForBar(){

        if(ball.collisionBox.intersects(leftBar)){
            ball.callBack(-1);
            return;
        }

        if(ball.collisionBox.intersects(rightBar)){
            ball.callBack( 1);
            return;
        }

        ball.callBack(0);
    }

}

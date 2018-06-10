package Entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

public abstract class Bonus extends GameObject {

    protected Image image;

    protected GameObject gameObject;
    protected Ball ball;

    protected Bonus(float x, float y, String name, Shape collisionBox) {
        super(x, y, name, collisionBox);
     }

    protected Bonus(String name, Shape collisionBox) {
        super(name, collisionBox);
    }

    public abstract void affectGameObject(GameObject gameObject, Ball ball);

    public abstract void reverseEffect(GameObject gameObject, Ball ball);


    protected void saveTempPair(GameObject gameObject, Ball ball){
        this.gameObject = gameObject;
        this.ball = ball;
    }

}

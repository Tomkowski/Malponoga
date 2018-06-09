package Entities;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;

public abstract class GameObject implements Entity {

    protected float x;
    protected float y;

    Shape collisionBox;

    String name;

    protected GameObject(float x, float y, String name, Shape collisionBox) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.collisionBox = collisionBox;
    }

    protected GameObject(String name, Shape collisionBox){
        this.name = name;
        this.collisionBox = collisionBox;
    }

    public boolean collidesWith(GameObject gameObject){
        return (collisionBox.intersects(gameObject.collisionBox) || collisionBox.contains(gameObject.collisionBox)) || gameObject.collisionBox.contains(collisionBox);
    }
}

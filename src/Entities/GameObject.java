package Entities;

import org.newdawn.slick.geom.Shape;

public abstract class GameObject implements Entity {

    protected float x;
    protected float y;

    protected float velX;
    protected float velY;
    boolean visible = true;

    Shape collisionBox;

    String name;

    public boolean isVisible(){
        return visible;
    }

    protected GameObject(float x, float y, String name, Shape collisionBox) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.collisionBox = collisionBox;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelX() {
        return velX;
    }

    protected GameObject(String name, Shape collisionBox){
        this.name = name;
        this.collisionBox = collisionBox;
    }

    public boolean collidesWith(GameObject gameObject){
        return (collisionBox.intersects(gameObject.collisionBox) || collisionBox.contains(gameObject.collisionBox)) || gameObject.collisionBox.contains(collisionBox);
    }


}

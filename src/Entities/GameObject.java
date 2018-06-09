package Entities;

import org.newdawn.slick.SlickException;

public abstract class GameObject implements Entity {

    protected float x;
    protected float y;

    String name;

    protected GameObject(float x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    protected GameObject(String name){
        this.name = name;
    }


}

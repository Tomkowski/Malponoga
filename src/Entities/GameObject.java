package Entities;

import org.newdawn.slick.SlickException;

public abstract class GameObject implements Entity {

    protected double x;
    protected double y;

    String name;

    protected GameObject(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    protected GameObject(String name){
        this.name = name;
    }


}

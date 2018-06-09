package Entities;

import org.newdawn.slick.SlickException;

public abstract class GameObject implements Entity {

    private double x;
    private double y;

    String name;

    public GameObject(double x, double y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    private GameObject(String name){
        this.name = name;
    }


}

package Entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

public class Player extends GameObject {


    SpriteSheet mainSheet;

    Animation walkingLeft;
    Animation getWalkingRight;
    Animation jumpLeft;
    Animation jumpRight;
    Animation idle;

    final int CROPPER_SIZE_X = 64;
    final int CROPPER_SIZE_Y = 128;

    public Player(double x, double y, String name) {
        super(x, y, name);
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return x;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void render(Graphics g){

    }

    @Override
    public void update(int delta) {

    }

    @Override
    public void init() throws SlickException {
        mainSheet = new SpriteSheet("/res/textures/playerSpriteSheet.png",CROPPER_SIZE_X,CROPPER_SIZE_Y);

    }
}

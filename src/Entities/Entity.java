package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {

    float getX();

    float getY();

    String getName();

    void render(Graphics g);

    void update(int delta, GameContainer gameContainer);

    void init() throws SlickException;

}

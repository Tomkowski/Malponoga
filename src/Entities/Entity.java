package Entities;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public interface Entity {

    double getX();

    double getY();

    String getName();

    void render(Graphics g);

    void update(int delta);

    void init() throws SlickException;

}

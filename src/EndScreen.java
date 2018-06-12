import Entities.Board;
import Entities.Score;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;

public class EndScreen extends BasicGameState {

    Image endScreen;
    Image endScreenHover;
    Image background;
    Image net;

    Rectangle back_rect;

    TrueTypeFont scoreFont;

    boolean hover = false;

    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {

        endScreen = new Image("/res/textures/end/final_score.png");
        endScreenHover = new Image("/res/textures/end/final_score_hover.png");
        background = new Image("/res/textures/board/stadium.png");
        net = new Image("/res/textures/board/net.png");

        back_rect = new Rectangle(gc.getWidth()*0.33f,gc.getHeight()*0.8f,gc.getWidth()*0.33f,gc.getHeight()*0.15f);

        scoreFont = new TrueTypeFont(new Font("Helvetica", Font.BOLD, 90), true);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background.getScaledCopy(gc.getWidth(),gc.getHeight()),0,0);
        g.drawImage(net.getScaledCopy(gc.getWidth(),gc.getHeight()),0,0);
        g.drawImage(endScreen.getScaledCopy(gc.getWidth(),gc.getHeight()),0,0);
        g.setColor(Color.lightGray);
        //g.draw(back_rect);
        g.setFont(scoreFont);
        g.drawString(Score.homePoints + " : " + Score.awayPoints, gc.getWidth()*0.42f,gc.getHeight()*0.4f);

        Point temp = new Point(Mouse.getX(),gc.getHeight() - Mouse.getY());

        if (back_rect.contains(temp) || back_rect.intersects(temp) ){
            g.drawImage(endScreenHover.getScaledCopy(gc.getWidth(),gc.getHeight()),0,0);

            if (gc.getInput().isMousePressed(0)){
                Score.awayPoints = 0;
                Score.homePoints = 0;
                Score.maxPoints = 1;
                Score.maxTime = 1;
                sbg.enterState(0);
            }

        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }
}

import Entities.Ball;
import Entities.GameObject;
import Entities.Player;
import javafx.scene.shape.Shape;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class Board extends BasicGameState{


    Ball ball;

    Image background;

    List<GameObject> entities = new ArrayList<>();
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("/res/textures/board/football_pitch.png");

        entities.add(new Ball("name", gameContainer));
        entities.add(new Player(gameContainer.getWidth()/4,gameContainer.getHeight()*0.7f,"player1",gameContainer));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //background.draw(shiftX,shiftY,1.2f);
        g.drawImage(background.getScaledCopy(gc.getWidth(),gc.getHeight()),0 ,0);

        entities.forEach(e -> e.render(g));
    }
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {


        entities.forEach(e -> e.update(delta,gameContainer));    // UPDATES ALL ENTITIES.
    }

}

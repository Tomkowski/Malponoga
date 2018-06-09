import Camera.Camera;
import Entities.Ball;
import Entities.GameObject;
import Entities.Player;
import Entities.StaticFields;
import javafx.scene.shape.Shape;
import org.lwjgl.Sys;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;

public class Board extends BasicGameState{


    Ball ball;

    Image background;

    List<GameObject> entities = new ArrayList<>();

    Camera camera;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("/res/textures/board/football_pitch.png");

        entities.add(new Ball("name", gameContainer));
        entities.add(new Player(gameContainer.getWidth()/4,gameContainer.getHeight()*0.8f,"player1",gameContainer));

        camera = new Camera();

//        camera.focusOnPoint(Math.abs((entities.get(0).getX() + entities.get(1).getX())) / 2, StaticFields.lowPosition);
         camera.focusOnEntity(entities.get(1));
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.translate( - StaticFields.cameraZoom * camera.camX ,- StaticFields.cameraZoom * camera.camY);

        g.scale(StaticFields.cameraZoom,StaticFields.cameraZoom);


        g.drawImage(background.getScaledCopy(gc.getWidth(),gc.getHeight()),0 ,0);


        g.drawString(StaticFields.cameraZoom +"", entities.get(1).getX() , entities.get(1).getY() - 100 );

        entities.forEach(e -> e.render(g));
    }
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {


        entities.forEach(e -> e.update(delta,gameContainer));    // UPDATES ALL ENTITIES.

        camera.update(gameContainer);

        System.out.println(camera.camX+" "+camera.camY);

       StaticFields.cameraZoom = 2 - Math.abs(camera.getFocusObject().getX() - entities.get(0).getX()) / 1000;

       if(StaticFields.cameraZoom < 1) StaticFields.cameraZoom = 1;

    }

}

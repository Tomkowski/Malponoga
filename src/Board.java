import Camera.Camera;
import Entities.*;
import Entities.Ball;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Board extends BasicGameState{



    Image background;
    Image net;
    Image bground;
    int bgroundX;
    int bgroundY;
    List<GameObject> entities = new ArrayList<>();

    CollisionHandler collisionHandler;

    Camera camera;


    Rectangle leftGoal;
    Rectangle rightGoal;

    Line leftBar;
    Line rightBar;



    int result;
    int leftPlayerScore = 0;
    int rightPlayerScore = 0;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("/res/textures/board/stadium.png");
        net = new Image("/res/textures/board/net.png");
        bground = new Image("/res/textures/scoreboard/Bground.png");
        entities.add(new Ball(gameContainer.getWidth()/3 ,gameContainer.getHeight() * 0.4f ,"name" , gameContainer));
        entities.add(new Player(gameContainer.getWidth()/4 ,64,"player1" ,gameContainer, true));
        entities.add(new Player(gameContainer.getWidth()/1.5f ,64,"player2" ,gameContainer,  false));
        bgroundX=bground.getWidth();
        bgroundY=bground.getHeight();

        float width150 = gameContainer.getWidth() / 14f;
        float height300 = gameContainer.getHeight() / 3.6f;
        float height686 = gameContainer.getHeight() / 1.4f;

        leftGoal =  new Rectangle(gameContainer.getWidth()/23,height686,width150,height300);

        rightGoal =  new Rectangle(gameContainer.getWidth()/ 1.16f,height686,width150,height300);

        leftBar = new Line(0,height686*0.9f,gameContainer.getWidth()/20 + width150 * 1.2f, height686 * 0.9f);

        rightBar = new Line(gameContainer.getWidth()/ 1.16f  , height686 * 0.9f,gameContainer.getWidth() / 1.16f + 2 * width150 * 0.9f ,height686 * 0.9f);


                collisionHandler
                = new CollisionHandler(new ArrayList<>() {{ add(entities.get(1)); add(entities.get(2));}},
                leftGoal ,rightGoal ,leftBar,rightBar, (Ball) entities.get(0));


        camera = new Camera();

        camera.focusOnEntity(entities.get(0));

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.translate( - StaticFields.cameraZoom * camera.camX ,- StaticFields.cameraZoom * camera.camY);

        g.scale(StaticFields.cameraZoom,StaticFields.cameraZoom);
        g.drawImage(background.getScaledCopy(gc.getWidth(),gc.getHeight()),0 ,0);
        
        g.drawImage(bground.getScaledCopy(bgroundX,bgroundY), camera.camX, camera.camY);

        entities.forEach(e -> e.render(g));

        g.drawImage(net.getScaledCopy(gc.getWidth(),gc.getHeight()),0 ,0);




    }
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {



        entities.forEach(e -> e.update(delta,gameContainer));    // UPDATES ALL ENTITIES.

        camera.update(gameContainer);

        collisionHandler.checkForCollisions();

        collisionHandler.checkForBar();

        result =  collisionHandler.checkForGoal(); // RETURNS 0 IF NO GOAL IS SCORED - 1 FOR LEFT - (-1) FOR RIGHT

        if(result != 0){
            reset(result, gameContainer);
        }
       StaticFields.cameraZoom = 2 - Math.abs(entities.get(2).getX() - entities.get(1).getX()) / 1000;

       if(StaticFields.cameraZoom < 1) StaticFields.cameraZoom = 1;


        camera.focusOnPoint((entities.get(1).getX() + entities.get(2).getX())/2, (entities.get(1).getY() + entities.get(2).getY())/2);


    }

    private void reset(int result, GameContainer gameContainer){

        if(result == 1)
            rightPlayerScore++;

        else
            leftPlayerScore++;


        entities.get(0).setPosition(gameContainer.getHeight() * 0.5f , 64);

        entities.get(1).setPosition(gameContainer.getWidth() /4 , entities.get(1).getY());

        entities.get(2).setPosition(gameContainer.getWidth() /1.5f , entities.get(2).getY());

        ((Ball) entities.get(0)).setVelX((float) Math.random()*2 -1);

    }

    @Override
    public int getID() {
        return 1;
    }
}

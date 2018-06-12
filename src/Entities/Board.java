package Entities;

import Camera.Camera;
import Entities.*;
import Entities.Ball;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.awt.Font;

public class Board extends BasicGameState{


    Music spectators;
    Sound whistle;

    Image background;
    Image net;
    Image bground;
    Image tv;


    int bgroundX;
    int bgroundY;
    int time;

    String timeString;

    List<GameObject> entities = new ArrayList<>();

    CollisionHandler collisionHandler;


    Camera camera;
    TrueTypeFont scoreFont;

    Rectangle leftGoal;
    Rectangle rightGoal;

    Line leftBar;
    Line rightBar;

    static boolean endOfGame = false;

    int result;

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        spectators = new Music("/res/sound/spectators.ogg");
        spectators.loop();

        background = new Image("/res/textures/board/stadium.png");
        net = new Image("/res/textures/board/net.png");
        bground = new Image("/res/textures/scoreboard/Bground.png");
        tv = new Image("/res/textures/scoreboard/tv.png");

        entities.add(new Ball(gameContainer.getWidth()/3 ,gameContainer.getHeight() * 0.4f ,"name" , gameContainer));
        entities.add(new Player(gameContainer.getWidth()/4 ,64,"player1" ,gameContainer, true));
        entities.add(new Player(gameContainer.getWidth()/1.5f ,64,"player2" ,gameContainer,  false));



        whistle = new Sound("/res/sound/whistle/RefereeWhistle.wav");

        float width150 = gameContainer.getWidth() / 14f;
        float height300 = gameContainer.getHeight() / 3.6f;
        float height686 = gameContainer.getHeight() / 1.4f;

        leftGoal =  new Rectangle(gameContainer.getWidth()/23,height686,width150,height300);

        rightGoal =  new Rectangle(gameContainer.getWidth()/ 1.11f,height686,width150,height300);

        leftBar = new Line(0,height686*0.9f,gameContainer.getWidth()/20 + width150 * 1.2f, height686 * 0.9f);

        rightBar = new Line(gameContainer.getWidth()/ 1.16f  , height686 * 0.9f,gameContainer.getWidth() / 1.16f + 2 * width150 * 0.9f ,height686 * 0.9f);


                collisionHandler
                = new CollisionHandler(new ArrayList<GameObject>() {{ add(entities.get(1)); add(entities.get(2));}},
                leftGoal ,rightGoal ,leftBar,rightBar, (Ball) entities.get(0));


        camera = new Camera();

        camera.focusOnEntity(entities.get(0));

        time = 0;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.translate( - StaticFields.cameraZoom * camera.camX ,- StaticFields.cameraZoom * camera.camY);

        g.scale(StaticFields.cameraZoom,StaticFields.cameraZoom);
        g.drawImage(background.getScaledCopy(gc.getWidth(),gc.getHeight()),0 ,0);


        entities.forEach(e -> e.render(g));
        g.drawImage(net.getScaledCopy(gc.getWidth(),gc.getHeight()),0 ,0);


        g.translate(0,0);
        g.scale(1,1);
        g.drawImage(bground.getScaledCopy(1/StaticFields.cameraZoom), camera.camX, camera.camY);
        g.drawImage(tv.getScaledCopy(256,256).getScaledCopy(1/StaticFields.cameraZoom), camera.camX +gc.getWidth()/1.25f * (1/StaticFields.cameraZoom),camera.camY);


        //scoreFont = new TrueTypeFont(new Font("Helvetica", Font.PLAIN, 50), true);
        //g.setFont(new TrueTypeFont(new Font("Helvetica", Font.PLAIN, 50), false));
        g.drawString("HOME "+ Score.homePoints + " : " + Score.awayPoints + " AWAY", camera.camX + 10, camera.camY + 10);
        g.drawString(timeString, camera.camX + gc.getWidth()/8.6f, camera.camY + 10);

    }
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {

        time+=delta;
        entities.forEach(e -> e.update(delta,gameContainer));    // UPDATES ALL ENTITIES.

        camera.update(gameContainer);

        collisionHandler.checkForCollisions();

        collisionHandler.checkForBar();

        result =  collisionHandler.checkForGoal(); // RETURNS 0 IF NO GOAL IS SCORED - 1 FOR LEFT - (-1) FOR RIGHT

        if(result != 0){
            reset(result, gameContainer,delta);
        }
        if(timer == 0){


        camera.focusOnPoint((entities.get(1).getX() + entities.get(2).getX())/2, (entities.get(1).getY() + entities.get(2).getY())/2);
        StaticFields.cameraZoom = 2 - Math.abs(entities.get(2).getX() - entities.get(1).getX()) / 1000;

       if(StaticFields.cameraZoom < 1) StaticFields.cameraZoom = 1;

        int minutes = (time/1000 % 3600) / 60;
        int seconds = time/1000 % 60;

        timeString = String.format("%02d:%02d", minutes, seconds);


        checkEnd(minutes);

            if (endOfGame){
                endOfGame = false;
                time = 0;
                stateBasedGame.enterState(2);
            }

    }}

    public static int timer = 0;

    private void reset(int result, GameContainer gameContainer, int delta) throws SlickException {
        timer += delta;


        if (timer > 2000) {
            if (result == 1) {
                Score.awayPoints++;
                whistle.play();
            } else {
                whistle.play();
                Score.homePoints++;
            }



            entities.get(0).setPosition(gameContainer.getHeight() * 0.5f, 64);

            entities.get(1).setPosition(gameContainer.getWidth() / 4, entities.get(1).getY());

            entities.get(2).setPosition(gameContainer.getWidth() / 1.5f, entities.get(2).getY());



             camera.focusOnPoint((entities.get(1).getX() + entities.get(2).getX())/2, (entities.get(1).getY() + entities.get(2).getY())/2);
             StaticFields.cameraZoom = 2 - Math.abs(entities.get(2).getX() - entities.get(1).getX()) / 1000;
             entities.get(0).setVelX((float) (15 - Math.random() * 30));
            timer = 0;


        } else {
            entities.get(0).setVelX(Math.signum(entities.get(0).getVelX()) * 2.5f);
            camera.focusOnEntity(entities.get(0));
            StaticFields.cameraZoom = 5;
        }
    }



    @Override
    public int getID() {
        return 1;
    }

    private void checkEnd(int minutes){

        if (Score.homePoints == Score.maxPoints || Score.awayPoints == Score.maxPoints || Score.maxTime == minutes){
            endOfGame = true;
        }

    }
}

import Entities.Ball;
import Entities.CollisionHandler;
import Entities.Player;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;

public class Menu extends BasicGameState {

    public Menu(){
        super();
    }
    Image background;
    Image b_play;
    Image b_play_border;
    Image b_exit;
    Image b_exit_border;

    Rectangle playBox;
    Rectangle exitBox;

    CollisionHandler collisionHandler;
    CollisionHandler collisionHandler2;
    CollisionHandler collisionHandler3;
    CollisionHandler collisionHandler4;
    CollisionHandler collisionHandler5;


    Ball ball,ball1,ball2,ball3,ball4;


    Player p1,p2,p3,p4,p5,p6;

    int mouseX;
    int mouseY;

    boolean exit_flag = false;
    boolean play_flag = false;
    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("/res/textures/board/stadium.png");
        b_play = new Image("res/textures/menu/button_play.png");
        b_play_border = new Image("/res/textures/menu/button_play_border.png");
        playBox = new Rectangle(gameContainer.getWidth()/30,gameContainer.getHeight()/3,gameContainer.getWidth()/2, b_play.getHeight());

        b_exit = new Image("/res/textures/menu/button_exit.png");
        b_exit_border = new Image("/res/textures/menu/button_exit_border.png");
        exitBox = new Rectangle(gameContainer.getWidth()/30,gameContainer.getHeight()/2,gameContainer.getWidth()/2,b_exit.getHeight());

            ball = new Ball(gameContainer.getWidth()/1.9f , gameContainer.getHeight() /2f , "ball", gameContainer);
            ball1 = new Ball(gameContainer.getWidth()/1.8f , gameContainer.getHeight() /3.3f , "ball", gameContainer);
            ball2 = new Ball(gameContainer.getWidth()/1.75f , gameContainer.getHeight() /5f , "ball", gameContainer);
            ball3 = new Ball(gameContainer.getWidth()/2.7f , gameContainer.getHeight() /4f , "ball", gameContainer);
            ball4 = new Ball(gameContainer.getWidth()/1.85f , gameContainer.getHeight() /1.3f , "ball", gameContainer);


        p1 = new Player(gameContainer.getWidth()/6,gameContainer.getHeight() /1.3f,"",gameContainer,false);

                p2 = new Player(gameContainer.getWidth()/1.8f,gameContainer.getHeight() /1.3f,"",gameContainer,false);

                p3 = new Player(gameContainer.getWidth()/10f,gameContainer.getHeight() /1.3f,"",gameContainer,false);

                p4 = new Player(gameContainer.getWidth()/2.8f,gameContainer.getHeight() /1.3f,"",gameContainer,false);

                p5 = new Player(gameContainer.getWidth()/1.4f,gameContainer.getHeight() /1.3f,"",gameContainer,false);

                p6 = new Player(gameContainer.getWidth()/1.1f,gameContainer.getHeight() /1.3f,"",gameContainer,false);


        collisionHandler = new CollisionHandler(new ArrayList<>(){{add(p1); add(p2); add(p3); add(p4); add(p5); add(p6);}},new Rectangle(0,0,0,0),new Rectangle(0,0,0,0),
                new Line(0,0,0,0), new Line(0,0,0,0),ball1);
        collisionHandler2 = new CollisionHandler(new ArrayList<>(){{add(p1); add(p2); add(p3); add(p4); add(p5); add(p6);}},new Rectangle(0,0,0,0),new Rectangle(0,0,0,0),
                new Line(0,0,0,0), new Line(0,0,0,0),ball2);
        collisionHandler3 = new CollisionHandler(new ArrayList<>(){{add(p1); add(p2); add(p3); add(p4); add(p5); add(p6);}},new Rectangle(0,0,0,0),new Rectangle(0,0,0,0),
                new Line(0,0,0,0), new Line(0,0,0,0),ball3);
        collisionHandler4 = new CollisionHandler(new ArrayList<>(){{add(p1); add(p2); add(p3); add(p4); add(p5); add(p6);}},new Rectangle(0,0,0,0),new Rectangle(0,0,0,0),
                new Line(0,0,0,0), new Line(0,0,0,0),ball4);
        collisionHandler5 = new CollisionHandler(new ArrayList<>(){{add(p1); add(p2); add(p3); add(p4); add(p5); add(p6);}},new Rectangle(0,0,0,0),new Rectangle(0,0,0,0),
                new Line(0,0,0,0), new Line(0,0,0,0),ball);



    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background.getScaledCopy(gc.getWidth(),gc.getHeight()),
                0,0);
        ball.render(g);
        ball1.render(g);
        ball2.render(g);
        ball3.render(g);
        ball4.render(g);

        g.drawImage(hoverPlay(mouseX,mouseY),playBox.getX(),playBox.getY());
        g.drawImage(hoverExit(mouseX,mouseY),exitBox.getX(),exitBox.getY());


    }

    private Image hoverPlay(int x, int y){

        if (playBox.contains(x,y)){

            play_flag = true;
            return b_play_border;
        }
        else
        {
            play_flag = false;
            return b_play;
        }
    }

    private Image hoverExit(int x, int y){
        if (exitBox.contains(x,y)){

            exit_flag = true;
            return b_exit_border;
        }
        else
        {
            exit_flag = false;
            return b_exit;
        }
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Input input = gameContainer.getInput();

        mouseX = input.getMouseY();
        mouseY = input.getMouseY();

        if(exit_flag && input.isMousePressed(0)) gameContainer.exit();
        if(play_flag && input.isMousePressed(0)) stateBasedGame.enterState(1);

        ball.update(delta,gameContainer);
        ball1.update(delta,gameContainer);
        ball2.update(delta,gameContainer);
        ball3.update(delta,gameContainer);
        ball4.update(delta,gameContainer);

        collisionHandler.checkForCollisions();
        collisionHandler2.checkForCollisions();
        collisionHandler3.checkForCollisions();
        collisionHandler4.checkForCollisions();
        collisionHandler5.checkForCollisions();


    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

}

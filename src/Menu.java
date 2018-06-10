import Entities.Ball;
import Entities.CollisionHandler;
import Entities.Player;
import org.lwjgl.Sys;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
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
    Image b_start_hover;

    Image b_score_hover1, b_score_hover2, b_time_hover1, b_time_hover2;

    Image b_score;
    Image b_time;
    Image b_start;

    Rectangle playBox;
    Rectangle exitBox;
    Rectangle scoreBox;
    Rectangle timeBox;
    Rectangle startBox;

    Rectangle up1, down1, up2, down2;

    CollisionHandler collisionHandler;
    CollisionHandler collisionHandler2;
    CollisionHandler collisionHandler3;
    CollisionHandler collisionHandler4;
    CollisionHandler collisionHandler5;

    int timer = 0;


    Ball ball,ball1,ball2,ball3,ball4;


    Player p1,p2,p3,p4,p5,p6;

    int mouseX;
    int mouseY;

    int score_value = 0;
    int time_value = 0;

    boolean exit_flag = false;
    boolean play_flag = false;
    boolean started = false;
    boolean fallen = false;


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

        b_score = new Image("/res/textures/game_menu/b1.png");
        b_time = new Image("/res/textures/game_menu/b2.png");
        b_start = new Image("/res/textures/game_menu/button_start.png");
        b_start_hover = new Image(("/res/textures/game_menu/button_start_hover.png"));

        b_score_hover1 = new Image("/res/textures/game_menu/b1_hover_fst.png");
        b_score_hover2 = new Image("/res/textures/game_menu/b1_hover_scd.png");
        b_time_hover1 = new Image("/res/textures/game_menu/b2_hover_fst.png");
        b_time_hover2 = new Image("/res/textures/game_menu/b2_hover_scd.png");

        scoreBox = new Rectangle(gameContainer.getWidth()/3,gameContainer.getHeight()/2.3f,gameContainer.getWidth()/2, b_score.getHeight());
        timeBox = new Rectangle(gameContainer.getWidth()/3,gameContainer.getHeight()/2,gameContainer.getWidth()/2, b_time.getHeight());
        startBox = new Rectangle(gameContainer.getWidth()/3,gameContainer.getHeight()/1.76f,gameContainer.getWidth()/2, b_start.getHeight());


        up1 = new Rectangle(scoreBox.getX() + 0.56f * scoreBox.getWidth(),
                scoreBox.getY(), scoreBox.getHeight(), scoreBox.getHeight());

        down1 = new Rectangle(scoreBox.getX() + 0.63f * scoreBox.getWidth(),
                scoreBox.getY(), scoreBox.getHeight(), scoreBox.getHeight());

        up2 = new Rectangle(timeBox.getX() + 0.56f * timeBox.getWidth(),
                timeBox.getY(), timeBox.getHeight(), timeBox.getHeight());

        down2 = new Rectangle(timeBox.getX() + 0.63f * timeBox.getWidth(),
                timeBox.getY(), timeBox.getHeight(), timeBox.getHeight());


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

        if (fallen){
            g.drawImage(b_score, scoreBox.getX(), scoreBox.getY());
            g.drawImage(b_time, timeBox.getX(), timeBox.getY());
            g.drawImage(b_start, startBox.getX(), startBox.getY());
            g.setColor(Color.white);
            g.drawString(String.valueOf(score_value), scoreBox.getX() + 0.5f * scoreBox.getWidth(), scoreBox.getY() + 1/4f * scoreBox.getHeight());
            g.drawString(String.valueOf(time_value), timeBox.getX() + 0.5f * scoreBox.getWidth(), timeBox.getY() + 1/4f * scoreBox.getHeight());


            Point temp = new Point(Mouse.getX(),gc.getHeight() - Mouse.getY());

            if (up1.contains(temp) || up1.intersects(temp) ){
                g.drawImage(b_score_hover1, scoreBox.getX(), scoreBox.getY());
                g.drawString(String.valueOf(score_value), scoreBox.getX() + 0.5f * scoreBox.getWidth(), scoreBox.getY() + 1/4f * scoreBox.getHeight());

                if (gc.getInput().isMousePressed(0))
                    if (score_value < 10)
                        score_value++;
            }

            else if (down1.contains(temp) || down1.intersects(temp)){
                g.drawImage(b_score_hover2, scoreBox.getX(), scoreBox.getY());
                g.drawString(String.valueOf(score_value), scoreBox.getX() + 0.5f * scoreBox.getWidth(), scoreBox.getY() + 1/4f * scoreBox.getHeight());

                if (gc.getInput().isMousePressed(0))
                    if (score_value > 0)
                        score_value--;
            }

            else if (up2.contains(temp) || up2.intersects(temp)){
                g.drawImage(b_time_hover1, timeBox.getX(), timeBox.getY());
                g.drawString(String.valueOf(time_value), timeBox.getX() + 0.5f * scoreBox.getWidth(), timeBox.getY() + 1/4f * scoreBox.getHeight());

                if (gc.getInput().isMousePressed(0))
                    if (time_value < 6)
                        time_value++;
            }

            else if (down2.contains(temp) || down2.intersects(temp) ){
                g.drawImage(b_time_hover2, timeBox.getX(), timeBox.getY());
                g.drawString(String.valueOf(time_value), timeBox.getX() + 0.5f * scoreBox.getWidth(), timeBox.getY() + 1/4f * scoreBox.getHeight());

                if (gc.getInput().isMousePressed(0))
                    if (time_value > 0)
                        time_value--;
            }

            else if (startBox.contains(temp) || startBox.intersects(temp)){
                g.drawImage(b_start_hover, startBox.getX(), startBox.getY());

                if (gc.getInput().isMousePressed(0))
                    sbg.enterState(1);
            }
        }



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

        mouseX = input.getMouseX();
        mouseY = input.getMouseY();

        if(exit_flag && input.isMousePressed(0)) gameContainer.exit();
        if(play_flag && input.isMousePressed(0)) {
            started = true;
        }

        if (started){
            playBox.setY(playBox.getY() + 10);
            exitBox.setY(exitBox.getY() + 10);
        }

        if (playBox.getY() > gameContainer.getHeight())
            fallen = true;


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

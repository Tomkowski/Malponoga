import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Board extends BasicGameState{


    Ball ball;

    Image background;
    Animation footballer1, movingUp, movingDown, movingLeft, movingRight;
    float fpositionX=200;
    float fpositionY=-200;
    float shiftX=fpositionX+200; /// na środku ma być uzależnić od danych użytkownika
    float shiftY=fpositionY+865; /// tu też
    int [] duration = {200,200};
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("/res/textures/board/football_pitch.png");
        Image [] walkup = {new Image ("/res/textures/footballers/up1.png"), new Image ("/res/textures/footballers/up1.png")};
        Image [] walkdown = {new Image ("/res/textures/footballers/down1.png"), new Image ("/res/textures/footballers/down1.png")};
        Image [] walkleft = {new Image ("/res/textures/footballers/left1.png"), new Image ("/res/textures/footballers/left1.png")};
        Image [] walkright = {new Image ("/res/textures/footballers/right1.png"), new Image ("/res/textures/footballers/right1.png")};
        movingUp = new Animation(walkup, duration, false);
        movingLeft = new Animation(walkleft, duration, false);
        movingRight = new Animation(walkright, duration, false);
        movingDown = new Animation(walkdown, duration, false);
        footballer1 = movingDown;
        ball = new Ball();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.drawImage(background,fpositionX,fpositionY);
        //footballer1.draw(shiftX,shiftY);
        g.drawString("Pozycja piłkarza\n X: "+fpositionX+" Y:"+fpositionY,400,22);
        ball.render(g);
    }
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Input input = gameContainer.getInput();
        /*if(input.isKeyDown(Input.KEY_UP)){
            footballer1 = movingUp;
            fpositionY += delta * .1f;
            if(fpositionY>160){
                fpositionY -= delta * .1f;
            }
        }
        if(input.isKeyDown(Input.KEY_DOWN)){
            footballer1 = movingDown;
            fpositionY -= delta * .1f;
            if(fpositionY<-836){
                fpositionY += delta * .1f;
            }
        }*/
        if(input.isKeyDown(Input.KEY_LEFT)){
            footballer1 = movingLeft;
            fpositionX += delta * .1f;
            if(fpositionX>318){
                fpositionX -= delta * .1f;
            }
            //if(fpositionX>-0.6){
            //    shiftX += delta *.1f;
            //}
        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            footballer1 = movingRight;
            fpositionX -= delta * .1f;
            if(fpositionX<-1283){
                fpositionX += delta * .1f;
            }
        }

        ball.update(delta);
    }

}

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Board extends BasicGameState{


    Image background;
    Animation footballer1, movingUp, movingDown, movingLeft, movingRight;
    float shiftX=700;
    float shiftY=850;
    float fpositionX=shiftX-700; /// na środku ma być uzależnić od danych użytkownika
    float fpositionY=-200; /// tu też
    int [] duration = {200,200};
    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        background = new Image("/res/textures/board/football_pitch.png");
        Image [] walkleft = {new Image ("/res/textures/footballers/left1.png"), new Image ("/res/textures/footballers/left1.png")};
        Image [] walkright = {new Image ("/res/textures/footballers/right1.png"), new Image ("/res/textures/footballers/right1.png")};
        movingLeft = new Animation(walkleft, duration, false);
        movingRight = new Animation(walkright, duration, false);
        footballer1 = movingLeft;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //background.draw(shiftX,shiftY,1.2f);
        g.translate((int)fpositionX,(int)fpositionY);
        g.drawImage(background,0 ,0);
        footballer1.draw(shiftX,shiftY);
        g.drawString("Pozycja piłkarza\n X: "+fpositionX+" Y:"+fpositionY,400,22);
    }
    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Input input = gameContainer.getInput();
        float speed=.2f;
        if(input.isKeyDown(Input.KEY_LEFT)){
            footballer1 = movingLeft;
            // shiftX gracz, fpositionX kamera
            if(shiftX<=700&&shiftX>=0){
                shiftX -= delta * speed;
            }
            else if(shiftX>700&&shiftX<1200){
                shiftX -= delta *speed;
                fpositionX += delta *speed;
            }
            else if(shiftX>=1200&&shiftX<1730){
                shiftX -= delta *speed;
            }


        }
        if(input.isKeyDown(Input.KEY_RIGHT)){
            footballer1 = movingRight;
            if(shiftX>=-10&&shiftX<=700){
                shiftX += delta * speed;
            }
            else if(shiftX>700&&shiftX<1200){
                shiftX += delta *speed;
                fpositionX -= delta *speed;
            }
            else if(shiftX>=1200&&shiftX<1722) {
                shiftX += delta * speed;
            }
        }
    }

}

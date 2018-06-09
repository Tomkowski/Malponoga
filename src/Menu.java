import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

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
        background = new Image("/res/textures/menu/background.jpg");
        b_play = new Image("res/textures/menu/button_play.png");
        b_play_border = new Image("/res/textures/menu/button_play_border.png");
        playBox = new Rectangle(gameContainer.getWidth()/30,gameContainer.getHeight()/3,b_play.getWidth(), b_play.getHeight());

        b_exit = new Image("/res/textures/menu/button_exit.png");
        b_exit_border = new Image("/res/textures/menu/button_exit_border.png");
        exitBox = new Rectangle(gameContainer.getWidth()/30,gameContainer.getHeight()/2,b_exit.getWidth(),b_exit.getHeight());


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        /*g.drawImage(background,0,0, Display.getWidth(), Display.getHeight(),
                0,0,background.getWidth(), background.getHeight(),null);*/
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
    }

    @Override
    public void enter(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

}

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main  extends StateBasedGame
{
    private static final String name = "Name of the game";
    private final static int menu = 0;
    public Main(String name) throws SlickException {
        super(name);
        this.addState(new Menu());
        //this.addState(new Intro());
        //this.addState(new Level1("/res/maps/level1.tmx"));
        //this.addState(new Level("/res/worlds/map4.tmx",1));
        //this.addState(new Level("/res/worlds/map2.tmx",2));
        //this.addState(new Level("/res/worlds/map3.tmx",3));
        //this.addState(new Level("/res/worlds/map5.tmx",4));

    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.enterState(0);
    }
    public static void main(String[] args) throws SlickException, LWJGLException {

        AppGameContainer appgc = null;
        try {
            appgc = new AppGameContainer(new Main(name));
        } catch (SlickException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        appgc.setTargetFrameRate(60);
        //   appgc.setFullscreen(true);
        appgc.setShowFPS(true);

        DisplayMode[] modes = Display.getAvailableDisplayModes();

        appgc.setDisplayMode(modes[0].getWidth(),modes[0].getHeight(), true);

        appgc.start();

    }


}

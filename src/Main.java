import Entities.Board;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Main  extends StateBasedGame
{
    private static final String name = "Małponoga";
    public Main(String name) throws SlickException {
        super(name);
        this.addState(new Menu());
        this.addState(new Board());

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
       // appgc.setTargetFrameRate(60);
       // appgc.setShowFPS(true);

        DisplayMode[] modes = Display.getAvailableDisplayModes();
        int w_max = 0, h_max = 0;

        for (DisplayMode v : modes){
            if (v.getWidth() > w_max){
                w_max = v.getWidth();
                h_max = v.getHeight();
            }
        }

       // appgc.setDisplayMode(modes[0].getWidth(),modes[0].getHeight(), true);
        appgc.setDisplayMode(w_max, h_max,true);

        appgc.start();

    }


}

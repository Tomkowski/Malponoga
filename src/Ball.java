import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball {

    Image ballImage;

    Image arena;
    int ballX, ballY;

    Ball() throws SlickException {
        ballImage = new Image("res/textures/ball.png").getScaledCopy(64,64);
        arena = new Image("res/textures/pitch.png");
        ballX = 500;
        ballY = 200;
    }

    public void update (int delta){
        //if (ballImage != null)
        fallDown(delta);

    }

    public void render(Graphics g){
        if (arena != null)
            g.drawImage(arena, 0,0);

        if (ballImage != null)
            g.drawImage(ballImage, ballX, ballY);


    }


    void fallDown(int delta){
        if (ballY < 600)
            ballY += 0.5f * delta;
    }
}

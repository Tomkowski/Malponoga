import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball {

    Image ballImage;

    double lastBounce = 0;
    double vel = 1.5f;
    int ballX, ballY;
    int lowPosition = 676;

    boolean falling = true;

    Ball() throws SlickException {
        ballImage = new Image("res/textures/ball.png").getScaledCopy(64,64);
        ballX = 600;
        ballY = 200;
    }

    public void update (int delta){
        //if (ballImage != null)
        move(delta);

    }

    public void render(Graphics g){

        if (ballImage != null)
            g.drawImage(ballImage, ballX, ballY);


    }


    void move (int delta){


        //System.out.println(vel + " " + ballY);


        // go down
        ballY += vel;

        if (vel != 0)
            vel += 0.04f * delta;



        // decrease vel when it touch the floor
        // val > 0 because there was a loop
        if (ballY > lowPosition && vel > 0){
            vel = vel * -0.65f;
            System.out.println("FLOOR");


            if (Math.abs(vel - lastBounce) < 0.05f)
                vel = 0;

            lastBounce = vel;

        }

    }

}

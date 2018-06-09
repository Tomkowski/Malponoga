package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Ball extends GameObject{

    Image ballImage;

    double lastBounce = 0;
    double vel = 1.5f;
    float ballX;
    float ballY;
    float lowPosition;


    public Ball(String name, GameContainer gameContainer) {
        super(name);
        init(gameContainer);

    }

    public void update (int delta, GameContainer gameContainer){
        //if (ballImage != null)
        move(delta, gameContainer);

    }

    @Override
    public void init(GameContainer gameContainer) {
        try {
            ballImage = new Image("res/textures/ball.png").getScaledCopy(64,64);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        ballX = gameContainer.getWidth()/3;
        ballY = gameContainer.getHeight() * 0.4f;
        lowPosition = gameContainer.getHeight() * 0.8f;
    }

    @Override
    public float getX() {
        return ballX;
    }

    @Override
    public float getY() {
        return ballY;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void render(Graphics g){

        if (ballImage != null)
            g.drawImage(ballImage, ballX, ballY);


    }


    void move (int delta, GameContainer gameContainer){


        // go down
        ballY += vel;

        if (vel != 0)
            vel += 0.04f * delta;



        // decrease vel when it touch the floor
        // val > 0 because there was a loop
        if (ballY > lowPosition && vel > 0){
            vel = vel * -0.65f;

            if (Math.abs(vel - lastBounce) < 0.05f)
                vel = 0;

            lastBounce = vel;

        }

    }

}

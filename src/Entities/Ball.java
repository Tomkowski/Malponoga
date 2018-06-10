package Entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;

public class Ball extends GameObject{

    Image ballImage;

    float velY = 0.4f;
    float velX;
    float ballX;
    float ballY;

    boolean collisionFromRight = false;
    boolean collisionFromLeft = false;




    public Ball(float x, float y, String name, GameContainer gameContainer) {
        super(name, new Circle(x, y, 16));
        this.x = x;
        this.y = y;
        init(gameContainer);

    }

    public void update (int delta, GameContainer gameContainer){
        //if (ballImage != null)
        collisionBox = new Circle(ballX + 16, ballY + 16, 16);


        move(delta, gameContainer);

    }

    public void callBack(int flag){
        switch (flag){
            case 0:
                collisionFromRight = false;
                collisionFromLeft = false;
                return;
            case 1:
                collisionFromRight = true;
                velX = -8f;
                velY = -15f;
                break;
            case -1:
                collisionFromLeft = true;
                velX = 8f;
                velY = -15f;
                break;
            default:

                return;
        }
    }

    @Override
    public void init(GameContainer gameContainer) {
        try {
            ballImage = new Image("res/textures/ball.png").getScaledCopy(32,32);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        ballX = x;
        ballY = y;
        StaticFields.lowPosition = gameContainer.getHeight() * 0.8f;


    }

    @Override
    public void setPosition(float x, float y) {
        ballX = x;
        ballY = y;
    }

    public void setVelX(float x){
        velX = x;
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

   //     g.fill(collisionBox);

    }


    private void move(int delta, GameContainer gameContainer){

        if(ballX <= 0 && velX < 0) velX *= -1;

        if(ballX >= gameContainer.getWidth() - 32 && velX > 0) velX *= -1;

        if(ballY < StaticFields.lowPosition)
        ballY += velY;

        if(ballY >= StaticFields.lowPosition && velY > 0) {
            velY *= -0.65f;
            ballY = StaticFields.lowPosition - 1;
        }


        velY += 0.04f * delta;


        ballX += velX;

        velX -= Math.signum(velX) * 0.001f * delta;

        ballImage.rotate(velX);




    }




}

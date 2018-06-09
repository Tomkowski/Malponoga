package Entities;

import org.newdawn.slick.*;

public class Player extends GameObject {

    SpriteSheet walkRightSheet;
    SpriteSheet walkLeftSheet;
    SpriteSheet jumpLeftSheet;
    SpriteSheet jumpRightSheet;
    SpriteSheet idleSheet;

    Animation walkLeft;
    Animation walkRight;
    Animation jumpLeft;
    Animation jumpRight;
    Animation idle;

    Animation currentAnimation;


    float x =700;
    float shiftY=850;
    float fpositionX= x -700; /// na środku ma być uzależnić od danych użytkownika
    float fpositionY=-200; /// tu też


    final int CROPPER_SIZE_X = 64;
    final int CROPPER_SIZE_Y = 128;

    public Player(float x, float y, String name, GameContainer gameContainer) {
        super(x, y, name);
        try {
            init(gameContainer);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void render(Graphics g){

        g.translate((int)fpositionX,(int)fpositionY);

        if(currentAnimation != null)
        g.drawAnimation(currentAnimation,x,y);
        g.drawString("Pozycja piłkarza\n X: "+fpositionX+" Y:"+fpositionY,400,22);

    }

    @Override
    public void update(int delta , GameContainer gameContainer) {
        Input input = gameContainer.getInput();

        if(input.isKeyDown(Input.KEY_LEFT)){
            currentAnimation = walkLeft;
         if(currentAnimation.isStopped()) currentAnimation.start();

           if(x > 0)
           x -= delta * 0.2f;
           else x = 1;

        }else
        if(input.isKeyDown(Input.KEY_RIGHT)){
            currentAnimation = walkRight;

        if(currentAnimation.isStopped()) currentAnimation.start();

        if(x < gameContainer.getWidth())
          x += delta * 0.2f;
        else x = gameContainer.getWidth() - 1;
        }
        else {
            currentAnimation = idle;
            if(currentAnimation.isStopped()) currentAnimation.start();
        }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        Image mainSheet = new Image("/res/textures/footballers/playerSpriteSheet.png");  // j r - j l -r r - r l - idle

        jumpRightSheet = new SpriteSheet(mainSheet.getSubImage(0,0,5 * CROPPER_SIZE_X, CROPPER_SIZE_Y),CROPPER_SIZE_X,CROPPER_SIZE_Y);
        jumpLeftSheet = new SpriteSheet(mainSheet.getSubImage(0,CROPPER_SIZE_Y,5 * CROPPER_SIZE_X, CROPPER_SIZE_Y),CROPPER_SIZE_X,CROPPER_SIZE_Y);
        walkRightSheet = new SpriteSheet(mainSheet.getSubImage(0,CROPPER_SIZE_Y * 2,5 * CROPPER_SIZE_X, CROPPER_SIZE_Y),CROPPER_SIZE_X,CROPPER_SIZE_Y);
        walkLeftSheet = new SpriteSheet(mainSheet.getSubImage(0,CROPPER_SIZE_Y * 3,5 * CROPPER_SIZE_X, CROPPER_SIZE_Y),CROPPER_SIZE_X,CROPPER_SIZE_Y);
        idleSheet = new SpriteSheet(mainSheet.getSubImage(0,CROPPER_SIZE_Y * 4,5 * CROPPER_SIZE_X, CROPPER_SIZE_Y),CROPPER_SIZE_X,CROPPER_SIZE_Y);

        int duration = 200;
        jumpRight = new Animation(jumpRightSheet,duration);
        jumpLeft = new Animation(jumpLeftSheet,duration);
        walkLeft = new Animation(walkLeftSheet,duration);
        walkRight = new Animation(walkRightSheet,duration);
        idle = new Animation(idleSheet,duration);

        /*
        jumpRight.setPingPong(true);
        jumpLeft.setPingPong(true);
        walkRight.setPingPong(true);
        walkLeft.setPingPong(true);
*/
        currentAnimation = idle;
       // if(currentAnimation == null) System.exit(-1);
    }
}

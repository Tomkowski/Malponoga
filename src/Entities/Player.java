package Entities;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

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

    boolean moveRight = false;
    boolean moveLeft = false;
    boolean jumpingRight = false;
    boolean jumpingLeft = false;

    boolean jumping = false;
    boolean jumpable = false;


    float verSpeed = 0;
    float gravity = 0.5f;
    float jumpStrength = -12;


    float playerX;
    float playerY;

    final float PLAYER_SPEED = 0.4f;


    final int CROPPER_SIZE_X = 64;
    final int CROPPER_SIZE_Y = 128;



    public Player(float x, float y, String name, GameContainer gameContainer) {


        super(x, y, name, new Rectangle(x,y - 64,64,128));


        playerX = x;

        playerY = y;

        try {
            init(gameContainer);
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public float getX() {
        return playerX;
    }

    @Override
    public float getY() {
        return playerY;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void render(Graphics g){



        if(currentAnimation != null)
        g.drawAnimation(currentAnimation,playerX,playerY - 64);


    //    g.fill(collisionBox);
      //  g.setColor(Color.black);
     //   g.drawString(getY() +" "+ StaticFields.lowPosition,playerX,playerY - 100);

    }

    @Override
    public void update(int delta , GameContainer gameContainer) {

        collisionBox = new Rectangle(playerX,playerY - 64,64,128);

        Input input = gameContainer.getInput();

        if(input.isKeyDown(Input.KEY_A)){
            moveLeft = true;
            moveRight = false;

            if (!jumping)
                currentAnimation = walkLeft;

            if(currentAnimation.isStopped())
                currentAnimation.start();

           if(playerX > 0)
                playerX -= delta * PLAYER_SPEED;
           else
               playerX = 0;

        }

        else if(input.isKeyDown(Input.KEY_D)) {
            moveRight = true;
            moveLeft = false;

            if (!jumping)
                currentAnimation = walkRight;

            if (currentAnimation.isStopped())
                currentAnimation.start();

            if (playerX < gameContainer.getWidth() - 64)
                playerX += delta * PLAYER_SPEED;
            else
                playerX = gameContainer.getWidth() - 64;
        }


        verSpeed += gravity;


        if (input.isKeyDown(Input.KEY_W) ){

            if (jumpable){
                jumping = true;
                jumpable = false;
                verSpeed = jumpStrength;

                if (moveRight){
                    currentAnimation = jumpRight;

                    if(currentAnimation.isStopped())
                        currentAnimation.start();
                }

                if (moveLeft){
                    currentAnimation = jumpLeft;

                    if(currentAnimation.isStopped())
                        currentAnimation.start();
                }
            }
        }

        playerY += verSpeed;

        if (playerY > StaticFields.lowPosition){
           // y = y - verSpeed;
            playerY  = StaticFields.lowPosition;
            verSpeed = 0;
            jumping = false;
            jumpable = true;

            if(!input.isKeyDown(Input.KEY_A) && !input.isKeyDown(Input.KEY_D)){
                currentAnimation = idle;
                if (currentAnimation.isStopped()) currentAnimation.start();
            }
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

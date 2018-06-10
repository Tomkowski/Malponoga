package Entities;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Shape;

public class SandalBonus extends Bonus {

    float xPos;
    float yPos;

    float timer = 0;

    public SandalBonus(Shape collisionBox) throws SlickException {
        super(0, 0,"sandal", collisionBox);

        image = new Image("/res/bonus/sandals.png");

        visible = false;

    }


    @Override
    public void affectGameObject(GameObject gameObject, Ball ball) {
         gameObject.setVelX(2 * gameObject.getVelX());
    }

    @Override
    public void reverseEffect(GameObject gameObject, Ball ball) {
        if(gameObject != null && ball != null)
        gameObject.setVelX(0.5f * gameObject.getVelX());
    }

    @Override
    public float getX() {
        return xPos;
    }

    @Override
    public float getY() {
        return yPos;
    }

    @Override
    public String getName() {
        return super.name;
    }

    @Override
    public void render(Graphics g) {
     g.fill(collisionBox);}

    @Override
    public void update(int delta, GameContainer gameContainer) {
         timer+=delta;

         if(timer > 8000) {

             float x = (float) (gameContainer.getWidth()/4 + .5f * gameContainer.getWidth() * Math.random());

             float y = (float) (gameContainer.getHeight() + gameContainer.getHeight()/2 * Math.random() - StaticFields.lowPosition);


             setPosition(x,y); // any width - low enough to be hit by a ball.
             collisionBox = new Circle(xPos + 32, yPos + 32, 32);




             timer = 0;
             if(!visible) reverseEffect(gameObject,ball);
             visible = !visible;

         }
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

    }

    @Override
    public void setPosition(float x, float y) {
         xPos = x;
         yPos = y;

         this.x = x;
         this.y = y;
    }

    @Override
    public boolean collidesWith(GameObject gameObject){
        return (collisionBox.intersects(gameObject.collisionBox) || collisionBox.contains(gameObject.collisionBox)) || gameObject.collisionBox.contains(collisionBox);
    }


}

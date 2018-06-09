package Camera;

import Entities.GameObject;
import Entities.Player;
import Entities.StaticFields;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;


public class Camera {

    public int offsetMaxX;
    public int offsetMaxY;
    public int offsetMinX;
    public int offsetMinY;

    public float camX;
    public float camY;

    private GameObject gameObject;

    public Camera() {

        //Setting offset max's and minimums
        offsetMaxX = 0 ;
        offsetMaxY = 0;
        offsetMinX = 0;
        offsetMinY = 0;


    }

    public void focusOnEntity(GameObject gameObject){
     this.gameObject = gameObject;
    }

    public void focusOnPoint(float x, float y){
        this.gameObject = new Player(x,y,"middle", null);
    }

    public GameObject getFocusObject(){
        return gameObject;
    }

    public void update(GameContainer gc) {

        camX =  (int)gameObject.getX() - (gc.getWidth() / (StaticFields.cameraZoom * 2)); // scale * 2

        if(camX < 0) camX = 0;

        if(camX >  gc.getWidth() * (  (1 - ( 1.0f / StaticFields.cameraZoom))))  camX =  gc.getWidth() * (  (1 - ( 1.0f / StaticFields.cameraZoom))); //weird formula I came up with to solve this problem



        camY =  (int)gameObject.getY() - (gc.getHeight() / (StaticFields.cameraZoom * 2));

        if(camY < 0) camY = 0;

        if(camY >  gc.getHeight() * (  (1 - ( 1.0f / StaticFields.cameraZoom))))  camY =  gc.getHeight() * (  (1 - ( 1.0f / StaticFields.cameraZoom))); //weird formula I came up with to solve this problem

    }

}
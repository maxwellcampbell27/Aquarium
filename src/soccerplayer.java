import java.awt.*;

public class soccerplayer {

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the player in the x direction
    public int dy;                    //the speed of the player in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle hitbox;
    public boolean isEast;
    public boolean isWest;
    public boolean isNorth;
    public boolean isSouth;

    public soccerplayer(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 3;
        dy = 6;
        width = 60;
        height = 60;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
    }


public void move(){


    if (isNorth == true) {
        dy = -2;
    }
    if (isNorth == false && isSouth == false) {
        dy = 0;
    }
    if (isSouth == true) {
        dy = 5;
    }
    if (isEast == true) {
        dx = 5;
    }
    if (isEast == false && isWest == false) {
        dx = 0;
    }

    if (isWest == true) {
        dx = -5;
    }
            if (xpos < 0) { //bounce off the left wall
                dx = -dx;
            }

            //bounce off right wall
            if (xpos > 950) {
                dx = -dx;
            }
            //bounce off top wall
            if (ypos < 0) {
                dy = -dy;
            }
            //bounce off the bottom wall
            if (ypos > 650) {
                dy = -dy;
            }
            xpos = xpos + dx;
            ypos = ypos + dy;

    hitbox = new Rectangle(xpos,ypos,width,height);
        }



}
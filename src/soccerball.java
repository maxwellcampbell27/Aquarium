import java.awt.*;

public class soccerball {


    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the ball in the x direction
    public int dy;                    //the speed of the ball in the y direction
    public int width;
    public int height;
    public boolean isAlive;             //a boolean to denote if the ball is alive or dead.
    public Rectangle hitbox;
    public boolean isCrashing = true;

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows to specify the ball's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public soccerball(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 3;
        dy = 3;
        width = 50;
        height = 50;
        isAlive = true;
        hitbox = new Rectangle(xpos,ypos,width,height);
    }

    // constructor
    // The move method.  Everytime this is run (or "called") the ball's x position and y position change by dx and dy
    public void move(){
//        if (xpos < 0) { //bounce off the left wall
//            dx = -dx;
//        }
//
//        //bounce off right wall
//        if (xpos > 950) {
//            dx = -dx;
 //       }
        //bounce off top wall
        if (ypos < 0) {
            dy = -dy;
        }
        //bounce off the bottom wall
        if (ypos > 650) {
            dy = -dy;
        }

        //wrapping horizontally

        if(xpos>900){
            xpos = -width;
        }

        if (xpos+width<0){

            xpos=900;

        }

        if(xpos<-900){
            xpos = -width;
            ypos=-1;
        }
        if (xpos+width<0){

            xpos=-900;
        }

        xpos = xpos + dx;
        ypos = ypos + dy;

        hitbox = new Rectangle(xpos,ypos,width,height);

    }
}
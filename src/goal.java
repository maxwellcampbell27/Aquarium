import java.awt.*;

public class goal {
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the goal in the x direction
    public int dy;                    //the speed of the goal in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    public Rectangle hitbox;
    public boolean isCrashing;

    public goal(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 0;
        dy = 0;
        width = 100;
        height = 250;
        isAlive = false;
        hitbox = new Rectangle(xpos,ypos,width,height);
        isCrashing = false;
    }

}

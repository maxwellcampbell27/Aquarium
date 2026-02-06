public class soccerplayer {

    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;

    public soccerplayer(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 3;
        dy = 6;
        width = 60;
        height = 60;
        isAlive = true;



    }

    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
    }

}

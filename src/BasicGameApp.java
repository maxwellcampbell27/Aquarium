//Basic Game Application
//Version 2
// Basic Object, Image, Movement

// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.security.Key;
import javax.swing.JFrame;
import javax.swing.JPanel;

//TODO: ADD COMMENTS
//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable, KeyListener, MouseListener {

   //Variable Definition Section
   //Declare the variables used in the program
   //You can set their initial values too

   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;

	public BufferStrategy bufferStrategy;

   public Image soccerfield;
    public Image soccerballPic;
    public Image soccerPlayerPic;
    public Image soccerPlayer2Pic;
    public Image goalPic;
    public Image goal2Pic;
    public Image fanPic;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type

    private soccerball soccerball;
    private soccerplayer soccerPlayer1;
    private soccerplayer soccerPlayer2;
    public goal goal1;
    public goal goal2;
    public  int player1Score= 0;
    public int player2Score= 0;
    public boolean startGame;
    public Rectangle startHitbox;
    public Rectangle[] fans;


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {

      setUpGraphics();

      //variable and objects
      //create (construct) the objects needed for the game and load up

        soccerballPic = Toolkit.getDefaultToolkit().getImage("Soccerball.png");
        soccerfield = Toolkit.getDefaultToolkit().getImage("Soccerfield.png");
        soccerball = new soccerball(450,250);
        soccerPlayer1 = new soccerplayer(100,300);
        soccerPlayerPic = Toolkit.getDefaultToolkit().getImage("SoccerPlayer1.png");
        soccerPlayer2 = new soccerplayer(500,10);
        soccerPlayer2Pic = Toolkit.getDefaultToolkit().getImage("SoccerPlayer2.png");
        goal1 = new goal(1, 250);
        goalPic = Toolkit.getDefaultToolkit().getImage("goal.png");
        goal2 = new goal(910, 250);
        goal2Pic = Toolkit.getDefaultToolkit().getImage("goal2.png");

        startHitbox = new Rectangle(100,100,100,100);
        startGame = false;

        //draws array that creates the multiple strips of fans at the top and bottom
        fanPic = Toolkit.getDefaultToolkit().getImage("fans.jpeg");

        fans = new Rectangle[22];

        fans[0] = new Rectangle(100, 0, 100, 20);
        fans[1] = new Rectangle(200, 0, 100, 20);
        fans[2] = new Rectangle(300, 0, 100, 20);
        fans[3] = new Rectangle(400, 0, 100, 20);
        fans[4] = new Rectangle(500, 0, 100, 20);
        fans[5] = new Rectangle(600, 0, 100, 20);
        fans[6] = new Rectangle(700, 0, 100, 20);
        fans[7] = new Rectangle(800, 0, 100, 20);
        fans[8] = new Rectangle(900, 0, 100, 20);
        fans[9] = new Rectangle(1000, 0, 100, 20);
        fans[10] = new Rectangle(0, 0, 100, 20);
        fans[11] = new Rectangle(100, 685, 100, 20);
        fans[12] = new Rectangle(200, 685, 100, 20);
        fans[13] = new Rectangle(300, 685, 100, 20);
        fans[14] = new Rectangle(400, 685, 100, 20);
        fans[15] = new Rectangle(500, 685, 100, 20);
        fans[16] = new Rectangle(600, 685, 100, 20);
        fans[17] = new Rectangle(700, 685, 100, 20);
        fans[18] = new Rectangle(800, 685, 100, 20);
        fans[19] = new Rectangle(900, 685, 100, 20);
        fans[20] = new Rectangle(1000, 685, 100, 20);
        fans[21] = new Rectangle(0, 685, 100, 20);







        // this live of code draws the fans
	}


//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
         bounce();
         score();
		}
	}


	public void moveThings()
	{
      //calls the move( ) code in the objects

        soccerball.move();
        soccerPlayer1.move();
      soccerPlayer2.move();

	}
// bounce() establishes the interactions between the players and the wall, the ball and the goal.
    public void bounce(){

        if(soccerPlayer1.hitbox.intersects(soccerPlayer2.hitbox)) {
            soccerPlayer1.dx = -soccerPlayer1.dx;
            soccerPlayer1.dy = -soccerPlayer1.dy;
        }
        if(soccerPlayer1.hitbox.intersects(goal1.hitbox)) {
            soccerPlayer1.dx = -soccerPlayer1.dx;
            soccerPlayer1.dy = -soccerPlayer1.dy;

        }
        if(soccerPlayer1.hitbox.intersects(goal2.hitbox)) {
            soccerPlayer1.dx = -soccerPlayer1.dx;
            soccerPlayer1.dy = -soccerPlayer1.dy;
        }
        if(soccerPlayer2.hitbox.intersects(goal1.hitbox)) {
            soccerPlayer2.dx = -soccerPlayer2.dx;
            soccerPlayer2.dy = -soccerPlayer2.dy;

        }
        if(soccerPlayer2.hitbox.intersects(goal2.hitbox)) {
            soccerPlayer2.dx = -soccerPlayer2.dx;
            soccerPlayer2.dy = -soccerPlayer2.dy;
        }
//soccerball bounce
        if(soccerball.hitbox.intersects(soccerPlayer1.hitbox)){
            soccerball.dx = -soccerball.dx;
            soccerball.dy = -soccerball.dy;
        }
        if(soccerball.hitbox.intersects(soccerPlayer2.hitbox)) {
            soccerball.dx = -soccerball.dx;
            soccerball.dy = -soccerball.dy;

        }

    }

    // Score() sets up the scoring mechanic of the game by using hitboxes and if statements.
    public void score(){


        if(soccerball.hitbox.intersects(goal1.hitbox) && goal1.isCrashing == false){
           goal1.isCrashing = true;
            player1Score = player1Score+ 1;
            System.out.println();
        }
        if(soccerball.hitbox.intersects(goal2.hitbox) && goal2.isCrashing == false){
            goal2.isCrashing = true;
            player2Score = player2Score + 1;
            System.out.println();
        }
        if(!goal1.hitbox.intersects(soccerball.hitbox)){
            goal1.isCrashing = false;
        }
        if(!goal2.hitbox.intersects(soccerball.hitbox)) {
            goal2.isCrashing = false;
        }
    }

   //Pauses or sleeps the computer for the amount specified in milliseconds
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.

      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
      canvas.addKeyListener(this);
      canvas.addMouseListener(this);

      panel.add(canvas);  // adds the canvas to the panel.

      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");

   }


	//paints things on the screen using bufferStrategy
    //render() renders all elements of the game. (Characters, background, start screen ect)
	private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.blue);
        g.fillRect(100, 100, 100, 100);
        if (startGame == true) {
            //draw the image
            g.drawImage(soccerfield, 0, 0, WIDTH, HEIGHT, null);
            g.drawImage(soccerballPic, soccerball.xpos, soccerball.ypos, soccerball.width, soccerball.height, null);
            g.drawImage(soccerPlayerPic, soccerPlayer1.xpos, soccerPlayer1.ypos, soccerPlayer1.width, soccerPlayer1.height, null);
            g.drawImage(soccerPlayer2Pic, soccerPlayer2.xpos, soccerPlayer2.ypos, soccerPlayer2.width, soccerPlayer2.height, null);
            g.drawRect(soccerPlayer1.hitbox.x, soccerPlayer1.hitbox.y, soccerPlayer1.hitbox.width, soccerPlayer1.hitbox.height);
            g.drawRect(soccerPlayer2.hitbox.x, soccerPlayer2.hitbox.y, soccerPlayer2.hitbox.width, soccerPlayer2.hitbox.height);
            g.drawRect(soccerball.hitbox.x, soccerball.hitbox.y, soccerball.hitbox.width, soccerball.hitbox.height);
            g.drawImage(goalPic, goal1.xpos, goal1.ypos, goal1.width, goal1.height, null);
            g.drawImage(goal2Pic, goal2.xpos, goal2.ypos, goal2.width, goal2.height, null);
            g.drawRect(goal1.hitbox.x, goal1.hitbox.y, goal1.hitbox.width, goal1.hitbox.height);
            g.drawRect(goal2.hitbox.x, goal2.hitbox.y, goal2.hitbox.width, goal2.hitbox.height);
            g.setColor(Color.BLACK);
            g.drawString("SCORE " + player1Score + "-" + player2Score, +100, 100);

for (int i = 0; i< fans.length; i++){

    g.drawImage(fanPic, fans[i].x, fans[i].y,fans[i].width, fans[i].height,null);
    // renders array(fans)
}



        }
        bufferStrategy.show();

        g.dispose();
	}

// KeyListener allows for the keys to be used to interact with the soccer players
    @Override
    public void keyTyped(KeyEvent e) {

    }
//keypressed allows for the soccer players to move, when the key corresponding with the number is pressed the soccerplayer moves in the direction of the key
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("The key code is:" + e.getKeyCode());
        if (e.getKeyCode() == 38) {
            System.out.println("Player 1 is going up the field");
            soccerPlayer1.isNorth = true;
        }
        if (e.getKeyCode() == 39) {
            System.out.println("Player 1 is going right");
            soccerPlayer1.isEast = true;
        }
        if (e.getKeyCode() == 40) {
            System.out.println("Player 1 is going down the field");
            soccerPlayer1.isSouth = true;
        }
        if (e.getKeyCode() == 37) {
            System.out.println("Player 2 is going left");
            soccerPlayer1.isWest = true;
        }
        if (e.getKeyCode() == 87) {
            System.out.println("Player 2 is going up the field");
            soccerPlayer2.isNorth = true;
        }

        if (e.getKeyCode() == 68) {
            System.out.println("Player 2 is going right");
            soccerPlayer2.isEast = true;
        }
        if (e.getKeyCode() == 83) {
            System.out.println("Player 2 is going down the field");
            soccerPlayer2.isSouth = true;
        }
        if (e.getKeyCode() == 65) {
            System.out.println("Player 2 is going left");
            soccerPlayer2.isWest = true;

        }
    }
    //KeyReleased prevents the soccerplayers from moving when the key is not pressed.
    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == 38) {
            System.out.println("Player 1 is going up the field");
            soccerPlayer1.isNorth = false;
        }
        if (e.getKeyCode() == 39) {
            System.out.println("Player 1 is going right");
            soccerPlayer1.isEast = false;
        }
        if (e.getKeyCode() == 40) {
            System.out.println("Player 1 is going down the field");
            soccerPlayer1.isSouth = false;
        }
        if (e.getKeyCode() == 37) {
            System.out.println("Player 2 is going left");
            soccerPlayer1.isWest = false;
        }
        if (e.getKeyCode() == 87) {
            System.out.println("Player 2 is going up the field");
            soccerPlayer2.isNorth = false;
        }

        if (e.getKeyCode() == 68) {
            System.out.println("Player 2 is going right");
            soccerPlayer2.isEast = false;
        }
        if (e.getKeyCode() == 83) {
            System.out.println("Player 2 is going down the field");
            soccerPlayer2.isSouth = false;
        }
        if (e.getKeyCode() == 65) {
            System.out.println("Player 2 is going left");
            soccerPlayer2.isWest = false;
        }


    }
//MouseClicked is used to create the blue square that starts the game, once clicked the game starts.
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println(e.getPoint());
        Rectangle pointhitbox = new Rectangle(e.getX(), e.getY(), 1, 1);
        if (startHitbox.intersects(pointhitbox)) {
            System.out.println("start game");
            startGame = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GamePanel extends JFrame implements MouseListener, KeyListener{
	
	private Paddle paddle;
	private Ball ball1;
	private Bricks bricks;
	PowerUp powerup1 = new PowerUp(0, 0, 0, 0, 0, Color.BLACK),
				    powerup2 = new PowerUp(0, 0, 0, 0, 0, Color.BLACK),
					powerup3 = new PowerUp(0, 0, 0, 0, 0, Color.BLACK);
	static Boolean isGameOver=false;
	private Random rand = new Random();
	private BufferedImage buffer;
	private JLabel pointsLabel;
    private int points = 0, indexOfPowerUp = 0;
	Thread thread;
			
	public GamePanel() {
		
		//window settings
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(700, 700);
        getContentPane().setBackground(Color.BLACK); 
		setVisible(true);	
		
		pointsLabel = new JLabel("Points: " + points);
	    pointsLabel.setForeground(Color.YELLOW); 
	    pointsLabel.setFont(new Font("Arial", Font.BOLD, 24)); 
	    // set the layout to left-aligned flow layout
	    setLayout(new FlowLayout(FlowLayout.LEFT));
	    add(pointsLabel);
	    
		//Fixed flickering on the screen
		buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		//Defining objects
		paddle = new Paddle(280, 660, 150, 35, Color.GREEN);
		bricks = new Bricks();
		ball1 = new Ball(320,550,15,15,1,1,Color.RED);
		
		thread = new Thread(new Thread() {
			public void run() {
				while (true) {
			        ball1.movingTheBall();
					pingPaddle(paddle, ball1);
					pingBricks(bricks, ball1);
					
					switch (indexOfPowerUp) {
				    case 1:
				        if (indexOfPowerUp == 1) {
				            powerup1.movingThePowerUp();
				            if (isPingPaddlePowerUp(paddle, powerup1)) {
				                PowerUp1();
				            }
				        }
				        break;
				    case 2:
				        if (indexOfPowerUp == 2) {
				            powerup2.movingThePowerUp();
				            if (isPingPaddlePowerUp(paddle, powerup2)) {
				                PowerUp2();
				            }
				        }
				        break;
				    case 3:
				        if (indexOfPowerUp == 3) {
				            powerup3.movingThePowerUp();
				            if (isPingPaddlePowerUp(paddle, powerup3)) {
				                PowerUp3();
				            }
				        }
				        break;   
				    default:
				        break;
				}	
					try {
						this.sleep(5);
					} catch (Exception e) {}
					repaint();
				}
			}
		});
		//To start the game, click on the window
		addMouseListener(this);
		//move the paddle object
		addKeyListener(this);
	}
	
	public void paint(Graphics g){
		//Fixed flickering on the screen
		Graphics2D g2 = (Graphics2D) buffer.getGraphics();
	    super.paint(g2);
	    ball1.draw(g2);
	    paddle.draw(g2);
	    bricks.draw(g2);
	    powerup1.draw(g2);
	    powerup2.draw(g2);
	    powerup3.draw(g2);
	    this.drawGameOver(g2);
	    g.drawImage(buffer, 0, 0, null);
	}
	
	public void drawGameOver(Graphics g) {
		if (isGameOver) {
			g.setColor(Color.RED);
			g.setFont(new Font("Ariel",Font.BOLD, 96));
			g.drawString("GAME OVER", 50, 500);
		}
	}
	//Checks for ping between the ball and the paddle
	public void pingPaddle(Paddle paddle, Ball ball) {
		if(ball.cordX > paddle.cordX && ball.cordX < paddle.cordX 
				+ paddle.widht && (ball.cordY +10 ) > paddle.cordY ) {
			ball.speedX *= -1.4; //random value
			ball.speedY *= -1.3;
			Sound.playSound("countdown.wav");
	}
        }
		//Checks for ping between the powerup and the paddle
		public boolean isPingPaddlePowerUp(Paddle paddle, PowerUp powerup) {
			if (powerup.cordX > paddle.cordX && powerup.cordX < paddle.cordX 
			+ paddle.widht && (powerup.cordY +10) > paddle.cordY) {
				return true;
			}
        return false;
        }	
	//Checks for ping between the ball and the bricks	
	public void pingBricks(Bricks bricks, Ball ball) {
		//bricks defined -> ArrayList<Paddle> bricks = new ArrayList<Paddle>();
		for(Paddle p : bricks.bricks) {
			if(ball.cordX + ball.sizeOfXBall > p.getCordX() && ball.cordX <
			p.getCordX() + p.getWidht() && (ball.cordY ) == p.getCordY() ) { 
				if (p.getWidht() !=0) { 
					p.setWidht(0);
					ball.setSpeedX(ball.getSpeedX() * -1);
					ball.setSpeedY(ball.getSpeedY() * -1);
					Sound.playSound("countdown.wav");
					updatePoints(10);
					//Random number draw for a powerup coming out of the bricks		
					int random = rand.nextInt(100);
					
					//GameOver
					if (random < 30) {
						powerup1 = new PowerUp(p.cordX,p.cordY,10,60,4,Color.RED);
						indexOfPowerUp = 1;	
					}
					//add points
					if (random < 60 && random > 30) {
						powerup2 = new PowerUp(p.cordX,p.cordY,30,20,3,Color.CYAN);
						indexOfPowerUp = 2;
					}
					//increases the padlle
					if (random > 60) {
						powerup3 = new PowerUp(p.cordX,p.cordY,30,20,3,Color.GREEN);
						indexOfPowerUp = 3;
					}
					
				}
			} 
		}
	}	
	public void PowerUp1() {
		isGameOver = true;
		paddle.cordX = 700; paddle.cordY=700;
		Sound.playSound("INNERMK.wav");	
	}
	
	public void PowerUp2() {
		updatePoints(50);
		indexOfPowerUp = 0;
		powerup2.sizeX=0;
		powerup2.sizeY=0;
	}
	
	public void PowerUp3() {
		updatePoints(100);
		this.paddle.setWidht(paddle.getWidht() + 50);
		powerup3.sizeX=0;
		powerup3.sizeY=0;
		indexOfPowerUp = 0;
	}
	
	public void updatePoints(int newPoints) {
	        points += newPoints;
	        pointsLabel.setText("Points: " + points);
	}
	
	public void mouseClicked(MouseEvent e) {
		thread.start();
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			paddle.left();
			break;
		case KeyEvent.VK_RIGHT:
			paddle.right();
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}
	
	public static void main(String[] args) {		
	GamePanel gamepanel = new GamePanel();
	}
}
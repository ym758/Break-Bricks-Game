import java.awt.Color;
import java.awt.Graphics;
import javax.swing.RepaintManager;

public class Ball {

	int cordX, cordY, sizeOfXBall, sizeOfYBall, speedX, speedY;
	Color color;
	
	public Ball(int cordX, int cordY, int sizeOfXBall, int sizeOfYBall, int speedX, int speedY, Color color) {
		this.cordX = cordX;
		this.cordY = cordY;
		this.sizeOfXBall = sizeOfXBall;
	    this.sizeOfYBall = sizeOfYBall; 
		this.speedX = speedX;
		this.speedY = speedY;
		this.color = color;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(cordX, cordY, sizeOfXBall, sizeOfYBall);
	}

	public void movingTheBall() {
		cordX = cordX + speedX;
		cordY = cordY + speedY;
		if (cordX < 0 || cordX > 700) speedX *= -1;
		if (cordY < 0 ) speedY *= -1;  
		//out of bounds
		if (cordY > 690) {
			cordX = 700; cordY=700;
			GamePanel.isGameOver = true;
		}
	}
	public int getSpeedX() {
		return speedX;
	}
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
		

	
	
}
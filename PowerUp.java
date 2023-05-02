import java.awt.Color;
import java.awt.Graphics;
import javax.swing.RepaintManager;

public class PowerUp {

	int cordX, cordY, sizeX, sizeY, speedY;
	Color color;
	
	public PowerUp(int cordX, int cordY, int sizeX, int sizeY, int speedY, Color color) {
		this.cordX = cordX;
		this.cordY = cordY;
		this.sizeX = sizeX;
	    this.sizeY = sizeY; 
		this.speedY = speedY;
		this.color = color;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fill3DRect(cordX, cordY, sizeX, sizeY, true);
	}
	public void movingThePowerUp() {
		cordY = cordY + speedY;
		if (cordY > 690) {
			cordX = 700; cordY=700;
		}
	}
	public int getCordX() {
		return cordX;
	}
	public void setCordX(int cordX) {
		this.cordX = cordX;
	}
	public int getCordY() {
		return cordY;
	}
	public void setCordY(int cordY) {
		this.cordY = cordY;
	}
	public int getSpeedY() {
		return speedY;
	}
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
		

	
	
}
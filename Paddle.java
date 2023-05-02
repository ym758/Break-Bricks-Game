import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
	int cordX, cordY, widht, lenght;
	Color color;
	
	public Paddle(int cordX, int cordY, int widht, int lenght, Color color) {
		this.cordX = cordX;
		this.cordY = cordY;
		this.widht = widht;
		this.lenght = lenght;	
		this.color = color;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(cordX, cordY, widht, lenght);
	}
	public void left(){
		cordX = cordX - 40;
		if (cordX < 0) cordX =0;
	}
	public void right(){
		cordX = cordX + 40;
		if (cordX > 700 - widht) cordX = 700 - widht;
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
	public int getLenght() {
		return lenght;
	}
	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	public int getWidht() {
		return widht;
	}
	public void setWidht(int widht) {
		this.widht = widht;
	}

}

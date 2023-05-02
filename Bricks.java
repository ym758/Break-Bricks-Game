import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Bricks{
	
	ArrayList<Paddle> bricks = new ArrayList<Paddle>();

	public Bricks() {
		for(int i=0; i<11; i++) {
			bricks.add(new Paddle(i * 60 + 20 , 100 , 40, 20,Color.WHITE));	
		}
		for(int i=0; i<11; i++) {
			bricks.add(new Paddle(i * 60 + 20 , 150 , 40, 20,Color.WHITE));	
		}
		for(int i=0; i<11; i++) {
			bricks.add(new Paddle(i * 60 + 20 , 200 , 40, 20,Color.WHITE));	
		}
		for(int i=0; i<11; i++) {
			bricks.add(new Paddle(i * 60 + 20 , 250 , 40, 20,Color.WHITE));	
		}
		for(int i=0; i<11; i++) {
			bricks.add(new Paddle(i * 60 + 20 , 300 , 40, 20,Color.WHITE));	
		}
	}
	
	public void draw(Graphics g) {
		for(Paddle p : bricks) {
			p.draw(g);
		}
	}
	
	
}
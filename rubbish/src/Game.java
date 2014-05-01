

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

public class Game {
	public static final int IMAGE_SIZE = 600;
	public static final int OBJECT_SIZE = 60;
	
	private final Image image;
	private final Critter critterFactory;
	private List<Critter> critterList;
	private boolean isOver;
	Graphics graphics;
	
	Game() {
		image= new BufferedImage( IMAGE_SIZE, IMAGE_SIZE, BufferedImage.TYPE_INT_RGB);
		graphics = image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,IMAGE_SIZE, IMAGE_SIZE);
		graphics.setColor(Color.BLACK);
		critterFactory = null;
		isOver = true;
	}
	
	public boolean isGameOver(){ return isOver; }
	
	public void start(){
		isOver = false;
		critterList.add(critterFactory.makeCritter());
		critterList.add(critterFactory.makeCritter()); 
		critterList.add(critterFactory.makeCritter());
		//*
	}
	
	public void draw(){
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0,0,IMAGE_SIZE, IMAGE_SIZE);
		for (int i=0; i<critterList.size(); i++){
			critterList.get(i).move();
			critterList.get(i).draw(graphics);
		}
	}
	
	public void processClick(int X, int Y){
		for (int i=critterList.size()-1; i>=0; i--){
			if (critterList.get(i).within(X, Y)){
				critterList.remove(i);
			}
		}
		if (critterList.size()==0){ isOver = true; }
	}
	
	
	
	Image getImage(){ return this.image; }
	
	abstract private class Critter{
		int x;
		int y;
		int deltaX;
		int deltaY;
		Color c;
		
		Critter(){}
		
		Critter makeCritter(){
			int randomNum = (int)(Math.random()*3);
			Critter ct = null;
			switch (randomNum){
			case 0:
				ct = new SquareCritter();
				break;
			case 1:
				ct = new RoundCritter();
				break;
			case 2:
				ct = new WowCritter();
				break;
			}
			return ct;
		}
		
		abstract void draw( Graphics graphics );
		
		void move(){
			x+=deltaX;
			y+=deltaY;
		}
		
		abstract boolean within(int x, int y);
		
		Color setRandomColor(){
			float r = (float)Math.random();
			float g = (float)Math.random();
			float b = (float)Math.random();
			return new Color(r, g, b);
		}
	}
		
		private class SquareCritter extends Critter{
			SquareCritter(){
				x = (int)(Math.random()*IMAGE_SIZE);
				y= (int)(Math.random()*IMAGE_SIZE);
				int maxDelta = (int)Math.pow(IMAGE_SIZE, 1/2);
				int minDelta = (int)Math.pow(IMAGE_SIZE, 1/4);
				int sign = ((int)(Math.random()*2)==1)?1:-1;
				deltaX = sign*((int)(Math.random()*(maxDelta-minDelta)+minDelta));
				deltaY = sign*((int)(Math.random()*(maxDelta-minDelta)+minDelta));
				c = setRandomColor();
			}
			void draw(Graphics graphics) {
				graphics.setColor(c);
				graphics.fillRect(x, y, x+OBJECT_SIZE, y+OBJECT_SIZE);
			}
			boolean within(int X, int Y){
				return (X>x)&&(X<x+OBJECT_SIZE)&&(Y>OBJECT_SIZE)&&(Y<y+OBJECT_SIZE);
			}
		}
		
		private class RoundCritter extends Critter{
			RoundCritter(){
				int maxDelta = (int)Math.pow(IMAGE_SIZE, 1/2);
				int minDelta = (int)Math.pow(IMAGE_SIZE, 1/4);
				int sign = ((int)(Math.random()*2)==1)?1:-1;
				x = (int)(Math.random()*IMAGE_SIZE);
				y= (int)(Math.random()*IMAGE_SIZE);
				deltaX = sign*((int)(Math.random()*(maxDelta-minDelta)+minDelta));
				deltaY = sign*((int)(Math.random()*(maxDelta-minDelta)+minDelta));
				c = setRandomColor();
			}
			void draw(Graphics graphics) {
				graphics.setColor(c);
				graphics.fillOval(x, y, x+OBJECT_SIZE, y+OBJECT_SIZE);
				
			}
			boolean within(int X, int Y){
				return (X-x-OBJECT_SIZE/2)*(X-x-OBJECT_SIZE/2)+(Y-y-OBJECT_SIZE/2)*(Y-y-OBJECT_SIZE/2)<900;
			}
		}
		
		private class WowCritter extends Critter{
			WowCritter(){
				int maxDelta = (int)Math.pow(IMAGE_SIZE, 1/2);
				int minDelta = (int)Math.pow(IMAGE_SIZE, 1/4);
				int sign = ((int)(Math.random()*2)==1)?1:-1;
				x = (int)(Math.random()*IMAGE_SIZE);
				y= (int)(Math.random()*IMAGE_SIZE);
				deltaX = sign*((int)(Math.random()*(maxDelta-minDelta)+minDelta));
				deltaY = sign*((int)(Math.random()*(maxDelta-minDelta)+minDelta));
				c = setRandomColor();
			}
			void draw(Graphics graphics) {
				graphics.setColor(c);
				graphics.fillOval(x, y, x+OBJECT_SIZE/2, y+OBJECT_SIZE/2);
				c = setRandomColor();
			}
			
			boolean within(int X, int Y){
				return (X-x-OBJECT_SIZE/2)*(X-x-OBJECT_SIZE/2)+(Y-y-OBJECT_SIZE/2)*(Y-y-OBJECT_SIZE/2)<900;
			}
		}
		
}

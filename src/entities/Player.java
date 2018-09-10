package entities;

import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;



public class Player{
	private Coordinate position;
	private int dx;
	private int dy;
	private int imageWidth;
	private int imageHeight;
	private Image image;
	
	public Player(int xPos, int yPos){
		loadImage();
		this.position = new Coordinate(xPos, yPos);
	}

	
    /**
	 * @return the image
	 */
	public Image getImage() {
		return image;
	}


	/**
	 * @param image the image to set
	 */
	public void setImage(Image image) {
		this.image = image;
	}


	private void loadImage() {
        
        ImageIcon icon = new ImageIcon("src/resources/player.png");
        image = icon.getImage(); 
        System.out.println("Player created with dimesnions: " + image.getWidth(null) + " x " + image.getHeight(null));
        imageWidth = image.getWidth(null);
        imageHeight = image.getHeight(null);
    }
    
	public void keyPressed(KeyEvent e) {
		
	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_LEFT) {
	        dx = -32;
	        dy = 0;
	        System.out.println("LEFT");
	    }

	    if (key == KeyEvent.VK_RIGHT) {
	        dx = 32;
	        dy = 0;
	        System.out.println("RIGHT");
	    }

	    if (key == KeyEvent.VK_UP) {
	        dy = -32;
	        dx = 0;
	        System.out.println("UP");
	    }

	    if (key == KeyEvent.VK_DOWN) {
	        dy = 32;
	        dx = 0;
	        System.out.println("DOWN");
	    }
	}

	public void move() {

		//if(isValidMove()){;
		
	    int newX = position.getxPosition() + dx;
	    int newY = position.getyPosition() + dy;
	    
	    position.setxPosition(newX);
	    position.setyPosition(newY);
//	    if (x < 1) {
//	        x = 1;
//	    }
//
//	    if (y < 1) {
//	        y = 1;
//	    }
	}
	
	public Coordinate getPosition(){
		return position;
	}
	
	public String returnPosition() {
		return "X Coordinate:" + position.getxPosition() + "Y Coordinate:" + position.getyPosition(); 
	}
}




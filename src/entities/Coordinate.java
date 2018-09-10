package entities;

public class Coordinate {
	private int xPosition;
	private int yPosition;
	
	Coordinate(int xPosition, int yPosition){
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	/**
	 * @return the xPosition
	 */
	public int getxPosition() {
		return xPosition;
	}

	/**
	 * @param xPosition the xPosition to set
	 */
	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	/**
	 * @return the yPosition
	 */
	public int getyPosition() {
		return yPosition;
	}

	/**
	 * @param yPosition the yPosition to set
	 */
	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}
	
	
}

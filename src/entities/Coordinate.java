package entities;


public class Coordinate {
	private int xPosition;
	private int yPosition;
	
	public Coordinate(int xPosition, int yPosition){
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

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if(obj == this) return true;
		if(obj == null) return false;
		
		if(!(obj instanceof Coordinate)) return false;
		
		Coordinate other = (Coordinate) obj;
		if(xPosition == other.getxPosition() && yPosition == other.getyPosition()) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return the string displaying the current Coordinate of the entity
	 * The X and the Y Coordinate respectively
	 */
	public String returnPosition() {
		return "X Coordinate:" + getxPosition() + " Y Coordinate:" + getyPosition(); 
	}
	
}

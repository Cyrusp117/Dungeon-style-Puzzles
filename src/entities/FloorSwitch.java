package entities;

import java.awt.event.KeyEvent;


public class FloorSwitch extends Entity {
	private boolean active = false;
	
	public FloorSwitch(Coordinate position)  {
		super(position);
		this.keyCode = KeyEvent.VK_PERIOD;
	}
	
	/**
	 * 
	 * @return position, the Coordinate of the Wall
	 */
	public Coordinate getPosition(){
		return position;
	}

	/**
	 * turns on floorswitch
	 */
	public void activate() {
		active = true;
	}
	
	public void deactivate() {
		active = false;
	}
	
	public boolean getState() {
		return active;
	}

	@Override
	public String getName() {
		return "FloorSwitch";
	}


}

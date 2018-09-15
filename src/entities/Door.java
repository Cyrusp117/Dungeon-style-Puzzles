package entities;

import java.awt.event.KeyEvent;

public class Door extends Entity {
	private static int numOfDoors;
	private int DoorId;
	private static final int OPEN = 1;
	private static final int CLOSE = 0;
	private int state;
	public Door(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_D;
		DoorId = numOfDoors;
		numOfDoors++;
		state = CLOSE;
	}
	
	@Override
	public boolean interactWithPlayer(Player player) {
		if(this.getState() == CLOSE) {
			if(player.hasItem("Key")) {
				if(player.hasKey(this.getDoorId())) {
					System.out.println("You have the right key, open the door");
					this.setState(OPEN);
				}else {
					player.setPosition(player.getOldPosition());
					System.out.println("You don't have the right key");
				}
			}else {
				player.setPosition(player.getOldPosition());
				System.out.println("You don't have key");
			}
		}else {
			System.out.println("Go through the door");
		}
		
		return false;
	}
	
	
	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the numOfDoors
	 */
	public int getNumOfDoors() {
		return numOfDoors;
	}

	/**
	 * @return the doorId
	 */
	public int getDoorId() {
		return DoorId;
	}

	public String getName() {
		return "Door";
	}
}

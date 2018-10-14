package entities;

import java.awt.event.KeyEvent;

import javafx.scene.input.KeyCode;

public class Door extends Entity {
	private static int numOfDoors = 0;
	private int DoorId;
	private static final int OPEN = 1;
	private static final int CLOSE = 0;
	private int state;
	
	public Door() {
		this.keyCode = KeyCode.D;
	}
	
	public Door(Coordinate position) {
		super(position);
		this.keyCode = KeyCode.D;
		DoorId = numOfDoors;
		numOfDoors++;
		state = CLOSE;
	}
	
	@Override
	public Coordinate interactWithPlayer(Player player) {
		if(this.getState() == CLOSE) {
			if(player.hasItem("Key")) {
				if(player.hasKey(this.getDoorId())) {
					System.out.println("You have the right key, open the door");
					player.removeKey(this.getDoorId());
					this.setState(OPEN);
					return null;
				}else {
					player.setPosition(player.getOldPosition());
					System.out.println("You don't have the right key");
					System.out.println("DoorID: " + DoorId);
				}
			}else {
				player.setPosition(player.getOldPosition());
				System.out.println("You don't have key");
			}
		}else {
			System.out.println("Go through the door");
		}
		
		return position;
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
	
	public static void resetNumOfDoors() {
		numOfDoors = 0;
	}
}
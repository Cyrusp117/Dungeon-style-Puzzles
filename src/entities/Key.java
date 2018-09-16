package entities;

import java.awt.event.KeyEvent;

public class Key extends Entity {
	private static int numOfKeys = 0;
	private int keyId;
	
	public Key() {}

	public Key(Coordinate position) {
		super(position);
		this.keyCode = KeyEvent.VK_K;
		System.out.println(numOfKeys);
		keyId = numOfKeys;
		numOfKeys++;
	}
	
	@Override
	public boolean interactWithPlayer(Player player) {
		player.pickUp(this);
		System.out.println("keyID is: "+ this.getKeyId());
		return true;
	}
	
	/**
	 * @return the numOfKey
	 */
	public int getNumOfKeys() {
		return numOfKeys;
	}

	/**
	 * @return the keyId
	 */
	public int getKeyId() {
		return keyId;
	}

	public String getName() {
		return "Key";
	}
	
	public static void resetNumOfKeys() {
		numOfKeys = 0;
	}
	
	
}
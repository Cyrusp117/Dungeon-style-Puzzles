package ui;

import java.util.Scanner;

public class Launcher { // Will likely move this into Game at a later time
	
	public static void main(String[] args) {
		Scanner sc = null;
		System.out.println("Specify board dimensions e.g. '#horizontalTiles #verticalTiles'");
		sc = new Scanner(System.in);
		int xSize = sc.nextInt();
		int ySize = sc.nextInt();
		
		xSize = xSize * 32;
		ySize = ySize * 32;
		Game game = new Game("Title!", xSize, ySize);
		game.init();
	}
}

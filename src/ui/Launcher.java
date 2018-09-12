package ui;

import java.util.Scanner;

public class Launcher { // Will likely move this into Game at a later time
	
	public static void main(String[] args) {
		
		System.out.println("Specify board dimensions e.g. '#horizontalTiles <= 40 #verticalTiles <= 20'");
		int valid = 0;
		
		int xSize = 1, ySize = 1;
		Scanner sc = null;
		sc = new Scanner(System.in);
		
		while(valid == 0) {
			valid = 1;
			xSize = sc.nextInt();
			ySize = sc.nextInt();
			if(xSize > 40 || xSize <= 2 || ySize > 20 || ySize <= 2) {
				System.out.println("Please enter valid dimensions: 2 < #horizontalTiles <= 40 2 < #verticalTiles <= 20 ");
				valid = 0;
			}
			
		}
		//sc.close();
		
		xSize = xSize * 32;
		ySize = ySize * 32;
		Game game = new Game("Title!", xSize, ySize);
		game.init();
	}
}

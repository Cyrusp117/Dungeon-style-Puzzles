package UnitTests;

import java.awt.event.KeyEvent;

import org.junit.Test;

import entities.Coordinate;
import entities.Entity;
import entities.Bone;
import ui.Game;
import entities.Hound;
import entities.Sword;
import entities.Wall;

public class BoneTest extends testSetup {

	@Test
	public void placeBone() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(1,1);
		game.createPlayer(player);

		Coordinate bonePos = new Coordinate(3, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		//game.printGame();
		assert(game.getFirstEntity(bonePos) instanceof Bone);
	}
	
	@Test
	public void playerPickupBone() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(1,1);
		game.createPlayer(player);

		Coordinate bonePos = new Coordinate(3, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		//game.printGame();
		
		
		game.getPlayer().setDx(1);
		game.getPlayer().setDy(0);
		game.update();
		//game.printGame();
		game.update();
		//game.printGame();
		
		assert(game.getPlayer().hasItem(bone));
	}
	
	@Test
	public void boneLureHound() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(2,1);
		game.createPlayer(player);
		Coordinate houndPos = new Coordinate(9,1); 
        Hound hound = new Hound(houndPos);
        
		Coordinate bonePos = new Coordinate(3, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		game.addEntity(hound);
		//game.printGame();
		
		
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		//game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(-1);
		game.update();
		//game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(-1);
		game.update();
		//game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		//game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(-1);
		game.update();
		//game.printGame();
		
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		//game.printGame();
		game.update();
		//game.printGame();
		game.update();
		//game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		//game.printGame();
		game.update();
		//game.printGame();
		game.update();
		//game.printGame();
		
		assert(!game.getAllEntities().contains(bone));
		
		
	}
	
	@Test
	public void houndCantGetToBone() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(2,1);
		game.createPlayer(player);
		Coordinate houndPos = new Coordinate(9,1); 
        Hound hound = new Hound(houndPos);
        
		Coordinate bonePos = new Coordinate(7, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		game.addEntity(hound);
		
		Coordinate wall1Pos = new Coordinate(8,1);
		Coordinate wall2Pos = new Coordinate(8,2);
		Coordinate wall3Pos = new Coordinate(7,2);
		Coordinate wall4Pos = new Coordinate(6,2);
		Coordinate wall5Pos = new Coordinate(6,1);
		
		
		game.addEntity(new Wall(wall1Pos));
		game.addEntity(new Wall(wall2Pos));
		game.addEntity(new Wall(wall3Pos));
		game.addEntity(new Wall(wall4Pos));
		game.addEntity(new Wall(wall5Pos));
		
		
		game.printGame();
		
		
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(-1);
		game.update();
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(-1);
		game.update();
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(-1);
		game.update();
		game.printGame();
		
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.getPlayer().setDx(0);
		game.getPlayer().setDy(1);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		
		assert(!hound.getPosition().equals(houndPos));
		
		
	}
	//@Test
	public void playerThrowBone() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(1,1);
		game.createPlayer(player);

		Coordinate bonePos = new Coordinate(3, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		game.printGame();
		
		
		game.getPlayer().setDx(1);
		game.getPlayer().setDy(0);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		assert(game.getPlayer().hasItem(bone));
		
		Bone boneNew = null;

        //need to check if wall, because then no can do
    	for (Entity curItem : game.getPlayerInventory()) {
    		if (curItem instanceof Bone) {
    			boneNew = (Bone)curItem;
    			boneNew.setPosition(game.getPlayer().getPosition());
    			System.out.println("currently x is " + game.getPlayer().getDx()  );
    			System.out.println("currently y is " + game.getPlayer().getDy()  );
    			boneNew.setDx(game.getPlayer().getDx()); 
    			boneNew.setDy(game.getPlayer().getDy());
    			System.out.println("times moved = " + boneNew.timesMoved());
    			assert(game.addEntity(boneNew));
    			//assert(game.getEntities().contains(boneNew));
    			break;
    		}
    	}
    	if(boneNew!=null) {
    		System.out.println("Shooting bone");
    		game.getPlayer().removeItem(bone);
    		game.getPlayer().setDx(0);
    		game.getPlayer().setDy(1);
    		game.update();
    	} else {
    		
    	}
    	
		game.printGame();
		game.update();
		
		game.printGame();
        game.update();
		
		game.printGame();
		assert(boneNew.getPosition().equals(new Coordinate(5,1) ));
	}
	
	//@Test
	public void playerThrowBoneHitWall() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(1,1);
		game.createPlayer(player);

		Coordinate bonePos = new Coordinate(3, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		game.printGame();
		
		Coordinate wall1Pos = new Coordinate(5,1);
		game.addEntity(new Wall(wall1Pos));
		
		game.getPlayer().setDx(1);
		game.getPlayer().setDy(0);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		assert(game.getPlayer().hasItem(bone));
		
		Bone boneNew = null;

        //need to check if wall, because then no can do
    	for (Entity curItem : game.getPlayerInventory()) {
    		if (curItem instanceof Bone) {
    			boneNew = (Bone)curItem;
    			boneNew.setPosition(game.getPlayer().getPosition());
    			System.out.println("currently x is " + game.getPlayer().getDx()  );
    			System.out.println("currently y is " + game.getPlayer().getDy()  );
    			boneNew.setDx(game.getPlayer().getDx()); 
    			boneNew.setDy(game.getPlayer().getDy());
    			System.out.println("times moved = " + boneNew.timesMoved());
    			assert(game.addEntity(boneNew));
    			//assert(game.getEntities().contains(boneNew));
    			break;
    		}
    	}
    	if(boneNew!=null) {
    		System.out.println("Shooting bone");
    		game.getPlayer().removeItem(bone);
    		game.getPlayer().setDx(0);
    		game.getPlayer().setDy(1);
    		game.update();
    	} else {
    		
    	}
    	
		game.printGame();
		game.update();
		
		game.printGame();
        game.update();
		
		game.printGame();
		assert(boneNew.getPosition().equals(new Coordinate(4,1) ));
	}
	
	@Test
	public void playerThrowBoneNextToWall() {
		Game game = new Game(10,10);
		game.generatePerimeter();
		Coordinate player = new Coordinate(1,1);
		game.createPlayer(player);

		Coordinate bonePos = new Coordinate(3, 1);
		Bone bone = new Bone(bonePos);
		game.addEntity(bone);
		game.printGame();
		
		Coordinate wall1Pos = new Coordinate(4,1);
		game.addEntity(new Wall(wall1Pos));
		
		game.getPlayer().setDx(1);
		game.getPlayer().setDy(0);
		game.update();
		game.printGame();
		game.update();
		game.printGame();
		
		assert(game.getPlayer().hasItem(bone));
		
		Bone boneNew = null;

        //need to check if wall, because then no can do
    	for (Entity curItem : game.getPlayerInventory()) {
    		if (curItem instanceof Bone) {
    			boneNew = (Bone)curItem;
    			boneNew.setPosition(game.getPlayer().getPosition());
    			System.out.println("currently x is " + game.getPlayer().getDx()  );
    			System.out.println("currently y is " + game.getPlayer().getDy()  );
    			boneNew.setDx(game.getPlayer().getDx()); 
    			boneNew.setDy(game.getPlayer().getDy());
    			System.out.println("times moved = " + boneNew.timesMoved());
    			assert(game.addEntity(boneNew));
    			//assert(game.getEntities().contains(boneNew));
    			break;
    		}
    	}
    	if(boneNew!=null) {
    		System.out.println("Shooting bone");
    		game.getPlayer().removeItem(bone);
    		game.getPlayer().setDx(0);
    		game.getPlayer().setDy(1);
    		game.update();
    	} else {
    		
    	}
    	
		game.printGame();
		game.update();
		
		game.printGame();
        game.update();
		
		game.printGame();
		assert(boneNew.getPosition().equals(new Coordinate(3,1) ));
	}
	
	

}

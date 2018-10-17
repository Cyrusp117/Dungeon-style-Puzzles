package ui;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Scanner;

import entities.Coordinate;
import entities.Entity;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class DesignController extends MapController {
	
	ArrayList<Entity> allDesignerObjects;
	
	public DesignController(Stage stage, Game game) {
		super(stage,game);
		allDesignerObjects = game.getAllEntities();
	} 
	
	
	public void initialize() {
		printGame();
		StringBuilder sb = new StringBuilder();
		for (Entity entity : game.getAllEntities()) {
			sb.append(entity.getDesignerDescription() + "\n");
		}
		instructions.setText(sb.toString());
	}
	
	public void previousMenu() {
		Screen map = new Screen(super.getS(), "Map", "view/map.fxml");
		MapController pc = new MapController(super.getS(), game);
		map.start(pc);
	}
	
	public void keyPressed(KeyEvent ke) {
		super.keyPressed(ke);
		KeyCode key = ke.getCode();
		//ke.get
		Entity requiredEntity = null;
		for (Entity entity : allDesignerObjects) {
			if (entity.getKeyCode() == key) {
				System.out.println("Please enter co-ordinates of " + entity.getName() + ": ");
				requiredEntity = entity;
			}
		}
		
		if (requiredEntity != null) { 
			Scanner sc = new Scanner(System.in);
			int dx = sc.nextInt();
			int dy = sc.nextInt();
			Coordinate entityPos = new Coordinate(dx, dy);
			
			Class<? extends Entity> c = requiredEntity.getClass();
			Constructor<? extends Entity> constructor = null;
			Entity instance = null;
			try {
				constructor = c.getConstructor(Coordinate.class);
				instance = constructor.newInstance(entityPos);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			game.addEntity(instance);
		}
		
	}

}



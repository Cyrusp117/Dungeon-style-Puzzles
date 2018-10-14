package ui;

import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;

import entities.Arrow;
import entities.Bomb;
import entities.Coordinate;
import entities.Entity;
import entities.Player;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import sun.print.resources.serviceui;

public class MapController extends Controller {
	
	@FXML
	private AnchorPane screen;
	@FXML 
	private Button upBtn;
	@FXML 
	private Button rightBtn;
	@FXML 
	private Button downBtn;
	@FXML 
	private Button leftBtn;
	@FXML
	private Button escapeBtn;
	@FXML
	protected Label instructions;
	@FXML
	protected GridPane imageMap;
	
	
	protected Game game;
	protected Player player;
	protected Scene scene;
	
	public MapController(Stage s, Game game) {
		super(s);
		this.game = game;
		this.player = game.getPlayer();
		this.scene = s.getScene();
	}
	

	
	public void initialize() {
		makeGridPane(game.getHeight(), game.getWidth()); // makes it more readable even though possible code smell?
		printGame();
		instructions.setText("Arrow Keys to move, 1 for Inventory, 2 to shoot arrow, Escape to exit");
		//map.setText(game.toString());
		//imageMap.setCenterShape(true);
		//imageMap.setGridLinesVisible(true);

//        for (int row = 0 ; row < game.getHeight() ; row++ ){
//            RowConstraints rc = new RowConstraints();
////          rc.setMaxHeight(32);
////          rc.setPrefHeight(32);
//            rc.setFillHeight(true);
////          rc.setVgrow(Priority.ALWAYS);
//            imageMap.getRowConstraints().add(rc);
//        }
//        for (int col = 0 ; col < game.getWidth(); col++ ) {
//            ColumnConstraints cc = new ColumnConstraints();
////          cc.setMaxWidth(32);
////          cc.setPrefWidth(32);
//            cc.setFillWidth(true);
////          cc.setHgrow(Priority.ALWAYS);
//            imageMap.getColumnConstraints().add(cc);
//        }

	}
	
	private void makeGridPane(int height, int width) {
		// TODO Auto-generated method stub

		for( int i = 0; i <= width; i++ ) {
	        ColumnConstraints cc = new ColumnConstraints();
	        cc.setPercentWidth(100.0 / width);
	        cc.setMaxWidth(32);
	        cc.setPrefWidth(32);
	        cc.setFillWidth(true);
	        imageMap.getColumnConstraints().add(cc);
		}
		
	   for (int i = 0; i <= height; i++) {
            RowConstraints rc = new RowConstraints();
            rc.setPercentHeight(100.0 / height);
	        rc.setMaxHeight(32);
	        rc.setPrefHeight(32);
	        rc.setFillHeight(true);
            imageMap.getRowConstraints().add(rc);         
       }
	}



	public void printGame() {
		imageMap.getChildren().clear();
		for( int i = 0; i <= game.getWidth(); i++ ) {
			for ( int j = 0; j <= game.getHeight(); j++ ) {
				Coordinate newPos = new Coordinate(i,j);
				Entity entity = game.getFirstEntity(newPos);
				Image image = new Image("resources/white.png");
				if (entity != null) {
					image = new Image("resources/" + entity.getName()
												+ ".png");
				}
				
				ImageView iv = new ImageView(image);
				iv.setFitHeight(32);
				iv.setFitWidth(32);
//				GridPane.setFillWidth(iv, true);
//				GridPane.setFillHeight(iv, true);
				imageMap.add(iv, i, j);

			}
		}

	}
	
	public void keyPressed(KeyEvent ke) {
		KeyCode key = ke.getCode();
		if (key.equals(KeyCode.W)) {
			moveUp();
		} else if (key.equals(KeyCode.S)) {
			moveDown();
		} else if (key.equals(KeyCode.A)) {
			moveLeft();
		} else if (key.equals(KeyCode.D)) {
			moveRight();
		} else if (key.equals(KeyCode.ESCAPE)) {
			previousMenu();
		} else if (key.equals(KeyCode.DIGIT1)) {
	    	System.out.println("Inventory contents: ");
	    	for (Entity curItem : game.getPlayerInventory()) {
	    		System.out.print(curItem.getName() + " ");
	    	}
	    	System.out.println("\n");
		} else if (key.equals(KeyCode.DIGIT2)) {
	    	Arrow arrow = null;
	    	Player player = game.getPlayer();
	    	for (Entity curItem : game.getPlayerInventory()) {
	    		if (curItem instanceof Arrow) {
	    			arrow = (Arrow)curItem;
	    			arrow.setPosition(game.getPlayer().getPosition());
	    			arrow.setDy(-1); 
	    			arrow.setDx(0);
	    			game.addEntity(arrow);
	    			break;
	    		}
	    	}
	    	if(arrow!=null) {
	    		System.out.println("Shooting upwards");
	    		player.removeItem(arrow);
	    		game.update();
	    	} else {
	    		System.out.println("No arrows :(");
	    	}
		} else if (key.equals(KeyCode.V)) {
	    	System.out.println("Checking for bomb");
	    	Player player = game.getPlayer();
	    	if(player.hasItem("Bomb")) {
	    		System.out.println("Light and drop the bomb");
	    		Bomb placedBomb = player.setBomb();
	    		placedBomb.setPosition(player.getPosition());
	    		game.addEntity(placedBomb);
	    	}
		} else if (key.equals(KeyCode.E)) {
			boolean designer = (boolean)super.getS().getUserData();
			if(designer) {
			      Screen map1 = new Screen(super.getS(), "Map", "view/design.fxml");
			      DesignController dc = new DesignController(super.getS(), game);
			      map1.start(dc);
			}
		}
		printGame();
	}
	
	
	
	public void previousMenu() {
        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}
	
	public void moveUp () {
		player.setDx(0);
		player.setDy(-1);
		game.update();
		printGame();
		//map.setText(game.toString());
	}
	public void moveRight () {
		player.setDx(1);
		player.setDy(0);
		game.update();
		printGame();
		//map.setText(game.toString());
	}
	
	public void moveLeft () {
		player.setDx(-1);
		player.setDy(0);
		game.update();
		printGame();
		//map.setText(game.toString());
	}
	public void moveDown () {
		player.setDx(0);
		player.setDy(1);
		game.update();
		printGame();
		//map.setText(game.toString());
	}
}
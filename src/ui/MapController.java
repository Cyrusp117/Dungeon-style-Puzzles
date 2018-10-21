package ui;

import java.util.ArrayList;


import com.sun.javafx.collections.MappingChange.Map;

import entities.Arrow;
import entities.Bomb;
import entities.Bone;
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
import javafx.scene.media.AudioClip;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

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
        for (int i = 0 ; i <= width ; i++) {
            ColumnConstraints colConstraints = new ColumnConstraints();
            colConstraints.setHgrow(Priority.NEVER);
            //colConstraints.setMaxWidth(32);
            //colConstraints.setMinWidth(32);
            imageMap.getColumnConstraints().clear();
            imageMap.getColumnConstraints().add(colConstraints);
            //gridTest.getColumnConstraints().add(colConstraints);
        }

        for (int i = 0 ; i <= height ; i++) {
            RowConstraints rowConstraints = new RowConstraints();
            rowConstraints.setVgrow(Priority.NEVER);
            
            //rowConstraints.setMaxHeight(32);
            //rowConstraints.setMinHeight(32);
            imageMap.getRowConstraints().clear();
            imageMap.getRowConstraints().add(rowConstraints);
        }
        
//		for( int i = 0; i <= width; i++ ) {
//	        ColumnConstraints cc = new ColumnConstraints();
//	        cc.setPercentWidth(100.0 / width);
//	        cc.setMaxWidth(32);
//	        cc.setPrefWidth(32);
//	        cc.setFillWidth(true);
//	        imageMap.getColumnConstraints().add(cc);
//		}
//		
//	   for (int i = 0; i <= height; i++) {
//            RowConstraints rc = new RowConstraints();
//            rc.setPercentHeight(100.0 / height);
//	        rc.setMaxHeight(32);
//	        rc.setPrefHeight(32);
//	        rc.setFillHeight(true);
//            imageMap.getRowConstraints().add(rc);         
//       }
	}



	public void printGame() {
		imageMap.getChildren().clear();
		String path;
		for( int i = 0; i <= game.getWidth(); i++ ) {
			for ( int j = 0; j <= game.getHeight(); j++ ) {
				Coordinate newPos = new Coordinate(i,j);
				Entity entity = game.getFirstEntity(newPos);
				if(game.getTheme().equals("theme1")) {
					path = "resources/theme1/";
				}else {
					path = "resources/theme2/";
				}
				Image image = new Image(path + "white.png");
				if (entity != null) {
					image = new Image(path + entity.getName()
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
    	Player player = game.getPlayer();
    	player.setDx(0);
    	player.setDy(0);
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
		} else if (Arrow.checkKeyCode(key)) {
	    	Arrow arrow = game.getPlayerArrow();
	    	if(arrow!=null) {
	    		arrow.setDirection(key);
	    		arrow.setPosition(player.getPosition());
	    		//arrow.move();
	    		player.removeItem(arrow);
	    		game.addEntity(arrow);
	    		update();
	    	} else {
	    		System.out.println("No arrows :(");
	    	}

		} else if (key.equals(KeyCode.V)) {
	    	System.out.println("Checking for bomb");
	    	if(player.hasEntity(Bomb.class)) {
	    		System.out.println("Light and drop the bomb");
	    		Bomb placedBomb = player.setBomb();
	    		placedBomb.setPosition(player.getPosition());
	    		game.addEntity(placedBomb);
	    	}
		} 
		else if (key.equals(KeyCode.E)) {
//			boolean designer = (boolean)super.getS().getUserData();
//			if(designer) {
//			      Screen map1 = new Screen(super.getS(), "Map", "view/design.fxml");
//			      DesignController dc = new DesignController(super.getS(), game);
//			      map1.start(dc);
//			}
		} else if (key.equals(KeyCode.DIGIT2)) {
	    	Bone bone = null;

            //need to check if wall, because then no can do
	    	for (Entity curItem : game.getPlayerInventory()) {
	    		if (curItem instanceof Bone) {
	    			bone = (Bone)curItem;
	    			bone.setPosition(game.getPlayer().getPosition());
	    			bone.setDx(player.getDx()); 
	    			bone.setDy(player.getDy());
	    			game.addEntity(bone);
	    			break;
	    		}
	    	}
	    	if(bone!=null) {
	    		System.out.println("Shooting bone");
	    		player.removeItem(bone);
	    		update();
	    	} else {
	    		System.out.println("No bones :(");
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
		play_move();
		player.setDx(0);
		player.setDy(-1);
		update();
		printGame();
		//map.setText(game.toString());
	}
	public void moveRight () {
		play_move();
		player.setDx(1);
		player.setDy(0);
		update();
		printGame();
		//map.setText(game.toString());
	}
	
	public void moveLeft () {
		play_move();
		player.setDx(-1);
		player.setDy(0);
		update();
		printGame();
		//map.setText(game.toString());
	}
	public void moveDown () {
		play_move();
		player.setDx(0);
		player.setDy(1);
		update();
		printGame();
		//map.setText(game.toString());
	}



	private void update() {
		game.update();
		if (game.isPlayerAlive() && game.hasPlayerWon()) {
		      Screen wonScreen = new Screen(super.getS(), "Winner", "view/won.fxml");
		      WonController wc = new WonController(super.getS());
		      wonScreen.start(wc);
		} else if (!game.isPlayerAlive()) {
		      Screen lossScreen = new Screen(super.getS(), "Dead", "view/loss.fxml");
		      LossController wc = new LossController(super.getS());
		      lossScreen.start(wc);
		}
	}
	
	private void play_move() {
		AudioClip note = new AudioClip(this.getClass().getResource("move.wav").toString());
		//note.setCycleCount(3);
		note.play();
	}
}

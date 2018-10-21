package ui;
import entities.Coordinate;
import entities.Entity;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class EditorController extends Controller {
    	@FXML
    	private GridPane map;
    	@FXML
    	private GridPane selector;
    	@FXML
    	private TextArea descriptor;
    	@FXML
    	private Pane Arrow;
    	@FXML
    	private Pane Bomb;
    	@FXML
    	private Pane Boulder;
    	@FXML
    	private Pane Coward;
    	@FXML
    	private Pane Door;
    	@FXML
    	private Pane Exit;
    	@FXML
    	private Pane FloorSwitch;
    	@FXML
    	private Pane Hound;
    	@FXML
    	private Pane HoverPotion;
    	@FXML
    	private Pane Hunter;
    	@FXML
    	private Pane InvincibilityPotion;
    	@FXML
    	private Pane Key;
    	@FXML
    	private Pane Pit;
    	@FXML
    	private Pane Player;
    	@FXML
    	private Pane Strategist;
    	@FXML
    	private Pane Sword;
    	@FXML
    	private Pane Treasure;
    	@FXML
    	private Pane Wall;
    	@FXML
    	private Button deleteButton;
    	@FXML
    	private Button addButton;
    	@FXML
    	private Button testButton;
    	@FXML
    	private Label targetName;
    	@FXML
    	private GridPane imageMap;
    	@FXML
    	private Button bckButton;
    	@FXML
    	private Pane TreasureGoblin;
    	@FXML
    	private Pane Bone;
    	@FXML
    	private CheckBox FloorCBox;
    	@FXML
    	private CheckBox KillCBox;
    	@FXML
    	private CheckBox TreasureCBox;
    	@FXML
    	private AnchorPane winConPanel;
    	@FXML
    	private Label houndMarker;
    	@FXML
    	private Button setHunterBtn;
    	
    	private String selectedEntity = null;
    	private EntityProducer producer;
    	private Game game;
    	private EditorInvoker invoker;
    	private Entity deleteTarget = null;
    	private CheckWinCon winCheck;
    	private int nextHunter = 0;
    	private entities.Hound houndToSet = null;
    	
    	public EditorController(Stage s, Game game) {
    		super(s);
    		this.game = game;
    		this.winCheck = new WinChecker();
       	}
    	
    	/**
    	 * Function executed upon start
    	 */
    	//Lines 104-137 adapted from StackOverflow https://stackoverflow.com/questions/31095954
	    public void initialize() {
	    	EntityFactory factory = new EntityFactory();
	    	producer = new EntityProducer(factory); // probably need to add a game to this
	    	invoker = new EditorInvoker();
	        int numCols = game.getWidth() ;
	        int numRows = game.getHeight() ;
	        game.generatePerimeter();
	        setupColumns(numCols);
	        setupRows(numRows);
	        setupPanes(numCols, numRows);  
	        setupSelector();
	        printGame();

	    }

		/**
		 * // Instantiating Selector pictures and functionality
		 */
		public void setupSelector() {
			
			// For every pane in the selector, add click registration
	        for (Node node: selector.getChildren()) {
	        	node.setOnMouseClicked(e -> {
	        		selectedEntity = node.getId();
	        		descriptor.setText(selectedEntity + "\n" + description(selectedEntity));
	        	});
	        }

	        //For every pane in the selector, add respective sprite
	        String path;
	        if(game.getTheme().equals("theme1")) {
				path = "resources/theme1/";
			}else {
				path = "resources/theme2/";
			}
	        for( Node node: selector.getChildren()) {
	        	if(node instanceof Pane) {
	        		for(Node childNode: ((Pane) node).getChildren()) {
	        			if(childNode instanceof ImageView) {
	        				Image sprite = new Image(path + node.getId()
							+ ".png");
	        				((ImageView) childNode).setImage(sprite);
	        			}
	        		}
	        	}
	        }
		}

		/**
		 * @param numCols is number of columns in the GridPane
		 * @param numRows is number of rows in the GridPane
		 */
		public void setupPanes(int numCols, int numRows) {
			// Adding a FXML Pane to each cell in the GridPane map
	        for (int i = 0 ; i <= numCols ; i++) {
	            for (int j = 0; j <= numRows; j++) {
	                addPane(i, j);
	            }
	        }
		}

		/**
		 * @param numRows is the number of rows in the GridPane
		 */
		public void setupRows(int numRows) {
			//Setting Row constraints of GridPane map
	        for (int i = 0 ; i <= numRows ; i++) {
	            RowConstraints rowConstraints = new RowConstraints();
	            rowConstraints.setVgrow(Priority.NEVER);
	            map.getRowConstraints().clear();
	            map.getRowConstraints().add(rowConstraints);  
	            imageMap.getRowConstraints().clear();
	            imageMap.getRowConstraints().add(rowConstraints);
	        }
		}

		/**
		 * @param numCols is the number of columns in the GridPane
		 */
		public void setupColumns(int numCols) {
			//Setting Column constraints of GridPane map
	        for (int i = 0 ; i <= numCols ; i++) {
	            ColumnConstraints colConstraints = new ColumnConstraints();
	            colConstraints.setHgrow(Priority.NEVER);
	            map.getColumnConstraints().clear();
	            map.getColumnConstraints().add(colConstraints);
	            imageMap.getColumnConstraints().clear();
	            imageMap.getColumnConstraints().add(colConstraints);
	        }
		}
		
		/**
		 * 
		 * @param colIndex specified cell in the GridPane
		 * @param rowIndex specified cell in the GridPane
		 */
	    private void addPane(int colIndex, int rowIndex) {
	        Pane pane = new Pane();    
	        pane.setMinSize(32,32);
	        pane.setMaxSize(32,32);
	        pane.setOnMouseClicked(e -> {
	        	insertEntity(colIndex, rowIndex);
	        	Coordinate curTile = new Coordinate(colIndex, rowIndex);
	        	deleteTarget = game.getFirstEntity(curTile);
	        	setHunter();
	        	if(deleteTarget == null) {
	        		targetName.setText("None");
	        	}else {
	        		targetName.setText(deleteTarget.getName());
	        	}
	        });
	        map.add(pane, colIndex, rowIndex);
	    }

		/**
		 * Test if Hunter needs to be set
		 */
		public void setHunter() {
			if(deleteTarget instanceof entities.Hunter && nextHunter == 1) {
				houndToSet.setHunter((entities.Hunter)deleteTarget);
				nextHunter = 0;
				houndToSet = null;
				houndMarker.setText("None Selected");
			}
		}

		/**
		 * 
		 * @param colIndex specified cell in the GridPane
		 * @param rowIndex specified cell in the GridPane
		 */
	    public void insertEntity(int colIndex, int rowIndex) {
	    	if(selectedEntity != null){
	    		Coordinate requestedSpace = new Coordinate(colIndex, rowIndex);
	    		InsertEntityCommand toDo = new InsertEntityCommand(producer, game, requestedSpace, selectedEntity);
	    		invoker.invoke(toDo);
	    	}
	    	printGame();
	    }
	    
	    /**
	     * Delete currently selected entity
	     */
	    public void deleteEntity() {
	    	if(deleteTarget != null) {
	    		DeleteEntityCommand toDo = new DeleteEntityCommand(game, deleteTarget);
	    		invoker.invoke(toDo);
	    	}
	    	printGame();
	    }
	    
	    /**
	     * undo last action
	     */
	    public void undo() {
	    	invoker.undo();
	    	printGame();
	    }
	    
	    /*
	     * Nullify selection and target
	     */
	    public void nullify() {
	    	deleteTarget = null;
	    	targetName.setText("None");
	    	selectedEntity = null;
	    	descriptor.setText("None");
	    }
	    
	    /**
	     * Print out current game state
	     */
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
					//Image image = new Image(path + "white.png");
					if (entity != null) {
						Image image = new Image(path + entity.getName()
													+ ".png");
						ImageView iv = new ImageView(image);
						iv.setFitHeight(32);
						iv.setFitWidth(32);
						imageMap.add(iv, i, j);
					}

				}
			}
	    }
	    /**
	     * Test the current game, changes screen state
	     */
			public void testGame() {
				int layers = constructWinCon();
				if(layers == 0) {
					descriptor.setText("Please Select a Win Condition Or include an Exit");
					return;
				}
				game.setWinChecker(winCheck);
		        Screen test = new Screen(super.getS(), "Test", "view/test.fxml");
		        TestController pc = new TestController(super.getS(), game);
		        pc.getClass();
		        test.start(pc);
				
			}
			
			/**
			 * Return to menu
			 */
			public void previousMenu() {
		        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
		        MapSelectController msc = new MapSelectController(super.getS());
		        mapSelect.start(msc);
			}
			
			/**
			 * Adds TreasureWin Decorator
			 */
			public void addTreasure() {
				winCheck = new TreasureWin(winCheck);
			}
			
			/**
			 * Adds FloorWin Decorator
			 */
			public void addFloor() {
				winCheck = new FloorWin(winCheck);
			}
			
			/**
			 * Adds KillWin Decorator
			 */
			public void addKill() {
				winCheck = new KillWin(winCheck);
			}
			/**
			 * Adds ExitWin Decorator
			 */
			public int exitCheck(int layers) {
				for(Entity entity: game.getEntities()) {
					if(entity instanceof entities.Exit) {
						winCheck = new ExitWin(new WinChecker());
						layers++;
						return layers;
					}
				}
				return layers;
			}
			
			/**
			 * Construct the winCondition based on selection
			 */
			public int constructWinCon() {
				int layers = 0;
				for( Node node: winConPanel.getChildren()) {
					if( node instanceof CheckBox) {
						if ( ((CheckBox) node).isSelected() ) {
							layers++;
							node.getOnContextMenuRequested().handle(null);
						} 
					}
				}
				layers = exitCheck(layers);
				return layers;
			}
			
			/**
			 * Enables assignment of hunter to currently selected Hound
			 */
			public void assignHunter() {
				if(deleteTarget == null) return;
				if(deleteTarget instanceof entities.Hound) {
					houndMarker.setText("Select Hunter");
					nextHunter = 1;
					houndToSet = (entities.Hound)deleteTarget;
				}
			}
			
			public String description(String name) {
				if(name.equals("Player")) {
					return "The player can be moved either up, down, left,"+"\n"
							+ " or right into adjacent squares,"+"\n"
							+ " as long as another entity does not stop them (e.g. a wall).";
				}else if(name.equals("Wall")) {
					return "Blocks the movement of the player, enemies, boulders and arrows.";
				}else if(name.equals("Exit")) {
					return "If the player goes through an exit the puzzle is complete.";
				}else if(name.equals("Treasure")) {
					return "Can be collected by the player.";
				}else if(name.equals("Door")) {
					return "Exists in conjunction with a single key that can open it." +"\n"
							+ " If the player holds the key, they can open the door by moving through it. " +"\n"
							+ "After opening it remains open. ";
				}else if(name.equals("Key")) {
					return "Can be picked up by the player when they move into the containing square."+"\n"
							+ " The player can carry only one key at a time,"+"\n"
							+ " and only one door has a lock that fits the key." +"\n"
							+ " The key disappears once it is used to open its corresponding door.";
				}else if(name.equals("Boulder")) {
					return "Acts like a wall in most cases. "+"\n"
							+ "The only differences are that it can be pushed by the player"+"\n"
							+ " into adjacent squares and can be destroyed by a bomb."+"\n"
							+ " The player is only strong enough to push one boulder at a time.";
				}else if(name.equals("FloorSwitch")) {
					return "Switches behave like empty squares so other entities can appear on top of them. "+"\n"
							+ "When a boulder is pushed onto a floor switch, it is triggered."+"\n"
							+ " Pushing a boulder off the floor switch untriggers it.";
				}else if(name.equals("Bomb_4")) {
					return "The bomb is picked up by the player when they move into the square containing it.";
				}else if(name.equals("Pit")) {
					return "If the player falls into a pit they die. Boulders pushed into a pit disappear.";
				}else if(name.equals("Hunter")) {
					return "This enemy constantly moves toward the player, stopping if it cannot move any closer."+"\n"
							+ " Like all enemies, the player dies upon collision with them.";
				}else if(name.equals("Strategist")) {
					return "An enemy that moves towards a square the player is likely to move to next.";
				}else if(name.equals("Hound")) {
					return "An enemy that assists the hunter by positioning "
							+ "itself such that the player is between it and the hunter.";
				}else if(name.equals("Coward")) {
					return "An enemy that behaves like the hunter when far away from the player,"+"\n"
							+ " but runs away when it gets close. Like all enemies, the player dies upon collision with them.";
				}else if(name.equals("Sword")) {
					return "This can be picked up the player and used to kill enemies. "+"\n"
							+ "Only one sword can be carried at once. "+"\n"
							+ "Each sword is only capable of 5 hits and disappears after that. "+"\n"
							+ "One hit of the sword is sufficient to destroy any enemy.";
				}else if(name.equals("Arrow")) {
					return "Can be collected by the player and at will either left, right, up or down."+"\n"
							+ " Enemies are destroyed if they are hit with an arrow."+"\n"
							+ " Arrows are destroyed upon collision with anything."+"\n"
							+ " There is no limit to how many arrows the player can carry.";
				}else if(name.equals("InvincibilityPotion")) {
					return "If the player picks this up they become invincible to all bombs and enemies."+"\n"
							+ " Colliding with an enemy should result in their immediate destruction."+"\n"
							+ "Because of this, all enemies will run away from the player when they are invincible. "+"\n"
							+ "The effect of the potion only lasts a limited time.";
				}else if(name.equals("HoverPotion")) {
					return "Gives the player the ability to hover and fly over pits."+"\n"
							+ " This potion lasts until either the player is destroyed or the dungeon is complete.";
				}else if(name.equals("TreasureGoblin")) {
					return "Steals treasure, if no treasure is available will attack the Player."+"\n"
							+ " Drops Treasure on death";
				}else if(name.equals("Bone")) {
					return "Bone distracts the Hound, Can be picked up and thrown";
				}
			
				return " ";
			}
	    }

	

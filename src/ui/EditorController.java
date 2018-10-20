package ui;
import entities.Coordinate;
import entities.Entity;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    	//private GridPane gridTest;
    	
    	private String selectedEntity = null;
    	private EntityProducer producer;
    	private Game game;
    	private EditorInvoker invoker;
    	private Entity deleteTarget = null;
    	
    	public EditorController(Stage s, Game game) {
    		super(s);
    		this.game = game;
       	}
    	
    	//Lines 22 to 50 Taken from StackOverflow https://stackoverflow.com/questions/31095954
	    public void initialize() {
	    	EntityFactory factory = new EntityFactory();
	    	producer = new EntityProducer(factory); // probably need to add a game to this
	    	invoker = new EditorInvoker();
	        int numCols = game.getWidth() ;
	        int numRows = game.getHeight() ;
	        game.generatePerimeter();

	        for (int i = 0 ; i <= numCols ; i++) {
	            ColumnConstraints colConstraints = new ColumnConstraints();
	            colConstraints.setHgrow(Priority.NEVER);
	            //colConstraints.setMaxWidth(32);
	            //colConstraints.setMinWidth(32);
	            map.getColumnConstraints().clear();
	            map.getColumnConstraints().add(colConstraints);
	            imageMap.getColumnConstraints().clear();
	            imageMap.getColumnConstraints().add(colConstraints);
	            //gridTest.getColumnConstraints().add(colConstraints);
	        }

	        for (int i = 0 ; i <= numRows ; i++) {
	            RowConstraints rowConstraints = new RowConstraints();
	            rowConstraints.setVgrow(Priority.NEVER);
	            
	            //rowConstraints.setMaxHeight(32);
	            //rowConstraints.setMinHeight(32);
	            map.getRowConstraints().clear();
	            map.getRowConstraints().add(rowConstraints);  
	            imageMap.getRowConstraints().clear();
	            imageMap.getRowConstraints().add(rowConstraints);
	        }

	        for (int i = 0 ; i <= numCols ; i++) {
	            for (int j = 0; j <= numRows; j++) {
	                addPane(i, j);
	            }
	        }
	        
	        for(Node node: selector.getChildren()) {
	        	node.setOnMouseClicked(e -> {
	        		selectedEntity = node.getId();
	        		descriptor.setText(selectedEntity);
	        	});
	        }
	        printGame();
	        
	        for( Node node: selector.getChildren()) {
	        	if(node instanceof Pane) {
	        		for(Node childNode: ((Pane) node).getChildren()) {
	        			if(childNode instanceof ImageView) {
	        				Image sprite = new Image("resources/" + node.getId()
							+ ".png");
	        				System.out.println("resources/" + node.getId()
							+ ".png");
	        				((ImageView) childNode).setImage(sprite);
	        			}
	        		}
	        	}
	        }
	    }

	    private void addPane(int colIndex, int rowIndex) {
	        Pane pane = new Pane();    
	        pane.setMinSize(32,32);
	        pane.setMaxSize(32,32);
	    
	        pane.setOnMouseClicked(e -> {
	        	insertEntity(colIndex, rowIndex);
	        	Coordinate curTile = new Coordinate(colIndex, rowIndex);
	        	deleteTarget = game.getFirstEntity(curTile);
	        	//System.out.println("NULL");
	        	targetName.getId();
	        	if(deleteTarget == null) {
	        		targetName.setText("None");
	        	}else {
	        		targetName.setText(deleteTarget.getName());
	        	}
	            System.out.printf("Mouse enetered cell [%d, %d]%n", colIndex, rowIndex);
	        });
	        map.add(pane, colIndex, rowIndex);
	    }

	    public void insertEntity(int colIndex, int rowIndex) {
	    	if(selectedEntity != null){
	    		Coordinate requestedSpace = new Coordinate(colIndex, rowIndex);
	    		//EntityProducer producer, Game game, Coordinate co, String entity
	    		InsertEntityCommand toDo = new InsertEntityCommand(producer, game, requestedSpace, selectedEntity);
	    		invoker.invoke(toDo);
	    	}
	    	printGame();
	    }
	    
	    public void deleteEntity() {
	    	if(deleteTarget != null) {
	    		DeleteEntityCommand toDo = new DeleteEntityCommand(game, deleteTarget);
	    		invoker.invoke(toDo);
	    	}
	    	printGame();
	    }
	    
	    public void undo() {
	    	invoker.undo();
	    	printGame();
	    }
	    
	    public void nullify() {
	    	deleteTarget = null;
	    	targetName.setText("None");
	    	selectedEntity = null;
	    	descriptor.setText("None");
	    }
	    
	    public void printGame() {
			imageMap.getChildren().clear();
			for( int i = 0; i <= game.getWidth(); i++ ) {
				for ( int j = 0; j <= game.getHeight(); j++ ) {
					Coordinate newPos = new Coordinate(i,j);
					Entity entity = game.getFirstEntity(newPos);
					Image image = new Image("resources/white.png");
					if (entity != null) {
						//System.out.println("Adding Entity: " + entity.getName());
						image = new Image("resources/" + entity.getName()
													+ ".png");
					}
					
					ImageView iv = new ImageView(image);
					iv.setFitHeight(32);
					iv.setFitWidth(32);
					iv.setNodeOrientation(NodeOrientation.INHERIT);
//					GridPane.setFillWidth(iv, true);
//					GridPane.setFillHeight(iv, true);
					imageMap.add(iv, i, j);

				}
			}
	    }
			public void testGame() {
		        Screen test = new Screen(super.getS(), "Test", "view/test.fxml");
		        TestController pc = new TestController(super.getS(), game);
		        pc.getClass();
		        test.start(pc);
				
			}
	    }

	    
	    
//        pane.setOnMouseClicked(e -> {
//            System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
//        });
	

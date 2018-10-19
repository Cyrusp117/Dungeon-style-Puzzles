package ui;
import entities.Coordinate;
import entities.Entity;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
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
    	
    	private String selectedEntity = null;
    	private EntityProducer producer;
    	private Game game;
    	private EditorInvoker invoker;
    	
    	public EditorController(Stage s, Game game) {
    		super(s);
    		this.game = game;
       	}
    	
    	//Lines 22 to 50 Taken from StackOverflow https://stackoverflow.com/questions/31095954
	    public void initialize() {
	    	EntityFactory factory = new EntityFactory();
	    	producer = new EntityProducer(factory); // probably need to add a game to this
	    	invoker = new EditorInvoker();
	        int numCols = 20 ;
	        int numRows = 20 ;

	        for (int i = 0 ; i < numCols ; i++) {
	            ColumnConstraints colConstraints = new ColumnConstraints();
	            colConstraints.setHgrow(Priority.SOMETIMES);
	            map.getColumnConstraints().add(colConstraints);
	        }

	        for (int i = 0 ; i < numRows ; i++) {
	            RowConstraints rowConstraints = new RowConstraints();
	            rowConstraints.setVgrow(Priority.SOMETIMES);
	            map.getRowConstraints().add(rowConstraints);
	        }

	        for (int i = 0 ; i < numCols ; i++) {
	            for (int j = 0; j < numRows; j++) {
	                addPane(i, j);
	            }
	        }
	        
	        for(Node node: selector.getChildren()) {
	        	node.setOnMouseClicked(e -> {
	        		selectedEntity = node.getId();
	        		descriptor.setText(selectedEntity);
	        	});
	        }
	    }

	    private void addPane(int colIndex, int rowIndex) {
	        Pane pane = new Pane();
	        pane.setOnMouseClicked(e -> {
	        	insertEntity(colIndex, rowIndex);
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
	    }
	    
	    public void deleteEntity(Entity entity) {
	    	if(entity != null) {
	    		DeleteEntityCommand toDo = new DeleteEntityCommand(game, entity);
	    		invoker.invoke(toDo);
	    	}
	    }
	    
	    public void undo() {
	    	invoker.undo();
	    }
//        pane.setOnMouseClicked(e -> {
//            System.out.printf("Mouse entered cell [%d, %d]%n", colIndex, rowIndex);
//        });
	}

package UnitTests;

import java.awt.Robot;
import java.awt.event.KeyListener;

import org.junit.Before;

import ui.Game;
import entities.Coordinate;
import entities.Entity;
import entities.Player;
import entities.Wall;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import ui.MainMenuController;
import ui.Screen;

public abstract class testSetup extends Application {
	protected static final KeyCode DOWN = KeyCode.S;
	protected static final KeyCode UP = KeyCode.W;
	protected static final KeyCode LEFT = KeyCode.A;
	protected static final KeyCode RIGHT = KeyCode.D;
	protected Robot robot;
	protected Player player;
	protected Game game;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
        Screen mainMenu = new Screen(primaryStage, "Main Menu", "view/mainmenu.fxml");
        MainMenuController mmc = new MainMenuController(primaryStage);
        mainMenu.start(mmc);
//		this.robot = new Robot();
//		robot.waitForIdle();
//		robot.setAutoDelay(50);
//		robot.keyPress();(javafx.scene.input.KeyEvent);
//		robot.keyRelease(KeyEvent.VK_2);
//		robot.delay(100);
//		robot.keyPress(KeyEvent.VK_1);
//		robot.keyRelease(KeyEvent.VK_1);
//		robot.delay(100);
//		player = game.getPlayer();
	}
	
	@Before
	public void setUp() throws Exception {
//		Application app = new Application("Game Menu", 1, 1); //Create window application
//		KeyListener playerInput = new InputManagerMenu(app);
//		app.getFrame().addKeyListener(playerInput);  //refactor this

//		InputManagerDesigner inputManager = (InputManagerDesigner) (app.getFrame().getKeyListeners()[0]);
//		game = inputManager.getGame();

//
//		KeyListener playerInput = new InputManagerPlayer(game, app);
//		app.getFrame().addKeyListener(playerInput);
//		game.init();
//		game.setPlayerInput((InputManagerPlayer)playerInput);
//		game.setPlayerOne(player);
	}
	
    public static void main(String[] args) {
        launch(args);
    }


	public void move (int keyCode, int count) {
		int i = 0;
		while (i < count) {
			robot.keyPress(keyCode);
			robot.keyRelease(keyCode);
			robot.delay(100);
			i++;
		}
	}

}

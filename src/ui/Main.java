package ui;

import java.io.File;

import javafx.application.Application;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Screen mainMenu = new Screen(primaryStage, "Main Menu", "view/mainmenu.fxml");
        MainMenuController mmc = new MainMenuController(primaryStage);
        mainMenu.start(mmc);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

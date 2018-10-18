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
//        play_background();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private void play_background() {
    	Media sound = new Media(new File("src/ui/background.mp3").toURI().toString());
    	MediaPlayer mediaPlayer = new MediaPlayer(sound);
    	mediaPlayer.play();
	}
}

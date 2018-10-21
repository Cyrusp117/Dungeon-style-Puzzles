package ui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;


public class MainMenuController extends Controller implements Initializable {

	@FXML
	private MediaView mv;
	MediaPlayer mp;
	Media me;
	@FXML 
	private Button playerMode;
	@FXML
	private Button designMode;
	
	public MainMenuController(Stage s) {
		super(s);
	}
	
	/**
	 * Move to Map selection screen
	 */
	public void startGame() {
		play_note();
		Stage curStage = super.getS();
        Screen mapSelect = new Screen(curStage, "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}
	
	/*
	 * Play audio clip
	 */
	private void play_note() {
		AudioClip note = new AudioClip(this.getClass().getResource("start_game.wav").toString());
		//note.setCycleCount(3);
		note.play();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		String path = new File("src/ui/background.mp3").getAbsolutePath();
		me = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(me);
		mv.setMediaPlayer(mp);
		mp.setCycleCount(99);
		mp.play();
		
	}

}

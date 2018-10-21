package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class LossController extends Controller {

	@FXML
	private Button escapeBtn;
	
	
	
	public LossController(Stage s) {
		super(s);
	}

	/*
	 * Returns to previous Menu screen
	 */
	public void previousMenu() {
        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}
}

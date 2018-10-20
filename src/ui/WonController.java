package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class WonController extends Controller {

	@FXML
	private Button escapeBtn;
	
	public WonController(Stage s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	public void previousMenu() {
        Screen mapSelect = new Screen(super.getS(), "Map Select", "view/MapSelect.fxml");
        MapSelectController msc = new MapSelectController(super.getS());
        mapSelect.start(msc);
	}

}

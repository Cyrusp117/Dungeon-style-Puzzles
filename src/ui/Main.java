package ui;

import javafx.application.Application;
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

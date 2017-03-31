package Controller;

import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartController {
    @FXML
    public Button playVsPc;
    public Button playVsFriend;
    private MainController mainController;

    public void setStart(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void playVsFriend(){
        mainController.initFriendGameGui();
    }

    @FXML
    private void playVsPc() {
        mainController.initPcGameGui();
    }

    @FXML
    private void logOut() {
        mainController.initLoginGui();
    }
}

package Controller;

import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class StartController.
 */
public class StartController {
    /**
     * All used controls from View.
     */
    @FXML
    public Button playVsPc;
    public Button playVsFriend;

    /**
     * mainController.
     */
    @FXML
    private MainController mainController;

    /**
     * Set start function.
     *
     * This function is used to set the maincontroller and to manipulate the View.
     *
     * @param mainController
     */
    @FXML
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

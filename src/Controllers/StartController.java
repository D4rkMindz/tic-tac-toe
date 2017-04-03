package Controllers;

import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class StartController.
 */
public class StartController {
    /**
     * All used controls from Resources.Views.
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
     * This function is used to set the maincontroller and to manipulate the Resources.Views.
     *
     * @param mainController MainController
     */
    @FXML
    public void setStart(MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * Initialize Play GUI to play against a friend.
     */
    @FXML
    private void playVsFriend(){
        mainController.initFriendGameGui();
    }

    /**
     * Initialize Play GUI to play against the Computer.
     */
    @FXML
    private void playVsPc() {
        mainController.initPcGameGui();
    }

    /**
     * Return back to the login screen.
     */
    @FXML
    private void logOut() {
        mainController.initLoginGui();
    }
}

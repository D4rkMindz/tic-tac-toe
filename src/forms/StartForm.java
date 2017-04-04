package forms;

import start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Class StartForm.
 */
public class StartForm {
    /**
     * All used controls from resource.view.
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
     * This function is used to set the maincontroller and to manipulate the resource.view.
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
    private void playVsFriendOnClick(){
        mainController.initFriendGameGui();
    }

    /**
     * Initialize Play GUI to play against the Computer.
     */
    @FXML
    private void playVsPcOnClick() {
        mainController.initPcGameGui();
    }

    /**
     * Return backOnClick to the login screen.
     */
    @FXML
    private void logOutOnClick() {
        mainController.initLoginGui();
    }
}

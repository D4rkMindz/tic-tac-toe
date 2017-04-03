package Controllers;

import Services.FriendPlayService;
import Start.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Class VsFriendController.
 */
public class VsFriendController {
    /**
     * Array for clicked positions (button positions).
     */
    private String[] positions = new String[9];

    /**
     * Array to check, which button is active and which not.
     */
    private boolean[] btnActive = new boolean[9];

    /**
     * Class variables.
     */
    private char player = 'x';
    private int playsPossible = 9;
    private static boolean hasEnded;
    private static char charWinner;

    /**
     * All used controls from Resources.Views.
     */
    @FXML
    private Button[] btnArray = new Button[9];
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    public Label FriendWinner;

    /**
     * mainController.
     */
    @FXML
    private MainController mainController;

    /**
     * Set start function.
     * <p>
     * This function is used to set the maincontroller and to manipulate the Resources.Views.
     *
     * @param mainController
     */
    @FXML
    public void setStart(MainController mainController) {
        this.mainController = mainController;
        this.setDefaults();

        if (charWinner == 'x' || charWinner == 'o') {
            FriendWinner.setText("Player " + (Character.toUpperCase(charWinner)) + " Won");
        }

        this.addAction(btn0, 0);
        this.addAction(btn1, 1);
        this.addAction(btn2, 2);
        this.addAction(btn3, 3);
        this.addAction(btn4, 4);
        this.addAction(btn5, 5);
        this.addAction(btn6, 6);
        this.addAction(btn7, 7);
        this.addAction(btn8, 8);
    }

    /**
     * Set Default values into variables.
     */
    private void setDefaults() {
        positions[0] = "empty";
        positions[1] = "empty";
        positions[2] = "empty";
        positions[3] = "empty";
        positions[4] = "empty";
        positions[5] = "empty";
        positions[6] = "empty";
        positions[7] = "empty";
        positions[8] = "empty";
        btnActive[0] = true;
        btnActive[1] = true;
        btnActive[2] = true;
        btnActive[3] = true;
        btnActive[4] = true;
        btnActive[5] = true;
        btnActive[6] = true;
        btnActive[7] = true;
        btnActive[8] = true;
        btnArray[0] = btn0;
        btnArray[1] = btn1;
        btnArray[2] = btn2;
        btnArray[3] = btn3;
        btnArray[4] = btn4;
        btnArray[5] = btn5;
        btnArray[6] = btn6;
        btnArray[7] = btn7;
        btnArray[8] = btn8;
    }

    /**
     * Add action to button.
     * <p>
     * This function adds an action for the button (setOnAction)
     *
     * @param btn Button
     * @param nr  int Buttonnumber
     */
    private void addAction(Button btn, int nr) {
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (e.getSource().equals(btn) && btnActive[nr]) {
                    if (hasEnded) {
                        return;
                    }
                    FriendPlayService friendPlayService = new FriendPlayService();
                    if (player == 'x') {
                        String image = VsPcController.class.getResource("/Resources/Images/cross.png").toExternalForm();
                        btn.setStyle("-fx-background-image: url(" + image + ");" +
                                "-fx-background-size: 40 40;" +
                                "-fx-background-repeat: no-repeat;" +
                                "-fx-background-position: center;");

                        player = 'o';
                        btnActive[nr] = false;
                        playsPossible--;
                        positions[nr] = "cross";

                        if (friendPlayService.checkWinner(positions)) {
                            setStart(mainController);
                        }

                    } else {
                        String image = VsPcController.class.getResource("/Resources/Images/circle.png").toExternalForm();
                        btn.setStyle("-fx-background-image: url(" + image + ");" +
                                "-fx-background-size: 40 40;" +
                                "-fx-background-repeat: no-repeat;" +
                                "-fx-background-position: center;");

                        player = 'x';
                        btnActive[nr] = false;
                        playsPossible--;
                        positions[nr] = "circle";

                    }
                    if (friendPlayService.checkWinner(positions)) {
                        FriendWinner.setText("Player " + charWinner + " Won");
                    }
                }
            }
        });
    }

    /**
     * Set winner into variable.
     *
     * This function sets the winner into the class-variable. Also, the boolean state of hasEnded is going to be changed
     * to true (game has ended)
     *
     * @param winner char winner ('x' or 'o')
     */
    public void setWinner(char winner) {
        hasEnded = true;
        charWinner = winner;
        System.out.print("Player " + winner + " won");
    }

    /**
     * Reset game.
     *
     * This function resets every state to the beginning state.
     */
    @FXML
    public void reset() {
        hasEnded = false;
        charWinner = ' ';
        mainController.initFriendGameGui();
    }

    /**
     * Return back to previous screen.
     */
    @FXML
    public void back() {
        mainController.initStartGui();
    }
}

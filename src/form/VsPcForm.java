package form;

import service.EasyPlayService;
import service.MediumPlayService;
import service.PcPlayService;
import start.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Class VsPcForm.
 * This class is for the GUI, where you are playing against the computer, responsible.
 */
public class VsPcForm {
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
    private char humanPlaysWith = 'x';
    private int playsPossible = 9;
    private static boolean hasEnded;
    private static char charWinner;
    private static int computerLevel = 0;


    /**
     * All used controls from resource.view.
     */
    @FXML
    private Button[] btnArray = new Button[9];
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, easy, medium, hard;
    public Label Winner;

    /**
     * mainController.
     */
    @FXML
    private MainController mainController;

    /**
     * Set start function.
     * <p>
     * This function is used to set the maincontroller and to manipulate the resource.view.
     *
     * @param mainController
     */
    @FXML
    public void setStart(MainController mainController) {
        this.mainController = mainController;
        this.setDefaults();

        if (charWinner == 'x' || charWinner == 'o') {
            Winner.setText("Player " + (Character.toUpperCase(charWinner)) + " Won");
        }

        if (computerLevel == 0) {
            easy.setStyle("-fx-background-color: chartreuse");
        }
        if (computerLevel == 1) {
            medium.setStyle("-fx-background-color: chartreuse");
        }
        if (computerLevel == 2) {
            hard.setStyle("-fx-background-color: chartreuse");
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
                    PcPlayService PcPlayService = new PcPlayService();
                    if (player == 'x') {
                        String image = VsPcForm.class.getResource("/resource/image/cross.png").toExternalForm();
                        btn.setStyle("-fx-background-image: url(" + image + ");" +
                                "-fx-background-size: 40 40;" +
                                "-fx-background-repeat: no-repeat;" +
                                "-fx-background-position: center;");

                        player = 'o';
                        btnActive[nr] = false;
                        playsPossible--;
                        positions[nr] = "cross";

                        if (PcPlayService.checkWinner(positions)) {
                            setStart(mainController);
                        }

                        if (player != humanPlaysWith) {
                            computerPlay();
                        }
                    } else {
                        String image = VsPcForm.class.getResource("/resource/image/circle.png").toExternalForm();
                        btn.setStyle("-fx-background-image: url(" + image + ");" +
                                "-fx-background-size: 40 40;" +
                                "-fx-background-repeat: no-repeat;" +
                                "-fx-background-position: center;");

                        player = 'x';
                        btnActive[nr] = false;
                        playsPossible--;
                        positions[nr] = "circle";

                        if (PcPlayService.checkWinner(positions)) {
                            Winner.setText("Player " + charWinner + " Won");
                        }

                        if (player != humanPlaysWith) {
                            computerPlay();
                        }
                    }
                }
            }
        });
    }

    /**
     * Set the computer difficulty to easy.
     */
    public void setDifficultyEasy() {
        computerLevel = 0;
        resetOnClick();
    }

    /**
     * Set the computer difficulty to medium.
     */
    public void setDifficultyMedium() {
        computerLevel = 1;
        resetOnClick();
    }

    /**
     * Set the computer difficulty to hard.
     */
    public void setDifficultyHard() {
        computerLevel = 2;
        resetOnClick();
    }

    /**
     * Reset game.
     * <p>
     * This function resets every state to the beginning state.
     */
    public void resetOnClick() {
        hasEnded = false;
        charWinner = ' ';
        mainController.initPcGameGui();
    }

    /**
     * Return backOnClick to previous screen.
     */
    public void backOnClick() {
        hasEnded = false;
        charWinner = ' ';
        mainController.initStartGui();
    }

    /**
     * Computer play.
     * <p>
     * This function lets the computer, depending on which level he's playing, play
     */
    private void computerPlay() {
        if (hasEnded) {
            return;
        }
        if (computerLevel == 0) {
            this.playEasy();
        }
        if (computerLevel == 1) {
            this.playMedium();
        }
        if (computerLevel == 2) {
            this.playHard();
        }
    }

    /**
     * Set winner into variable.
     * <p>
     * This function sets the winner into the class-variable. Also, the boolean state of hasEnded is going to be changed
     * to true (game has ended)
     *
     * @param winner char winner ('x' or 'o')
     */
    public void setWinner(char winner) {
        hasEnded = true;
        charWinner = winner;
    }

    /**
     * Computer play Easy.
     */
    private void playEasy() {
        EasyPlayService easyPlayService = new EasyPlayService();
        int result = easyPlayService.playEasy(btnActive, playsPossible);
        Button btn = btnArray[result];
        btn.fire();

    }

    /**
     * Computer play Medium.
     */
    private void playMedium() {
        MediumPlayService mediumPlayService = new MediumPlayService();

        char computerPlayWith;
        if (humanPlaysWith == 'x') {
            computerPlayWith = 'o';
        } else {
            computerPlayWith = 'x';
        }
        int result = mediumPlayService.playMedium(positions, computerPlayWith, playsPossible, btnActive, 50, 70);
        Button btn = btnArray[result];
        btn.fire();
    }

    /**
     * Computer play Hard.
     */
    private void playHard() {
        MediumPlayService mediumPlayService = new MediumPlayService();
        char computerPlayWith;
        if (humanPlaysWith == 'x') {
            computerPlayWith = 'o';
        } else {
            computerPlayWith = 'x';
        }
        int result = mediumPlayService.playMedium(positions, computerPlayWith, playsPossible, btnActive, 100, 100);
        Button btn = btnArray[result];
        btn.fire();
    }
}

package Controller;

import Service.FriendPlayService;
import Start.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * Created by Björn on 17.03.2017.
 */
public class VsFriendController {
    @FXML
    private static boolean hasEnded;
    private static int computerLevel = 0;
    private static char charWinner;
    private int playsPossible = 9;


    private String[] positions = new String[9];

    @FXML
    private Button[] btnArray = new Button[9];

    @FXML
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;

    @FXML
    private boolean[] btnActive = new boolean[9];

    @FXML
    public Label FriendWinner;

    private char player = 'x';

    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {
        this.mainController = mainController;
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

    private Button addAction(Button btn, int nr) {
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (e.getSource().equals(btn) && btnActive[nr]) {
                    if (hasEnded) {
                        return;
                    }
                    FriendPlayService friendPlayService = new FriendPlayService();
                    if (player == 'x') {
                        String image = VsPcController.class.getResource("/img/cross.png").toExternalForm();
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
                        String image = VsPcController.class.getResource("/img/circle.png").toExternalForm();
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
        return btn;
    }

    public void setWinner(char winner) {
        hasEnded = true;
        charWinner = winner;
        System.out.print("Player " + winner + " won");
    }

    @FXML
    public void reset() {
        hasEnded = false;
        charWinner = ' ';
        mainController.initFriendGameGui();
    }

    @FXML
    public void back(){
        mainController.initStartGui();
    }
}

package Controller;

import Service.PlayService;
import Start.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sun.applet.Main;

import java.util.Random;


public class VsPcController {
    @FXML
    private static boolean hasEnded;
    private static int computerLevel = 0;
    private static char charWinner;

    private char player = 'x';
    private char humanPlaysWith = 'x';
    private int playsPossible = 9;

    private String[] positions = new String[9];

    @FXML
    private Button[] btnArray = new Button[9];

    @FXML
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, easy, medium;

    @FXML
    private boolean[] btnActive = new boolean[9];

    @FXML
    public Label Winner;

    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {
        this.mainController = mainController;
        this.setDefaults();

        if (charWinner == 'x' || charWinner == 'o') {
            Winner.setText("Player " + (Character.toUpperCase(charWinner)) + " Won");
        }

        if(computerLevel == 0){
            easy.setStyle("-fx-background-color: chartreuse");
        }
        if (computerLevel == 1){
            medium.setStyle("-fx-background-color: chartreuse");
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
                    PlayService playService = new PlayService();
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

                        if (playService.checkWinner(positions)) {
                            setStart(mainController);
                        }

                        if (player != humanPlaysWith) {
                            computerPlay();
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

                        if (playService.checkWinner(positions)) {
                            Winner.setText("Player " + charWinner + " Won");
                        }

                        if (player != humanPlaysWith) {
                            computerPlay();
                        }
                    }
                }
            }
        });
        return btn;
    }

    public void setDifficultyEasy(){
        computerLevel = 0;
        reset();
    }

    public void setDifficultyMedium(){
        computerLevel = 1;
        reset();
    }

    public void back() {
        hasEnded = false;
        charWinner = ' ';
        mainController.initStartGui();
    }

    public void reset() {
        hasEnded = false;
        charWinner = ' ';
        mainController.initPcGameGui();
    }

    private void computerPlay() {
        if (hasEnded) {
            return;
        }
        if (computerLevel == 0) {
           this.playEasy();
        }
        if (computerLevel == 1){
            this.playEasy();
        }
    }

    public void setWinner(char winner) {
        hasEnded = true;
        charWinner = winner;
        System.out.print("Player " + winner + " won");
    }

    private void playEasy(){
        Random r = new Random();
        boolean played = false;
        do {
            int randomBtn = r.nextInt(8);
            if (btnActive[randomBtn]) {
                Button btn = btnArray[randomBtn];
                played = true;
                btn.fire();
            }
            if (playsPossible < 2) {
                played = true;
            }
        } while (!played);
    }

    private void playMedium(){

    }
}

package Controller;

import Start.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class VsPcController {
    protected char player = 'x';

    @FXML
    public Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8;
    public boolean[] btnActive = new boolean[9];
    public boolean hasNotEnded = true;

    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {
        btnActive[0] = true;
        btnActive[1] = true;
        btnActive[2] = true;
        btnActive[3] = true;
        btnActive[4] = true;
        btnActive[5] = true;
        btnActive[6] = true;
        btnActive[7] = true;
        btnActive[8] = true;
        this.mainController = mainController;
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

    private Button addAction(Button btn, int nr) {
        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                if (e.getSource().equals(btn) &&btnActive[nr] && hasNotEnded) {
                    if (player == 'x') {
                        String image = VsPcController.class.getResource("/img/cross.png").toExternalForm();
                        btn.setStyle("-fx-background-image: url(" + image + ");" +
                                "-fx-background-size: 40 40;" +
                                "-fx-background-repeat: no-repeat;" +
                                "-fx-background-position: center;");
                        player = 'o';
                    } else {
                        String image = VsPcController.class.getResource("/img/circle.png").toExternalForm();
                        btn.setStyle("-fx-background-image: url(" + image + ");" +
                                "-fx-background-size: 40 40;" +
                                "-fx-background-repeat: no-repeat;" +
                                "-fx-background-position: center;");
                        player = 'x';
                    }
                    btnActive[nr] = false;
                }
            }
        });
        return btn;
    }

}

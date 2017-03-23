package Controller;

import Start.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class VsPcController {
    protected char player = 'x';

    @FXML
    public Button btn0;
    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public Button btn7;
    public Button btn8;

    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {

        this.mainController = mainController;
        btn0.setOnAction(new MyButtonHandler());
        btn1.setOnAction(new MyButtonHandler());
        btn2.setOnAction(new MyButtonHandler());
        btn3.setOnAction(new MyButtonHandler());
        btn4.setOnAction(new MyButtonHandler());
        btn5.setOnAction(new MyButtonHandler());
        btn6.setOnAction(new MyButtonHandler());
        btn7.setOnAction(new MyButtonHandler());
        btn8.setOnAction(new MyButtonHandler());
    }

    private class MyButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent evt) {
            if (evt.getSource().equals(btn6)) {
                System.out.print("Hello");
            } else {
                System.out.print("No");
            }
        }
    }

}

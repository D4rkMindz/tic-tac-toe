package Controller;

import Service.LoginValidationService;
import Start.MainController;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private MainController mainController;
    public TextField username;
    public TextField password;
    public Button Register;
    public Button Login;

    public void setStart(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void handleLogin() {
        String username = this.username.getText();
        String password = this.password.getText();

        LoginValidationService loginValServ = new LoginValidationService();
        if (loginValServ.isLoginValid(username, password)){
            mainController.initStartGui();
        } else{
            mainController.initLoginGui();
        }
    }

    @FXML
    private void handleRegister() {
        mainController.initRegisterGui();
    }
}
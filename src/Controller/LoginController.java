package Controller;

import Service.LoginValidationService;
import Service.ProxyService;
import Start.MainController;

import env.Environment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private MainController mainController;
    public TextField username;
    public TextField password;
    public Button Register;
    public Button Login;
    public Label errorMessage;

    @FXML
    public void setStart(MainController mainController) {

        this.mainController = mainController;
        LoginValidationService ls = new LoginValidationService();

        boolean err = ls.error;
        if (err) {
            this.errorMessage.setText("Invalid Credentials");
        }

    }

    @FXML
    private void handleRegister() {
        mainController.initRegisterGui();
    }

    @FXML
    private void handleLogin() {
        String username = this.username.getText();
        String password = this.password.getText();

        LoginValidationService loginValServ = new LoginValidationService();
        Environment env = new Environment();
        ProxyService proxyService = new ProxyService();

        if (loginValServ.isLoginValid(username, password, proxyService.useProxy , env.loginUrl)) {
            mainController.initStartGui();
        } else {
            mainController.initLoginGui();
        }
    }

    @FXML
    private void handleProxy() {
        mainController.initProxyGui();
    }
}
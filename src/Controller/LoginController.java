package Controller;

import Service.LoginValidationService;
import Service.ProxyService;
import Start.MainController;

import env.Environment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Class LoginController.
 */
public class LoginController {
    /**
     * Class variables.
     */
    private boolean success, useProxy = false;

    /**
     * All used controls from View.
     */
    public TextField username;
    public TextField password;
    public Label errorMessage;

    /**
     * mainController.
     */
    @FXML
    private MainController mainController;

    /**
     * Set start function.
     *
     * This function is used to set the maincontroller and to manipulate the View
     *
     * @param mainController
     */
    @FXML
    public void setStart(MainController mainController) {

        this.mainController = mainController;
        LoginValidationService ls = new LoginValidationService();

        boolean err = ls.error;
        if (!err) {
            this.errorMessage.setText("Invalid Credentials");
        }
        if (useProxy) {
            this.errorMessage.setText("Proxysettings activated");
        } else {
            this.errorMessage.setText("Proxysettings deactivated");
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
        boolean useProxyService = proxyService.getUseProxy();

        if (loginValServ.isLoginValid(username, password, useProxyService, env.getLoginUrl())) {
            mainController.initStartGui();
        } else {
            mainController.initLoginGui();
        }
    }

    @FXML
    private void handleProxy() {
        ProxyService proxyService = new ProxyService();
        if (useProxy) {
            proxyService.setUseProxy(false);
            useProxy = false;
        } else {
            proxyService.setUseProxy(true);
            useProxy = true;
        }

        if (this.success) {
            this.success = false;
        } else {
            this.success = true;
        }
        this.setStart(mainController);
    }
}
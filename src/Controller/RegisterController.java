package Controller;

import Service.ProxyService;
import Service.RegisterService;
import Start.MainController;
import env.Environment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sun.awt.image.ImageWatched;

public class RegisterController {
    public TextField username, email, password1, password2;
    public Label errorMessage, successMessage;
    public Button register;
    private static String tempUsername, tempEmail, tempPassword1, tempPassword2;
    private static boolean success = false;

    @FXML
    private MainController mainController;

    public void setStart(MainController mainController) {
        RegisterService registerService = new RegisterService();

        this.mainController = mainController;
        String err = registerService.error;
        if (err == null || err.equalsIgnoreCase("ok")) {
            if (success){
                this.successMessage.setText("Credentials Accepted");
                this.register.isDisabled();
            }
        } else if (err.equalsIgnoreCase("notOk")) {
            this.errorMessage.setText("Invalid Credentials");
            this.username.setText(tempUsername);
            this.email.setText(tempEmail);
            this.password1.setText(tempPassword1);
            this.password2.setText(tempPassword2);
        } else if (err.equalsIgnoreCase("userExists")) {
            this.errorMessage.setText("User Already Exists");
            this.username.setText(tempUsername);
            this.email.setText(tempEmail);
            this.password1.setText(tempPassword1);
            this.password2.setText(tempPassword2);
        }
    }

    @FXML
    private void validateRegistration() {
        String username = this.username.getText();
        String email = this.email.getText();
        String password1 = this.password1.getText();
        String password2 = this.password2.getText();

        tempUsername = username;
        tempEmail = email;
        tempPassword1 = password1;
        tempPassword2 = password2;

        RegisterService registerService = new RegisterService();
        ProxyService proxyService = new ProxyService();
        Environment env = new Environment();

        if (registerService.isRegistrationValid(username, email, password1, password2, proxyService.useProxy, env.registerUrl)) {
            success = true;
        }
        mainController.initRegisterGui();
    }

    @FXML
    private void goBack() {
        mainController.initLoginGui();
    }
}

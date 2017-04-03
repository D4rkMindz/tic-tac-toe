package Controllers;

import Services.RegisterService;
import Start.MainController;
import Config.Environment;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Config.Settings;

/**
 * Class RegisterController.
 */
public class RegisterController {
    /**
     * Class variables.
     */
    private static String tempUsername, tempEmail, tempPassword1, tempPassword2;
    private static boolean success = false;

    /**
     * All used controls from Resources.Views.
     */
    @FXML
    public TextField username, email, password1, password2;
    public Label errorMessage, successMessage;
    public Button register;

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
        RegisterService registerService = new RegisterService();

        this.mainController = mainController;
        String err = registerService.error;
        if (err == null || err.equalsIgnoreCase("ok")) {
            if (success == true) {
                this.successMessage.setText("User created successfully");
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

    /**
     * Validate registration.
     * <p>
     * This function takes the values from the input fields and sends it to the server-function. If the serverresponse
     * is okay, the validation process is finished and a successmessage is set into a class-variable
     */
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

        if (registerService.isRegistrationValid(username, email, password1, password2)) {
            success = true;
        }
        mainController.initRegisterGui();
    }

    /**
     * Return back to the login screen.
     */
    @FXML
    private void goBack() {
        mainController.initLoginGui();
    }
}

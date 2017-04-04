package forms;

import service.RegisterService;
import start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * Class RegisterForm.
 */
public class RegisterForm {
    /**
     * All used controls from resource.view.
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
     * This function is used to set the maincontroller and to manipulate the resource.view.
     *
     * @param mainController
     */
    @FXML
    public void setStart(MainController mainController) {
        this.mainController = mainController;
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

        RegisterService registerService = new RegisterService();

        if (registerService.isRegistrationValid(username, email, password1, password2)) {
            this.successMessage.setText(RegisterService.success);
            this.errorMessage.setText("");
            this.username.setText("");
            this.email.setText("");
            this.password1.setText("");
            this.password2.setText("");
        } else {
            this.successMessage.setText("" +
                    "");
            this.errorMessage.setText(RegisterService.error);
        }
    }

    /**
     * Return backOnClick to the login screen.
     */
    @FXML
    private void returnToLoginOnClick() {
        mainController.initLoginGui();
    }
}

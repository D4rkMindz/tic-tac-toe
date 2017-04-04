package forms;

import service.LoginValidationService;
import start.MainController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


/**
 * Class LoginForm.
 */
public class LoginForm {
    /**
     * All used controls from resource.view.
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
     * <p>
     * This function is used to set the maincontroller and to manipulate the resource.view
     *
     * @param mainController MainController
     */
    @FXML
    public void setStart(MainController mainController) {
        this.mainController = mainController;
        this.errorMessage.setText("");
    }

    /**
     * Handle registration.
     *
     * Go to the registration screen.
     */
    @FXML
    private void registrationLinkOnClick() {
        mainController.initRegisterGui();
    }

    /**
     * Handle login.
     *
     * This function takes the values from the input fields and sends them to the server-function. If the server
     * response is okay, the user is redirected to the start screen
     */
    @FXML
    private void loginButtonOnClick() {
        String username = this.username.getText();
        String password = this.password.getText();

        LoginValidationService loginValServ = new LoginValidationService();

        if (loginValServ.isLoginValid(username, password)) {
            mainController.initStartGui();
        } else {
            this.password.setText("");
            this.errorMessage.setText("Invalid Credentials");
        }
    }

    /**
     * Handle Proxy settings.
     *
     * This function changes the boolean state of the useProxy variable. Afterwards, there is a response displayed
     */
    @FXML
    private void proxyLinkOnClick() {
        mainController.initProxyGui();
    }
}
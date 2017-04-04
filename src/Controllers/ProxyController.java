package Controllers;


import Config.Settings;
import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProxyController {
    /**
     * Class variables.
     */
    private boolean success, useProxy = false;

    /**
     * All used controls from Resources.Views.
     */
    public TextField ip;
    public TextField port;
    public TextField username;
    public TextField password;
    public Label successMessage;

    /**
     * mainController.
     */
    @FXML
    private MainController mainController;

    /**
     * Set start function.
     * <p>
     * This function is used to set the maincontroller and to manipulate the Resources.Views
     *
     * @param mainController MainController
     */
    @FXML
    public void setStart(MainController mainController) {
        this.mainController = mainController;
        if (Settings.proxy.useProxy) {
            this.successMessage.setText("Proxysettings activated");
        } else {
            this.successMessage.setText("Proxysettings deactivated");
        }
    }

    public void returnToLogin(){
        mainController.initLoginGui();
    }

    public void handleSettings(){
        String ip = this.ip.getText();
        int port = Integer.parseInt(this.port.getText());
        String username = this.username.getText();
        String password = this.password.getText();

        Settings.proxy.useProxy = true;
        Settings.proxy.ip = ip;
        Settings.proxy.port = port;
        Settings.proxy.username = username;
        Settings.proxy.password = password;
        this.successMessage.setText("Proxy Activated");
    }

    public void discardSettings(){
        Settings.proxy.useProxy = false;
        Settings.proxy.ip = "";
        Settings.proxy.port = 0;
        Settings.proxy.username = "";
        Settings.proxy.password = "";
        this.ip.setText("");
        this.port.setText("");
        this.username.setText("");
        this.password.setText("");
        this.successMessage.setText("Proxy deactivated");
    }
}

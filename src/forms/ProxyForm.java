package forms;


import config.Settings;
import start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class ProxyForm {
    /**
     * All used controls from resource.view.
     */
    public TextField ip;
    public TextField port;
    public TextField username;
    public TextField password;
    public Label successMessage;
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
        if (Settings.proxy.useProxy) {
            this.successMessage.setText("Proxysettings activated");
        } else {
            this.successMessage.setText("Proxysettings deactivated");
        }
    }

    public void returnToLoginOnClick(){
        mainController.initLoginGui();
    }

    public void SaveSettingsOnClick(){
        String ip = this.ip.getText();
        int port = Integer.parseInt(this.port.getText());
        String username = this.username.getText();
        String password = this.password.getText();

        if (!Pattern.matches("^\\d*$", this.port.getText())){
            this.errorMessage.setText("Port must be a number");
        }


        Settings.proxy.useProxy = true;
        Settings.proxy.ip = ip;
        Settings.proxy.port = port;
        Settings.proxy.username = username;
        Settings.proxy.password = password;
        this.successMessage.setText("Proxy Activated");
    }

    public void discardSettingsOnClick(){
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

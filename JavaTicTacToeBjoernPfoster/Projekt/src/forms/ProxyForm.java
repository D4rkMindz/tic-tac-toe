package forms;


import config.Settings;
import javafx.scene.control.Button;
import start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

/**
 * Class ProxyForm
 * This class is for the Proxy-settings form responsible.
 */
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
    public Label passwordMessage;
    public Button proxyButton;

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
            this.proxyButton.setText("Deactivate");
        } else {
            this.successMessage.setText("Proxysettings deactivated");
            this.proxyButton.setText("Activate");
        }
        this.ip.setText(Settings.proxy.ip);
        this.port.setText(Integer.toString(Settings.proxy.port));
        this.username.setText(Settings.proxy.username);
        if (!Settings.proxy.password.equals("")){
            this.passwordMessage.setText("Password won't be displayed");
        } else {
            this.passwordMessage.setText("");
        }
    }

    /**
     * Return back to Login.
     */
    public void returnToLoginOnClick(){
        mainController.initLoginGui();
    }

    /**
     * Validate all Proxy settings and save them afterwards.
     */
    public void SaveSettingsOnClick(){
        if(Settings.proxy.useProxy){
            Settings.proxy.useProxy = false;
            this.proxyButton.setText("Activate");
            this.successMessage.setText("Proxy Deactivated");
            return;
        }
        if (!this.validateInsertedProxySettings()){
            return;
        }

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
        this.proxyButton.setText("Deactivate");
    }

    /**
     * Reset all Proxy settings to "nothing" ("").
     */
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
        this.errorMessage.setText("");
        this.successMessage.setText("Proxysettings discarded");
    }

    private boolean validateInsertedProxySettings(){
        this.successMessage.setText("");
        if (this.ip.getText().equals("")){
            this.errorMessage.setText("Please enter an IP-Address");
            return false;
        }
        if (!Pattern.matches("^\\d*$", this.port.getText())){
            this.errorMessage.setText("Port must be a number");
            return false;
        }
        if (this.port.getText().equals("")){
            this.errorMessage.setText("Please enter a Proxy Port");
            return false;
        }
        if (this.username.getText().equals("")){
            this.errorMessage.setText("Please enter an Username");
            return false;
        }
        if (this.password.getText().equals("")){
            this.errorMessage.setText("Please enter a Password");
            return false;
        }
        this.errorMessage.setText("");
        return true;
    }
}

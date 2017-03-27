package Controller;

import Service.ProxyService;
import Start.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProxyController {
    public TextField ip, port, username, password;
    public Label successMessage;
    private static boolean success = false;

    @FXML
    private MainController mainController;

    @FXML
    public void setStart(MainController mainController) {

        this.mainController = mainController;
        if (success){
            this.successMessage.setText("Proxysettings saved");
            success = false;
        }
    }

    @FXML
    private void returnToLogin() {
        mainController.initLoginGui();
    }

    @FXML
    private void handleSettings() {
        ProxyService proxyService = new ProxyService();
        proxyService.ip = this.ip.getText();
        proxyService.port = Integer.parseInt(this.port.getText());
        proxyService.username = this.username.getText();
        proxyService.password = this.password.getText();
        success = true;
    }
}

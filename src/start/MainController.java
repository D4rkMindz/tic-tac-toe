package start;

import config.Settings;
import forms.*;
import service.LoginValidationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainController extends Application {
    private Stage stage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Tic Tac Toe");
        Settings.Init("prod");
        initLoginGui();
    }

    public void initLoginGui() {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/resource/view/LoginGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            LoginForm controller = loader.getController();
            controller.setStart(this);

            scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initRegisterGui() {
        try {
            LoginValidationService lvs = new LoginValidationService();
            if (!lvs.isLoggedIn()) {
                this.initLoginGui();
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/resource/view/RegisterGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            RegisterForm controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initStartGui() {
        try {
            LoginValidationService lvs = new LoginValidationService();
            if (!lvs.isLoggedIn()) {
                this.initLoginGui();
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/resource/view/StartGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            StartForm controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initPcGameGui() {
        try {
            LoginValidationService lvs = new LoginValidationService();
            if (!lvs.isLoggedIn()) {
                this.initLoginGui();
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/resource/view/PcGameGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            VsPcForm controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initFriendGameGui() {
        try {
            LoginValidationService lvs = new LoginValidationService();
            if (!lvs.isLoggedIn()) {
                this.initLoginGui();
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/resource/view/FriendGameGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            VsFriendForm controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initProxyGui() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/resource/view/ProxyGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            ProxyForm controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

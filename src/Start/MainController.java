package Start;

import Config.Settings;
import Controllers.*;
import Services.LoginValidationService;
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
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/Resources/Views/LoginGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            LoginController controller = loader.getController();
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
            loader.setLocation(MainController.class.getResource("/Resources/Views/RegisterGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            RegisterController controller = loader.getController();
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
            loader.setLocation(MainController.class.getResource("/Resources/Views/StartGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            StartController controller = loader.getController();
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
            loader.setLocation(MainController.class.getResource("/Resources/Views/PcGameGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            VsPcController controller = loader.getController();
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
            loader.setLocation(MainController.class.getResource("/Resources/Views/FriendGameGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            VsFriendController controller = loader.getController();
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
            loader.setLocation(MainController.class.getResource("/Resources/Views/ProxyGui.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            ProxyController controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

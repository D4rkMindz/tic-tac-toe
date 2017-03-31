package Start;

import Controller.*;
import Service.LoginValidationService;
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
        initOnlinePlayerList();


    }

    public void initLoginGui() {
        try {
            FXMLLoader loader = new FXMLLoader(MainController.class.getResource("/View/LoginGui.fxml"));
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
            loader.setLocation(MainController.class.getResource("/View/RegisterGui.fxml"));
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
            loader.setLocation(MainController.class.getResource("/View/StartGui.fxml"));
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
            loader.setLocation(MainController.class.getResource("/View/PcGameGui.fxml"));
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

    public void initProxyGui() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/View/ProxyGui.fxml"));
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


    public void initOnlinePlayerList() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/View/OnlinePlayers.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            OnlinePlayersController controller = loader.getController();
            controller.setStart(this);

            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

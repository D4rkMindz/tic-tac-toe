package start;

import config.Settings;
import form.*;
import service.LoginValidationService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Class MainController. (StartClass)
 * This class manages the loading process of every GUI.
 */
public class MainController extends Application {
    private Stage stage;
    private Scene scene;

    /**
     * Everything begins here (because of "@Override" there is no usage for "public static void main(String[] args)". This is like the index.php file for Webdevelopers.
     * @param primaryStage Stage
     * @throws Exception if there was any failure
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Tic Tac Toe");
        Settings.Init("prod");
        initLoginGui();
    }

    /**
     * Initialize Login Graphical User Interface
     */
    public void initLoginGui() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainController.class.getResource("/resource/view/LoginGui.fxml"));
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

    /**
     * Initialize Registration Graphical User Interface
     */
    public void initRegisterGui() {
        try {
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

    /**
     * Initialize Proxy management Graphical User Interface
     */
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

    /**
     * Initialize Start Graphical User Interface (Where you can choose between "Play against Computer"
     * and "Play against Friend"
     */
    public void initStartGui() {
        try {
            if (!LoginValidationService.loggedIn) {
                this.initLoginGui();
                return;
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

    /**
     * Initialize Game Graphical User Interface to play against the Computer
     */
    public void initPcGameGui() {
        try {
            if (!LoginValidationService.loggedIn) {
                this.initLoginGui();
                return;
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

    /**
     * Initialize Game Graphical User Interface to play against a Friend
     */
    public void initFriendGameGui() {
        try {
            if (!LoginValidationService.loggedIn) {
                this.initLoginGui();
                return;
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
}

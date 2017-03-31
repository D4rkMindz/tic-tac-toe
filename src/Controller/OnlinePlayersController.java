package Controller;

import Service.OnlinePlayersList;
import Start.MainController;
import javafx.fxml.FXML;

import java.util.Map;

public class OnlinePlayersController {
    @FXML
    public MainController mainController;


    public void setStart(MainController mainController) {

        this.mainController = mainController;
        this.getPlayers();
    }

    public void getPlayers(){
        OnlinePlayersList onlinePlayersList = new OnlinePlayersList();
        onlinePlayersList.getOnlinePlayers();
        Map data = onlinePlayersList.data;
        System.out.print("bye");
    }

    public void goBack(){
        mainController.initStartGui();
    }
}

package Controller;

import Service.OnlinePlayersList;
import Start.MainController;
import javafx.fxml.FXML;

public class OnlinePlayersController {
    @FXML
    public MainController mainController;


    public void setStart(MainController mainController) {

        this.mainController = mainController;
        String[] res = this.getPlayers();
    }

    public String[] getPlayers(){
        OnlinePlayersList onlinePlayersList = new OnlinePlayersList();
        String[] result = onlinePlayersList.getOnlinePlayers();
        return result;
    }

    public void goBack(){
        mainController.initStartGui();
    }
}

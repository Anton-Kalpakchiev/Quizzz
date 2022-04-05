package client.scenes;

import com.google.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LobbyCtrl implements Initializable {

    private final MainCtrl mainCtrl;

    private ObservableList<String>  playerlist;


    @FXML
    private Button startButton;
    @FXML
    private Button leaveButton;
    @FXML
    private TableView<String> table;
    @FXML
    private TableColumn<String, String> col1;

    @Inject
    public LobbyCtrl(MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col1.setCellValueFactory(q -> new SimpleStringProperty(q.getValue()));
        //col2.setCellValueFactory(q -> new SimpleStringProperty(q.getValue()));
    }

    public void refresh() throws IOException, InterruptedException {
        System.out.println("REFRESHING LOBBY");
        var players = mainCtrl.getPlayers();
        System.out.println("Refresh: " + players.size());
        playerlist = FXCollections.observableList(players);
        table.setItems(playerlist);
    }

    public void leave() {
        System.out.println("Leaving");
    }


    @FXML
    /**
     * Gets called when the Start Game button in the lobby is pressed. Initiates a game with the players from the lobby.
     * Creates a new lobby to be available for the next game. Shows the first question of the initiated game.
     */
    public void startGame() throws IOException, InterruptedException {
        mainCtrl.initiateGame();
        mainCtrl.showQuestion();
        //System.out.println(lobbyCommunication.getPlayers().size());
    }
}

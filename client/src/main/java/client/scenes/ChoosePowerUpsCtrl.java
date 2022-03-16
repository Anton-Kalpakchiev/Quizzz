package client.scenes;

import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.PowerUp;
import jakarta.ws.rs.WebApplicationException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import commons.HalfTime;
import commons.EliminateWrongAnswer;
import commons.DoublePoints;
import javafx.stage.Modality;

import java.io.IOException;

public class ChoosePowerUpsCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;

    @FXML
    public Button DoublePointsButton;

    @FXML
    public Button EliminateWrongAnswerButton;

    @FXML
    public Button HalfTimeButton;

    @Inject
    public ChoosePowerUpsCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;

    }

    @FXML
    void DoublePointsButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        try {
            server.addPowerUp(getDoublePoints());
        } catch (WebApplicationException e) {

            var alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }

    @FXML
    void EliminateWrongAnswerButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        try {
            server.addPowerUp(getEliminateWrongAnswer());
        } catch (WebApplicationException e) {

            var alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }

    @FXML
    void HalfTimeButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        try {
            server.addPowerUp(getHalftime());
        } catch (WebApplicationException e) {

            var alert = new Alert(Alert.AlertType.ERROR);
            alert.initModality(Modality.APPLICATION_MODAL);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }
    }
    public PowerUp getDoublePoints(){
        return new DoublePoints("player","0:20");

    }
    public PowerUp getHalftime(){
        return new HalfTime("player","0:20");

    }
    public PowerUp getEliminateWrongAnswer(){
        return new EliminateWrongAnswer("player","0:20");

    }
}


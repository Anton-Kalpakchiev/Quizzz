package client.scenes;

import client.Communication.PowerUpsCommunication;
import client.utils.ServerUtils;
import com.google.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import commons.HalfTime;
import commons.EliminateWrongAnswer;
import commons.DoublePoints;
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
        PowerUpsCommunication.sendPowerUps(new DoublePoints().getStatus());
    }

    @FXML
    void EliminateWrongAnswerButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        PowerUpsCommunication.sendPowerUps(new EliminateWrongAnswer().getStatus());
    }

    @FXML
    void HalfTimeButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        PowerUpsCommunication.sendPowerUps(new HalfTime().getStatus());
    }
}


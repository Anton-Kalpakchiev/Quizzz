package client.scenes;

import client.Communication.AnswerCommunication;
import client.utils.ServerUtils;
import com.google.inject.Inject;
import commons.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChooseAnswerCtrl {

    private final ServerUtils server;
    private final MainCtrl mainCtrl;
    private Question question;
    private long sessionid;
    private long questionid;

    @FXML
    public Button Button1;

    @FXML
    public Button Button2;

    @FXML
    public Button Button3;

    @FXML
    public Button NextQuestionButton;

    @FXML
    public TextField QuestionText;

    @Inject
    public ChooseAnswerCtrl(ServerUtils server, MainCtrl mainCtrl) {
        this.mainCtrl = mainCtrl;
        this.server = server;
    }

    public void initialize() throws IOException, InterruptedException {
        AnswerCommunication.getQuestion(sessionid);
        sessionid = 1;
    }

    @FXML
    void Button1Pressed(ActionEvent event) throws IOException, InterruptedException {
        AnswerCommunication.sendAnswer(Button1.getText(), questionid);
    }

    @FXML
    void Button2Pressed(ActionEvent event) throws IOException, InterruptedException {
        AnswerCommunication.sendAnswer(Button2.getText(), questionid);
    }

    @FXML
    void Button3Pressed(ActionEvent event) throws IOException, InterruptedException {
        AnswerCommunication.sendAnswer(Button3.getText(), questionid);
    }

    @FXML
    void GetQuestionButton(ActionEvent event) throws IOException, InterruptedException {
        Question q = AnswerCommunication.getQuestion(sessionid);
        questionid = q.id;
        QuestionText.setText(q.question);
        Button1.setText(q.answer);
        Button2.setText(q.wrongAnswer1);
        Button3.setText(q.wrongAnswer2);
    }


}


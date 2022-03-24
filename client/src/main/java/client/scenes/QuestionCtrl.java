package client.scenes;

import client.Communication.AnswerCommunication;
import client.Communication.ImageCommunication;
import client.Communication.PowerUpsCommunication;
import commons.GameState;
import commons.Question;
import commons.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class QuestionCtrl {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button answer1;

    @FXML
    private Button answer2;

    @FXML
    private Button answer3;

    @FXML
    private Button doublePoints;

    @FXML
    private Button eliminateWrongAnswer;

    @FXML
    private Button halfTime;


    @FXML
    private ImageView questionImage;

    @FXML
    private Label questionText;

    @FXML
    private Label questionTitle;

    @FXML
    private Label questionTime;

    private Timer timer;

    private GameState gameState;

    private String selectedAnswer;

    private long timeSinceQuestion;//if we want to reduce time left by half with a joker, timeSinceQuestion-=(timeLeft/2)

    private long currentTime;

    private double timeLeft;



    void updateGameState(GameState gameState) {
        this.gameState = gameState;

        timeSinceQuestion = new Date().getTime();
        currentTime = timeSinceQuestion;
//        new Thread(() -> {
//            while(true) {
//                currentTime = new Date().getTime();
//                timeLeft = 10 - (currentTime - timeSinceQuestion);
//                questionTime.setText(timeLeft + "");
//            }
//        }).start();

        //TODO: Update question number based on current question
        this.questionTitle.setText("Question 10");
        this.questionText.setText(gameState.question.question);

        this.answer1.setText(gameState.question.answer);
        this.answer2.setText(gameState.question.wrongAnswer1);
        this.answer3.setText(gameState.question.wrongAnswer2);


    }

    //TODO: Send correct Game ID
    @FXML
    void Answer1Pressed(ActionEvent event) throws IOException, InterruptedException {
        selectedAnswer = answer1.getText();
    }
    //TODO: Send correct Game ID
    @FXML
    void Answer2Pressed(ActionEvent event) throws IOException, InterruptedException {
        selectedAnswer = answer2.getText();
    }

    //TODO: Send correct Game ID
    @FXML
    void Answer3Pressed(ActionEvent event) throws IOException, InterruptedException {
        selectedAnswer = answer3.getText();
    }

    @FXML
    public void SubmitPressed(ActionEvent actionEvent) throws IOException, InterruptedException {
        AnswerCommunication.sendAnswer(selectedAnswer, gameState);
    }


    @FXML
    void DoublePointsButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        PowerUpsCommunication.sendPowerUps(doublePoints.getText() + " WAS USED!");
    }

    @FXML
    void EliminateWrongAnswerButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        PowerUpsCommunication.sendPowerUps(eliminateWrongAnswer.getText()+ " WAS USED!");
    }

    @FXML
    void HalfTimeButtonPressed(ActionEvent event) throws IOException, InterruptedException {
        PowerUpsCommunication.sendPowerUps(halfTime.getText() +" WAS USED!");
    }

    @FXML
    void initialize() {
        assert questionImage != null : "fx:id=\"questionImage\" was not injected: check your FXML file 'Question.fxml'.";
        assert questionText != null : "fx:id=\"questionText\" was not injected: check your FXML file 'Question.fxml'.";
        assert questionTitle != null : "fx:id=\"questionTitle\" was not injected: check your FXML file 'Question.fxml'.";

        timer = new Timer(0,5);
//        Timeline timeline= new Timeline( new KeyFrame(javafx.util.Duration.millis(1), e ->{
//            questionTime.setText(timer.toTimerDisplayString());
//        }));
        Timeline timeline2 = new Timeline( new KeyFrame(javafx.util.Duration.millis(1), e ->{

            currentTime = new Date().getTime();
            timeLeft = (10000 - (currentTime - timeSinceQuestion))/1000.0;
            questionTime.setText(timeLeft + "");

//            questionTime.setText(timer.toTimerDisplayString());
        }));
        timeline2.setCycleCount(20000);//change to 10 000 in the future
        timeline2.play();
    }

    public void syncTimer(long syncLong, long duration) {
        timer.setDuration(duration);
        timer.synchronize(syncLong);
    }

    public void setQuestion(Question q) {
        questionText.setText(q.question);
        if (q.questionImage != null) {
            try {
                questionImage.setImage(ImageCommunication.getImage("https://localhost:8080/" + q.questionImage));
            }
            catch (IOException e) {
                System.out.println("Failed to set the question image.");
            }
        }
        List<String> answerList = new LinkedList<>(List.of(q.answer, q.wrongAnswer1, q.wrongAnswer2));

        Collections.shuffle(answerList);

        answer1.setText(answerList.get(0));
        answer2.setText(answerList.get(1));
        answer3.setText(answerList.get(2));
    }

    public void markAnswer(String correct, String ofplayer) {
        for (Button answer : List.of(answer1, answer2, answer3)) {
            if (answer.getText().equals(ofplayer)) {
                answer.getStyleClass().add("wrong");
            }
            if (answer.getText().equals(correct)) {
                answer.getStyleClass().add("right");
            }
        }
    }

    public void clearAnswer() {
        for (Button answer : List.of(answer1, answer2, answer3)) {
            answer.getStyleClass().removeAll("wrong", "right");
        }
    }
}
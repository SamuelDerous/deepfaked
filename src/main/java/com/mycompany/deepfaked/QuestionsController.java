/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.database.dao.ChoiceDao;
import com.mycompany.deepfaked.database.dao.QuestionDao;
import com.mycompany.deepfaked.database.model.Question;
import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class QuestionsController implements Initializable {
    
    private final MainScreenController mainScreenController;
    
    private static double money = 0;
    private static int followers = 0;
    private double amountofmoney;
    private int amountoffollowers;
    
    private Stage questionsStage;
    
    private List<Question> questions;
    private List<QuestionChoice> choices;
    private Question question;
    
    private List<ToggleButton> buttons;
    
    @FXML
    private Parent root;
    
    @FXML
    private Button btnReady;
    

    @FXML
    private Pane pnQuestionsField;
    
    @FXML
    private Label lblQuestion;
    
    @FXML
    private ScrollPane pnInformation;
    
    @FXML
    private Label lblMore;
    
    @FXML
    private ImageView btnContinue;
    
    @FXML
    private ImageView btnNextQuestion;
    
    public QuestionsController(MainScreenController controller) {
        this.mainScreenController = controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("questions.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load(), 885, 740); 
        questionsStage = new Stage();
        questionsStage.setTitle("Questions");
        questionsStage.setScene(scene);
        questionsStage.initModality(Modality.WINDOW_MODAL);
        questionsStage.initOwner(
            ((Node)mainScreenController.getParent()).getScene().getWindow());
        questionsStage.show();
        questionsStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                endGame(e);
            }
        });
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
    
    @FXML
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnReady.setVisible(false);
        btnNextQuestion.setVisible(false);
         String intro = "We hebben enkele vragen opgesteld om te kijken of je beslissing om de video als echt of vals aan te vinken gegrond zijn. Elke goede vraag levert je geld op. Als je er klaar voor bent druk dan op c of klik op de knop.";
         lblQuestion.setText(intro);
         
         
    }

    @FXML
    protected void continuePressed() {
        questions = MainScreenController.getQuestions();
        btnContinue.setVisible(false);
        Question testQuestion = getQuestion();
        lblQuestion.setText(testQuestion.getQuestion());
        createButtons();
        
    }
    
    /*@FXML
    protected void continuePressed() {
        questions = QuestionDao.getQuestionsForDeepfake();
        Question testQuestion = getQuestion();
        lblQuestion.setText(testQuestion.getQuestionId() + " " + testQuestion.getQuestion());
        createButtons();
    }*/
    
    private Question getQuestion() {
        question = null;
            if(!questions.isEmpty()) {
                int min = 0;
                int max = questions.size();
                int questionNumber = (int) (Math.random() * (max - min) + min);
                question = questions.get(0); //questionNumber);
                questions.remove(0); //questionNumber);
                //System.out.println(question.getQuestion());
                choices = ChoiceDao.getChoicesForQuestion(question);
                /*for(int i = 0; i < choices.size(); i++) {
                    System.out.println(choices.get(i).getChoice().getAnswer());
                }*/
            }
            /*if(question.getChoices() != null) {
                System.out.println(question.getChoices());
            }*/
            return question;
    }
    
    private void createButtons() {
        Collections.shuffle(choices);
        if(buttons != null && !buttons.isEmpty()) {
            pnQuestionsField.getChildren().removeAll(buttons);
        }
        buttons = new ArrayList<>();
        if(choices != null && !choices.isEmpty()) {
            int beginY = 275;
            int layoutY = 325 / choices.size();
            ToggleGroup group = new ToggleGroup();
            int i = 0;
                //buttons = new ArrayList<>();
                for(QuestionChoice choice : choices) {
                    ToggleButton button = new ToggleButton();
                    button.setWrapText(true);
                    if(question.getMulti() == 0) {
                        button.setToggleGroup(group);
                    }
                    button.setText(choice.getChoice().getAnswer());
                    button.setPrefSize(654, 52);
                    button.setLayoutX(100);
                    button.setLayoutY(beginY + layoutY * i);
                    buttons.add(button);
                    i++;
                }
                
                pnQuestionsField.getChildren().addAll(buttons);
                btnReady.setVisible(true);
        } else {
            nextQuestion();
        }
    }
    
    @FXML
    protected void checkAnswers() {
        if(buttons != null && !buttons.isEmpty()) {
            if(question.getMulti() == 1) {
                checkMultipleAnswers();
            } else {
                checkSingleAnswer();
            }
        }
        
        btnNextQuestion.setVisible(true);
        btnReady.setVisible(false);
        pnInformation.setVisible(true);
        mainScreenController.setPrDeepfakeProgress();
    }
    
    private void checkSingleAnswer() {
        Text information = new Text();
        int i = 0;
        for(ToggleButton button : buttons) {
                if(button.isSelected()) {
                    if(choices.get(i).getCorrect() == 1) {
                        money += question.getValue().getMoney();
                        followers += question.getValue().getFollowers();
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("i = " + i + " Correct. Followers: " + question.getValue().getFollowers() + " Money: " + question.getValue().getMoney());
                        a.show();
                    }
                    information.setText(information.getText() + choices.get(i).getChoice().getConsequence().getFeedback());
                    information.wrappingWidthProperty().bind(pnInformation.widthProperty());
                    pnInformation.setContent(information);
                }
                button.setDisable(true);
                i++;
                }
    }
    
    private void checkMultipleAnswers() {
        Text information = new Text();
        double m = 0.0;
        int f = 0;
        int i = 0;
        boolean same = false;
        List<Integer> corrects = new ArrayList<>();
        for(ToggleButton button : buttons) {
                if(button.isSelected()) {
                    if(choices.get(i).getCorrect() == 1) {
                        m += question.getValue().getMoney();
                        f += question.getValue().getFollowers();
                        corrects.add(i);
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText("i = " + i + " Correct. Followers: " + question.getValue().getFollowers() + " Money: " + question.getValue().getMoney());
                        a.show();
                    } else {
                        m = 0.0;
                        f = 0;
                    } 
                    for(int correct : corrects) {
                        if(correct != i) {
                            if(choices.get(correct).getChoice().getConsequence().getFeedback().equals(
                                choices.get(i).getChoice().getConsequence().getFeedback())) {
                                same = true;
                            }
                        }
                    }
                    if(!same) {
                        information.setText(information.getText() + choices.get(i).getChoice().getConsequence().getFeedback() + "\n");
                    }
                    information.wrappingWidthProperty().bind(pnInformation.widthProperty());
                    pnInformation.setContent(information);
                }
                money += m;
                followers += f;
                button.setDisable(true);
                i++;
                }
    }
    
    @FXML
    protected void nextQuestion() {
        Question testQuestion = getQuestion();
        if(testQuestion != null) {
            lblQuestion.setText(testQuestion.getId() + " " + testQuestion.getQuestion());
            createButtons();
            pnInformation.setVisible(false);
            btnNextQuestion.setVisible(false);
        } else {
            amountOfFollowers();
        }
    }
    
    
    private void amountOfFollowers() {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        
        VBox vbox = new VBox();
        Text intro = new Text("Met het beantwoorden van deze vragen hebt u volgende scores behaald:");
        Text txtMoney = new Text("Verworven inkomsten: € 0.00");
        Text txtFollowers = new Text("Nieuwe volgers: 0");
        amountofmoney = 0.00;
        amountoffollowers = 0;
        Timeline timelineMoney = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
             if(amountofmoney < money) {
                 txtMoney.setText("Verworven inkomsten: € " + amountofmoney + "0");
                 amountofmoney++;
             }
        }));
         
         Timeline timeLineFollowers = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
            if(amountoffollowers < followers) {
                txtFollowers.setText("Nieuwe volgers: " + amountoffollowers);
                amountoffollowers++;
            }
        }));
        timelineMoney.setCycleCount(Animation.INDEFINITE);
        timeLineFollowers.setCycleCount(Animation.INDEFINITE);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(intro);
        vbox.getChildren().add(txtMoney);
        vbox.getChildren().add(txtFollowers);
        Button btnOK = new Button("OK");
        btnOK.setOnAction((event) -> {
            dialogStage.close();
            
        });
        vbox.getChildren().add(btnOK);
        vbox.setPadding(new Insets(15));
        
        dialogStage.setScene(new Scene(vbox));
        dialogStage.setOnHiding(event -> {
            closeDialog(dialogStage);
        });
        dialogStage.show();
        timelineMoney.play();
        timeLineFollowers.play();
    }
    
    private void closeDialog(Stage stage) {
        stage.close();
        questionsStage.close();
        mainScreenController.createPersonalTiktok();
        System.out.println("test");
        //MainScreenController.getQuestionsStage().
        
    }
    
    private void endGame(WindowEvent event) {
        Alert alert = new Alert(AlertType.WARNING, "Weet je zeker dat je het spel wilt afsluiten?\n" +
                "Al je vooruitgang bij deze deepfake zal hierbij verloren gaan.", ButtonType.YES, ButtonType.NO);
        alert.setTitle("De applicatie beëindigen");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.YES) {
            System.exit(0);
        }
        if(result.get() == ButtonType.NO) {
            event.consume();
            alert.hide();
        }
    }

    public static double getMoney() {
        return money;
    }

    public static int getFollowers() {
        return followers;
    }
    
    
    
}

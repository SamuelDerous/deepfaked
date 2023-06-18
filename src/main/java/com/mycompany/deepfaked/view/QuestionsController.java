/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked.view;

import com.mycompany.deepfaked.Win;
import com.mycompany.deepfaked.CorrectContext;
import com.mycompany.deepfaked.main.App;
import com.mycompany.deepfaked.Loss;
import com.mycompany.deepfaked.database.model.Question;
import com.mycompany.deepfaked.database.model.QuestionChoice;
import com.mycompany.deepfaked.strategies.Answer;
import com.mycompany.deepfaked.strategies.AnswerContext;
import com.mycompany.deepfaked.strategies.MultipleAnswers;
import com.mycompany.deepfaked.strategies.SingleAnswer;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import com.mycompany.deepfaked.database.model.QuestionCollection;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class QuestionsController implements Initializable {
    
    private final MainScreenController mainScreenController;
    
    private static final ImageView VIEWMONEY = new ImageView(new Image(QuestionsController.class.getResource("/assets/icons/money.png").toString()));
    private static final ImageView VIEWFOLLOWERS = new ImageView(new Image(QuestionsController.class.getResource("/assets/icons/followers.png").toString()));
    private static double money = 0;
    private static int followers = 0;
    private double amountofmoney;
    private int amountoffollowers;
    
    private Stage questionsStage;
    
    private QuestionCollection<Question> questions;
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
    
    @FXML
    private Pane pnQuestion;
    
    @FXML
    private Pane overallPane;
    
    @FXML
    private Pane pnDifficulty;
    
    @FXML
    private Label lblDifficulty;
    
    public QuestionsController(MainScreenController controller) {
        this.mainScreenController = controller;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/questions.fxml"));
            fxmlLoader.setController(this);
            Scene scene = new Scene(fxmlLoader.load(), 885, 740); 
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    keyPressed(event);
                }
            });
        scene.getStylesheets().add(getClass().getResource("/assets/border.css").toExternalForm());
        questionsStage = new Stage();
        questionsStage.setResizable(false);
        questionsStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
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
        BackgroundSize backgroundSize = new BackgroundSize(overallPane.getWidth(), overallPane.getHeight(), false, false, true, true);
        BackgroundImage bossImage = new BackgroundImage(new Image(getClass().getResource("/assets/textures/me.jpg").toString()), BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            //bossImage.setFitHeight(scene.getHeight());
            //bossImage.setFitWidth(scene.getWidth());
            overallPane.setBackground(new Background(bossImage));
           
        btnReady.setVisible(false);
        btnNextQuestion.setVisible(false);
         String intro = "We hebben enkele vragen opgesteld om te kijken of je beslissing om de video als echt of vals aan te vinken gegrond zijn. Elke goede vraag levert je munten op. Als je er klaar voor bent druk dan op c of klik op de knop.";
         lblQuestion.setText(intro);
         //pnQuestion.getChildren().add(lblQuestion);
         VIEWFOLLOWERS.setFitHeight(25);
         VIEWFOLLOWERS.setFitWidth(35);
         VIEWMONEY.setFitHeight(25);
         VIEWMONEY.setFitWidth(35);
         overallPane.requestFocus();
         
    }

    @FXML
    protected void continuePressed() {
        questions = MainScreenController.getQuestions();
        btnContinue.setVisible(false);
        question = (Question) questions.get();
        choices = questions.getChoices();
        lblQuestion.setText(setTextQuestionsLabel(question));
        lblQuestion.setTooltip(new Tooltip("Het leerdoel van deze vraag is: " + question.getLearningObjective().getLabel()));
        createLevelPane(question.getLevel());
        pnDifficulty.setVisible(true);
        createButtons();
        
    }
    
    public String setTextQuestionsLabel(Question question) {
        String apply = "";
        if(question.getMulti() == 1) {
            apply = " (kies alle van toepassing)";
        }
        return question.getQuestion() + apply;
        
            
    }
    
    /*public void fillPane(Question question) {
        //LevelNotificationPane levelPane = new LevelNotificationPane(question.getLevel());
        lblQuestion.setText(setTextQuestionsLabel(question));
        //levelPane.createPane(20, 20);
        pnQuestion.getChildren().clear();
        pnQuestion.getChildren().add(lblQuestion);
        //pnQuestion.getChildren().add(levelPane.getPane());
    }*/
    
    /*@FXML
    protected void continuePressed() {
        questions = QuestionDao.getQuestionsForDeepfake();
        Question testQuestion = getQuestion();
        lblQuestion.setText(testQuestion.getQuestionId() + " " + testQuestion.getQuestion());
        createButtons();
    }*/
    
    private void createButtons() {
        Collections.shuffle(choices);
        if(buttons != null && !buttons.isEmpty()) {
            pnQuestionsField.getChildren().removeAll(buttons);
        }
        buttons = new ArrayList<>();
        if(choices != null && !choices.isEmpty()) {
            int beginY = 5;
            int layoutY = 300 / choices.size();
            ToggleGroup group = new ToggleGroup();
            int i = 0;
                //buttons = new ArrayList<>();
                for(QuestionChoice choice : choices) {
                    ToggleButton button = new ToggleButton();
                    button.setOnAction(e -> {
                        if(button.isSelected()) {
                            button.getStyleClass().add("questionsButtonPressed");
                        } else {
                            System.out.println("Not pressed anymore");
                            button.getStyleClass().remove("questionsButtonPressed");
                        }
                    });
                    button.selectedProperty().addListener(((observable, oldValue, newValue) -> {
                        if(newValue == false) {
                            button.getStyleClass().remove("questionsButtonPressed");
                        }
                    }));
                     
                    
                    button.setWrapText(true);
                    if(question.getMulti() == 0) {
                        button.setToggleGroup(group);
                    }
                    button.setText(choice.getChoice().getAnswer());
                    button.setPrefSize(654, 52);
                    button.setLayoutX(10);
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
                checkAnswer(new MultipleAnswers());
            } else {
                checkAnswer(new SingleAnswer());
            }
        btnNextQuestion.setVisible(true);
        btnReady.setVisible(false);
        pnInformation.setVisible(true);
        mainScreenController.setPrDeepfakeProgress();
        } else {
            Alert alert = new Alert(AlertType.WARNING, "Je hebt geen antwoord gegeven op de vraag. Er is altijd ten minste één optie correct");
        alert.setTitle("De applicatie beëindigen");
        }
    }
    
    //public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers) {
    
    private void checkAnswer(Answer answer) {
        AnswerContext context = new AnswerContext();
        context.setState(answer);
        var correct = context.checkAnswer(buttons, choices, question.getValue().getMoney(), question.getValue().getFollowers());
        CorrectContext correctCtx;
        if(correct) {
            correctCtx = new CorrectContext(new Win(), overallPane);
            
        } else {
            correctCtx = new CorrectContext(new Loss(), overallPane);
        } 
        correctCtx.animate();
        money += context.getState().getMoney();
        followers += context.getState().getFollowers();
        System.out.println(context.getState().getMoney());
        Text information = new Text(context.getState().getInformation());
        information.wrappingWidthProperty().bind(pnInformation.widthProperty());
        pnInformation.setContent(information);
    }
    
    @FXML
    protected void nextQuestion() {
        question = (Question) questions.get();
        choices = questions.getChoices();
        if(question != null) {
            lblQuestion.setText(setTextQuestionsLabel(question));
            lblQuestion.setTooltip(new Tooltip("Het leerdoel van deze vraag is: " + question.getLearningObjective().getLabel()));
            createLevelPane(question.getLevel());
            
            createButtons();
            pnInformation.setVisible(false);
            btnNextQuestion.setVisible(false);
        } else {
            amountOfFollowers();
        }
    }
    
    
    private void amountOfFollowers() {
        Stage dialogStage = new Stage();
        dialogStage.setResizable(false);
        dialogStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
        dialogStage.setTitle("Resultaten");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        
        VBox vbox = new VBox();
        Text intro = new Text("Met het beantwoorden van deze vragen hebt u volgende scores behaald:");
        HBox hboxMoney = new HBox();
        Text txtMoney = new Text("€ 0.00");
        txtMoney.setFont(new Font(16));
        hboxMoney.getChildren().add(VIEWMONEY);
        hboxMoney.getChildren().add(txtMoney);
        hboxMoney.setAlignment(Pos.CENTER);
        hboxMoney.setPadding(new Insets(5, 5, 5, 5));
        HBox hboxFollowers = new HBox();
        Text txtFollowers = new Text("0");
        txtFollowers.setFont(new Font(16));
        hboxFollowers.setAlignment(Pos.CENTER);
        hboxFollowers.setPadding(new Insets(5, 15, 5, 15));
        hboxFollowers.getChildren().add(VIEWFOLLOWERS);
        hboxFollowers.getChildren().add(txtFollowers);
        
        
        amountofmoney = 0.00;
        amountoffollowers = 0;
        Timeline timelineMoney = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
             if(amountofmoney < money) {
                 txtMoney.setText("€ " + amountofmoney + "0");
                 amountofmoney++;
             }
        }));
         
         Timeline timeLineFollowers = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
            if(amountoffollowers < followers) {
                txtFollowers.setText("" + amountoffollowers);
                amountoffollowers++;
            }
        }));
        timelineMoney.setCycleCount(Animation.INDEFINITE);
        timeLineFollowers.setCycleCount(Animation.INDEFINITE);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(intro);
        vbox.getChildren().add(hboxMoney);
        vbox.getChildren().add(hboxFollowers);
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
        //MainScreenController.getQuestionsStage().
        
    }
    
    private void endGame(WindowEvent event) {
        Alert alert = new Alert(AlertType.WARNING, """
                                                   Weet je zeker dat je het spel wilt afsluiten?
                                                   Al je vooruitgang bij deze deepfake zal hierbij verloren gaan.""", ButtonType.YES, ButtonType.NO);
        alert.setTitle("De applicatie beëindigen");
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
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
    
    
    private void createLevelPane(int level) {
        switch(level) {
            case 1: pnDifficulty.setBackground(new Background(new BackgroundFill(Color.GREEN, new CornerRadii(0), new Insets(0))));
            lblDifficulty.setText("A");
            break;
            case 2: pnDifficulty.setBackground(new Background(new BackgroundFill(Color.ORANGE, new CornerRadii(0), new Insets(0))));
            lblDifficulty.setText("B");
            break;
            case 3: pnDifficulty.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0))));
            lblDifficulty.setText("C");
            break;
        }
    } 
    
    @FXML
    protected void keyPressed(KeyEvent event) {
        if(event.getCode() == KeyCode.C) {
            if(btnNextQuestion.isVisible()) {
                nextQuestion();
            } else if (btnContinue.isVisible()) {
                continuePressed();
            }
        }
        
    }
}

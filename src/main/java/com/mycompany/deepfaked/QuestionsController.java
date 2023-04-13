/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.database.dao.ChoiceDao;
import com.mycompany.deepfaked.database.model.Question;
import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class QuestionsController implements Initializable {
    
    private List<Question> questions;
    private List<QuestionChoice> choices;
    private Question question;
    

    @FXML
    private Pane pnQuestionsField;
    
    @FXML
    private Label lblQuestion;
    
    @FXML
    private ImageView btnContinue;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         String intro = "We hebben enkele vragen opgesteld om te kijken of je beslissing om de video als echt of vals aan te vinken gegrond zijn. Elke goede vraag levert je geld op. Als je er klaar voor bent druk dan op c of klik op de knop.";
         lblQuestion.setText(intro);
         
         
    }

    @FXML
    protected void continuePressed() {
        questions = MainScreenController.getQuestions();
        btnContinue.setVisible(false);
        Question testQuestion = getQuestion();
        
    }
    
    private Question getQuestion() {
        Question question = null;
            if(!questions.isEmpty()) {
                int min = 0;
                int max = questions.size();
                int questionNumber = (int) (Math.random() * (max - min) + min);
                question = questions.get(questionNumber);
                questions.remove(questionNumber);
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
    
    private void createToggleButtons() {
        if(choices != null && !choices.isEmpty()) {
        if(question.getMulti() == 1) {
            List<ToggleButton> buttons = new ArrayList<>();
            for(QuestionChoice choice : choices) {
                ToggleButton button = new ToggleButton();
                button.setText(choice.getChoice().getAnswer());
        }
        }
        }
    }
    
    
}

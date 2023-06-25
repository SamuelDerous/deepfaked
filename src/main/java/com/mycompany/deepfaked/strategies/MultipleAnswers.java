/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author ZENODotus
 */
public class MultipleAnswers extends Answer {

    @Override
    public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers) {
        int i = 0;
        boolean incorrect = false;
        Set<String> corrects = new TreeSet<>();
        int correctAnswers = 0;
        int defaultCorrectAnswers = 0;
        for (QuestionChoice choice : choices) {
            if (choice.getCorrect() == 1) {
                defaultCorrectAnswers++;
            }
        }
        for (ToggleButton button : buttons) {
            if (button.isSelected()) {
                corrects.add(choices.get(i).getChoice().getConsequence().getFeedback());
                this.addHyperlink(choices.get(i).getChoice().getConsequence().getReadMore());
                if (choices.get(i).getCorrect() == 1) {
                    this.setMoney(money);
                    this.setFollowers(followers);
                    correctAnswers++;
                } else {
                    money = 0.0;
                    followers = 0;
                    button.getStyleClass().add("questionsButtonWrong");
                    incorrect = true;
                }
            } else {
                if (choices.get(i).getCorrect() == 1) {
                    button.getStyleClass().add("questionsButtonNotPressed");
                    corrects.add(choices.get(i).getChoice().getConsequence().getFeedback());
                    this.addHyperlink(choices.get(i).getChoice().getConsequence().getReadMore());
                }
            }
            button.setDisable(true);
            i++;
        }
        Iterator<String> informationIterator = corrects.iterator();
        while(informationIterator.hasNext()) {
            this.setInformation(this.getInformation() + informationIterator.next() + "\n");
        }
        if (correctAnswers != defaultCorrectAnswers) {
            System.out.println("Wrong");
            incorrect = true;
            this.setMoney(0.0);
            this.setFollowers(0);
        }
        return (!incorrect);

    }

}

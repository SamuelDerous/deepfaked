/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author ZENODotus
 */
public class SingleAnswer extends Answer {

    @Override
    public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers) {
        boolean incorrect = true;
        Set<String> information = new TreeSet<>();
        int i = 0;
        for (ToggleButton button : buttons) {
            //information = choices.get(i).getChoice().getConsequence().getFeedback();
            if (button.isSelected()) {
                if (choices.get(i).getCorrect() == 1) {
                    this.setMoney(this.getMoney() + money);
                    this.setFollowers(this.getFollowers() + followers);
                    incorrect = false;

                } else {
                    button.getStyleClass().add("questionsButtonWrong");
                }
                information.add(choices.get(i).getChoice().getConsequence().getFeedback());
                this.addHyperlink(choices.get(i).getChoice().getConsequence().getReadMore());
                //this.setInformation(this.getInformation() + information + "\n");
                
            } else {
                if(choices.get(i).getCorrect() == 1) {
                    button.getStyleClass().add("questionsButtonNotPressed");
                    information.add(choices.get(i).getChoice().getConsequence().getFeedback());
                    this.addHyperlink(choices.get(i).getChoice().getConsequence().getReadMore());
                }
            }
            button.setDisable(true);
            i++;
        }
        Iterator<String> informationIterator = information.iterator();
        while(informationIterator.hasNext()) {
            this.setInformation(this.getInformation() + informationIterator.next() + "\n");
        }
        
        return (!incorrect);
    }

}

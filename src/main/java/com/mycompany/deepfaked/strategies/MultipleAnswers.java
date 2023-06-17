/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import com.mycompany.deepfaked.Win;
import com.mycompany.deepfaked.Loss;
import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

/**
 *
 * @author ZENODotus
 */
public class MultipleAnswers extends Answer {

    @Override
    public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers) {
        int i = 0;
        boolean same = false;
        boolean incorrect = false;
        List<Integer> corrects = new ArrayList<>();
        for (ToggleButton button : buttons) {

            if (button.isSelected()) {
                if (choices.get(i).getCorrect() == 1) {
                    this.setMoney(money);
                    this.setFollowers(followers);
                    corrects.add(i);

                } else {
                    money = 0.0;
                    followers = 0;
                    incorrect = true;
                }
                for (int correct : corrects) {
                    if (correct != i) {
                        if (choices.get(correct).getChoice().getConsequence().getFeedback().equals(
                                choices.get(i).getChoice().getConsequence().getFeedback())) {
                            same = true;
                        }
                    }
                }
                if (!same) {
                    this.setInformation(this.getInformation() + choices.get(i).getChoice().getConsequence().getFeedback() + "\n");
                }

            }
            button.setDisable(true);
            i++;
        }
        return !incorrect;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import com.mycompany.deepfaked.AnimatedCoins;
import com.mycompany.deepfaked.Loss;
import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.text.Text;

/**
 *
 * @author ZENODotus
 */
public class SingleAnswer extends Answer {

    @Override
    public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers) {
        boolean correct = false;
        int i = 0;
        for (ToggleButton button : buttons) {
            if (button.isSelected()) {
                if (choices.get(i).getCorrect() == 1) {
                    this.setMoney(this.getMoney() + money);
                    this.setFollowers(this.getFollowers() + followers);
                    correct = true;

                }
                this.setInformation(this.getInformation() + choices.get(i).getChoice().getConsequence().getFeedback());

            }
            button.setDisable(true);
            i++;
        }
        return correct;
    }

}
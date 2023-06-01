/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.List;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author ZENODotus
 */
public class AnswerContext implements AnswerState {
    
    private Answer state;

    @Override
    public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers) {
        return state.checkAnswer(buttons, choices, money, followers);
    }

    public Answer getState() {
        return state;
    }

    public void setState(Answer state) {
        this.state = state;
    }
    
    
    
}

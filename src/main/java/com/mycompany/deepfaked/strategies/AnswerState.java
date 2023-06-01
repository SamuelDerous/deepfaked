/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import com.mycompany.deepfaked.database.model.QuestionChoice;
import java.util.List;
import javafx.scene.control.ToggleButton;

/**
 *
 * @author ZENODotus
 */
public interface AnswerState {
    
    public boolean checkAnswer(List<ToggleButton> buttons, List<QuestionChoice> choices, double money, int followers);
}

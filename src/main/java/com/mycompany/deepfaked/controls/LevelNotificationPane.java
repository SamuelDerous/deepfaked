/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import java.util.List;
import javafx.scene.control.Label;

/**
 *
 * @author ZENODotus
 */
public class LevelNotificationPane extends AnimationPane {
    
    private final int level;
    
    public LevelNotificationPane(int level) {
        this.level = level;
    }

    @Override
    public double calculateAmount() {
        return level;
    }

    @Override
    public List<Label> createLabels() {
        return null;
    }
    
    
}

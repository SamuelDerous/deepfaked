/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

/**
 * The pane for the deepfake detection; the overall pane
 * @author ZENODotus
 */
public abstract class AnimationPane {
    
    private javafx.scene.layout.Pane deepfakeDetectionPane;
    private ScrollPane deepfakeDetectionScrollPane;
    
    
    public void createPane(double layoutX, double layoutY) {
        deepfakeDetectionPane = new javafx.scene.layout.Pane();
        deepfakeDetectionScrollPane = new ScrollPane();
        deepfakeDetectionScrollPane.setLayoutX(layoutX);
        deepfakeDetectionScrollPane.setLayoutY(layoutY);
        deepfakeDetectionScrollPane.setPrefSize(300, 400);
        deepfakeDetectionScrollPane.toFront();
        
        deepfakeDetectionScrollPane.setContent(deepfakeDetectionPane);
        
        LevelAnimation scanningAnimation = new LevelAnimation();
        scanningAnimation.animation(deepfakeDetectionPane, calculateAmount(), createLabels());
        //createLabels();
        
        //System.out.println(factCheckWebsites.size());
        
    }
    
    public abstract double calculateAmount();
    
    public abstract List<Label> createLabels();
    
    
    public ScrollPane getPane() {
        return deepfakeDetectionScrollPane;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author ZENODotus
 */
public class LevelAnimation {
    
    private ImageView imgArrow;
    private double amount;
    private Pane pane;
    
    public void animation(Pane pane, double calculation, List<Label> labels) {
        this.pane = pane;
        Image image = new Image(getClass().getResource("/assets/icons/arrow.png").toString());
        imgArrow = new ImageView(image);
        imgArrow.setFitWidth(60);
        imgArrow.setPreserveRatio(true);
        double aspectRatio = image.getWidth() / image.getHeight();
        double realWidth = Math.min(imgArrow.getFitWidth(), imgArrow.getFitHeight() * aspectRatio);
        double realHeight = imgArrow.getFitWidth() / aspectRatio;
        imgArrow.setLayoutY(250.0 - (realHeight));
        imgArrow.setLayoutX(150.0 - (imgArrow.getFitWidth() / 2));
        imgArrow.setRotate(0);
        final double upper = calculation * 180 / 100;
        System.out.println("upper: " + upper);
        var i = new SimpleIntegerProperty(0);
        var j = new SimpleIntegerProperty(0);
        amount = 0;
        //hoeveelheid = 1.0;
        Timeline waitingTime = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                j.set(j.get() + 1);
            }
        }));
        Timeline testTime = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //hoeveelheid = hoeveelheid + ((hoeveelheid * 180) / 100);
                imgArrow.setRotate(amount++); 
                i.set(i.get() + 1);
                System.out.println(i.get());
            }
        }));
        waitingTime.setCycleCount(Timeline.INDEFINITE);
        waitingTime.play();
        j.addListener((ov, prevStatus, newStatus) -> {
            if(newStatus.intValue() >= 50) {
                waitingTime.stop();
                testTime.setCycleCount(Timeline.INDEFINITE);
                testTime.play();
                createLabels(labels);
            }
        });
        i.addListener((ov, prevStatus, newStatus) -> {
            if(newStatus.intValue() >= upper) {
                testTime.stop();
                //createLabels(labels);
            }
        });
        //deepfakeDetectionPane.getChildren().remove(lblScan);
        //deepfakeDetectionPane.getChildren().remove(progressScanVideo);
        SemiRing semiRing = new SemiRing(150, 250, 120, 100);
        pane.getChildren().add(semiRing.getSemiRing());
        pane.getChildren().add(imgArrow);
        
        
        /*System.out.println(imgArrow.getLayoutY());
        
        Group root = new Group();
        root.getChildren().add(drawSemiRing(200, 200, 200, 150, Color.LIGHTGREEN, Color.DARKGREEN));
        root.getChildren().add(imgArrow);
        
        //root.getChildren().add(drawSemiRing(350, 350, 200, 30, Color.LIGHTSKYBLUE, Color.DARKBLUE));

        Scene scene = new Scene(root, 450, 250);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }
    
    private void createLabels(List<Label> labels) {
        for(Label label : labels) {
            pane.getChildren().add(label);
        }
    }
    
}

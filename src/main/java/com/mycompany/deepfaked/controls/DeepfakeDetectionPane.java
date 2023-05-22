/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import com.mycompany.deepfaked.database.model.Deepfake;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author ZENODotus
 */
public class DeepfakeDetectionPane extends AnimationPane {
    
    private Label lblOverall;
    private Label lblSeferbekov;
    private Label lblAvatarify;
    private Label lblDeepware;
    private Label lblEnsemble;
    private Deepfake deepfake;
    
    
    public DeepfakeDetectionPane(Deepfake deepfake) {
        this.deepfake = deepfake;
    }
    
    @Override
    public double calculateAmount() {
         double amount = deepfake.getAvatarify();
         amount += deepfake.getDeepware();
         amount += deepfake.getEnsemble();
         amount += deepfake.getSeferbekov();
         
         return amount / 4;
     }
     
    @Override
    public List<Label> createLabels() {
         lblAvatarify = new Label();
         lblDeepware = new Label();
         lblSeferbekov = new Label();
         lblEnsemble = new Label();
         lblOverall = new Label();
         lblOverall.setLayoutX(60);
         lblOverall.setLayoutY(42);
         lblAvatarify.setLayoutX(22);
         lblDeepware.setLayoutX(22);
         lblSeferbekov.setLayoutX(22);
         lblEnsemble.setLayoutX(22);
         lblAvatarify.setLayoutY(312);
         lblDeepware.setLayoutY(332);
         lblSeferbekov.setLayoutY(352);
         lblEnsemble.setLayoutY(372);
         lblOverall.setFont(new Font(16));
         if(deepfake.getAnalyst().equals("deepfake") || calculateAmount() > 60.0) {
             lblOverall.setTextFill(Color.RED);
             lblOverall.setText("Waarschijnlijk deepfake!");
         } else {
             if(calculateAmount() < 40.0) {
                 lblOverall.setTextFill(Color.GREEN);
                 lblOverall.setText("Waarschijnlijk geen deepfake!");
             } else if (calculateAmount() < 60) {
                 lblOverall.setTextFill(Color.ORANGE);
                 lblOverall.setText("Analyse is onbepaald!");
             } 
         }
         if(deepfake.getAvatarify() < 40.0) {
             lblAvatarify.setTextFill(Color.GREEN);
             lblAvatarify.setText("Avatarify: geen deepfake (" + deepfake.getAvatarify() + "%)" );
         } else if (deepfake.getAvatarify() < 60.0) {
             lblAvatarify.setTextFill(Color.ORANGE);
             lblAvatarify.setText("Avatarify: onbepaald (" + deepfake.getAvatarify() + "%)" );
         } else {
             lblAvatarify.setTextFill(Color.RED);
             lblAvatarify.setText("Avatarify: deepfake (" + deepfake.getAvatarify() + "%)" );
         }
         
         if(deepfake.getDeepware() < 40.0) {
             lblDeepware.setTextFill(Color.GREEN);
             lblDeepware.setText("Deepware: geen deepfake (" + deepfake.getDeepware() + "%)" );
         } else if (deepfake.getDeepware() < 60.0) {
             lblDeepware.setTextFill(Color.ORANGE);
             lblDeepware.setText("Deepware: onbepaald (" + deepfake.getDeepware() + "%)" );
         } else {
             lblDeepware.setTextFill(Color.RED);
             lblDeepware.setText("Deepware: deepfake (" + deepfake.getDeepware() + "%)" );
         }
         
         if(deepfake.getSeferbekov() < 40.0) {
             lblSeferbekov.setTextFill(Color.GREEN);
             lblSeferbekov.setText("Seferbekov: geen deepfake (" + deepfake.getSeferbekov()+ "%)" );
         } else if (deepfake.getSeferbekov()< 60.0) {
             lblSeferbekov.setTextFill(Color.ORANGE);
             lblSeferbekov.setText("Seferbekov: onbepaald (" + deepfake.getSeferbekov()+ "%)" );
         } else {
             lblSeferbekov.setTextFill(Color.RED);
             lblSeferbekov.setText("Seferbekov: deepfake (" + deepfake.getSeferbekov()+ "%)" );
         }
         
         if(deepfake.getEnsemble() < 40.0) {
             lblEnsemble.setTextFill(Color.GREEN);
             lblEnsemble.setText("Ensemble: geen deepfake (" + deepfake.getEnsemble()+ "%)" );
         } else if (deepfake.getEnsemble()< 60.0) {
             lblEnsemble.setTextFill(Color.ORANGE);
             lblEnsemble.setText("Ensemble: onbepaald (" + deepfake.getEnsemble()+ "%)" );
         } else {
             lblEnsemble.setTextFill(Color.RED);
             lblEnsemble.setText("Ensemble: deepfake (" + deepfake.getEnsemble() + "%)" );
         }
         List<Label> labels = new ArrayList<>();
         labels.add(lblAvatarify);
         labels.add(lblDeepware);
         labels.add(lblSeferbekov);
         labels.add(lblEnsemble);
         labels.add(lblOverall);
         return labels;
         
         
     }
}

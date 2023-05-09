/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import com.mycompany.deepfaked.database.model.Deepfake;
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
public class DeepfakeDetectionPane {
    
    private Pane deepfakeDetectionPane;
    private ScrollPane deepfakeDetectionScrollPane;
    private ProgressBar progressScanVideo;
    private Label lblScan;
    private Label lblOverall;
    private Label lblSeferbekov;
    private Label lblAvatarify;
    private Label lblDeepware;
    private Label lblEnsemble;
    private ImageView imgArrow;
    private Deepfake deepfake;
    private double amount;
    
    public DeepfakeDetectionPane(Deepfake deepfake) {
        this.deepfake = deepfake;
    }
    
    public void createPane(double layoutX, double layoutY) {
        deepfakeDetectionPane = new Pane();
        deepfakeDetectionScrollPane = new ScrollPane();
        deepfakeDetectionScrollPane.setLayoutX(layoutX);
        deepfakeDetectionScrollPane.setLayoutY(layoutY);
        deepfakeDetectionScrollPane.setPrefSize(300, 400);
        deepfakeDetectionScrollPane.toFront();
        
        lblScan = new Label("Scanning video");
        lblScan.setLayoutX(60);
        lblScan.setLayoutY(42);
        lblScan.setFont(new Font(24));
        
        progressScanVideo = new ProgressBar(0);
        progressScanVideo.setLayoutX(30);
        progressScanVideo.setLayoutY(127);
        progressScanVideo.setPrefWidth(240);
        
        deepfakeDetectionPane.getChildren().add(lblScan);
        deepfakeDetectionPane.getChildren().add(progressScanVideo);
        deepfakeDetectionScrollPane.setContent(deepfakeDetectionPane);
        scanningAnimation();
        //createLabels();
        
        //System.out.println(factCheckWebsites.size());
        
    }
    
    private void preparingAnimation() {
        var i = new SimpleIntegerProperty(0);
         Timeline testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(i.get() + "test");
                //hoeveelheid = hoeveelheid + ((hoeveelheid * 180) / 100);
                progressScanVideo.setProgress(i.get() / 100.0);
                i.set(i.get() + 1);
            }
        }));
        testTime.setCycleCount(Timeline.INDEFINITE);
        testTime.play();
        i.addListener((ov, prevStatus, newStatus) -> {
            //System.out.println(newStatus.intValue());
            //System.out.println(prevStatus.intValue());
            if(newStatus.intValue() > 100) {
                testTime.stop();
                try {
                    Thread.sleep(2000);
                } catch (Exception ex) {
                    System.out.println("test");
                }
                scanningAnimation();
                
                
            }
        });
    }
    
     private void scanningAnimation() {
    
        Image image = new Image(getClass().getClassLoader().getResource("assets/icons/arrow.png").toString());
        imgArrow = new ImageView(image);
        imgArrow.setFitWidth(60);
        imgArrow.setPreserveRatio(true);
        double aspectRatio = image.getWidth() / image.getHeight();
        double realWidth = Math.min(imgArrow.getFitWidth(), imgArrow.getFitHeight() * aspectRatio);
        double realHeight = imgArrow.getFitWidth() / aspectRatio;
        imgArrow.setLayoutY(250.0 - (realHeight));
        imgArrow.setLayoutX(150.0 - (imgArrow.getFitWidth() / 2));
        imgArrow.setRotate(0);
        final double upper = calculateAmount() * 180 / 100;
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
                createLabels();
            }
        });
        i.addListener((ov, prevStatus, newStatus) -> {
            if(newStatus.intValue() >= upper) {
                testTime.stop();
                createLabels();
            }
        });
        deepfakeDetectionPane.getChildren().remove(lblScan);
        deepfakeDetectionPane.getChildren().remove(progressScanVideo);
        SemiRing semiRing = new SemiRing(150, 250, 120, 100);
        deepfakeDetectionPane.getChildren().add(semiRing.getSemiRing());
        deepfakeDetectionPane.getChildren().add(imgArrow);
        
        
        /*System.out.println(imgArrow.getLayoutY());
        
        Group root = new Group();
        root.getChildren().add(drawSemiRing(200, 200, 200, 150, Color.LIGHTGREEN, Color.DARKGREEN));
        root.getChildren().add(imgArrow);
        
        //root.getChildren().add(drawSemiRing(350, 350, 200, 30, Color.LIGHTSKYBLUE, Color.DARKBLUE));

        Scene scene = new Scene(root, 450, 250);
        primaryStage.setScene(scene);
        primaryStage.show();*/
    }
     
     public double calculateAmount() {
         double amount = deepfake.getAvatarify();
         amount += deepfake.getDeepware();
         amount += deepfake.getEnsemble();
         amount += deepfake.getSeferbekov();
         
         return amount / 4;
     }
     
     private void createLabels() {
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
         deepfakeDetectionPane.getChildren().add(lblOverall);
         deepfakeDetectionPane.getChildren().add(lblAvatarify);
         deepfakeDetectionPane.getChildren().add(lblDeepware);
         deepfakeDetectionPane.getChildren().add(lblSeferbekov);
         deepfakeDetectionPane.getChildren().add(lblEnsemble);
         
         
         
     }
    
    public ScrollPane getPane() {
        return deepfakeDetectionScrollPane;
    }
    
    
}

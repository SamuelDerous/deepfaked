/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import java.io.File;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Animation when a question is answered incorrectly.
 *
 * @author ZENODotus
 */
public class Loss extends Application implements CorrectState {
    private static final double W = 885; // canvas dimensions.
    private static final double H = 740;
    
    private static final Image police = new Image(Win.class.getResource("/assets/textures/police.png").toString());
    private static final ImageView viewPolice = new ImageView(police);
    
    private static int i = 0;
    
    private static Timeline testTime = new Timeline();
    private static Timeline timeline = new Timeline();

    /**
     * The actual animation
     * @param pane the pane where the animation has to be performed on.
     */
    public void animate(Pane pane) {
        viewPolice.setFitWidth(100);
        viewPolice.setPreserveRatio(true);
        viewPolice.setLayoutX(380);
        viewPolice.setLayoutY(400);
        viewPolice.setOpacity(1.0);
        i = 0;
        pane.getChildren().add(viewPolice);
            
        timeline = new Timeline(
                new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent> () {
                    @Override
                    public void handle(ActionEvent event) {
                        viewPolice.setOpacity(viewPolice.getOpacity() - 0.1);
                        if(viewPolice.getOpacity() < 0) {
                            pane.getChildren().remove(viewPolice);
                            timeline.stop();
                        }
                        
                    }
                }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        testTime = new Timeline(new KeyFrame(Duration.millis(20), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (i == 50) {
                    timeline.play();
                } else if (i > 150) {
                    testTime.stop();
                    
                }
                i++;
            }
        }));
        testTime.setCycleCount(Timeline.INDEFINITE);
        testTime.play();
        try {
        String wrongFile = Loss.class.getResource("/assets/sound/wrong.mp3").toURI().toString();
        Media soundWrong = new Media(wrongFile);
        MediaPlayer mediaPlayer = new MediaPlayer(soundWrong);
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            
            });
        mediaPlayer.setOnError(() -> {
            mediaPlayer.dispose();
        });
        } catch(Exception ex) {
        }
    }
    
    public void create(Pane pane) {
        animate(pane);
    }

   @Override public void start(Stage stage) {
        Pane pane = new Pane();
        pane.setPrefWidth(600);
        pane.setPrefHeight(800);
        
        animate(pane);
        stage.setScene(
            new Scene(pane
                
            )
        );
        stage.show();
            
        //timer.start();
        //timeline.play();
    }
   
   public static void main(String[] args) { launch(args); }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author ZENODotus
 */
public class AnimatedCoins extends Application {
    private static final double W = 885; // canvas dimensions.
    private static final double H = 740;
    
    private static AnimationTimerExt timer; // = new AnimationTimerExt(10);
    public static final Image coin = new Image(AnimatedCoins.class.getResource("/assets/textures/coin.png").toString());
    //public static final ImageView viewCoin = new ImageView(coin);

    public static final double D = 20;  // diameter.
    public static void animate(Pane pane) {
        DoubleProperty x  = new SimpleDoubleProperty();
        DoubleProperty y  = new SimpleDoubleProperty();
         ImageView viewCoin = new ImageView(coin);
            viewCoin.setFitWidth(20);
            viewCoin.setFitHeight(20);
            double layoutX = Math.random() * W;
            double layoutY = Math.random() * 600 + 400;
            viewCoin.setLayoutX(layoutX);
            viewCoin.setLayoutY(0); //layoutY);
        
        
        double duration1 = Math.random() * 5;
        double duration2 = Math.random() * 5;
        
        //final Pane pane = new Pane();
        pane.setPrefSize(W, H);
        pane.getChildren().add(viewCoin);
            /*Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(duration1),
                    new KeyValue(x, 0),
                    new KeyValue(y, 0)
            ),
            new KeyFrame(Duration.seconds(duration2),
                    new KeyValue(x, W - D),
                    new KeyValue(y, H - D)
            )
            );
            timeline.setAutoReverse(true);
            timeline.setCycleCount(Timeline.INDEFINITE);*/
            int test = (int) (Math.random() * 50);
            System.out.println(test);
            timer = new AnimationTimerExt(test) {
                @Override
                public void handle() {
                    if(viewCoin.getY() < H - 20) {
                    viewCoin.setY(viewCoin.getY() + 20.0);
                  } else {
                      viewCoin.setX(viewCoin.getX() + 20.0);
                  }
                    
                    
                  }
            
        };
        try {
        String coinsFallingFile = AnimatedCoins.class.getResource("/assets/sound/money.mp3").toURI().toString();
        Media soundCoins = new Media(coinsFallingFile);
        MediaPlayer mediaPlayer = new MediaPlayer(soundCoins);
        timer.start();
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.dispose();
            
            });
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //timeline.play();
        
        
    }
    @Override public void start(Stage stage) {
        Pane pane = new Pane();
        for(int i = 0; i < 50; i++) {
            animate(pane);
        }
            
        stage.setScene(
            new Scene(pane
                
            )
        );
        stage.show();
            
        //timer.start();
        //timeline.play();
    }
    
    public static void create(Pane pane) {
        for(int i = 0; i < 50; i++) {
            animate(pane);
        }
    }

    public static void main(String[] args) { launch(args); }
}  



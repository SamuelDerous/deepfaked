/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import java.net.URISyntaxException;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * An animation for answering questions correctly.
 *
 * @author ZENODotus
 */
public class Win implements CorrectState {

    private static final double W = 885; // canvas dimensions.
    private static final double H = 740;

    private static AnimationTimerExt timer; // = new AnimationTimerExt(10);
    public static final Image coin = new Image(Win.class.getResource("/assets/textures/coin.png").toString());
    //public static final ImageView viewCoin = new ImageView(coin);

    public static final double D = 20;  // diameter.

    /**
     * The animation for a single coin
     * @param pane the pane the animation has to be performed on.
     */
    public void animate(Pane pane) {
        DoubleProperty x = new SimpleDoubleProperty();
        DoubleProperty y = new SimpleDoubleProperty();
        ImageView viewCoin = new ImageView(coin);
        viewCoin.setFitWidth(20);
        viewCoin.setFitHeight(20);
        double layoutX = Math.random() * W;
        viewCoin.setLayoutX(layoutX);
        viewCoin.setLayoutY(0); //layoutY);
        pane.setPrefSize(W, H);
        pane.getChildren().add(viewCoin);
        int test = (int) (Math.random() * 50);
        timer = new AnimationTimerExt(test) {
            @Override
            public void handle() {
                if (viewCoin.getY() < H - 20) {
                    viewCoin.setY(viewCoin.getY() + 20.0);
                } else {
                    viewCoin.setX(viewCoin.getX() + 20.0);
                }
            }
        };
            timer.start();
            try {
            String coinsFallingFile = Win.class.getResource("/assets/sound/money.mp3").toURI().toString();
            Media soundCoins = new Media(coinsFallingFile);
            MediaPlayer mediaPlayer = new MediaPlayer(soundCoins);
            
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(() -> {
                mediaPlayer.dispose();
            });
            mediaPlayer.setOnError(() -> {
                mediaPlayer.dispose();
                
            });
            }catch(Exception ex) {
                
            }
        
    }

    /**
     * the animation for 50 coins at once.
     * @param pane the pane the animation has to be performed on.
     */
    public void create(Pane pane) {
        for (int i = 0; i < 50; i++) {
            animate(pane);
        }
    }
}

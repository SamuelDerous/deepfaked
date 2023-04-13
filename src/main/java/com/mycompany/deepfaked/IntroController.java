/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;


import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;


public class IntroController implements Initializable {

    @FXML
    private MediaPlayer mediaPlayer;

    @FXML
    private MediaView mediaIntro;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            init();
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    public void init() throws MalformedURLException, URISyntaxException {
        System.out.println("Dit is een test");
        // intro = new File(getClass().getClassLoader().getResource("assets/test.mp4").toString());
        Media media = new Media(getClass().getClassLoader().getResource("assets/test.mp4").toURI().toString());
        //Media media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setOnError(()->
                System.out.println("media error"+mediaPlayer.getError().toString()));
        //mediaPlayer.setAutoPlay(true);
        mediaIntro.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();

        mediaPlayer.setOnPlaying(new Runnable() {
            public void run() {
                /*if (stopRequested) {
                    mediaPlayer.pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }*/
         }
        });
        mediaPlayer.setOnEndOfMedia(() -> {
            LoginController.getIntroStage().close();
            
            });
        //mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        //mediaIntro.setMediaPlayer(mediaPlayerComponent);

    }
}

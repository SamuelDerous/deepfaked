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
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;


public class IntroController implements Initializable {
    
    private static Stage introStage;
    
    private final static String INTRO =  "Dit is de CEO van Tiktok. Momenteel worden we overspoeld door fake video's.\n "
            + "Jullie zijn de beste in jullie vak, en wij hebben de beste nodig.\n"
            + "Daarom hebben we een test opgesteld waar jullie tegen elkaar zullen strijden.\n"
            + "De beste kan rekenen op een belangrijke functie binnen ons bedrijf.\n"
            + "Als u wilt deelnemen, druk dan op de knop 'Ja' of druk op de toets 'j'.\n";
    

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
        Media media = new Media(getClass().getResource("/assets/test.mp4").toURI().toString());
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
            createIntroPage();
            
            });
        //mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        //mediaIntro.setMediaPlayer(mediaPlayerComponent);

    }
    
    @FXML
    protected void skipIntro() {
        createIntroPage();
    }
    
    private void createIntroPage() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("intro.fxml"));
            
            introStage = new Stage();
            Intro test = new Intro();
            introStage.setTitle("Deepfaked");
            introStage.setScene(test.playScene(INTRO, false, true));
            introStage.show();
            
            LoginController.getIntroStage().close();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.MediaPlayer;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;


public class VideoMedia extends MediaFactory implements Initializable {
    
    
    @FXML
    private MediaPlayer videoMediaPlayer;

    @FXML
    private MediaView mediaIntro;
    //private ImageView mediaIntro;
    
    //private MediaPlayerFactory mediaPlayerFactory;

    //private EmbeddedMediaPlayer embeddedMediaPlayer;

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
        // intro = new File(getClass().getClassLoader().getResource("assets/test.mp4").toString());
        Media media = new Media("https://dl.dropboxusercontent.com/s/8vo9huv60hq8frq/intro.mp4?dl=0");
        //Media media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        videoMediaPlayer = new MediaPlayer(media);
        videoMediaPlayer.setOnError(()->
                System.out.println("media error"+videoMediaPlayer.getError().toString()));
        //mediaPlayer.setAutoPlay(true);
        mediaIntro.setMediaPlayer(videoMediaPlayer);
        videoMediaPlayer.play();

        videoMediaPlayer.setOnEndOfMedia(() -> {
            videoMediaPlayer.dispose();
            createIntroPage();
            
            });
        //mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        //mediaIntro.setMediaPlayer(mediaPlayerComponent);

}
    
    @FXML
    protected void skipIntro() {
        videoMediaPlayer.stop();
        videoMediaPlayer.dispose();
        createIntroPage();
    }
}

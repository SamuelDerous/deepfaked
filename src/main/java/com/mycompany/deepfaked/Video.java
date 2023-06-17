/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

/**
 *
 * @author ZENODotus
 */
public class Video implements Initializable {
    
    @FXML
    private ImageView mediaIntro;
    
    @FXML
    private AnchorPane rootPane;
    
    private MediaPlayerFactory mediaPlayerFactory;

    private EmbeddedMediaPlayer embeddedMediaPlayer;
    
    private String media;
    
    public Video(String media) {
        this.media = media;
    }

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
        this.mediaPlayerFactory = new MediaPlayerFactory();
        this.embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
        
        
        embeddedMediaPlayer.videoSurface().set(new ImageViewVideoSurface(this.mediaIntro));
        embeddedMediaPlayer.media().play(media);
        ControlsPane controlsPane = new ControlsPane(embeddedMediaPlayer);
        controlsPane.toFront();
        controlsPane.setLayoutY(310);
        rootPane.getChildren().add(controlsPane);
        
        

}
    
    public void stop() {
        System.out.println("We are stopped!");
        embeddedMediaPlayer.controls().stop();
        embeddedMediaPlayer.release();
        mediaPlayerFactory.release();
    }
}

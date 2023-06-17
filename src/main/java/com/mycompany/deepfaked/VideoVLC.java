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
import javafx.application.Platform;
import javafx.scene.image.ImageView;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


public class VideoVLC extends MediaFactory implements Initializable {
    
    @FXML
    private ImageView mediaIntro;
    
    private MediaPlayerFactory mediaPlayerFactory;

    private EmbeddedMediaPlayer embeddedMediaPlayer;

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
        String media = "https://dl.dropboxusercontent.com/s/8vo9huv60hq8frq/intro.mp4?dl=0";
        
        this.mediaPlayerFactory = new MediaPlayerFactory();
        this.embeddedMediaPlayer = mediaPlayerFactory.mediaPlayers().newEmbeddedMediaPlayer();
        this.embeddedMediaPlayer.events().addMediaPlayerEventListener(new MediaPlayerEventAdapter() {
            @Override
            public void finished(uk.co.caprica.vlcj.player.base.MediaPlayer mediaPlayer) {
                System.out.println("Dit is een test dat de mediaplayer stopt");
                Platform.runLater(() -> { 
                    createIntroPage();
                });
            }
            
        });
        
        embeddedMediaPlayer.videoSurface().set(new ImageViewVideoSurface(this.mediaIntro));
        embeddedMediaPlayer.media().play(media);

}
    
    @FXML
    protected void skipIntro() {
         embeddedMediaPlayer.controls().stop();
        embeddedMediaPlayer.release();
        mediaPlayerFactory.release();
        createIntroPage();
    }
}

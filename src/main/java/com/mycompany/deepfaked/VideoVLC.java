/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;
import com.mycompany.deepfaked.controls.PropertiesHolder;
import com.mycompany.deepfaked.main.App;
import com.mycompany.deepfaked.view.InfoFactory;
import com.mycompany.deepfaked.view.IntroView;
import com.mycompany.deepfaked.view.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.MediaPlayer;


import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

import uk.co.caprica.vlcj.factory.MediaPlayerFactory;
import uk.co.caprica.vlcj.javafx.videosurface.ImageViewVideoSurface;
import uk.co.caprica.vlcj.player.base.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;


public class VideoVLC implements Initializable {
    
    private static Stage introStage;
    
    private final static String INTRO =  "Dit is de CEO van Tiktok. Momenteel worden we overspoeld door fake video's.\n "
            + "Jullie zijn de beste in jullie vak, en wij hebben de beste nodig.\n"
            + "Daarom hebben we een test opgesteld waar jullie tegen elkaar zullen strijden.\n"
            + "De beste kan rekenen op een belangrijke functie binnen ons bedrijf.\n"
            + "Als u wilt deelnemen, druk dan op de knop 'Ja' of druk op de toets 'j'.\n";
    

    @FXML
    private MediaPlayer mediaPlayer;

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
    
    private void createIntroPage() {
        System.out.println("in intropageCreation");
        //embeddedMediaPlayer.controls().stop();
        //embeddedMediaPlayer.release();
        //mediaPlayerFactory.release();
        System.out.println("gestopt");
        //embeddedMediaPlayer.stop();
        //mediaPlayer.dispose();
        //FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(PropertiesHolder.getInstance().getProperty("defaultmap") + "/view/intro.fxml"));
            introStage = new Stage();
            IntroView test = InfoFactory.createIntroView();
            introStage.setTitle("Deepfaked");
            introStage.setScene(test.createScene(INTRO));
            introStage.show();
            
            MediaFactory.getIntroStage().close();
    }
    
    public static Stage getIntroStage() {
        return introStage;
    }
}

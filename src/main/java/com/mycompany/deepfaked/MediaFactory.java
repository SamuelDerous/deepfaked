/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.controls.PropertiesHolder;
import com.mycompany.deepfaked.main.App;
import com.mycompany.deepfaked.view.InfoFactory;
import com.mycompany.deepfaked.view.IntroView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author ZENODotus
 */
public class MediaFactory {
    
    private static boolean isError;
    private static Stage introStage;
    private static Stage viewStage;
    
    private final static String INTRO =  "Dit is de CEO van Tiktok. Momenteel worden we overspoeld door fake video's.\n "
            + "Jullie zijn de beste in jullie vak, en wij hebben de beste nodig.\n"
            + "Daarom hebben we een test opgesteld waar jullie tegen elkaar zullen strijden.\n"
            + "De beste kan rekenen op een belangrijke functie binnen ons bedrijf.\n"
            + "Als u wilt deelnemen, druk dan op de knop 'Ja' of druk op de toets 'j'.\n";
    
    
    private static MediaPlayer mediaPlayer;
    
    public static void createIntro() {
        isError = false;
        Media media = new Media("https://dl.dropboxusercontent.com/s/8vo9huv60hq8frq/intro.mp4?dl=0");
        //Media media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        //mediaPlayer.stop();
        mediaPlayer.setOnPlaying(() -> {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            openIntro("intro.fxml");
        });
        
        mediaPlayer.setOnError(()-> {
                //FXMLLoader fxmlLoader;
                openIntro("introVLC.fxml");
        });
        
        //mediaPlayer.play();
        //mediaPlayer.stop();
        
        
        //isError = true;
    }

    public static Stage getIntroStage() {
        return introStage;
    }
    
    private static void openIntro(String url) {
        FXMLLoader loader = new FXMLLoader(MediaFactory.class.getResource(PropertiesHolder.getInstance().getProperty("defaultMap") + "/view/" + url));
                try {
        Scene scene = new Scene(loader.load(), 600, 350);
            introStage = new Stage();
            introStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
            introStage.setTitle("Deepfaked");
            introStage.setScene(scene);
            introStage.show();
            App.getStage().close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected void createIntroPage() {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(PropertiesHolder.getInstance().getProperty("defaultmap") + "/view/intro.fxml"));
            viewStage = new Stage();
            IntroView test = InfoFactory.createIntroView();
            viewStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
            viewStage.setTitle("Deepfaked");
            viewStage.setScene(test.createScene(INTRO));
            viewStage.show();
            
            MediaFactory.getIntroStage().close();
    }
    
    public static Stage getViewStage() {
        return viewStage;
    }
}

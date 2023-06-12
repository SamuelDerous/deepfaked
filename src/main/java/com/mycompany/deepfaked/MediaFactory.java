/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import com.mycompany.deepfaked.controls.PropertiesHolder;
import com.mycompany.deepfaked.main.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
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
    
    private static MediaPlayer mediaPlayer;
    
    public static void createIntro() {
        System.out.println("Dit is een test");
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
            introStage.setTitle("Deepfaked");
            introStage.setScene(scene);
            introStage.show();
            App.getStage().close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

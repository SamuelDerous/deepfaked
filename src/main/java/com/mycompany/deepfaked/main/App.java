package com.mycompany.deepfaked.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;
import javafx.scene.image.Image;

public class App extends Application {

    private static Stage guiStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/mycompany/deepfaked/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 569);
        guiStage = stage;
        guiStage.setResizable(false);
        guiStage.setTitle("Deepfaked");
        guiStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
        guiStage.setScene(scene);
        guiStage.show();
    }

    public static Stage getStage() {
        return guiStage;
    }
    public static void main(String[] args) {
        launch();
        
        
    }
}
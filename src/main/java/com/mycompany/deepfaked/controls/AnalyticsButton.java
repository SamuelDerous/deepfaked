/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import com.mycompany.deepfaked.App;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author ZENODotus
 */
public class AnalyticsButton extends Button {
    
    public AnalyticsButton() {
        super();
        ImageView iw = new ImageView(new Image(getClass().getClassLoader().getResource("assets/icons/analyticsImage.png").toString()));
        this.setGraphic(iw);
        this.setPrefWidth(64);
        this.setPrefHeight(64);
        this.setPadding(new Insets(0.5, 0.5, 0.5, 0.5));
        this.setOnAction((event) -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("analytics.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(fxmlLoader.load(), 900, 730);
                stage.setScene(scene);
                stage.show();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
                
        });
    }
}

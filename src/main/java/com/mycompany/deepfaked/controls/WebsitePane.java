/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import com.mycompany.deepfaked.model.WebsiteLink;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * create the pane for the links
 * @author ZENODotus
 */
public class WebsitePane {
    
    private static List<WebsiteLink> websites;
    
    private Pane factCheckPane;
    
    private ScrollPane factCheckScrollPane;
   
    public void createPane(String tagName, double layoutX, double layoutY) {
        factCheckPane = new Pane();
        factCheckScrollPane = new ScrollPane();
        factCheckPane.setPadding(new Insets(15));
        factCheckScrollPane.setPrefSize(120, 200);
        factCheckScrollPane.setLayoutX(layoutX);
        factCheckScrollPane.setLayoutY(layoutY);
        factCheckScrollPane.toFront();
        //System.out.println(factCheckWebsites.size());
        WebsiteFiller websiteFiller = new WebsiteFiller();
        VBox vbox = websiteFiller.createVbox(tagName);
        StackPane imageHolder = new StackPane(vbox);
        imageHolder.minWidthProperty().bind(Bindings.createDoubleBinding(() -> 
        factCheckScrollPane.getViewportBounds().getWidth(), factCheckScrollPane.viewportBoundsProperty()));
        factCheckScrollPane.setContent(imageHolder);
        factCheckScrollPane.setFitToWidth(true);
    }
    
    
    
    
    /*public static void main(String[] args) {
        initializeList();
        for(int i = 0; i < factCheckWebsites.size(); i++) {
            System.out.println("Name: " + factCheckWebsites.get(i).getName());
            System.out.println("Url: " + factCheckWebsites.get(i).getUrl());
        }
    }*/

    public ScrollPane getPane() {
        return factCheckScrollPane;
    }
}

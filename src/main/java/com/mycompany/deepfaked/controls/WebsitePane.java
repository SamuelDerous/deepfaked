/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import com.mycompany.deepfaked.main.App;
import com.mycompany.deepfaked.view.TermsController;
import com.mycompany.deepfaked.model.WebsiteLink;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.stage.Stage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
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

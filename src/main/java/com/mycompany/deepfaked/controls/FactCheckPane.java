/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.controls;

import com.mycompany.deepfaked.App;
import com.mycompany.deepfaked.TermsController;
import com.mycompany.deepfaked.model.FactCheck;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
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
public class FactCheckPane {
    
    private static List<FactCheck> factCheckWebsites;
    
    private Pane factCheckPane;
    
    private ScrollPane factCheckScrollPane;
   
    public void createPane() {
        factCheckPane = new Pane();
        factCheckScrollPane = new ScrollPane();
        factCheckPane.setPadding(new Insets(15));
        factCheckScrollPane.setPrefSize(300, 200);
        factCheckScrollPane.setLayoutX(96);
        factCheckScrollPane.setLayoutY(80);
        factCheckScrollPane.toFront();
        System.out.println(factCheckWebsites.size());
        VBox vbox = new VBox();
        for(FactCheck factCheckWebsite : factCheckWebsites) {
            
            ImageView imageView = new ImageView(factCheckWebsite.getImage());
            //imageView.setFitHeight();
            //imageView.setFitWidth(100);
            imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventFilter -> {
                try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load(factCheckWebsite.getUrl());
                Stage infoStage = new Stage();
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                
            }
            });
            imageView.setOnMouseEntered((Event event) -> {
                imageView.setCursor(Cursor.HAND);
            });
            vbox.getChildren().add(imageView);
            
        }
        factCheckScrollPane.setContent(vbox);
    }
    
    
    public void initializeList() {
        try {
            factCheckWebsites = new ArrayList<>();
            InputStream file = FactCheckPane.class.getClassLoader().getResourceAsStream("assets/xml/factCheck.xml");
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.parse(file);
            NodeList nodeList = document.getElementsByTagName("factcheck");
            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    FactCheck factCheck = new FactCheck();
                    Element element = (Element) node;
                    factCheck.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    factCheck.setUrl(element.getElementsByTagName("url").item(0).getTextContent());
                    factCheck.setImage(new Image(getClass().getClassLoader().getResource(element.getElementsByTagName("image").item(0).getTextContent()).toString(), 280, 0, true, false));
                    factCheckWebsites.add(factCheck);
                    System.out.println(element.getElementsByTagName("image").item(0).getTextContent());
                }
                
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /*public static void main(String[] args) {
        initializeList();
        for(int i = 0; i < factCheckWebsites.size(); i++) {
            System.out.println("Name: " + factCheckWebsites.get(i).getName());
            System.out.println("Url: " + factCheckWebsites.get(i).getUrl());
        }
    }*/

    public ScrollPane getFactCheckPane() {
        return factCheckScrollPane;
    }
}

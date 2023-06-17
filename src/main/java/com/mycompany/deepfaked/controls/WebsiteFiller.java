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
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
 * The class that destills the links out of the XML.
 * @author ZENODotus
 */
public class WebsiteFiller {
    
    private static List<WebsiteLink> websites;
    
    /**
     * create the list
     * @param tagName the tagname in the XML
     */
    private void initializeList(String tagName) {
        try {
            websites = new ArrayList<>();
            InputStream file = WebsitePane.class.getResourceAsStream("/assets/xml/websites.xml");
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = docBuilder.parse(file);
            NodeList nodeList = document.getElementsByTagName(tagName);
            for(int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE) {
                    WebsiteLink website = new WebsiteLink();
                    Element element = (Element) node;
                    website.setName(element.getElementsByTagName("name").item(0).getTextContent());
                    website.setUrl(element.getElementsByTagName("url").item(0).getTextContent());
                    website.setImage(new Image(getClass().getResource(element.getElementsByTagName("image").item(0).getTextContent()).toString(), 280, 0, true, false));
                    websites.add(website);
                }
                
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * design the list
     * @param tagName tagname in the XML
     * @return return a VBOX with all the links.
     */
    public VBox createVbox(String tagName) {
        initializeList(tagName);
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        for(WebsiteLink website : websites) {
            ImageView imageView = new ImageView(website.getImage());
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, eventFilter -> {
                try {
                FXMLLoader fxmlLoader = new FXMLLoader(WebsiteFiller.class.getResource(PropertiesHolder.getInstance().getProperty("defaultMap") + "/view/terms.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 600, 400);
                TermsController controller = fxmlLoader.<TermsController>getController();
                WebEngine webEngine = controller.getFleTerms().getEngine();
                webEngine.load(website.getUrl());
                Stage infoStage = new Stage();
                infoStage.getIcons().add(new Image(App.class.getResource("/assets/DeepfakedSplash.png").toString()));
                infoStage.setTitle("Info");
                infoStage.setScene(scene);
                infoStage.show();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            });
            imageView.setOnMouseEntered((Event event) -> {
                imageView.setCursor(Cursor.HAND);
            });
            vbox.getChildren().add(imageView);
            
            
            
        }
        return vbox;
    }
}

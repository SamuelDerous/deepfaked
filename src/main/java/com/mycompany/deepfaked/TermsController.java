/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.deepfaked;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;

/**
 * FXML Controller class
 *
 * @author ZENODotus
 */
public class TermsController implements Initializable {

    @FXML
    private WebView fleTerms;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 

    public WebView getFleTerms() {
        return fleTerms;
    }

    public void setFleTerms(WebView fleTerms) {
        this.fleTerms = fleTerms;
    }
    
    
    
}

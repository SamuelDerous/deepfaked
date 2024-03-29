/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.model;

import javafx.scene.image.Image;

/**
 * The entity class for the websitelinks in the xml
 * @author ZENODotus
 */
public class WebsiteLink {
    
    private String name;
    private String url;
    private Image image;
    
    public WebsiteLink() {
        
    }
    
    public WebsiteLink(String name, String url, Image image) {
        this.name = name;
        this.url = url;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
    
    
}

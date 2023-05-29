/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.deepfaked.view;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 *
 * @author ZENODotus
 */
public interface InfoView {
    
    public static final List<Image> OWNERIMAGES = new ArrayList<>(List.of(new Image(InfoView.class.getResource("/assets/textures/Owner1.jpg").toString()),
            new Image(InfoView.class.getResource("/assets/textures/Owner2.jpg").toString()),
            new Image(InfoView.class.getResource("/assets/textures/Owner3.jpg").toString()),
            new Image(InfoView.class.getResource("/assets/textures/Owner4.jpg").toString())));

    
    public Scene createScene();
    public void createButtons();
    public Scene playScene(String intro);
    
}

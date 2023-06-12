/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked;

import javafx.scene.layout.Pane;

/**
 *
 * @author ZENODotus
 */
public class CorrectContext {
    
    private CorrectState state;
    private Pane pane;
    
    public CorrectContext(CorrectState state, Pane pane) {
        this.state = state;
        this.pane = pane;
    }
    
    public void animate() {
        state.create(pane);
    }
    
    public CorrectState getState() {
        return state;
    }
    
}

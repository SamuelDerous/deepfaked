/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

/**
 *
 * @author ZENODotus
 */
public class CorrectContext implements CorrectState {
    
    Correct state;

    @Override
    public void reward() {
        state.reward();
    }

    public Correct getState() {
        return state;
    }

    public void setState(Correct state) {
        this.state = state;
    }
    
    
}

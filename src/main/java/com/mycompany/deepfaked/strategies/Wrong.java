/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

/**
 *
 * @author ZENODotus
 */
public class Wrong extends Correct {

    @Override
    public void reward() {
        setFollowers(0);
        setMoney(0.0);
        
    }
    
}

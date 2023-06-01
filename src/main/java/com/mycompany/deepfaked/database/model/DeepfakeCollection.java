/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

/**
 *
 * @author ZENODotus
 */
public interface DeepfakeCollection<Deepfake> extends Collection {
    
    public int getProgress();
    public int getProgressMission();
    public int getProgressDeepfake();
}

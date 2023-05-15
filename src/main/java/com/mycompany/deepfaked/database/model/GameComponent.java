/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ZENODotus
 */
public interface GameComponent {
    
    public void appoint();
    public GameComponent getchild(int index);
    public Iterator<? extends GameComponent> createIterator();
    public List<GameComponent> getAll();
    int size();
}

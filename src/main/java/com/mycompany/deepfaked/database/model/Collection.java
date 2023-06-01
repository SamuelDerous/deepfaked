/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import java.util.List;

/**
 *
 * @author ZENODotus
 */
public interface Collection<T> {
    
    public void remove(T object);
    public T get();
    public Iterator iterator();
    public List<QuestionChoice> getChoices();
    
}

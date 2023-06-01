/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

/**
 *
 * @author ZENODotus
 */
public interface Iterator<T> {
    public boolean hasNext();
    public T next();
    public int size();
}

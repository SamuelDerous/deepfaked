/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.database.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.TableGenerator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author ZENODotus
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class GameComposite implements GameComponent {
    
    @GeneratedValue(strategy = GenerationType.TABLE, generator="GameCompositeGenerator")
    @TableGenerator(table="SEQUENCES", name="GameCompositeGenerator")
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public abstract Iterator<? extends GameComponent> createIterator();
    
    @Override
    public void appoint() {
        Iterator<? extends GameComponent> iterator = createIterator();
        while(iterator.hasNext()) {
            iterator.next().appoint();
        }
    }
    
    @Override
    public int size() {
        Iterator<? extends GameComponent> iterator = createIterator();
        int size = 0;
        while(iterator.hasNext()) {
            GameComponent component = iterator.next();
            size += component.size();
        }
        return size();
    }
    
    @Override
    public List<GameComponent> getAll() {
        List<GameComponent> all = new ArrayList<>();
        Iterator<? extends GameComponent> iterator = createIterator();
        while(iterator.hasNext()) {
            all.add(iterator.next());
        }
        return all;
    }
    
}

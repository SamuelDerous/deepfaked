/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.deepfaked.strategies;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ZENODotus
 */
public abstract class Answer implements AnswerState {
    private int followers;
    private double money;
    private String information;
    private Set<String> hyperlinks;
    
    public Answer() {
        this.followers = 0;
        this.money = 0.0;
        this.information = "";
        hyperlinks = new TreeSet<>();
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public Set<String> getHyperlinks() {
        return hyperlinks;
    }

    public void setHyperlinks(Set<String> hyperlinks) {
        this.hyperlinks = hyperlinks;
    }
    
    public void addHyperlink(String hyperlink) {
        hyperlinks.add(hyperlink);
    }
    
    
}

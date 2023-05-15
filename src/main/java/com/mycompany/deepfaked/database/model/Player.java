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
import java.util.Objects;

/**
 *
 * @author ZENODotus
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Player {
    @GeneratedValue(strategy = GenerationType.TABLE, generator="AccountGenerator")
    @TableGenerator(table="SEQUENCES", name="AccountGenerator")
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "userName", nullable = false, length = 50)
    private String userName;
    @Basic
    @Column(name = "avatar", nullable = true, length = 100)
    private String avatar;
    @Basic
    @Column(name = "followers", nullable = false)
    private int followers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return getId() == that.getId() && followers == that.getFollowers() && Objects.equals(userName, that.getUserName()) && Objects.equals(avatar, that.getAvatar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), userName, avatar, followers);
    }

    
    
}

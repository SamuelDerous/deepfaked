package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Gamer", schema = "Deepfaked", catalog = "")
public class Gamer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "gamerId", nullable = false)
    private int gamerId;
    @Basic
    @Column(name = "userName", nullable = false, length = 50)
    private String userName;
    @Basic
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Basic
    @Column(name = "avatar", nullable = true, length = 100)
    private String avatar;
    @Basic
    @Column(name = "birthdate", nullable = true)
    private LocalDate birthdate;
    @Basic
    @Column(name = "money", nullable = true, precision = 2)
    private double money;
    @Basic
    @Column(name = "followers", nullable = true)
    private Integer followers;
    
    public int getGamerId() {
        return gamerId;
    }

    public void setGamerId(int gamerId) {
        this.gamerId = gamerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer that = (Gamer) o;
        return gamerId == that.gamerId && Objects.equals(userName, that.userName) && Objects.equals(password, that.password) && Objects.equals(avatar, that.avatar) && Objects.equals(birthdate, that.birthdate) && Objects.equals(money, that.money) && Objects.equals(followers, that.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gamerId, userName, password, avatar, birthdate, money, followers);
    }
}

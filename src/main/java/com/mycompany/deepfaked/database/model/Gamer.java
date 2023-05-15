package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "Gamer", schema = "Deepfaked", catalog = "")
public class Gamer extends Player {
    @Basic
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "birthdate", nullable = true)
    private LocalDate birthdate;
    @Basic
    @Column(name = "money", nullable = true, precision = 2)
    private double money;
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gamer that = (Gamer) o;
        return getId() == that.getId() && Objects.equals(getUserName(), that.getUserName()) && 
                Objects.equals(getPassword(), that.getPassword()) && Objects.equals(getAvatar(), that.getAvatar()) && Objects.equals(getBirthdate(), that.getBirthdate())
                && Objects.equals(getMoney(), that.getMoney()) && Objects.equals(getFollowers(), that.getFollowers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPassword(), getAvatar(), getBirthdate(), getMoney(), getFollowers());
    }
}

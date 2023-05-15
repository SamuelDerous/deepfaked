package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Account", schema = "Deepfaked", catalog = "")
public class Account extends Player {
    
    @Basic
    @Column(name = "real", nullable = false)
    private int real;

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account that = (Account) o;
        return this.getId() == that.getId() && getFollowers() == that.getFollowers() && getReal() == that.getReal() && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getAvatar(), that.getAvatar());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getAvatar(), getFollowers(), getReal());
    }
}

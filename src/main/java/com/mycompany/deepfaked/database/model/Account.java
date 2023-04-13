package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Account", schema = "Deepfaked", catalog = "")
public class Account {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "accountId", nullable = false)
    private int accountId;
    @Basic
    @Column(name = "userName", nullable = false, length = 50)
    private String userName;
    @Basic
    @Column(name = "avatar", nullable = true, length = 100)
    private String avatar;
    @Basic
    @Column(name = "followers", nullable = false)
    private int followers;
    @Basic
    @Column(name = "real", nullable = false)
    private int real;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
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
        return accountId == that.accountId && followers == that.followers && real == that.real && Objects.equals(userName, that.userName) && Objects.equals(avatar, that.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, userName, avatar, followers, real);
    }
}

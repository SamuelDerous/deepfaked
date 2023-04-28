package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "FeedbackValue", schema = "Deepfaked", catalog = "")
public class FeedbackValue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "valueId", nullable = false)
    private int valueId;
    @Basic
    @Column(name = "followers", nullable = false)
    private int followers;
    @Basic
    @Column(name = "money", nullable = false, precision = 2)
    private double money;

    public int getValueId() {
        return valueId;
    }

    public void setValueId(int valueId) {
        this.valueId = valueId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedbackValue that = (FeedbackValue) o;
        return valueId == that.valueId && followers == that.followers && Objects.equals(money, that.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueId, followers, money);
    }
}

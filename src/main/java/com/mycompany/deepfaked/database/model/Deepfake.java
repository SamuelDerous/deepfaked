package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Deepfake", schema = "Deepfaked", catalog = "")
public class Deepfake {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "videoId", nullable = false)
    private int videoId;
    @Basic
    @Column(name = "location", nullable = false, length = 100)
    private String location;
    @Basic
    @Column(name = "real", nullable = false)
    private int real;
    @Basic
    @Column(name = "label", nullable = false, length = 255)
    private String label;
    @Basic
    @Column(name = "mission", nullable = false)
    private int mission;
    @Basic
    @Column(name = "consequence", nullable = false)
    private int consequence;
    @Basic
    @Column(name = "value", nullable = false)
    private int value;

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getReal() {
        return real;
    }

    public void setReal(int real) {
        this.real = real;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getMission() {
        return mission;
    }

    public void setMission(int mission) {
        this.mission = mission;
    }

    public int getConsequence() {
        return consequence;
    }

    public void setConsequence(int consequence) {
        this.consequence = consequence;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deepfake that = (Deepfake) o;
        return videoId == that.videoId && real == that.real && mission == that.mission && consequence == that.consequence && value == that.value && Objects.equals(location, that.location) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(videoId, location, real, label, mission, consequence, value);
    }
}

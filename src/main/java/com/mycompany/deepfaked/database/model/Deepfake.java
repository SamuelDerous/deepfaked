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
    @ManyToOne
    @JoinColumn(name = "mission", nullable = false)
    private Mission mission;
    @ManyToOne
    @JoinColumn(name = "consequence", nullable = false)
    private Consequence consequence;
    
    @ManyToOne
    @JoinColumn(name = "value", nullable = false)
    private FeedbackValue value;
    
    @Basic
    @Column(name = "analyst", nullable = true, length = 50)
    private String analyst;
    
    @Basic
    @Column(name = "avatarify", nullable = false)
    private double avatarify;
    
    @Basic
    @Column(name = "deepware", nullable = false)
    private double deepware;
    @Basic
    @Column(name = "seferbekov", nullable = false)
    private double seferbekov;
    @Basic
    @Column(name = "ensemble", nullable = false)
    private double ensemble;
    
    

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

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Consequence getConsequence() {
        return consequence;
    }

    public void setConsequence(Consequence consequence) {
        this.consequence = consequence;
    }

    public FeedbackValue getValue() {
        return value;
    }

    public void setValue(FeedbackValue value) {
        this.value = value;
    }

    public String getAnalyst() {
        return analyst;
    }

    public void setAnalyst(String analyst) {
        this.analyst = analyst;
    }

    public double getAvatarify() {
        return avatarify;
    }

    public void setAvatarify(double avatarify) {
        this.avatarify = avatarify;
    }

    public double getDeepware() {
        return deepware;
    }

    public void setDeepware(double deepware) {
        this.deepware = deepware;
    }

    public double getSeferbekov() {
        return seferbekov;
    }

    public void setSeferbekov(double seferbekov) {
        this.seferbekov = seferbekov;
    }

    public double getEnsemble() {
        return ensemble;
    }

    public void setEnsemble(double ensemble) {
        this.ensemble = ensemble;
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

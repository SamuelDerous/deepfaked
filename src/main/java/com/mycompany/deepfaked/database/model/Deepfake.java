package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Deepfake", schema = "Deepfaked", catalog = "")
public class Deepfake {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "location", nullable = false, length = 100)
    private String location;
    @Basic
    @Column(name = "real", nullable = false)
    private int real;
    @Basic
    @Column(name = "realComment", nullable = false, length = 255)
    private String realComment;
    @Basic
    @Column(name = "fakeComment", nullable = false, length = 255)
    private String fakeComment;
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
    
    

    public int getId() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
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

    public String getRealComment() {
        return realComment;
    }

    public void setRealComment(String realComment) {
        this.realComment = realComment;
    }

    public String getFakeComment() {
        return fakeComment;
    }

    public void setFakeComment(String fakeComment) {
        this.fakeComment = fakeComment;
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
        return id == that.id && real == that.real && mission == that.mission && consequence == that.consequence && value == that.value && Objects.equals(location, that.location) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, location, real, label, mission, consequence, value);
    }
}

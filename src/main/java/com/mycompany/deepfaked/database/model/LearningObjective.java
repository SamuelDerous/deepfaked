package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "LearningObjective", schema = "Deepfaked", catalog = "")
public class LearningObjective {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "learningId", nullable = false)
    private int learningId;
    @Basic
    @Column(name = "label", nullable = true, length = -1)
    private String label;
    @Basic
    @Column(name = "subject", nullable = false)
    private int subject;
    @Basic
    @Column(name = "bloomLevel", nullable = false, length = 50)
    private String bloomLevel;
    @Basic
    @Column(name = "keywords", nullable = true, length = 200)
    private String keywords;

    public int getLearningId() {
        return learningId;
    }

    public void setLearningId(int learningId) {
        this.learningId = learningId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getSubject() {
        return subject;
    }

    public void setSubject(int subject) {
        this.subject = subject;
    }

    public String getBloomLevel() {
        return bloomLevel;
    }

    public void setBloomLevel(String bloomLevel) {
        this.bloomLevel = bloomLevel;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LearningObjective that = (LearningObjective) o;
        return learningId == that.learningId && subject == that.subject && Objects.equals(label, that.label) && Objects.equals(bloomLevel, that.bloomLevel) && Objects.equals(keywords, that.keywords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(learningId, label, subject, bloomLevel, keywords);
    }
}

package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Subject", schema = "Deepfaked", catalog = "")
public class Subject {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "subjectId", nullable = false)
    private int subjectId;
    @Basic
    @Column(name = "title", nullable = false, length = 55)
    private String title;
    @Basic
    @Column(name = "description", nullable = true, length = 200)
    private String description;

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject that = (Subject) o;
        return subjectId == that.subjectId && Objects.equals(title, that.title) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, title, description);
    }
}

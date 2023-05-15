package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Consequence", schema = "Deepfaked", catalog = "")
public class Consequence {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "feedback", nullable = false, length = -1)
    private String feedback;
    @Basic
    @Column(name = "readMore", nullable = true, length = 255)
    private String readMore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getReadMore() {
        return readMore;
    }

    public void setReadMore(String readMore) {
        this.readMore = readMore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consequence that = (Consequence) o;
        return id == that.id && Objects.equals(feedback, that.feedback) && Objects.equals(readMore, that.readMore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedback, readMore);
    }
}

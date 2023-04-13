package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ProgressDeepfake", schema = "Deepfaked", catalog = "")
public class ProgressDeepfake {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "progressId", nullable = false)
    private int progressId;
    @Basic
    @Column(name = "gamer", nullable = false)
    private int gamer;
    @Basic
    @Column(name = "deepfake", nullable = false)
    private int deepfake;

    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getGamer() {
        return gamer;
    }

    public void setGamer(int gamer) {
        this.gamer = gamer;
    }

    public int getDeepfake() {
        return deepfake;
    }

    public void setDeepfake(int deepfake) {
        this.deepfake = deepfake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressDeepfake that = (ProgressDeepfake) o;
        return progressId == that.progressId && gamer == that.gamer && deepfake == that.deepfake;
    }

    @Override
    public int hashCode() {
        return Objects.hash(progressId, gamer, deepfake);
    }
}

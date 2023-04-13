package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ProgressMission", schema = "Deepfaked", catalog = "")
public class ProgressMission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "progressId", nullable = false)
    private int progressId;
    @Basic
    @Column(name = "gamer", nullable = false)
    private int gamer;
    @Basic
    @Column(name = "mission", nullable = false)
    private int mission;

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

    public int getMission() {
        return mission;
    }

    public void setMission(int mission) {
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressMission that = (ProgressMission) o;
        return progressId == that.progressId && gamer == that.gamer && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(progressId, gamer, mission);
    }
}

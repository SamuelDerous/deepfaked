package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "SaveSession", schema = "Deepfaked", catalog = "")
public class SaveSession {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "duration", nullable = false)
    private Time duration;
    @Basic
    @Column(name = "gamer", nullable = false)
    private int gamer;
    @Basic
    @Column(name = "mission", nullable = false)
    private int mission;
    @Basic
    @Column(name = "task", nullable = false)
    private int task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
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

    public int getTask() {
        return task;
    }

    public void setTask(int task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaveSession that = (SaveSession) o;
        return id == that.id && gamer == that.gamer && mission == that.mission && task == that.task && Objects.equals(duration, that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, gamer, mission, task);
    }
}

package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ProgressMission", schema = "Deepfaked", catalog = "")
public class ProgressMission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "gamer", nullable = false)
    private Gamer gamer;
    @ManyToOne
    @JoinColumn(name = "mission", nullable = false)
    private Mission mission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Gamer getGamer() {
        return gamer;
    }

    public void setGamer(Gamer gamer) {
        this.gamer = gamer;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressMission that = (ProgressMission) o;
        return id == that.id && gamer == that.gamer && mission == that.mission;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gamer, mission);
    }
}

package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Mission", schema = "Deepfaked", catalog = "")
public class Mission {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "missionId", nullable = false)
    private int missionId;
    @Basic
    @Column(name = "name", nullable = false, length = 55)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "introduction", nullable = false, length = -1)
    private String introduction;
    @Basic
    @Column(name = "goal", nullable = false)
    private int goal;

    public int getMissionId() {
        return missionId;
    }

    public void setMissionId(int missionId) {
        this.missionId = missionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission that = (Mission) o;
        return missionId == that.missionId && goal == that.goal && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionId, name, description, introduction, goal);
    }
}

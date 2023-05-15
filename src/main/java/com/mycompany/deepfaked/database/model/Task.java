package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;
import java.util.Iterator;

import java.util.Objects;

@Entity
@Table(name = "Task", schema = "Deepfaked", catalog = "")
public class Task extends GameComposite {
    
    @Basic
    @Column(name = "goal", nullable = false)
    private int goal;
    @Basic
    @Column(name = "learningObjective", nullable = false)
    private int learningObjective;
    @Basic
    @Column(name = "video", nullable = false)
    private int video;
    
    public Task() {
        
    }
    
    public Task(Task task) {
        setId(task.getId());
        setName(task.getName());
        setDescription(task.getDescription());
        goal = task.getGoal();
        learningObjective = task.getLearningObjective();
        video = task.getVideo();
    }
    
    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getLearningObjective() {
        return learningObjective;
    }

    public void setLearningObjective(int learningObjective) {
        this.learningObjective = learningObjective;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return getId() == that.getId() && goal == that.goal && learningObjective == that.learningObjective && video == that.video && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), goal, learningObjective, video);
    }

    @Override
    public Iterator<? extends GameComponent> createIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public GameComponent getchild(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;
import java.util.Iterator;

import java.util.Objects;

@Entity
@Table(name = "Task", schema = "Deepfaked", catalog = "")
public class Task {
    @GeneratedValue(strategy = GenerationType.TABLE, generator="GameCompositeGenerator")
    @TableGenerator(table="SEQUENCES", name="GameCompositeGenerator")
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = 255)
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "goal", referencedColumnName = "id", nullable=false)
    private Goal goal;
    @ManyToOne
    @JoinColumn(name = "learningObjective", referencedColumnName = "id", nullable=false)
    private LearningObjective learningObjective;
    @ManyToOne
    @JoinColumn(name = "video", referencedColumnName = "id", nullable=false)
    private Deepfake video;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public Task() {
        
    }
    
    public Task(Task task) {
        id = task.getId();
        name = task.getName();
        description = task.getDescription();
        goal = task.getGoal();
        learningObjective = task.getLearningObjective();
        video = task.getVideo();
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public LearningObjective getLearningObjective() {
        return learningObjective;
    }

    public void setLearningObjective(LearningObjective learningObjective) {
        this.learningObjective = learningObjective;
    }

    public Deepfake getVideo() {
        return video;
    }

    public void setVideo(Deepfake video) {
        this.video = video;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task that = (Task) o;
        return getId() == that.getId() && goal.equals(that.goal) && learningObjective.equals(that.learningObjective) && video.equals(that.video) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), goal, learningObjective, video);
    }
}

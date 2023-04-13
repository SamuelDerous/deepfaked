package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Task", schema = "Deepfaked", catalog = "")
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "taskId", nullable = false)
    private int taskId;
    @Basic
    @Column(name = "name", nullable = true, length = 50)
    private String name;
    @Basic
    @Column(name = "text", nullable = false, length = 255)
    private String text;
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
        taskId = task.getTaskId();
        name = task.getName();
        text = task.getText();
        goal = task.getGoal();
        learningObjective = task.getLearningObjective();
        video = task.getVideo();
    }
    
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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
        return taskId == that.taskId && goal == that.goal && learningObjective == that.learningObjective && video == that.video && Objects.equals(name, that.name) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, name, text, goal, learningObjective, video);
    }
}

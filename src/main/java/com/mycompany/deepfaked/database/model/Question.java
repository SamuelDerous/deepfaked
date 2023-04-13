package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Question", schema = "Deepfaked", catalog = "")
public class Question {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "questionId", nullable = false)
    private int questionId;
    @Basic
    @Column(name = "question", nullable = false, length = 255)
    private String question;
    @Basic
    @Column(name = "learningObjective", nullable = false)
    private int learningObjective;
    @Basic
    @Column(name = "video", nullable = false)
    private int video;
    @Basic
    @Column(name = "level", nullable = false)
    private int level;
    @Basic
    @Column(name = "multi", nullable = false)
    private int multi;
    @Basic
    @Column(name = "value", nullable = false)
    private int value;
    
    /*@ManyToMany(fetch = FetchType.EAGER)
    //@JoinTable(name="QuestionChoice")
    @JoinTable(name = "QuestionChoice",
        joinColumns = {
            @JoinColumn(name = "question"),
            //@JoinColumn(name = "choiceId")
        }
    )
    private Set<QuestionChoice> choices;*/

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getMulti() {
        return multi;
    }

    public void setMulti(int multi) {
        this.multi = multi;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question that = (Question) o;
        return questionId == that.questionId && learningObjective == that.learningObjective && video == that.video && level == that.level && multi == that.multi && value == that.value && Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, question, learningObjective, video, level, multi, value);
    }
}

package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "QuestionChoice", schema = "Deepfaked", catalog = "")
@IdClass(QuestionChoicePK.class)
public class QuestionChoice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    //@Column(name = "choice", nullable = false)
    @ManyToOne
    @JoinColumn(name = "choice", nullable = false)
    private Choice choice;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    //@Column(name = "question", nullable = false)
    @ManyToOne
    @JoinColumn(name = "question", nullable = false)
    private Question question;
    @Basic
    @Column(name = "correct", nullable = false)
    private int correct;

    public Choice getChoice() {
        return choice;
    }

    public void setChoice(Choice choice) {
        this.choice = choice;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public int getCorrect() {
        return correct;
    }

    public void setCorrect(int correct) {
        this.correct = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionChoice that = (QuestionChoice) o;
        return choice == that.choice && question == that.question && correct == that.correct;
    }

    @Override
    public int hashCode() {
        return Objects.hash(choice, question, correct);
    }
}

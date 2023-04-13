package com.mycompany.deepfaked.database.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import java.io.Serializable;
import java.util.Objects;

public class QuestionChoicePK implements Serializable {
    //@Column(name = "choice", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name="choice", nullable = false)
    private Choice choice;
    //@Column(name = "question", nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne
    @JoinColumn(name="question", nullable = false)
    private Question question;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionChoicePK that = (QuestionChoicePK) o;
        return choice == that.choice && question == that.question;
    }

    @Override
    public int hashCode() {
        return Objects.hash(choice, question);
    }
}

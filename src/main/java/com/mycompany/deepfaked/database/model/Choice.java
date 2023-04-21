package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Choice", schema = "Deepfaked", catalog = "")
public class Choice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "choiceId", nullable = false)
    private int choiceId;
    @Basic
    @Column(name = "answer", nullable = false, length = -1)
    private String answer;
    @ManyToOne
    @JoinColumn(name = "consequence", nullable = false)
    private Consequence consequence;
    
    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Consequence getConsequence() {
        return consequence;
    }

    public void setConsequence(Consequence consequence) {
        this.consequence = consequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Choice that = (Choice) o;
        return choiceId == that.choiceId && consequence == that.consequence && Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(choiceId, answer, consequence);
    }
}

package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ProgressDeepfake", schema = "Deepfaked", catalog = "")
public class ProgressDeepfake {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "gamer", nullable = false)
    private Gamer gamer;
    @ManyToOne
    @JoinColumn(name = "deepfake", nullable = false)
    private Deepfake deepfake;

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

    public Deepfake getDeepfake() {
        return deepfake;
    }

    public void setDeepfake(Deepfake deepfake) {
        this.deepfake = deepfake;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProgressDeepfake that = (ProgressDeepfake) o;
        return id == that.id && gamer == that.gamer && deepfake == that.deepfake;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gamer, deepfake);
    }
}

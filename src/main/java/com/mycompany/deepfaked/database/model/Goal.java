package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;
import java.util.Iterator;

import java.util.Objects;

@Entity
@Table(name = "Goal", schema = "Deepfaked", catalog = "")
public class Goal {
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
    
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Goal that = (Goal) o;
        return getId() == that.getId() && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription());
    }

    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
    }

       
}

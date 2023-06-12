package com.mycompany.deepfaked.database.model;

import com.mycompany.deepfaked.database.dao.MissionsDao;
import jakarta.persistence.*;
import java.util.Iterator;

import java.util.Objects;

@Entity
@Table(name = "Mission", schema = "Deepfaked", catalog = "")
public class Mission {
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
    
    @Basic
    @Column(name = "introduction", nullable = false, length = -1)
    private String introduction;
    @ManyToOne
    @JoinColumn(name = "goal", referencedColumnName = "id", nullable=false)
    private Goal goal;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission that = (Mission) o;
        return getId() == that.getId() && goal.equals(that.goal) && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), introduction, goal);
    }
}

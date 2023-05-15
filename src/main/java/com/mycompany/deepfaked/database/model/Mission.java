package com.mycompany.deepfaked.database.model;

import com.mycompany.deepfaked.database.dao.MissionsDao;
import jakarta.persistence.*;
import java.util.Iterator;

import java.util.Objects;

@Entity
@Table(name = "Mission", schema = "Deepfaked", catalog = "")
public class Mission extends GameComposite {
    @Basic
    @Column(name = "introduction", nullable = false, length = -1)
    private String introduction;
    @Basic
    @Column(name = "goal", nullable = false)
    private int goal;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mission that = (Mission) o;
        return getId() == that.getId() && goal == that.goal && Objects.equals(getName(), that.getName()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(introduction, that.introduction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), introduction, goal);
    }

    @Override
    public GameComponent getchild(int index) {
        return MissionsDao.getMission(index);
    }

    @Override
    public Iterator<? extends GameComponent> createIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
}

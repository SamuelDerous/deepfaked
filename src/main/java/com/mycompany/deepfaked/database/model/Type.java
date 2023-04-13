package com.mycompany.deepfaked.database.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Type", schema = "Deepfaked", catalog = "")
public class Type {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "typeId", nullable = false)
    private int typeId;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type that = (Type) o;
        return typeId == that.typeId && Objects.equals(name, that.name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeId, name, description);
    }
}

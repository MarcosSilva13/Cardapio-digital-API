package com.digitalmenu.digitalmenuapi.entities;

import com.digitalmenu.digitalmenuapi.dtos.CategoryRequestDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    public Category() {
    }

    public Category(CategoryRequestDTO requestDTO) {
        this.name = requestDTO.name();
    }

    public Category(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

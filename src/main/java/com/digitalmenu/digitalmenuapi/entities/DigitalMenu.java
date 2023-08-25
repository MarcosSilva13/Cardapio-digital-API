package com.digitalmenu.digitalmenuapi.entities;

import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuRequestDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_digital_menu")
public class DigitalMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String ingredients;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public DigitalMenu() {
    }

    public DigitalMenu(DigitalMenuRequestDTO requestDTO) {
        this.name = requestDTO.name();
        this.price = requestDTO.price();
        this.image = requestDTO.image();
        this.ingredients = requestDTO.ingredients();
        this.category = new Category(requestDTO.categoryId());
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
       this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalMenu that = (DigitalMenu) o;
        return Objects.equals(name, that.name) && Objects.equals(price, that.price)
                && Objects.equals(image, that.image) && Objects.equals(ingredients, that.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, image, ingredients);
    }
}

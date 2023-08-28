package com.digitalmenu.digitalmenuapi.entities;

import com.digitalmenu.digitalmenuapi.dtos.ItemRequestDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_item")
public class Item {
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

    public Item() {
    }

    public Item(ItemRequestDTO requestDTO) {
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
        if (!(o instanceof Item item)) return false;
        return Objects.equals(id, item.id) && Objects.equals(name, item.name) && Objects.equals(price, item.price)
                && Objects.equals(image, item.image) && Objects.equals(ingredients, item.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, ingredients);
    }
}

package com.digitalmenu.api.dtos;

import com.digitalmenu.api.entities.Item;

public record ItemResponseDTO(String id, String name, Double price, String image, String ingredients,
                              CategoryResponseDTO category) {

    public ItemResponseDTO(Item item) {
        this(item.getId(), item.getName(), item.getPrice(), item.getImage(),
                item.getIngredients(), new CategoryResponseDTO(item.getCategory()));
    }
}

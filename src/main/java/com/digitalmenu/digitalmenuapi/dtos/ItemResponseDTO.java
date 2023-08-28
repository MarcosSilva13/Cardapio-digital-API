package com.digitalmenu.digitalmenuapi.dtos;

import com.digitalmenu.digitalmenuapi.entities.Item;

public record ItemResponseDTO(String id, String name, Double price, String image, String ingredients,
                              CategoryResponseDTO category) {

    public ItemResponseDTO(Item item) {
        this(item.getId(), item.getName(), item.getPrice(), item.getImage(),
                item.getIngredients(), new CategoryResponseDTO(item.getCategory()));
    }
}

package com.digitalmenu.digitalmenuapi.dtos;

import com.digitalmenu.digitalmenuapi.entities.DigitalMenu;

public record DigitalMenuResponseDTO(String id, String name, Double price, String image, String ingredients) {

    public DigitalMenuResponseDTO(DigitalMenu digitalMenu) {
        this(digitalMenu.getId(), digitalMenu.getName(), digitalMenu.getPrice(), digitalMenu.getImage(),
                digitalMenu.getIngredients());
    }
}
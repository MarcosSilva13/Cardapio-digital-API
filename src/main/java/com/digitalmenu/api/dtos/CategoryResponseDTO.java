package com.digitalmenu.api.dtos;

import com.digitalmenu.api.entities.Category;

public record CategoryResponseDTO(String id, String name) {

        public CategoryResponseDTO(Category category){
                this(category.getId(), category.getName());
        }
}

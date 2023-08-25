package com.digitalmenu.digitalmenuapi.dtos;

import com.digitalmenu.digitalmenuapi.entities.Category;

public record CategoryResponseDTO(String id, String name) {

        public CategoryResponseDTO(Category category){
                this(category.getId(), category.getName());
        }
}

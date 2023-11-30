package com.digitalmenu.api.mapper;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryRequestDTO requestDTO);

    CategoryResponseDTO toCategoryResponseDTO(Category category);

    void toUpdateCategory(CategoryRequestDTO requestDTO, @MappingTarget Category category);
}

package com.digitalmenu.digitalmenuapi.services;

import com.digitalmenu.digitalmenuapi.dtos.CategoryRequestDTO;
import com.digitalmenu.digitalmenuapi.dtos.CategoryResponseDTO;
import com.digitalmenu.digitalmenuapi.entities.Category;
import com.digitalmenu.digitalmenuapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<CategoryResponseDTO> getAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CategoryResponseDTO::new)
                .toList();
    }

    @Transactional
    public CategoryResponseDTO save(CategoryRequestDTO requestDTO) {
        Category category = new Category(requestDTO);

        return new CategoryResponseDTO(categoryRepository.save(category));
    }

}

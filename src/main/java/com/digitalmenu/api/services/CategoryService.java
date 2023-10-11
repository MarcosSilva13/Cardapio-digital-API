package com.digitalmenu.api.services;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.exceptions.CategoryNotFoundException;
import com.digitalmenu.api.repositories.CategoryRepository;
import org.springframework.beans.BeanUtils;
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

    @Transactional
    public CategoryResponseDTO update(String id, CategoryRequestDTO requestDTO) {
        Category category = this.find(id);

        BeanUtils.copyProperties(requestDTO, category);

        return new CategoryResponseDTO(categoryRepository.save(category));
    }

    @Transactional
    public void delete(String id) {
        Category category = this.find(id);

        categoryRepository.delete(category);
    }

    private Category find(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada."));
    }
}

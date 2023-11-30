package com.digitalmenu.api.services;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.exceptions.CategoryNotFoundException;
import com.digitalmenu.api.mapper.CategoryMapper;
import com.digitalmenu.api.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
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
        Category category = categoryMapper.toCategory(requestDTO);

        return categoryMapper.toCategoryResponseDTO(categoryRepository.save(category));
    }

    @Transactional
    public CategoryResponseDTO update(String id, CategoryRequestDTO requestDTO) {
        Category category = this.find(id);

        categoryMapper.toUpdateCategory(requestDTO, category);

        return categoryMapper.toCategoryResponseDTO(categoryRepository.save(category));
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

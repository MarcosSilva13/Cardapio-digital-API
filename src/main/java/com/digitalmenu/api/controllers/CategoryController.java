package com.digitalmenu.api.controllers;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategory() {
        return ResponseEntity.ok().body(categoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> saveCategory(@RequestBody @Valid CategoryRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(requestDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable String id,
                                                              @RequestBody @Valid CategoryRequestDTO requestDTO) {
        return ResponseEntity.ok().body(categoryService.update(id,requestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        categoryService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.digitalmenu.digitalmenuapi.controllers;

import com.digitalmenu.digitalmenuapi.dtos.CategoryRequestDTO;
import com.digitalmenu.digitalmenuapi.dtos.CategoryResponseDTO;
import com.digitalmenu.digitalmenuapi.services.CategoryService;
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
}

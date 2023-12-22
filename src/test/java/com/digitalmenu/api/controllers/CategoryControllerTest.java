package com.digitalmenu.api.controllers;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.services.CategoryService;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryService categoryService;

    private CategoryResponseDTO response;

    private CategoryRequestDTO request;

    @BeforeEach
    void setUp() {
        request = new CategoryRequestDTO("Categoria 1");
        response = new CategoryResponseDTO("abcd", "Categoria 1");

    }

    @Test
    @DisplayName("SaveCategory returns CategoryResponseDTO when successful")
    void saveCategoryReturnsCategoryResponseDTOWhenSuccessful() {
        when(categoryService.save(request)).thenReturn(response);

        ResponseEntity<CategoryResponseDTO> category = categoryController.saveCategory(request);

        Assertions.assertThat(category).isNotNull();

        Assertions.assertThat(category.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(category.getBody().name()).isEqualTo(request.name());

        verify(categoryService, times(1)).save(request);
    }

    @Test
    @DisplayName("GetAllCategory returns list of CategoryResponseDTO when successful")
    void getAllCategoryReturnsListofCategoryResponseDTOWhenSuccessful() {
        when(categoryService.getAll()).thenReturn(Collections.singletonList(response));

        ResponseEntity<List<CategoryResponseDTO>> categories = categoryController.getAllCategory();

        Assertions.assertThat(categories.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(categories.getBody()).isNotEmpty().isNotNull().hasSize(1);

        Assertions.assertThat(categories.getBody().get(0).name()).isEqualTo(request.name());

        verify(categoryService, times(1)).getAll();
    }

    @Test
    @DisplayName("UpdateCategory return CategoryResponseDTO when successful")
    void updateCategoryReturnCategoryResponseDTOWhenSuccessful() {
        when(categoryService.save(request)).thenReturn(response);

        var categorySaved = categoryService.save(request);

        var requestToUpdate = new CategoryRequestDTO("Categoria 2");

        var responseUpdated = new CategoryResponseDTO(categorySaved.id(), "Categoria 2");

        when(categoryService.update(categorySaved.id(), requestToUpdate))
                .thenReturn(responseUpdated);

        ResponseEntity<CategoryResponseDTO> categoryResponseUpdated = categoryController.updateCategory(categorySaved.id(),
                requestToUpdate);

        Assertions.assertThat(categoryResponseUpdated.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(categoryResponseUpdated.getBody()).isNotNull();

        Assertions.assertThat(categoryResponseUpdated.getBody().id()).isEqualTo(categorySaved.id());

        Assertions.assertThat(categoryResponseUpdated.getBody().name()).isEqualTo(requestToUpdate.name());

        verify(categoryService, times(1)).update(categorySaved.id(), requestToUpdate);
    }

    @Test
    @DisplayName("DeleteCategory removes category when successful")
    void deleteCategoryRemovesCategoryWhenSuccessful() {
        when(categoryService.save(request)).thenReturn(response);

        var categorySaved = categoryService.save(request);

        doNothing().when(categoryService).delete(categorySaved.id());

        ResponseEntity<Void> entity = categoryController.deleteCategory(categorySaved.id());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        verify(categoryService, times(1)).delete(categorySaved.id());
    }
}

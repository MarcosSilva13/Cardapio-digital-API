package com.digitalmenu.api.services;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.mapper.CategoryMapper;
import com.digitalmenu.api.repositories.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryMapper categoryMapper;

    private CategoryRequestDTO request;

    private CategoryResponseDTO response;

    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();

        request = new CategoryRequestDTO("Categoria 1");

        category.setId("abcd");
        category.setName(request.name());

        response = new CategoryResponseDTO(category);
    }

    @Test
    @DisplayName("GetAll returns list of CategoryResponseDTO when successful")
    void getAllReturnsListOfCategoryResponseDTOWhenSuccessful() {
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<CategoryResponseDTO> responseDTOS = categoryService.getAll();

        Assertions.assertThat(responseDTOS).isNotEmpty().isNotNull().hasSize(1);

        Assertions.assertThat(responseDTOS.get(0).name()).isEqualTo(response.name());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("GetOne returns CategoryResponseDTO when successful")
    void getOneReturnsCategoryResponseDTOWhenSuccessful() {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        when(categoryMapper.toCategoryResponseDTO(any(Category.class))).thenReturn(response);

        var categoryFound = categoryService.getOne(category.getId());

        Assertions.assertThat(categoryFound).isNotNull();

        Assertions.assertThat(categoryFound.id()).isEqualTo(category.getId());

        Assertions.assertThat(categoryFound.name()).isEqualTo(category.getName());

        verify(categoryRepository, times(1)).findById(category.getId());

        verify(categoryMapper, times(1)).toCategoryResponseDTO(any(Category.class));
    }

    @Test
    @DisplayName("Save returns CategoryResponseDTOWhenSuccessful")
    void saveReturnsCategoryResponseDTOWhenSuccessful() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        when(categoryMapper.toCategory(request)).thenReturn(category);

        when(categoryMapper.toCategoryResponseDTO(category)).thenReturn(response);

        var categoryResponseDTO = categoryService.save(request);

        Assertions.assertThat(categoryResponseDTO).isNotNull().isEqualTo(response);

        verify(categoryRepository,times(1)).save(any(Category.class));

        verify(categoryMapper, times(1)).toCategory(any());

        verify(categoryMapper, times(1)).toCategoryResponseDTO(any());
    }

    @Test
    @DisplayName("Update returns CategoryResponseDTO when successful")
    void updateReturnsCategoryResponseDTOWhenSuccessful() {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        var requestDTO = new CategoryRequestDTO("Categoria 2");

        var responseDTO = new CategoryResponseDTO(category.getId(), "Categoria 2");

        when(categoryMapper.toCategoryResponseDTO(any(Category.class))).thenReturn(responseDTO);

        var categoryResponseDTO = categoryService.update(category.getId(), requestDTO);

        Assertions.assertThat(categoryResponseDTO).isNotNull();

        Assertions.assertThat(categoryResponseDTO.id()).isEqualTo(responseDTO.id());

        Assertions.assertThat(categoryResponseDTO.name()).isEqualTo(responseDTO.name());

        verify(categoryRepository, times(1)).save(any(Category.class));

        verify(categoryRepository, times(1)).findById(category.getId());

        verify(categoryMapper, times(1)).toCategoryResponseDTO(any(Category.class));
    }

    @Test
    @DisplayName("Update throws EntityNotFoundException when category not found")
    void updateThrowsEntityNotFoundExceptionWhenCategoryNotFound() {
        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> categoryService.update(null,null))
                .withMessageContaining("Categoria não encontrada.");
    }

    @Test
    @DisplayName("Delete removes category when successful")
    void deleteRemovesCategoryWhenSuccessful() {
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Assertions.assertThatCode(() -> categoryService.delete(category.getId()))
                .doesNotThrowAnyException();

        verify(categoryRepository,times(1)).findById(anyString());

        verify(categoryRepository, times(1)).delete(any());

    }
}

package com.digitalmenu.api.repositories;

import com.digitalmenu.api.entities.Category;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        this.category = new Category();
        category.setName("Hamburgues");
    }

    @Test
    @DisplayName("Save persist category when successful")
    void savePersistCategoryWhenSuccessful() {
        Category categorySaved = this.categoryRepository.save(category);

        Assertions.assertThat(categorySaved).isNotNull();

        Assertions.assertThat(categorySaved.getId()).isNotNull();

        Assertions.assertThat(categorySaved.getName()).isEqualTo(category.getName());
    }

    @Test
    @DisplayName("Save update category when successful")
    void saveUpdateCategoryWhenSuccessful() {
        Category categorySaved = this.categoryRepository.save(category);

        categorySaved.setName("Pizzas");

        Category categoryUpdated = this.categoryRepository.save(categorySaved);

        Assertions.assertThat(categoryUpdated).isNotNull();

        Assertions.assertThat(categoryUpdated.getId()).isNotNull();

        Assertions.assertThat(categoryUpdated.getName()).isEqualTo(categorySaved.getName());
    }

    @Test
    @DisplayName("Delete removes category when successful")
    void deleteRemovesCategoryWhenSuccessful() {
        Category categorySaved = this.categoryRepository.save(category);

        this.categoryRepository.delete(categorySaved);

        Optional<Category> categoryOptional = this.categoryRepository.findById(categorySaved.getId());

        Assertions.assertThat(categoryOptional).isEmpty();
    }

    @Test
    @DisplayName(("FindById returns category when successful"))
    void findByIdReturnCategoryWhenSuccessful() {
        Category categorySaved = this.categoryRepository.save(category);

        Optional<Category> categoryFound = this.categoryRepository.findById(categorySaved.getId());

        Assertions.assertThat(categoryFound).isNotEmpty();
        Assertions.assertThat(categoryFound.get().getId()).isEqualTo(categorySaved.getId());
    }

    @Test
    @DisplayName("FindAll returns list of category when successful")
    void findAllReturnListOfCategoryWhenSuccessful() {
        Category categorySaved = this.categoryRepository.save(category);

        List<Category> categories = this.categoryRepository.findAll();

        Assertions.assertThat(categories).isNotEmpty().contains(categorySaved);
    }
}

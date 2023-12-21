package com.digitalmenu.api.repositories;

import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.entities.Item;
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
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Item item;

    @BeforeEach
    void setUp() {
        var category = new Category();
        category.setName("Hamburgues");
        var categorySaved = categoryRepository.save(category);

        item = new Item();
        item.setName("X Tudo");
        item.setPrice(20D);
        item.setImage("imagem");
        item.setIngredients("Pão, Batata, Bife, Tomate, Alface");
        item.setCategory(categorySaved);

    }

    @Test
    @DisplayName("Save persist item when successful")
    void savePersistItemWhenSuccessful() {
        Item itemSaved = this.itemRepository.save(item);

        Assertions.assertThat(itemSaved).isNotNull();

        Assertions.assertThat(itemSaved.getId()).isNotNull();

        Assertions.assertThat(itemSaved.getName()).isEqualTo(item.getName());

        Assertions.assertThat(itemSaved.getCategory()).isEqualTo(item.getCategory());
    }

    @Test
    @DisplayName("Save update item when successful")
    void saveUpdateItemWhenSuccessful() {
        Item itemSaved = this.itemRepository.save(item);

        itemSaved.setName("X Picanha");
        itemSaved.setPrice(25D);
        itemSaved.setImage("imagem 2");
        itemSaved.setIngredients("Pão, Bife, Milho, Alface");

        Item itemUpdated = this.itemRepository.save(itemSaved);

        Assertions.assertThat(itemUpdated).isNotNull();

        Assertions.assertThat(itemUpdated.getId()).isNotNull();

        Assertions.assertThat(itemUpdated.getName()).isEqualTo(itemSaved.getName());

        Assertions.assertThat(itemUpdated.getPrice()).isEqualTo(itemSaved.getPrice());

        Assertions.assertThat(itemUpdated.getImage()).isEqualTo(itemSaved.getImage());

        Assertions.assertThat(itemUpdated.getIngredients()).isEqualTo(itemSaved.getIngredients());
    }

    @Test
    @DisplayName("Delete removes item when successful")
    void deleteRemovesItemWhenSuccessful() {
        Item itemSaved = this.itemRepository.save(item);

        itemRepository.delete(itemSaved);

        Optional<Item> itemOptional = this.itemRepository.findById(itemSaved.getId());

        Assertions.assertThat(itemOptional).isEmpty();
    }

    @Test
    @DisplayName("FindById returns item when successful")
    void findByIdReturnsItemWhenSuccessful() {
        Item itemSaved = this.itemRepository.save(item);

        Optional<Item> itemFound = this.itemRepository.findById(itemSaved.getId());

        Assertions.assertThat(itemFound).isNotEmpty();

        Assertions.assertThat(itemFound.get().getId()).isEqualTo(itemSaved.getId());
    }

    @Test
    @DisplayName("SearchAll returns list of items when successful")
    void searchAllReturnListOfItemsWhenSuccessful() {
        Item itemSaved = this.itemRepository.save(item);

        List<Item> items = this.itemRepository.searchAll();

        Assertions.assertThat(items).isNotEmpty().contains(itemSaved);
    }
}

package com.digitalmenu.api.services;

import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.entities.Item;
import com.digitalmenu.api.mapper.ItemMapper;
import com.digitalmenu.api.repositories.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private CategoryService categoryService;

    @Mock
    private ItemMapper itemMapper;

    private ItemRequestDTO requestDTO;

    private ItemResponseDTO responseDTO;

    private CategoryResponseDTO categoryResponseDTO;

    private Item item;

    private Category category;

    @BeforeEach()
    void setUp() {
        category = new Category("qwer", "Categoria 1");
        item = new Item("abcd", "X tudo", 20D, "imagem", "Pão, Milho, Batata, Bife", category);
        categoryResponseDTO = new CategoryResponseDTO(category);

        requestDTO = new ItemRequestDTO("X tudo", 20D, "imagem", "Pão, Milho, Batata, Bife", "qwer");
        responseDTO = new ItemResponseDTO("abcd","X tudo", 20D, "imagem",
                "Pão, Milho, Batata, Bife", categoryResponseDTO);
    }

    @Test
    @DisplayName("GetAll returns list of ItemResponseDTO when successful")
    void getAllReturnsListOfItemResponseDTOWhenSuccessful() {
        when(itemRepository.searchAll()).thenReturn(List.of(item));

        List<ItemResponseDTO> itemResponseDTOS = itemService.getAll();

        Assertions.assertThat(itemResponseDTOS).isNotEmpty().isNotNull().hasSize(1);

        Assertions.assertThat(itemResponseDTOS.get(0).id()).isEqualTo(item.getId());

        Assertions.assertThat(itemResponseDTOS.get(0).name()).isEqualTo(item.getName());

        Assertions.assertThat(itemResponseDTOS.get(0).price()).isEqualTo(item.getPrice());

        Assertions.assertThat(itemResponseDTOS.get(0).image()).isEqualTo(item.getImage());

        Assertions.assertThat(itemResponseDTOS.get(0).ingredients()).isEqualTo(item.getIngredients());

        Assertions.assertThat(itemResponseDTOS.get(0).category().name()).isEqualTo(item.getCategory().getName());

        verify(itemRepository, times(1)).searchAll();
    }

    @Test
    @DisplayName("GetOne returns ItemResponseDTO when successful")
    void getOneReturnsItemResponseDTOWhenSuccessful() {
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        when(itemMapper.toItemResponseDTO(any(Item.class))).thenReturn(responseDTO);

        var itemFound = itemService.getOne(item.getId());

        Assertions.assertThat(itemFound).isNotNull();

        Assertions.assertThat(itemFound.id()).isEqualTo(item.getId());

        Assertions.assertThat(itemFound.name()).isEqualTo(item.getName());

        Assertions.assertThat(itemFound.price()).isEqualTo(item.getPrice());

        Assertions.assertThat(itemFound.image()).isEqualTo(item.getImage());

        Assertions.assertThat(itemFound.ingredients()).isEqualTo(item.getIngredients());

        Assertions.assertThat(itemFound.category().name()).isEqualTo(item.getCategory().getName());

        verify(itemRepository, times(1)).findById(item.getId());

        verify(itemMapper, times(1)).toItemResponseDTO(item);
    }

    @Test
    @DisplayName("GetOne throws EntityNotFoundException when item not found")
    void getOneThrowsEntityNotFoundExceptionWhenItemNotFound() {
        Assertions.assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> itemService.getOne(null))
                .withMessageContaining("Item não encontrado!");
    }

    @Test
    @DisplayName("Save returns ItemResponseDTO when successful")
    void saveReturnsItemResponseDTOWhenSuccessful() {
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        when(itemMapper.toItem(requestDTO)).thenReturn(item);

        when(itemMapper.toItemResponseDTO(item)).thenReturn(responseDTO);

        when(categoryService.getOne(requestDTO.categoryId())).thenReturn(categoryResponseDTO);

        var itemResponseDTO = itemService.save(requestDTO);

        Assertions.assertThat(itemResponseDTO).isNotNull().isEqualTo(responseDTO);

        verify(itemRepository, times(1)).save(any(Item.class));

        verify(itemMapper, times(1)).toItem(requestDTO);

        verify(itemMapper, times(1)).toItemResponseDTO(item);

        verify(categoryService, times(1)).getOne(requestDTO.categoryId());
    }

    @Test
    @DisplayName("Update returns ItemResponseDTO when successful")
    void updateReturnsItemResponseDTOWhenSuccessful() {
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        when(itemRepository.save(any(Item.class))).thenReturn(item);

        var requestToUpdate = new ItemRequestDTO("X Picanha", 25D, "imagem 2", "Pão, Batata, Bife", "qwer");

        when(categoryService.getOne(requestToUpdate.categoryId())).thenReturn(categoryResponseDTO);

        var responseUpdated = new ItemResponseDTO("abcd","X Picanha", 25D, "imagem 2", "Pão, Batata, Bife", categoryResponseDTO);

        when(itemMapper.toItemResponseDTO(any(Item.class))).thenReturn(responseUpdated);

        var itemResponseDTO = itemService.update(item.getId(), requestToUpdate);

        Assertions.assertThat(itemResponseDTO).isNotNull();

        Assertions.assertThat(itemResponseDTO.id()).isEqualTo(responseUpdated.id());

        Assertions.assertThat(itemResponseDTO.name()).isEqualTo(responseUpdated.name());

        Assertions.assertThat(itemResponseDTO.price()).isEqualTo(responseUpdated.price());

        Assertions.assertThat(itemResponseDTO.image()).isEqualTo(responseUpdated.image());

        Assertions.assertThat(itemResponseDTO.ingredients()).isEqualTo(responseUpdated.ingredients());

        Assertions.assertThat(itemResponseDTO.category().id()).isEqualTo(responseUpdated.category().id());

        Assertions.assertThat(itemResponseDTO.category().name()).isEqualTo(responseUpdated.category().name());

        verify(itemRepository, times(1)).save(any(Item.class));

        verify(itemRepository, times(1)).findById(item.getId());

        verify(categoryService, times(1)).getOne(requestToUpdate.categoryId());

        verify(itemMapper, times(1)).toItemResponseDTO(any(Item.class));
    }

    @Test
    @DisplayName("Delete removes item when successful")
    void deleteRemovesItemWhenSuccessful() {
        when(itemRepository.findById(item.getId())).thenReturn(Optional.of(item));

        Assertions.assertThatCode(() -> itemService.delete(item.getId())).doesNotThrowAnyException();

        verify(itemRepository, times(1)).findById(item.getId());

        verify(itemRepository, times(1)).delete(item);
    }
}

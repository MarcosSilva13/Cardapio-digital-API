package com.digitalmenu.api.controllers;

import com.digitalmenu.api.dtos.CategoryRequestDTO;
import com.digitalmenu.api.dtos.CategoryResponseDTO;
import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.services.ItemService;
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
class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private ItemRequestDTO request;

    private ItemResponseDTO response;

    @BeforeEach
    void setUp() {
        request = new ItemRequestDTO("X tudo", 20D, "imagem", "Bife, Batata", "efgh");
        response = new ItemResponseDTO("abcd", "X tudo", 20D, "imagem", "Bife, Batata",
                new CategoryResponseDTO("efgh", "Categoria 1"));
    }

    @Test
    @DisplayName("SaveItem returns ItemResponseDTO when successful")
    void saveItemReturnsItemResponseDTOWhenSuccessful() {
        when(itemService.save(request)).thenReturn(response);

        ResponseEntity<ItemResponseDTO> item = itemController.saveItem(request);

        Assertions.assertThat(item).isNotNull();

        Assertions.assertThat(item.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Assertions.assertThat(item.getBody().name()).isEqualTo(request.name());

        Assertions.assertThat(item.getBody().price()).isEqualTo(request.price());

        Assertions.assertThat(item.getBody().ingredients()).isEqualTo(request.ingredients());

        Assertions.assertThat(item.getBody().image()).isEqualTo(request.image());

        Assertions.assertThat(item.getBody().category().id()).isEqualTo(request.categoryId());

        verify(itemService, times(1)).save(request);
    }

    @Test
    @DisplayName("GetAllItems return list of ItemResponseDTO when successful")
    void getAllItemsReturnsListOfItemReponseDTOWhenSuccessful() {
        when(itemService.getAll()).thenReturn(Collections.singletonList(response));

        ResponseEntity<List<ItemResponseDTO>> items = itemController.getAllItems();

        Assertions.assertThat(items.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(items.getBody()).isNotEmpty().isNotNull().hasSize(1);

        Assertions.assertThat(items.getBody().get(0).name()).isEqualTo(request.name());

        Assertions.assertThat(items.getBody().get(0).price()).isEqualTo(request.price());

        Assertions.assertThat(items.getBody().get(0).ingredients()).isEqualTo(request.ingredients());

        Assertions.assertThat(items.getBody().get(0).image()).isEqualTo(request.image());

        Assertions.assertThat(items.getBody().get(0).category().id()).isEqualTo(request.categoryId());

        verify(itemService, times(1)).getAll();
    }

    @Test
    @DisplayName("GetOneItem returns ItemResponseDTO when successful")
    void getOneItemReturnsItemResponseDTOWhenSuccessful() {
        when(itemService.save(request)).thenReturn(response);

        var itemSaved = itemService.save(request);

        when(itemService.getOne(itemSaved.id())).thenReturn(response);

        ResponseEntity<ItemResponseDTO> itemFound = itemController.getOneItem(itemSaved.id());

        Assertions.assertThat(itemFound).isNotNull();

        Assertions.assertThat(itemFound.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(itemFound.getBody().id()).isEqualTo(itemSaved.id());

        Assertions.assertThat(itemFound.getBody().name()).isEqualTo(itemSaved.name());

        Assertions.assertThat(itemFound.getBody().price()).isEqualTo(itemSaved.price());

        Assertions.assertThat(itemFound.getBody().image()).isEqualTo(itemSaved.image());

        Assertions.assertThat(itemFound.getBody().ingredients()).isEqualTo(itemSaved.ingredients());

        Assertions.assertThat(itemFound.getBody().category().id()).isEqualTo(itemSaved.category().id());

        verify(itemService, times(1)).getOne(itemSaved.id());

        verify(itemService, times(1)).save(request);
    }

    @Test
    @DisplayName("UpdateItem returns ItemResponseDTO when successful")
    void updateItemReturnsItemResponseDTOWhenSuccessful() {
        when(itemService.save(request)).thenReturn(response);

        var itemSaved = itemService.save(request);

        var requestToUpdate = new ItemRequestDTO("X picanha", 25D, "imagem 2", "Bife, Milho, Pão", "efgh");

        var responseUpdated = new ItemResponseDTO("abcd","X picanha", 25D, "imagem 2", "Bife, Milho, Pão", new CategoryResponseDTO("efgh", "Categoria 1"));

        when(itemService.update(itemSaved.id(), requestToUpdate))
                .thenReturn(responseUpdated);

        ResponseEntity<ItemResponseDTO> itemResponseUpdated = itemController.updateItem("abcd",
                requestToUpdate);

        Assertions.assertThat(itemResponseUpdated.getStatusCode()).isEqualTo(HttpStatus.OK);

        Assertions.assertThat(itemResponseUpdated.getBody()).isNotNull();

        Assertions.assertThat(itemResponseUpdated.getBody().id()).isEqualTo(responseUpdated.id());

        Assertions.assertThat(itemResponseUpdated.getBody().name()).isEqualTo(requestToUpdate.name());

        Assertions.assertThat(itemResponseUpdated.getBody().price()).isEqualTo(requestToUpdate.price());

        Assertions.assertThat(itemResponseUpdated.getBody().ingredients()).isEqualTo(requestToUpdate.ingredients());

        Assertions.assertThat(itemResponseUpdated.getBody().image()).isEqualTo(requestToUpdate.image());

        Assertions.assertThat(itemResponseUpdated.getBody().category().id()).isEqualTo(requestToUpdate.categoryId());

        verify(itemService, times(1)).update(itemSaved.id(), requestToUpdate);
    }

    @Test
    @DisplayName("DeleteItem removes item when successful")
    void deleteItemRemovesItemWhenSuccessful() {
        when(itemService.save(request)).thenReturn(response);

        var itemSaved = itemService.save(request);

        doNothing().when(itemService).delete(itemSaved.id());

        ResponseEntity<Void> entity = itemController.deleteItem(itemSaved.id());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        verify(itemService, times(1)).delete(itemSaved.id());
    }
}

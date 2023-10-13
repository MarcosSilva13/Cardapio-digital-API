package com.digitalmenu.api.controllers;

import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.services.ItemService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAll());
    }

    @PostMapping
    public ResponseEntity<ItemResponseDTO> saveItem(@RequestBody @Valid ItemRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(requestDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable String id,
                                                             @RequestBody @Valid ItemRequestDTO requestDTO) {
        return ResponseEntity.ok().body(itemService.update(id, requestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

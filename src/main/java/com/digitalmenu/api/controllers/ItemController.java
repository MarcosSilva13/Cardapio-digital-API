package com.digitalmenu.api.controllers;

import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.services.ItemService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/item")
@Tag(name = "Item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }


    @Operation(summary = "Get All Items", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json"))
    })
    @GetMapping
    public ResponseEntity<List<ItemResponseDTO>> getAllItems() {
        return ResponseEntity.ok().body(itemService.getAll());
    }

    @Operation(summary = "Get One Item", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(mediaType = "application/json"))
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<ItemResponseDTO> getOneItem(@PathVariable String id) {
        return ResponseEntity.ok().body(itemService.getOne(id));
    }

    @Operation(summary = "Save item", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json"))
    })
    @PostMapping
    public ResponseEntity<ItemResponseDTO> saveItem(@RequestBody @Valid ItemRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.save(requestDTO));
    }

    @Operation(summary = "Update item", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(mediaType = "application/json"))
    })
    @PutMapping(value = "/{id}")
    public ResponseEntity<ItemResponseDTO> updateItem(@PathVariable String id,
                                                             @RequestBody @Valid ItemRequestDTO requestDTO) {
        return ResponseEntity.ok().body(itemService.update(id, requestDTO));
    }

    @Operation(summary = "Delete item", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable String id) {
        itemService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

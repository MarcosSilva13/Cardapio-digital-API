package com.digitalmenu.digitalmenuapi.controllers;

import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuRequestDTO;
import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuResponseDTO;
import com.digitalmenu.digitalmenuapi.services.DigitalMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/menu")
public class DigitalMenuController {

    private final DigitalMenuService digitalMenuService;

    public DigitalMenuController(DigitalMenuService digitalMenuService) {
        this.digitalMenuService = digitalMenuService;
    }

    @GetMapping
    public ResponseEntity<List<DigitalMenuResponseDTO>> getAllDigitalMenu() {
        return ResponseEntity.ok().body(digitalMenuService.getAll());
    }

    @PostMapping
    public ResponseEntity<DigitalMenuResponseDTO> saveDigitalMenu(@RequestBody DigitalMenuRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(digitalMenuService.save(requestDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DigitalMenuResponseDTO> updateDigitalMenu(@PathVariable String id,
                                                                    @RequestBody DigitalMenuRequestDTO requestDTO) {
        return ResponseEntity.ok().body(digitalMenuService.update(id, requestDTO));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteDigitalMenu(@PathVariable String id) {
        digitalMenuService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package com.digitalmenu.digitalmenuapi.services;

import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuRequestDTO;
import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuResponseDTO;
import com.digitalmenu.digitalmenuapi.entities.DigitalMenu;
import com.digitalmenu.digitalmenuapi.repositories.DigitalMenuRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DigitalMenuService {

    private final DigitalMenuRepository digitalMenuRepository;

    public DigitalMenuService(DigitalMenuRepository digitalMenuRepository) {
        this.digitalMenuRepository = digitalMenuRepository;
    }

    @Transactional
    public List<DigitalMenuResponseDTO> getAll() {
        return digitalMenuRepository.findAll()
                .stream()
                .map(DigitalMenuResponseDTO::new)
                .toList();
    }

    @Transactional
    public DigitalMenuResponseDTO save(DigitalMenuRequestDTO requestDTO) {
        DigitalMenu digitalMenu = new DigitalMenu(requestDTO);

        DigitalMenu digitalMenuSaved = digitalMenuRepository.save(digitalMenu);

        return new DigitalMenuResponseDTO(digitalMenuSaved);
    }
}

package com.digitalmenu.digitalmenuapi.services;

import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuRequestDTO;
import com.digitalmenu.digitalmenuapi.dtos.DigitalMenuResponseDTO;
import com.digitalmenu.digitalmenuapi.entities.DigitalMenu;
import com.digitalmenu.digitalmenuapi.repositories.DigitalMenuRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DigitalMenuService {

    private final DigitalMenuRepository digitalMenuRepository;

    public DigitalMenuService(DigitalMenuRepository digitalMenuRepository) {
        this.digitalMenuRepository = digitalMenuRepository;
    }

    @Transactional(readOnly = true)
    public List<DigitalMenuResponseDTO> getAll() {
        return digitalMenuRepository.findAll()
                .stream()
                .map(DigitalMenuResponseDTO::new)
                .toList();
    }

    @Transactional
    public DigitalMenuResponseDTO save(DigitalMenuRequestDTO requestDTO) {
        DigitalMenu digitalMenu = new DigitalMenu(requestDTO);

        return new DigitalMenuResponseDTO(digitalMenuRepository.save(digitalMenu));
    }

    @Transactional
    public DigitalMenuResponseDTO update(String id, DigitalMenuRequestDTO requestDTO) {
        DigitalMenu digitalMenu = this.find(id);

        BeanUtils.copyProperties(requestDTO, digitalMenu);

        return new DigitalMenuResponseDTO(digitalMenuRepository.save(digitalMenu));
    }

    @Transactional
    public void delete(String id) {
        DigitalMenu digitalMenu = this.find(id);

        digitalMenuRepository.delete(digitalMenu);
    }

    private DigitalMenu find(String id) {
        return digitalMenuRepository.findById(id).orElseThrow(() -> new RuntimeException("Item não encontrado!"));
    }
}

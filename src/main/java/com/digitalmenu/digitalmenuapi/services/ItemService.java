package com.digitalmenu.digitalmenuapi.services;

import com.digitalmenu.digitalmenuapi.dtos.ItemRequestDTO;
import com.digitalmenu.digitalmenuapi.dtos.ItemResponseDTO;
import com.digitalmenu.digitalmenuapi.entities.Category;
import com.digitalmenu.digitalmenuapi.entities.Item;
import com.digitalmenu.digitalmenuapi.exceptions.ItemNotFoundException;
import com.digitalmenu.digitalmenuapi.repositories.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getAll() {
        return itemRepository.searchAll()
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    @Transactional
    public ItemResponseDTO save(ItemRequestDTO requestDTO) {
        Item item = new Item(requestDTO);

        return new ItemResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public ItemResponseDTO update(String id, ItemRequestDTO requestDTO) {
        Item item = this.find(id);

        item.setCategory(new Category(requestDTO.categoryId()));

        BeanUtils.copyProperties(requestDTO, item);

        return new ItemResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public void delete(String id) {
        Item item = this.find(id);

        itemRepository.delete(item);
    }

    private Item find(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item não encontrado!"));
    }
}

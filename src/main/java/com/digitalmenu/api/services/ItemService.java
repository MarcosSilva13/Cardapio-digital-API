package com.digitalmenu.api.services;

import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.entities.Item;
import com.digitalmenu.api.exceptions.CategoryNotFoundException;
import com.digitalmenu.api.exceptions.ItemNotFoundException;
import com.digitalmenu.api.repositories.CategoryRepository;
import com.digitalmenu.api.repositories.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
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
        this.checkValidCategory(requestDTO.categoryId());

        Item item = new Item(requestDTO);

        return new ItemResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public ItemResponseDTO update(String id, ItemRequestDTO requestDTO) {
        this.checkValidCategory(requestDTO.categoryId());

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

    private void checkValidCategory(String categoryId) {
        categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada."));
    }
}

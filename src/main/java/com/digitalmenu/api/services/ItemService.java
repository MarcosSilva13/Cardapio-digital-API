package com.digitalmenu.api.services;

import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.entities.Category;
import com.digitalmenu.api.entities.Item;
import com.digitalmenu.api.exceptions.CategoryNotFoundException;
import com.digitalmenu.api.exceptions.ItemNotFoundException;
import com.digitalmenu.api.mapper.ItemMapper;
import com.digitalmenu.api.repositories.CategoryRepository;
import com.digitalmenu.api.repositories.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, CategoryRepository categoryRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.itemMapper = itemMapper;
    }

    @Transactional(readOnly = true)
    public List<ItemResponseDTO> getAll() {
        return itemRepository.searchAll()
                .stream()
                .map(ItemResponseDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public ItemResponseDTO getOne(String id) {
        Item item = this.find(id);

        return itemMapper.toItemResponseDTO(item);
    }

    @Transactional
    public ItemResponseDTO save(ItemRequestDTO requestDTO) {
        Item item = itemMapper.toItem(requestDTO);

        item.setCategory(this.checkValidCategory(requestDTO.categoryId()));

        return itemMapper.toItemResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public ItemResponseDTO update(String id, ItemRequestDTO requestDTO) {
        Item item = this.find(id);

        itemMapper.toUpdateItem(requestDTO, item);

        item.setCategory(this.checkValidCategory(requestDTO.categoryId()));

        return itemMapper.toItemResponseDTO(itemRepository.save(item));
    }

    @Transactional
    public void delete(String id) {
        Item item = this.find(id);

        itemRepository.delete(item);
    }

    private Item find(String id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Item não encontrado!"));
    }

    private Category checkValidCategory(String categoryId) {
         return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Categoria não encontrada."));
    }
}

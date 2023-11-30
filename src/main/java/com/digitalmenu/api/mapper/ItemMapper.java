package com.digitalmenu.api.mapper;

import com.digitalmenu.api.dtos.ItemRequestDTO;
import com.digitalmenu.api.dtos.ItemResponseDTO;
import com.digitalmenu.api.entities.Item;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemRequestDTO requestDTO);

    ItemResponseDTO toItemResponseDTO(Item item);

    void toUpdateItem(ItemRequestDTO requestDTO, @MappingTarget Item item);
}

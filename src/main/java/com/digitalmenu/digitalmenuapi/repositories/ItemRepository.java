package com.digitalmenu.digitalmenuapi.repositories;

import com.digitalmenu.digitalmenuapi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

    @Query(value = "SELECT obj FROM Item obj JOIN FETCH obj.category")
    List<Item> searchAll();
}

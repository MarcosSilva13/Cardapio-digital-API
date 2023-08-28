package com.digitalmenu.digitalmenuapi.repositories;

import com.digitalmenu.digitalmenuapi.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {

}

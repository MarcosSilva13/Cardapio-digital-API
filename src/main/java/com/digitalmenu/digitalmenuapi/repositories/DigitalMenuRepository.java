package com.digitalmenu.digitalmenuapi.repositories;

import com.digitalmenu.digitalmenuapi.entities.DigitalMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DigitalMenuRepository extends JpaRepository<DigitalMenu, String> {

}

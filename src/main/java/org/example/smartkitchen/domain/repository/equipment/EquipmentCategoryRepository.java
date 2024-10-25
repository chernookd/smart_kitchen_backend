package org.example.smartkitchen.domain.repository.equipment;

import org.example.smartkitchen.domain.entity.equipment.EquipmentCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentCategoryRepository extends JpaRepository<EquipmentCategoryEntity, Long> {
}

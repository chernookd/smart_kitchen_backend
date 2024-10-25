package org.example.smartkitchen.domain.repository.equipment;

import org.example.smartkitchen.domain.entity.equipment.EquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {
}

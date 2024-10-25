package org.example.smartkitchen.domain.repository.equipment;

import org.example.smartkitchen.domain.entity.equipment.UserEquipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEquipmentRepository extends JpaRepository<UserEquipmentEntity, Long> {
}

package org.example.smartkitchen.dto.equipment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartkitchen.domain.entity.equipment.EquipmentEntity;
import org.example.smartkitchen.domain.entity.user.UserEntity;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEquipmentDTO {


    private Long id;


    private EquipmentEntity equipment_id;


    private Long user_id;

}

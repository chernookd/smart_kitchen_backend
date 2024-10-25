package org.example.smartkitchen.dto.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EquipmentDTO {

    private Long id;

    private String name;

    private Long categoryId;
}

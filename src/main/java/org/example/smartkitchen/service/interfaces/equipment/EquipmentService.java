package org.example.smartkitchen.service.interfaces.equipment;


import org.example.smartkitchen.dto.equipment.EquipmentDTO;

import java.util.List;

public interface EquipmentService {

    EquipmentDTO getEquipmentById(Long id);

    EquipmentDTO createEquipment(EquipmentDTO equipmentDTO);

    List<EquipmentDTO> getAllEquipments();

    EquipmentDTO updateEquipment(Long id, EquipmentDTO equipmentDTO);

    void deleteEquipment(Long id);
}

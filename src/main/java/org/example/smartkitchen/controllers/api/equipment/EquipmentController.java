package org.example.smartkitchen.controllers.api.equipment;

import org.example.smartkitchen.domain.entity.equipment.EquipmentEntity;
import org.example.smartkitchen.dto.equipment.EquipmentDTO;
import org.example.smartkitchen.service.interfaces.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EquipmentDTO>> getAllEquipments() {
        return ResponseEntity.ok().body(equipmentService.getAllEquipments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long id) {
        return ResponseEntity.ok().body(equipmentService.getEquipmentById(id));
    }

    @PostMapping
    public ResponseEntity<EquipmentDTO> createEquipment(@RequestBody EquipmentDTO equipment) {
        return ResponseEntity.ok().body(equipmentService.createEquipment(equipment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> updateEquipment(@PathVariable Long id, @RequestBody EquipmentDTO equipmentDetails) {
        return ResponseEntity.ok(equipmentService.updateEquipment(id, equipmentDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.noContent().build();
    }
}


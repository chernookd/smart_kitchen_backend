package org.example.smartkitchen.controllers;

import org.example.smartkitchen.dto.EquipmentDTO;
import org.example.smartkitchen.service.interfaces.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/equipment/{id}")
    public ResponseEntity<EquipmentDTO> getEquipmentById(@PathVariable Long id) {
        EquipmentDTO equipment = equipmentService.getEquipmentById(id);
        return ResponseEntity.ok(equipment);
    }
}

package org.example.smartkitchen.controllers.api.equipment;


import org.example.smartkitchen.dto.equipment.UserEquipmentDTO;
import org.example.smartkitchen.service.impl.equipment.UserEquipmentServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_equipments")
public class UserEquipmentController {
    private final UserEquipmentServiceImpl userEquipmentService;

    @Autowired
    public UserEquipmentController(UserEquipmentServiceImpl userEquipmentService) {
        this.userEquipmentService = userEquipmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEquipmentDTO> getUserEquipmentById(@PathVariable Long id) {
        UserEquipmentDTO userEquipmentDTO = userEquipmentService.getUserEquipmentById(id);
        return ResponseEntity.ok(userEquipmentDTO);
    }

    @PostMapping("/add")
    public ResponseEntity<UserEquipmentDTO> createUserEquipment(@RequestBody UserEquipmentDTO userEquipmentDTO) {
        UserEquipmentDTO createdUserEquipment = userEquipmentService.createUserEquipment(userEquipmentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUserEquipment);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserEquipmentDTO> updateUserEquipment(@PathVariable Long id, @RequestBody UserEquipmentDTO userEquipmentDTO) {
        UserEquipmentDTO updatedUserEquipment = userEquipmentService.updateUserEquipment(id, userEquipmentDTO);
        return ResponseEntity.ok(updatedUserEquipment);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserEquipment(@PathVariable Long id) {
        userEquipmentService.deleteUserEquipment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserEquipmentDTO>> getAllUserEquipment() {
        return ResponseEntity.ok().body(userEquipmentService.getAllUserEquipments());
    }

}

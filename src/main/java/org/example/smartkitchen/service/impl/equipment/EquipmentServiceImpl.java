package org.example.smartkitchen.service.impl.equipment;

import org.example.smartkitchen.domain.entity.equipment.EquipmentCategoryEntity;
import org.example.smartkitchen.domain.entity.equipment.EquipmentEntity;
import org.example.smartkitchen.domain.repository.equipment.EquipmentCategoryRepository;
import org.example.smartkitchen.domain.repository.equipment.EquipmentRepository;
import org.example.smartkitchen.dto.equipment.EquipmentDTO;
import org.example.smartkitchen.exceptions.CommentNotFoundException;
import org.example.smartkitchen.exceptions.EquipmentNotFondException;
import org.example.smartkitchen.service.interfaces.equipment.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;

    private final EquipmentCategoryRepository equipmentCategoryRepository;

    @Autowired
    public EquipmentServiceImpl(EquipmentRepository equipmentRepository, EquipmentCategoryRepository equipmentCategoryRepository) {
        this.equipmentRepository = equipmentRepository;
        this.equipmentCategoryRepository = equipmentCategoryRepository;
    }

    @Override
    public EquipmentDTO getEquipmentById(Long id) {
        Optional<EquipmentEntity> equipment = equipmentRepository.findById(id);

        if (!equipment.isPresent()) {
            throw new EquipmentNotFondException("equipment not found");
        }

        return new EquipmentDTO(equipment.get().getId(), equipment.get().getName(), equipment.get().getCategory().getId());
    }

    @Override
    public EquipmentDTO createEquipment(EquipmentDTO equipmentDTO) {
        Optional<EquipmentCategoryEntity> equipmentCategoryEntityOptional
                = equipmentCategoryRepository.findById(equipmentDTO.getCategoryId());

        if (!equipmentCategoryEntityOptional.isPresent()) {
            throw new EquipmentNotFondException("equipment category not found");
        }

        EquipmentEntity equipment = equipmentRepository.save(new EquipmentEntity(equipmentDTO.getId(), equipmentDTO.getName(),
                equipmentCategoryEntityOptional.get()));

        return new EquipmentDTO(equipment.getId(), equipment.getName(), equipmentCategoryEntityOptional.get().getId());
    }

    @Override
    public List<EquipmentDTO> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(equipment -> {
                    return new EquipmentDTO(equipment.getId(), equipment.getName(), equipment.getCategory().getId());
                }).collect(Collectors.toList());
    }

    @Override
    public EquipmentDTO updateEquipment(Long id, EquipmentDTO equipmentDTO) {
        EquipmentEntity equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFondException("Equipment not found with id: " + id));

        EquipmentCategoryEntity category = equipmentCategoryRepository.findById(equipmentDTO.getCategoryId())
                .orElseThrow(() -> new EquipmentNotFondException("Equipment category not found with id: " + equipmentDTO.getCategoryId()));

        equipment.setName(equipmentDTO.getName());
        equipment.setCategory(category);

        EquipmentEntity updatedEquipment = equipmentRepository.save(equipment);

        return new EquipmentDTO(updatedEquipment.getId(), updatedEquipment.getName(), updatedEquipment.getCategory().getId());
    }

    @Override
    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }
}

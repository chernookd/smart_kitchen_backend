package org.example.smartkitchen.service.impl.equipment;


import org.example.smartkitchen.domain.entity.equipment.EquipmentEntity;
import org.example.smartkitchen.domain.entity.equipment.UserEquipmentEntity;
import org.example.smartkitchen.domain.entity.user.UserEntity;
import org.example.smartkitchen.domain.repository.equipment.EquipmentRepository;
import org.example.smartkitchen.domain.repository.equipment.UserEquipmentRepository;
import org.example.smartkitchen.domain.repository.user.UserRepository;
import org.example.smartkitchen.dto.equipment.UserEquipmentDTO;
import org.example.smartkitchen.exceptions.EquipmentNotFondException;
import org.example.smartkitchen.exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserEquipmentServiceImpl {
    private final UserEquipmentRepository userEquipmentRepository;
    private final EquipmentRepository equipmentRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserEquipmentServiceImpl(UserEquipmentRepository userEquipmentRepository,
                                    EquipmentRepository equipmentRepository,
                                    UserRepository userRepository) {
        this.userEquipmentRepository = userEquipmentRepository;
        this.equipmentRepository = equipmentRepository;
        this.userRepository = userRepository;
    }

    public UserEquipmentDTO getUserEquipmentById(Long id) {
        UserEquipmentEntity userEquipment = userEquipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFondException("User equipment not found with id: " + id));
        return mapToDTO(userEquipment);
    }

    public UserEquipmentDTO createUserEquipment(UserEquipmentDTO userEquipmentDTO) {
        EquipmentEntity equipment = equipmentRepository.findById(userEquipmentDTO.getEquipment_id().getId())
                .orElseThrow(() -> new EquipmentNotFondException("Equipment not found with id: " + userEquipmentDTO.getEquipment_id().getId()));

        UserEntity user = userRepository.findById(userEquipmentDTO.getUser_id())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userEquipmentDTO.getUser_id()));

        UserEquipmentEntity userEquipment = new UserEquipmentEntity();
        userEquipment.setEquipment(equipment);
        userEquipment.setUser(user);

        UserEquipmentEntity createdUserEquipment = userEquipmentRepository.save(userEquipment);
        return mapToDTO(createdUserEquipment);
    }

    public UserEquipmentDTO updateUserEquipment(Long id, UserEquipmentDTO userEquipmentDTO) {
        UserEquipmentEntity userEquipment = userEquipmentRepository.findById(id)
                .orElseThrow(() -> new EquipmentNotFondException("User equipment not found with id: " + id));

        EquipmentEntity equipment = equipmentRepository.findById(userEquipmentDTO.getEquipment_id().getId())
                .orElseThrow(() -> new EquipmentNotFondException("Equipment not found with id: " + userEquipmentDTO.getEquipment_id().getId()));

        UserEntity user = userRepository.findById(userEquipmentDTO.getUser_id())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userEquipmentDTO.getUser_id()));

        userEquipment.setEquipment(equipment);
        userEquipment.setUser(user);

        UserEquipmentEntity updatedUserEquipment = userEquipmentRepository.save(userEquipment);
        return mapToDTO(updatedUserEquipment);
    }

    public void deleteUserEquipment(Long id) {
        if (!userEquipmentRepository.existsById(id)) {
            throw new EquipmentNotFondException("User equipment not found with id: " + id);
        }
        userEquipmentRepository.deleteById(id);
    }

    public List<UserEquipmentDTO> getAllUserEquipments() {
        return userEquipmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserEquipmentDTO mapToDTO(UserEquipmentEntity userEquipment) {
        return new UserEquipmentDTO(userEquipment.getId(), userEquipment.getEquipment(), userEquipment.getUser().getId());
    }
}

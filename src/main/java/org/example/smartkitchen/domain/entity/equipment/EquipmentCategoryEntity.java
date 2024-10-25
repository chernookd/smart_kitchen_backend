package org.example.smartkitchen.domain.entity.equipment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name = "equipment_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category"/*, cascade = CascadeType.ALL, orphanRemoval = true*/)
    private List<EquipmentEntity> equipmentList;
}


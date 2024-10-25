package org.example.smartkitchen.domain.entity.Ingredient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "abstract_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbstractIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingredient_category_id")
    private IngredientCategoryEntity ingredientCategory;
}

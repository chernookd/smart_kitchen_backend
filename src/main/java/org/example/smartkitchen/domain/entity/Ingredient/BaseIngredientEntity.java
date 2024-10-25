package org.example.smartkitchen.domain.entity.Ingredient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;

@Entity
@Table(name = "base_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "abstract_ingredient_id", nullable = false)
    private AbstractIngredientEntity abstractIngredient;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipe;
}

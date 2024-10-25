package org.example.smartkitchen.domain.entity.Ingredient;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;


@Entity
@Table(name = "user_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserIngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "user_description")
    private String userDescription;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeEntity recipe;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "abstract_ingredient_id")
    private AbstractIngredientEntity abstractIngredient;
}


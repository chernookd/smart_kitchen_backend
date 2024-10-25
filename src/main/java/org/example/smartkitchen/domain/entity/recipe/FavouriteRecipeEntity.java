package org.example.smartkitchen.domain.entity.recipe;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favourite_recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavouriteRecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JoinColumn(name = "recipe_id")
    @ManyToOne(fetch = FetchType.EAGER)
    RecipeEntity recipe;


}

package org.example.smartkitchen.domain.entity.recipe;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.smartkitchen.domain.entity.Ingredient.BaseIngredientEntity;
import org.example.smartkitchen.domain.entity.Ingredient.UserIngredientEntity;
import org.example.smartkitchen.domain.entity.user.UserEntity;

import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "isPublished" /*,columnDefinition = "boolean"*/)
    private boolean isPublished;
    @Column(name = "slug")
    private String slug;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private RecipeCategoryEntity category;

    @OneToMany(mappedBy = "recipe"/*, cascade = CascadeType.ALL ,orphanRemoval = true*/)
    private List<UserIngredientEntity> userIngredients;

    @OneToMany(mappedBy = "recipe"/*, cascade = CascadeType.ALL ,orphanRemoval = true*/)
    private List<BaseIngredientEntity> baseIngredients;
}


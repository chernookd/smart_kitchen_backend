package org.example.smartkitchen.service.impl.recipe;

import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;
import org.example.smartkitchen.domain.repository.recipe.RecipeRepository;
import org.example.smartkitchen.dto.recipe.RecipeDTO;
import org.example.smartkitchen.exceptions.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl {
    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public RecipeDTO getRecipeById(Long id) {
        RecipeEntity recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));
        return mapToDTO(recipe);
    }

    public RecipeDTO createRecipe(RecipeDTO recipeDTO) {
        RecipeEntity recipe = new RecipeEntity();
        recipe.setTitle(recipeDTO.getTitle());
        recipe.setContent(recipeDTO.getContent());
        recipe.setPublished(recipeDTO.isPublished());
        recipe.setSlug(recipeDTO.getSlug());

        RecipeEntity createdRecipe = recipeRepository.save(recipe);
        return mapToDTO(createdRecipe);
    }

    public RecipeDTO updateRecipe(Long id, RecipeDTO recipeDTO) {
        RecipeEntity recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + id));

        recipe.setTitle(recipeDTO.getTitle());
        recipe.setContent(recipeDTO.getContent());
        recipe.setPublished(recipeDTO.isPublished());
        recipe.setSlug(recipeDTO.getSlug());

        RecipeEntity updatedRecipe = recipeRepository.save(recipe);
        return mapToDTO(updatedRecipe);
    }

    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RecipeNotFoundException("Recipe not found with id: " + id);
        }
        recipeRepository.deleteById(id);
    }

    public List<RecipeDTO> getAllRecipes() {
        return recipeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private RecipeDTO mapToDTO(RecipeEntity recipe) {
        return new RecipeDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getContent(),
                recipe.isPublished(),
                recipe.getSlug(),
                recipe.getAuthor() != null ? recipe.getAuthor().getId() : null,
                recipe.getCategory() != null ? recipe.getCategory().getId() : null
        );
    }
}

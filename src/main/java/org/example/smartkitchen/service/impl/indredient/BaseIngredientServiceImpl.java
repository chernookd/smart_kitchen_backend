package org.example.smartkitchen.service.impl.indredient;

import org.example.smartkitchen.domain.entity.Ingredient.AbstractIngredientEntity;
import org.example.smartkitchen.domain.entity.Ingredient.BaseIngredientEntity;
import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;
import org.example.smartkitchen.domain.repository.ingredient.AbstractIngredientRepository;
import org.example.smartkitchen.domain.repository.ingredient.BaseIngredientRepository;
import org.example.smartkitchen.domain.repository.recipe.RecipeRepository;
import org.example.smartkitchen.dto.Ingredient.BaseIngredientDTO;
import org.example.smartkitchen.exceptions.IngredientNotFoundException;
import org.example.smartkitchen.exceptions.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BaseIngredientServiceImpl {
    private final BaseIngredientRepository baseIngredientRepository;
    private final AbstractIngredientRepository abstractIngredientRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public BaseIngredientServiceImpl(BaseIngredientRepository baseIngredientRepository,
                                     AbstractIngredientRepository abstractIngredientRepository,
                                     RecipeRepository recipeRepository) {
        this.baseIngredientRepository = baseIngredientRepository;
        this.abstractIngredientRepository = abstractIngredientRepository;
        this.recipeRepository = recipeRepository;
    }

    public BaseIngredientDTO getBaseIngredientById(Long id) {
        BaseIngredientEntity ingredient = baseIngredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Base ingredient not found with id: " + id));
        return mapToDTO(ingredient);
    }

    public BaseIngredientDTO createBaseIngredient(BaseIngredientDTO baseIngredientDTO) {
        AbstractIngredientEntity abstractIngredient = abstractIngredientRepository.findById(baseIngredientDTO.getAbstractIngredientId())
                .orElseThrow(() -> new IngredientNotFoundException("Abstract ingredient not found with id: " + baseIngredientDTO.getAbstractIngredientId()));

        RecipeEntity recipe = recipeRepository.findById(baseIngredientDTO.getRecipeId())
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + baseIngredientDTO.getRecipeId()));

        BaseIngredientEntity baseIngredient = new BaseIngredientEntity();
        baseIngredient.setAbstractIngredient(abstractIngredient);
        baseIngredient.setRecipe(recipe);

        BaseIngredientEntity createdIngredient = baseIngredientRepository.save(baseIngredient);
        return mapToDTO(createdIngredient);
    }

    public BaseIngredientDTO updateBaseIngredient(Long id, BaseIngredientDTO baseIngredientDTO) {
        BaseIngredientEntity ingredient = baseIngredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Base ingredient not found with id: " + id));

        AbstractIngredientEntity abstractIngredient = abstractIngredientRepository.findById(baseIngredientDTO.getAbstractIngredientId())
                .orElseThrow(() -> new IngredientNotFoundException("Abstract ingredient not found with id: " + baseIngredientDTO.getAbstractIngredientId()));

        RecipeEntity recipe = recipeRepository.findById(baseIngredientDTO.getRecipeId())
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + baseIngredientDTO.getRecipeId()));

        ingredient.setAbstractIngredient(abstractIngredient);
        ingredient.setRecipe(recipe);

        BaseIngredientEntity updatedIngredient = baseIngredientRepository.save(ingredient);
        return mapToDTO(updatedIngredient);
    }

    public void deleteBaseIngredient(Long id) {
        if (!baseIngredientRepository.existsById(id)) {
            throw new IngredientNotFoundException("Base ingredient not found with id: " + id);
        }
        baseIngredientRepository.deleteById(id);
    }

    public List<BaseIngredientDTO> getAllBaseIngredients() {
        return baseIngredientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BaseIngredientDTO mapToDTO(BaseIngredientEntity ingredient) {
        return new BaseIngredientDTO(ingredient.getId(), ingredient.getAbstractIngredient().getId(), ingredient.getRecipe().getId());
    }
}

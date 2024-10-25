package org.example.smartkitchen.service.impl.indredient;

import org.example.smartkitchen.domain.entity.Ingredient.AbstractIngredientEntity;
import org.example.smartkitchen.domain.entity.Ingredient.UserIngredientEntity;
import org.example.smartkitchen.domain.entity.recipe.RecipeEntity;
import org.example.smartkitchen.domain.repository.ingredient.AbstractIngredientRepository;
import org.example.smartkitchen.domain.repository.ingredient.UserIngredientRepository;
import org.example.smartkitchen.domain.repository.recipe.RecipeRepository;
import org.example.smartkitchen.dto.Ingredient.UserIngredientDTO;
import org.example.smartkitchen.exceptions.IngredientNotFoundException;
import org.example.smartkitchen.exceptions.RecipeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserIngredientServiceImpl {
    private final UserIngredientRepository userIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final AbstractIngredientRepository abstractIngredientRepository;

    @Autowired
    public UserIngredientServiceImpl(UserIngredientRepository userIngredientRepository,
                                     RecipeRepository recipeRepository,
                                     AbstractIngredientRepository abstractIngredientRepository) {
        this.userIngredientRepository = userIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.abstractIngredientRepository = abstractIngredientRepository;
    }

    public UserIngredientDTO getUserIngredientById(Long id) {
        UserIngredientEntity userIngredient = userIngredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("User ingredient not found with id: " + id));
        return mapToDTO(userIngredient);
    }

    public UserIngredientDTO createUserIngredient(UserIngredientDTO userIngredientDTO) {
        RecipeEntity recipe = recipeRepository.findById(userIngredientDTO.getRecipeId())
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + userIngredientDTO.getRecipeId()));

        AbstractIngredientEntity abstractIngredient = abstractIngredientRepository.findById(userIngredientDTO.getAbstractIngredientId())
                .orElseThrow(() -> new IngredientNotFoundException("Abstract ingredient not found with id: " + userIngredientDTO.getAbstractIngredientId()));

        UserIngredientEntity userIngredient = new UserIngredientEntity();
        userIngredient.setQuantity(userIngredientDTO.getQuantity());
        userIngredient.setUserDescription(userIngredientDTO.getUserDescription());
        userIngredient.setRecipe(recipe);
        userIngredient.setAbstractIngredient(abstractIngredient);

        UserIngredientEntity createdUserIngredient = userIngredientRepository.save(userIngredient);
        return mapToDTO(createdUserIngredient);
    }

    public UserIngredientDTO updateUserIngredient(Long id, UserIngredientDTO userIngredientDTO) {
        UserIngredientEntity userIngredient = userIngredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("User ingredient not found with id: " + id));

        RecipeEntity recipe = recipeRepository.findById(userIngredientDTO.getRecipeId())
                .orElseThrow(() -> new RecipeNotFoundException("Recipe not found with id: " + userIngredientDTO.getRecipeId()));

        AbstractIngredientEntity abstractIngredient = abstractIngredientRepository.findById(userIngredientDTO.getAbstractIngredientId())
                .orElseThrow(() -> new IngredientNotFoundException("Abstract ingredient not found with id: " + userIngredientDTO.getAbstractIngredientId()));

        userIngredient.setQuantity(userIngredientDTO.getQuantity());
        userIngredient.setUserDescription(userIngredientDTO.getUserDescription());
        userIngredient.setRecipe(recipe);
        userIngredient.setAbstractIngredient(abstractIngredient);

        UserIngredientEntity updatedUserIngredient = userIngredientRepository.save(userIngredient);
        return mapToDTO(updatedUserIngredient);
    }

    public void deleteUserIngredient(Long id) {
        if (!userIngredientRepository.existsById(id)) {
            throw new IngredientNotFoundException("User ingredient not found with id: " + id);
        }
        userIngredientRepository.deleteById(id);
    }

    public List<UserIngredientDTO> getAllUserIngredients() {
        return userIngredientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private UserIngredientDTO mapToDTO(UserIngredientEntity userIngredient) {
        return new UserIngredientDTO(
                userIngredient.getId(),
                userIngredient.getQuantity(),
                userIngredient.getUserDescription(),
                userIngredient.getRecipe().getId(),
                userIngredient.getAbstractIngredient().getId()
        );
    }
}

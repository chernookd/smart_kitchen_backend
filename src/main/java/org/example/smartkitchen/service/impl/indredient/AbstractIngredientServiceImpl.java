package org.example.smartkitchen.service.impl.indredient;

import org.example.smartkitchen.domain.entity.Ingredient.AbstractIngredientEntity;
import org.example.smartkitchen.domain.entity.Ingredient.IngredientCategoryEntity;
import org.example.smartkitchen.domain.repository.ingredient.AbstractIngredientRepository;
import org.example.smartkitchen.domain.repository.ingredient.IngredientCategoryRepository;
import org.example.smartkitchen.dto.Ingredient.AbstractIngredientDTO;
import org.example.smartkitchen.exceptions.IngredientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbstractIngredientServiceImpl {
    private final AbstractIngredientRepository abstractIngredientRepository;
    private final IngredientCategoryRepository ingredientCategoryRepository;

    @Autowired
    public AbstractIngredientServiceImpl(AbstractIngredientRepository abstractIngredientRepository,
                                         IngredientCategoryRepository ingredientCategoryRepository) {
        this.abstractIngredientRepository = abstractIngredientRepository;
        this.ingredientCategoryRepository = ingredientCategoryRepository;
    }

    public AbstractIngredientDTO getAbstractIngredientById(Long id) {
        AbstractIngredientEntity ingredient = abstractIngredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found with id: " + id));
        return mapToDTO(ingredient);
    }

    public AbstractIngredientDTO createAbstractIngredient(AbstractIngredientDTO abstractIngredientDTO) {
        IngredientCategoryEntity category = ingredientCategoryRepository.findById(abstractIngredientDTO.getIngredientCategoryId())
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient category not found with id: " + abstractIngredientDTO.getIngredientCategoryId()));

        AbstractIngredientEntity ingredient = new AbstractIngredientEntity();
        ingredient.setName(abstractIngredientDTO.getName());
        ingredient.setDescription(abstractIngredientDTO.getDescription());
        ingredient.setIngredientCategory(category);

        AbstractIngredientEntity createdIngredient = abstractIngredientRepository.save(ingredient);
        return mapToDTO(createdIngredient);
    }

    public AbstractIngredientDTO updateAbstractIngredient(Long id, AbstractIngredientDTO abstractIngredientDTO) {
        AbstractIngredientEntity ingredient = abstractIngredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found with id: " + id));

        IngredientCategoryEntity category = ingredientCategoryRepository.findById(abstractIngredientDTO.getIngredientCategoryId())
                .orElseThrow(() -> new IngredientNotFoundException("Ingredient category not found with id: " + abstractIngredientDTO.getIngredientCategoryId()));

        ingredient.setName(abstractIngredientDTO.getName());
        ingredient.setDescription(abstractIngredientDTO.getDescription());
        ingredient.setIngredientCategory(category);

        AbstractIngredientEntity updatedIngredient = abstractIngredientRepository.save(ingredient);
        return mapToDTO(updatedIngredient);
    }

    public void deleteAbstractIngredient(Long id) {
        if (!abstractIngredientRepository.existsById(id)) {
            throw new IngredientNotFoundException("Ingredient not found with id: " + id);
        }
        abstractIngredientRepository.deleteById(id);
    }

    public List<AbstractIngredientDTO> getAllAbstractIngredients() {
        return abstractIngredientRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AbstractIngredientDTO mapToDTO(AbstractIngredientEntity ingredient) {
        return new AbstractIngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getDescription(), ingredient.getIngredientCategory().getId());
    }
}

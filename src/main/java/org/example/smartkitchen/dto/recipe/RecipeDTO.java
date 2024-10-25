package org.example.smartkitchen.dto.recipe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDTO {
    private Long id;
    private String title;
    private String content;
    private boolean isPublished;
    private String slug;
    private Long authorId;
    private Long categoryId;
}

package com.pantrypalbackend.pantrypalbackend.dto;

import lombok.Data;

@Data
public class FavoriteRecipeRequest {
    private Long userId;
    private String recipeId;
}

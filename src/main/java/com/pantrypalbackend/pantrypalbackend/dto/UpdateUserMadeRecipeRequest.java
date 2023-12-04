package com.pantrypalbackend.pantrypalbackend.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdateUserMadeRecipeRequest {
    private String recipeId;

    private String name;
    private String source;
    private Integer preptime;
    private Integer waittime;
    private Integer cooktime;
    private Integer servings;
    private String comments;
    private Double calories;
    private Double fat;
    private Double carbs;
    private Double fiber;
    private Double sugar;
    private Double protein;
    private String instructions;
    private List<String> ingredients;
    private List<String> tags;
    private Double satfat;
}

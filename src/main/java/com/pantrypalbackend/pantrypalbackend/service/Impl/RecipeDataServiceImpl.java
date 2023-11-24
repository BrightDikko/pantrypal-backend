package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.repository.RecipeRepository;
import com.pantrypalbackend.pantrypalbackend.service.RecipeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeDataServiceImpl implements RecipeDataService {

    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    public void loadAndSaveRecipeData(String path) throws IOException {
        Map<String, Recipe> recipes = objectMapper.readValue(new File(path), new TypeReference<Map<String, Recipe>>() {
        });

        recipeRepository.saveAll(recipes.values());
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAllRecipes();
    }

    public Recipe getRecipeByName(String name) {
        Optional<Recipe> recipeOptional = recipeRepository.retrieveRecipeByName(name);
        return recipeOptional.orElse(null);
    }

    public List<Recipe> getRecipesByIngredients(List<String> ingredients) {
        List<Recipe> allRecipes = getAllRecipes();
        List<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : allRecipes) {
            // Extract individual words from each ingredient string
            Set<String> recipeIngredientWords = recipe.getIngredients().stream()
                    .flatMap(ingredient -> Arrays.stream(ingredient.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "")) // Remove non-alphabetic characters
                    .filter(word -> !word.isEmpty() && Character.isLetter(word.charAt(0))) // Filter out empty strings and strings not starting with a letter
                    .collect(Collectors.toSet());

            // Check if all provided ingredients are contained within the recipe's ingredients
            if (recipeIngredientWords.containsAll(ingredients)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;

    }

}

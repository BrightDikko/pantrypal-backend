package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pantrypalbackend.pantrypalbackend.domain.Recipe;
import com.pantrypalbackend.pantrypalbackend.repository.RecipeRepository;
import com.pantrypalbackend.pantrypalbackend.service.RecipeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceWorker implements RecipeDataService {

    private final RecipeRepository recipeRepository;
    private final ObjectMapper objectMapper;

    public void loadAndSaveRecipeData(String path) throws IOException {
        Map<String, Recipe> recipes = objectMapper.readValue(new File(path), new TypeReference<Map<String, Recipe>>() {
        });

        Path sqlFilePath = Paths.get("src/main/resources/scripts/add_recipes.sql");
        try (BufferedWriter writer = Files.newBufferedWriter(sqlFilePath)) {
            for (Recipe recipe : recipes.values()) {

//                System.out.println("CURRENT RECIPE:" + recipe);
                String insertStatement = generateInsertStatement(recipe) + ";";
                writer.write(insertStatement);
                writer.newLine();
            }
        }

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

    private String generateInsertStatement(Recipe recipe) {
        return String.format("INSERT INTO recipes (id, name, source, prep_time, wait_time, cook_time, servings, comments, calories, fat, sat_fat, carbs, fiber, sugar, protein, instructions, ingredients, tags) VALUES ('%s', '%s', '%s', %d, %d, %d, %d, '%s', %f, %f, %f, %f, %f, %f, %f, '%s', '%s', '%s')",
                escapeSql(recipe.getId()),
                escapeSql(recipe.getName()),
                escapeSql(recipe.getSource()),
                recipe.getPrepTime(),
                recipe.getWaitTime(),
                recipe.getCookTime(),
                recipe.getServings(),
                escapeSql(recipe.getComments()),
                recipe.getCalories(),
                recipe.getFat(),
                recipe.getSatFat(),
                recipe.getCarbs(),
                recipe.getFiber(),
                recipe.getSugar(),
                recipe.getProtein(),
                escapeSql(recipe.getInstructions()),
                escapeSql(String.join(", ", recipe.getIngredients())), // Concatenate list elements
                escapeSql(String.join(", ", recipe.getTags())));
    }

    private String escapeSql(String value) {
        return value != null ? value.replace("'", "''") : "NULL";
    }

}

package com.pantrypalbackend.pantrypalbackend;

import com.pantrypalbackend.pantrypalbackend.service.Impl.FoodDataServiceImpl;
import com.pantrypalbackend.pantrypalbackend.service.Impl.RecipeDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class PantryPalBackendApplication implements CommandLineRunner {
	private final FoodDataServiceImpl foodDataService;
	private final RecipeDataServiceImpl recipeDataService;

	public static void main(String[] args) {
		SpringApplication.run(PantryPalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Path to data files
		String FoodCSVPath = "src/main/resources/data/food.csv";
		String RecipesJSONPath = "src/main/resources/data/recipes.json";

		// Load data into database
		try {
			foodDataService.loadAndSaveFoodData(FoodCSVPath);
			recipeDataService.loadAndSaveRecipeData(RecipesJSONPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

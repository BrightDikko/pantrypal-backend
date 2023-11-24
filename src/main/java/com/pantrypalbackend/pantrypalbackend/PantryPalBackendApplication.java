package com.pantrypalbackend.pantrypalbackend;

import com.pantrypalbackend.pantrypalbackend.service.Impl.FoodDataServiceImpl;
import com.pantrypalbackend.pantrypalbackend.service.Impl.RecipeDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

import static com.pantrypalbackend.pantrypalbackend.constants.PathConstants.FOOD_CSV_PATH;
import static com.pantrypalbackend.pantrypalbackend.constants.PathConstants.RECIPES_JSON_PATH;

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
		System.out.println("INSIDE COMMANDLINE RUNNER");

		/*
		try {
			System.out.println("PARSE DATA - 1");
			foodDataService.loadAndSaveFoodData(FOOD_CSV_PATH);

			System.out.println("PARSE DATA - 2");
			recipeDataService.loadAndSaveRecipeData(RECIPES_JSON_PATH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 */
	}
}

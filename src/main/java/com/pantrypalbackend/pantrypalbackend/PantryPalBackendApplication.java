package com.pantrypalbackend.pantrypalbackend;

import com.pantrypalbackend.pantrypalbackend.service.Impl.FoodDataServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@RequiredArgsConstructor
public class PantryPalBackendApplication implements CommandLineRunner {
	private final FoodDataServiceImpl foodDataService;

	public static void main(String[] args) {
		SpringApplication.run(PantryPalBackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Path to the CSV file
		String csvPath = "src/main/resources/data/food.csv";

		// Load CSV data into database
		try {
			foodDataService.loadCSVData(csvPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

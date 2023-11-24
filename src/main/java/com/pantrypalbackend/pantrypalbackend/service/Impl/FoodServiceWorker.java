package com.pantrypalbackend.pantrypalbackend.service.Impl;

import com.pantrypalbackend.pantrypalbackend.domain.Food;
import com.pantrypalbackend.pantrypalbackend.repository.FoodRepository;
import com.pantrypalbackend.pantrypalbackend.service.FoodDataService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FoodServiceWorker implements FoodDataService {

    private final FoodRepository foodRepository;

    public void loadAndSaveFoodData(String path) throws IOException {
        Path sqlFilePath = Paths.get("src/main/resources/scripts/add_foods.sql");

        try (Reader reader = new FileReader(path);
             BufferedWriter writer = Files.newBufferedWriter(sqlFilePath)) {

            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
            List<Food> foods = new ArrayList<>();

            for (CSVRecord csvRecord : csvParser) {
                Food food = Food.builder()
                        .id(Integer.parseInt(csvRecord.get("ID")))
                        .foodGroup(csvRecord.get("FoodGroup"))
                        .shortDescrip(csvRecord.get("ShortDescrip"))
                        .descrip(csvRecord.get("Descrip"))
                        .commonName(csvRecord.get("CommonName"))
                        .mfgName(csvRecord.get("MfgName"))
                        .scientificName(csvRecord.get("ScientificName"))
                        .energyKcal(Double.parseDouble(csvRecord.get("Energy_kcal")))
                        .proteinG(Double.parseDouble(csvRecord.get("Protein_g")))
                        .fatG(Double.parseDouble(csvRecord.get("Fat_g")))
                        .carbG(Double.parseDouble(csvRecord.get("Carb_g")))
                        .sugarG(Double.parseDouble(csvRecord.get("Sugar_g")))
                        .fiberG(Double.parseDouble(csvRecord.get("Fiber_g")))
                        .vitAMcg(Double.parseDouble(csvRecord.get("VitA_mcg")))
                        .vitB6Mg(Double.parseDouble(csvRecord.get("VitB6_mg")))
                        .vitB12Mcg(Double.parseDouble(csvRecord.get("VitB12_mcg")))
                        .vitCMg(Double.parseDouble(csvRecord.get("VitC_mg")))
                        .vitEMg(Double.parseDouble(csvRecord.get("VitE_mg")))
                        .folateMcg(Double.parseDouble(csvRecord.get("Folate_mcg")))
                        .niacinMg(Double.parseDouble(csvRecord.get("Niacin_mg")))
                        .riboflavinMg(Double.parseDouble(csvRecord.get("Riboflavin_mg")))
                        .thiaminMg(Double.parseDouble(csvRecord.get("Thiamin_mg")))
                        .calciumMg(Double.parseDouble(csvRecord.get("Calcium_mg")))
                        .copperMcg(Double.parseDouble(csvRecord.get("Copper_mcg")))
                        .ironMg(Double.parseDouble(csvRecord.get("Iron_mg")))
                        .magnesiumMg(Double.parseDouble(csvRecord.get("Magnesium_mg")))
                        .manganeseMg(Double.parseDouble(csvRecord.get("Manganese_mg")))
                        .phosphorusMg(Double.parseDouble(csvRecord.get("Phosphorus_mg")))
                        .seleniumMcg(Double.parseDouble(csvRecord.get("Selenium_mcg")))
                        .zincMg(Double.parseDouble(csvRecord.get("Zinc_mg")))
                        .vitAUsrda(Double.parseDouble(csvRecord.get("VitA_USRDA")))
                        .vitB6Usrda(Double.parseDouble(csvRecord.get("VitB6_USRDA")))
                        .vitB12Usrda(Double.parseDouble(csvRecord.get("VitB12_USRDA")))
                        .vitCUsrda(Double.parseDouble(csvRecord.get("VitC_USRDA")))
                        .vitEUsrda(Double.parseDouble(csvRecord.get("VitE_USRDA")))
                        .folateUsrda(Double.parseDouble(csvRecord.get("Folate_USRDA")))
                        .niacinUsrda(Double.parseDouble(csvRecord.get("Niacin_USRDA")))
                        .riboflavinUsrda(Double.parseDouble(csvRecord.get("Riboflavin_USRDA")))
                        .thiaminUsrda(Double.parseDouble(csvRecord.get("Thiamin_USRDA")))
                        .calciumUsrda(Double.parseDouble(csvRecord.get("Calcium_USRDA")))
                        .copperUsrda(Double.parseDouble(csvRecord.get("Copper_USRDA")))
                        .magnesiumUsrda(Double.parseDouble(csvRecord.get("Magnesium_USRDA")))
                        .phosphorusUsrda(Double.parseDouble(csvRecord.get("Phosphorus_USRDA")))
                        .seleniumUsrda(Double.parseDouble(csvRecord.get("Selenium_USRDA")))
                        .zincUsrda(Double.parseDouble(csvRecord.get("Zinc_USRDA")))
                        .build();

                foods.add(food);

                // Generate SQL insert statement for each food
                String insertStatement = generateInsertStatement(food);
                System.out.println("\n-----------------------------------------");
                System.out.println("insertStatement: " + insertStatement);
                writer.write(insertStatement);
                writer.newLine();
            }

            try {
                foodRepository.saveAll(foods);
            } catch (Exception e) {
                e.printStackTrace(); // Log the exception for debugging
            }

        }
    }

    private String generateInsertStatement(Food food) {
        return String.format("INSERT INTO foods (" +
                        "id, " +
                        "food_group, " +
                        "short_descrip, " +
                        "descrip, " +
                        "common_name, " +
                        "mfg_name, " +
                        "scientific_name, " +
                        "energy_kcal, " +
                        "protein_g, " +
                        "fat_g, " +
                        "carb_g, " +
                        "sugar_g, " +
                        "fiber_g, " +
                        "vitA_mcg, " +
                        "vitB6_mg, " +
                        "vitB12_mcg, " +
                        "vitC_mg, " +
                        "vitE_mg, " +
                        "folate_mcg, " +
                        "niacin_mg, " +
                        "riboflavin_mg, " +
                        "thiamin_mg, " +
                        "calcium_mg, " +
                        "copper_mcg, " +
                        "iron_mg, " +
                        "magnesium_mg, " +
                        "manganese_mg, " +
                        "phosphorus_mg, " +
                        "selenium_mcg, " +
                        "zinc_mg, " +
                        "vitA_USRDA, " +
                        "vitB6_USRDA, " +
                        "vitB12_USRDA, " +
                        "vitC_USRDA, " +
                        "vitE_USRDA, " +
                        "folate_USRDA, " +
                        "niacin_USRDA, " +
                        "riboflavin_USRDA, " +
                        "thiamin_USRDA, " +
                        "calcium_USRDA, " +
                        "copper_USRDA, " +
                        "magnesium_USRDA, " +
                        "phosphorus_USRDA, " +
                        "selenium_USRDA, " +
                        "zinc_USRDA) VALUES (%d, '%s', '%s', '%s', '%s', '%s', '%s', %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f, %f);",
                food.getId(),
                escapeSql(food.getFoodGroup()),
                escapeSql(food.getShortDescrip()),
                escapeSql(food.getDescrip()),
                escapeSql(food.getCommonName()),
                escapeSql(food.getMfgName()),
                escapeSql(food.getScientificName()),
                food.getEnergyKcal(),
                food.getProteinG(),
                food.getFatG(),
                food.getCarbG(),
                food.getSugarG(),
                food.getFiberG(),
                food.getVitAMcg(),
                food.getVitB6Mg(),
                food.getVitB12Mcg(),
                food.getVitCMg(),
                food.getVitEMg(),
                food.getFolateMcg(),
                food.getNiacinMg(),
                food.getRiboflavinMg(),
                food.getThiaminMg(),
                food.getCalciumMg(),
                food.getCopperMcg(),
                food.getIronMg(),
                food.getMagnesiumMg(),
                food.getManganeseMg(),
                food.getPhosphorusMg(),
                food.getSeleniumMcg(),
                food.getZincMg(),
                food.getVitAUsrda(),
                food.getVitB6Usrda(),
                food.getVitB12Usrda(),
                food.getVitCUsrda(),
                food.getVitEUsrda(),
                food.getFolateUsrda(),
                food.getNiacinUsrda(),
                food.getRiboflavinUsrda(),
                food.getThiaminUsrda(),
                food.getCalciumUsrda(),
                food.getCopperUsrda(),
                food.getMagnesiumUsrda(),
                food.getPhosphorusUsrda(),
                food.getSeleniumUsrda(),
                food.getZincUsrda());
    }

    private String escapeSql(String value) {
        return value != null ? value.replace("'", "''") : "NULL";
    }


}

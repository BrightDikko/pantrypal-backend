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

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodDataServiceImpl implements FoodDataService {

    private final FoodRepository foodRepository;

    public void loadCSVData(String path) throws IOException {
        try (Reader reader = new FileReader(path)) {
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
            }

            foodRepository.saveAll(foods);
        }
    }

}

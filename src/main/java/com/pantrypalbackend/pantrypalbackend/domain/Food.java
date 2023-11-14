package com.pantrypalbackend.pantrypalbackend.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "food")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "food_group")
    private String foodGroup;

    @Column(name = "short_descrip")
    private String shortDescrip;

    @Column(name = "descrip", columnDefinition = "TEXT")
    private String descrip;

    @Column(name = "common_name")
    private String commonName;

    @Column(name = "mfg_name")
    private String mfgName;

    @Column(name = "scientific_name")
    private String scientificName;

    @Column(name = "energy_kcal")
    private Double energyKcal;

    @Column(name = "protein_g")
    private Double proteinG;

    @Column(name = "fat_g")
    private Double fatG;

    @Column(name = "carb_g")
    private Double carbG;

    @Column(name = "sugar_g")
    private Double sugarG;

    @Column(name = "fiber_g")
    private Double fiberG;

    @Column(name = "vitA_mcg")
    private Double vitAMcg;

    @Column(name = "vitB6_mg")
    private Double vitB6Mg;

    @Column(name = "vitB12_mcg")
    private Double vitB12Mcg;

    @Column(name = "vitC_mg")
    private Double vitCMg;

    @Column(name = "vitE_mg")
    private Double vitEMg;

    @Column(name = "folate_mcg")
    private Double folateMcg;

    @Column(name = "niacin_mg")
    private Double niacinMg;

    @Column(name = "riboflavin_mg")
    private Double riboflavinMg;

    @Column(name = "thiamin_mg")
    private Double thiaminMg;

    @Column(name = "calcium_mg")
    private Double calciumMg;

    @Column(name = "copper_mcg")
    private Double copperMcg;

    @Column(name = "iron_mg")
    private Double ironMg;

    @Column(name = "magnesium_mg")
    private Double magnesiumMg;

    @Column(name = "manganese_mg")
    private Double manganeseMg;

    @Column(name = "phosphorus_mg")
    private Double phosphorusMg;

    @Column(name = "selenium_mcg")
    private Double seleniumMcg;

    @Column(name = "zinc_mg")
    private Double zincMg;

    @Column(name = "vitA_USRDA")
    private Double vitAUsrda;

    @Column(name = "vitB6_USRDA")
    private Double vitB6Usrda;

    @Column(name = "vitB12_USRDA")
    private Double vitB12Usrda;

    @Column(name = "vitC_USRDA")
    private Double vitCUsrda;

    @Column(name = "vitE_USRDA")
    private Double vitEUsrda;

    @Column(name = "folate_USRDA")
    private Double folateUsrda;

    @Column(name = "niacin_USRDA")
    private Double niacinUsrda;

    @Column(name = "riboflavin_USRDA")
    private Double riboflavinUsrda;

    @Column(name = "thiamin_USRDA")
    private Double thiaminUsrda;

    @Column(name = "calcium_USRDA")
    private Double calciumUsrda;

    @Column(name = "copper_USRDA")
    private Double copperUsrda;

    @Column(name = "magnesium_USRDA")
    private Double magnesiumUsrda;

    @Column(name = "phosphorus_USRDA")
    private Double phosphorusUsrda;

    @Column(name = "selenium_USRDA")
    private Double seleniumUsrda;

    @Column(name = "zinc_USRDA")
    private Double zincUsrda;

}

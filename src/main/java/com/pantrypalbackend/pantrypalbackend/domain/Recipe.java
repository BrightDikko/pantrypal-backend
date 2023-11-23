package com.pantrypalbackend.pantrypalbackend.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pantrypalbackend.pantrypalbackend.utilities.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "source")
    private String source;

    @JsonProperty("preptime")
    @Column(name = "prep_time")
    private Integer prepTime;

    @JsonProperty("waittime")
    @Column(name = "wait_time")
    private Integer waitTime;

    @JsonProperty("cooktime")
    @Column(name = "cook_time")
    private Integer cookTime;

    @Column(name = "servings")
    private Integer servings;

    @Column(name = "comments", columnDefinition = "TEXT")
    private String comments;

    @Column(name = "calories")
    private Double calories;

    @Column(name = "fat")
    private Double fat;

    @JsonProperty("satfat")
    @Column(name = "sat_fat")
    private Double satFat;

    @Column(name = "carbs")
    private Double carbs;

    @Column(name = "fiber")
    private Double fiber;

    @Column(name = "sugar")
    private Double sugar;

    @Column(name = "protein")
    private Double protein;

    @Column(name = "instructions", columnDefinition = "TEXT")
    private String instructions;

    @Column(name = "ingredients", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> ingredients;

    @Column(name = "tags", columnDefinition = "TEXT")
    @Convert(converter = StringListConverter.class)
    private List<String> tags;

}

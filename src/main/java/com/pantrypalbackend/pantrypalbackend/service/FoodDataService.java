package com.pantrypalbackend.pantrypalbackend.service;

import java.io.IOException;

public interface FoodDataService {
    void loadAndSaveFoodData(String path) throws IOException;
}

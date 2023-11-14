package com.pantrypalbackend.pantrypalbackend.service;

import java.io.IOException;

public interface FoodDataService {
    void loadCSVData(String path) throws IOException;
}

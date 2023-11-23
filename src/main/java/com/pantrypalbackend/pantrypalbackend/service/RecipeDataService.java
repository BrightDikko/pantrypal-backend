package com.pantrypalbackend.pantrypalbackend.service;

import java.io.IOException;

public interface RecipeDataService {
    void loadAndSaveRecipeData(String path) throws IOException;
}

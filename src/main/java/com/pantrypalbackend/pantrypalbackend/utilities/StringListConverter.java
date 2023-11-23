package com.pantrypalbackend.pantrypalbackend.utilities;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;

public class StringListConverter implements AttributeConverter<List<String>, String > {


    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return list != null ? String.join(",", list) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        return joined != null ? Arrays.asList(joined.split(",")) : null;
    }
}

package com.pantrypalbackend.pantrypalbackend.utilities;

import jakarta.persistence.AttributeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringListConverter implements AttributeConverter<List<String>, String > {

    private static final String DELIMITER = ";"; // Use a delimiter that does not appear in your ingredients

    @Override
    public String convertToDatabaseColumn(List<String> list) {
        return list != null ? String.join(DELIMITER, list) : null;
    }

    @Override
    public List<String> convertToEntityAttribute(String joined) {
        return joined != null ? Arrays.stream(joined.split(DELIMITER))
                .map(String::trim)
                .collect(Collectors.toList()) : null;
    }
}

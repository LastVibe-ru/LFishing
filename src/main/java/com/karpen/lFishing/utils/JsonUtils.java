package com.karpen.lFishing.utils;

import java.util.Arrays;
import java.util.List;

public abstract class JsonUtils {
    public static List<String> parseJSONToList(String json) {
        String cleanJson = json.replaceAll("[\\[\\]\"]", "");

        if (cleanJson.isEmpty()) {
            return null;
        }

        return Arrays.asList(cleanJson.split(","));
    }
}

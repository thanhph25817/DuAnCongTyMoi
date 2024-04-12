package com.example.demo.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public class MapParamsUtil {
    public static <T> MultiValueMap<String, String> mapToParams(T request) {
        ObjectMapper objectMapper = new ObjectMapper();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        Map<String, String> fieldMap = objectMapper
                .convertValue(request, new TypeReference<Map<String, String>>() {});
        params.setAll(fieldMap);

        return params;
    }
}

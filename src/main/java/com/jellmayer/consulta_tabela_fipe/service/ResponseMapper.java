package com.jellmayer.consulta_tabela_fipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseMapper {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T parseJson(String json, Class<T> targetClass) {
        try {
            return objectMapper.convertValue(json, targetClass);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
    }

}

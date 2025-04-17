package com.eventvisualizer.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

public class ObjectMapperUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = JsonMapper.builder() // or different mapper for other format
                .addModule(new ParameterNamesModule())
                .addModule(new Jdk8Module())
                .addModule(new JavaTimeModule())
                .build();
        OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        OBJECT_MAPPER.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    }

    private ObjectMapperUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static ObjectMapper getMapper() {
        return OBJECT_MAPPER;
    }

    public static JsonNode getJsonNodeFromString(String jsonString) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readTree(jsonString);
    }

    public static <T> T extractEntity(String json, String entityKey, Class<T> entityClass) throws JsonProcessingException {
        JsonNode node = getMapper().readTree(json).path(entityKey);
        if (node.isMissingNode() || !node.isObject()) {
            throw new IllegalArgumentException("Invalid or missing entity: " + entityKey);
        }
        return getMapper().treeToValue(node, entityClass);
    }
}
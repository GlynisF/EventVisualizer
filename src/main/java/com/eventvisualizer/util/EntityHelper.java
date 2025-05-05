package com.eventvisualizer.util;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class EntityHelper {

    private final Map<String, Object> entities;

    public EntityHelper(Map<String, Object> entities) {
        this.entities = Map.copyOf(entities);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        Object value = entities.get(key);
        if (value == null) {
            throw new IllegalArgumentException("No entity found for key: " + key);
        }
        if (!clazz.isInstance(value)) {
            throw new IllegalStateException("Entity for key '" + key + "' is not of type " + clazz.getSimpleName());
        }
        return (T) value;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getList(String key, Class<T> clazz) {
        Object raw = entities.get(key);
        if (raw == null) {
            return Collections.emptyList();
        }
        if (!(raw instanceof List<?>)) {
            throw new IllegalStateException("Entity for key '" + key + "' is not a list");
        }
        return (List<T>) raw;
    }


    public boolean has(String key) {
        return entities.containsKey(key);
    }

    public Map<String, Object> asMap() {
        return entities;
    }
}
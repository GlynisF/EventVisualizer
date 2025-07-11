package com.eventvisualizer.util;

import com.eventvisualizer.entity.*;
import com.eventvisualizer.persistence.GenericDao;

import java.util.LinkedHashMap;
import java.util.Map;

public class EntityMapper {
    private static Map<String, Object> entityMap;

    public EntityMapper(){
        entityMap = new LinkedHashMap<>();
    }

    public EntityMapper(Map<String, Object> entityMapper) {
        entityMap = entityMapper;
    }

    public static Map<String, Object> getEntityMap() {
        entityMap.put("user", new GenericDao<>(User.class));
        entityMap.put("notebook", new GenericDao<>(Notebook.class));
        entityMap.put("events", new GenericDao<>(Event.class));
        entityMap.put("details", new GenericDao<>(Detail.class));
        entityMap.put("locations", new GenericDao<>(Location.class));
        entityMap.put("performers", new GenericDao<>(Performer.class));
        entityMap.put("goal", new GenericDao<>(Goal.class));
        entityMap.put("note", new GenericDao<>(Note.class));
        entityMap.put("reflection", new GenericDao<>(Reflection.class));
        entityMap.put("customDao", new GenericDao<>(Custom.class));
        return entityMap;
    }

}
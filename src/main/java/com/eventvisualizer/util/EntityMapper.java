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
    public static Map<String, Object> setEntityMap(Map<String, Object> entityMap) {
        entityMap.put("userDao", new GenericDao<>(User.class));
        entityMap.put("notebookDao", new GenericDao<>(Notebook.class));
        entityMap.put("eventDao", new GenericDao<>(Event.class));
        entityMap.put("detailDao", new GenericDao<>(Detail.class));
        entityMap.put("locationDao", new GenericDao<>(Location.class));
        entityMap.put("performerDao", new GenericDao<>(Performer.class));
        entityMap.put("goalDao", new GenericDao<>(Goal.class));
        entityMap.put("noteDao", new GenericDao<>(Note.class));
        entityMap.put("reflectionDao", new GenericDao<>(Reflection.class));
        entityMap.put("customDao", new GenericDao<>(Custom.class));
        return entityMap;
    }

}
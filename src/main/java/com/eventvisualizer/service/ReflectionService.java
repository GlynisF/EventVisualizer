package com.eventvisualizer.service;

import com.eventvisualizer.entity.Reflection;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;


public class ReflectionService {

    private final GenericDao<Reflection> reflectionDao;

    public ReflectionService() {
        reflectionDao = new GenericDao<>(Reflection.class);
    }

    public ReflectionService(GenericDao<Reflection> reflectionDao) {
        this.reflectionDao = reflectionDao;
    }

    public GenericDao<Reflection> getReflectionDao() {
        return reflectionDao;
    }

    public Reflection getReflection(int id) {
        return reflectionDao.getById(id);
    }

    public Reflection reflectionMapper(String json) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readValue(json, Reflection.class);
    }

    public void updateReflection(String json) throws JsonProcessingException {
        JsonNode node = ObjectMapperUtil.getMapper().readTree(json);
        Reflection reflectionToUpdate = reflectionDao.getById(node.get("id").asInt());
        Reflection updatedReflection = ObjectMapperUtil.getMapper().updateValue(reflectionToUpdate, node);
        reflectionDao.update(updatedReflection);
    }
}
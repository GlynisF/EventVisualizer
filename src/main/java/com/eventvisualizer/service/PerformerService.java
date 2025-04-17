package com.eventvisualizer.service;

import com.eventvisualizer.entity.Performer;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;


public class PerformerService {
    private final GenericDao<Performer> performerDao;

    public PerformerService() {
        performerDao = new GenericDao<>(Performer.class);
    }

    public PerformerService(GenericDao<Performer> performerDao) {
        this.performerDao = performerDao;
    }

    public GenericDao<Performer> getPerformerDao() {
        return performerDao;
    }

    public Performer getPerformerById(int id) {
        return performerDao.getById(id);
    }

    public Performer performerMapper(String json) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readValue(json, Performer.class);
    }

    public void updatePerformer(String json) throws JsonProcessingException {
        JsonNode node = ObjectMapperUtil.getJsonNodeFromString(json);
        Performer performerToUpdate = getPerformerById(node.get("id").asInt());
        Performer updatedPerformer = ObjectMapperUtil.getMapper().updateValue(performerToUpdate, node);
        performerDao.update(updatedPerformer);
    }




}
package com.eventvisualizer.service;

import com.eventvisualizer.entity.Goal;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;


public class GoalService {

    private final GenericDao<Goal> goalDao;


    public GoalService() {
        goalDao = new GenericDao<>(Goal.class);
    }

    public GoalService(GenericDao<Goal> goalDao) {
        this.goalDao = goalDao;
    }

    public GenericDao<Goal> getGoalDao() {
        return goalDao;
    }

    public Goal getGoalById(int goalId) {
        return goalDao.getById(goalId);
    }

    public Goal goalMapper(String json) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readValue(json, Goal.class);
    }

    public void updateGoal(Goal goal) throws JsonMappingException {
        JsonNode node = ObjectMapperUtil.getMapper().valueToTree(goal);
        Goal goalToUpdate = getGoalById(node.get("id").asInt());
        Goal updatedGoal = ObjectMapperUtil.getMapper().updateValue(goalToUpdate, node);
        goalDao.update(updatedGoal);

    }
}
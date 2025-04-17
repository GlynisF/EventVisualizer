package com.eventvisualizer.service;

import com.eventvisualizer.entity.Location;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;


public class LocationService {

    private final GenericDao<Location> locationDao;

    public LocationService() {
        locationDao = new GenericDao<Location>(Location.class);
    }

    public LocationService(GenericDao<Location> locationDao) {
        this.locationDao = locationDao;
    }

    public Location getLocationById(int id) {
        return locationDao.getById(id);
    }

    public Location locationMapper(String json) throws JsonProcessingException {
        return ObjectMapperUtil.getMapper().readValue(json, Location.class);
    }

    public void updateLocation(String json) throws JsonProcessingException {
        JsonNode node = ObjectMapperUtil.getMapper().readTree(json);
        Location locationToUpdate = locationDao.getById(node.get("id").asInt());
        Location updatedLocation = ObjectMapperUtil.getMapper().updateValue(locationToUpdate, node);
        locationDao.update(updatedLocation);
    }


}
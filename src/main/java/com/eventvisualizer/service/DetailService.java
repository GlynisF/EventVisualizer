package com.eventvisualizer.service;

import com.eventvisualizer.entity.Detail;
import com.eventvisualizer.entity.Event;
import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DetailService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Detail> detailDao;
    private GenericDao<Event> eventDao;
    private EventService eventService;



    public DetailService() {

        detailDao = new GenericDao<>(Detail.class);
        eventDao = new GenericDao<>(Event.class);
    }

    public DetailService(GenericDao<Detail> detailDao, EventService eventService) {
        this.detailDao = detailDao;
        this.eventService = eventService;


    }

    public GenericDao<Detail> getDetailDao() {
        return detailDao;
    }


    public Detail getDetailById(int id) {
        return detailDao.getById(id);
    }

    public Detail jsonToDetail(String json) throws JsonProcessingException {
        return ObjectMapperUtil.extractEntity(json, "detail", Detail.class);
    }

    public Detail addDetailToEvent(String json, Event event) throws JsonProcessingException {
        Detail detail = jsonToDetail(json);
        event.addDetail(detail);
        return detail;
    }


    public void updateDetail(String json) throws JsonProcessingException {
        JsonNode node = ObjectMapperUtil.getMapper().readTree(json);
        Detail detailToUpdate = detailDao.getById(node.get("id").asInt());
        Detail updatedDetail = ObjectMapperUtil.getMapper().updateValue(detailToUpdate, node);
        detailDao.update(updatedDetail);
    }


    public void insertDetail(Event event, Detail detail) {
        GenericDao<Event> eventDao = new GenericDao<Event>(Event.class);
        GenericDao<Notebook> nbd = new GenericDao<>(Notebook.class);
        Notebook nb = event.getNotebook();
        if (detail != null) {
            event.addDetail(detail);
            //eventDao.update(event);
            nbd.update(nb);
        }
    }

    public void deleteDetail(int id) {
        Detail detail = detailDao.getById(id);
        if (detail != null) {
            Event event = eventDao.getById(detail.getEvent().getId());
            if (event != null) {
                event.removeDetail(detail);
                eventDao.update(event);
                detailDao.delete(detail);
            }

        }
    }

}
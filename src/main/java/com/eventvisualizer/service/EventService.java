package com.eventvisualizer.service;

import com.eventvisualizer.entity.Event;
import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;


public class EventService {
    private final GenericDao<Event> eventDao;
   private final NotebookService notebookService;


    public EventService (GenericDao<Event> eventDao, NotebookService notebookService) {
        this.eventDao = eventDao;
        this.notebookService = notebookService;
    }

    public GenericDao<Event> getEventDao() {
        return eventDao;
    }

    public Event getEventById(int id) {
        return eventDao.getById(id);
    }

    public Event jsonToEvent(String json) throws JsonProcessingException {
        return ObjectMapperUtil.extractEntity(json, "event", Event.class);
    }

    public Event addEventToNotebook(String json, Notebook notebook) throws JsonProcessingException {
        Event event = jsonToEvent(json);
        if (notebook != null) {
            notebook.addEvent(event);
        }
        return event;
    }

    public void updateEvent(String json) throws JsonProcessingException {
        JsonNode node = ObjectMapperUtil.getJsonNodeFromString(json);
        Event eventToUpdate = getEventById(node.get("id").asInt());
        Event updatedEvent = ObjectMapperUtil.getMapper().updateValue(eventToUpdate, node);
        eventDao.update(updatedEvent);
    }






}
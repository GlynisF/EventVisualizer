package com.eventvisualizer.service;

import com.eventvisualizer.entity.*;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.util.EntityHelper;
import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class CrudService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final GenericDao<Notebook> notebookDao;
    private final GenericDao<User> userDao;
    private final GenericDao<Event> eventDao;


    public CrudService() {
        userDao = new GenericDao<>(User.class);
        notebookDao = new GenericDao<>(Notebook.class);
        eventDao = new GenericDao<>(Event.class);
    }

    public CrudService(GenericDao<Notebook> notebookDao, GenericDao<User> userDao, GenericDao<Event> eventDao) {
        this.notebookDao = notebookDao;
        this.userDao = userDao;
        this.eventDao = eventDao;
    }

    public GenericDao<Notebook> getNotebookDao() {
        return notebookDao;
    }

    public GenericDao<User> getUserDao() {
        return userDao;
    }

    public GenericDao<Event> getEventDao() {return eventDao;}


    public Set<Notebook> getUserNotebooks(int userId) {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(userId);
        return user.getNotebooks();
    }


    public Notebook addNewEventAndNotebook(String json, User user) throws IOException {
        Notebook notebook = ObjectMapperUtil.extractEntity(json, "notebook", Notebook.class);
        Event event = setEntityRelationships(json);
        notebook.addEvent(event);
        user.addNotebook(notebook);
        return notebook;
    }

    public Notebook addEventToExistingNotebook(String json, int notebookId) throws IOException {
        Notebook notebook = getNotebookDao().getById(notebookId);
        Event event = setEntityRelationships(json);
        notebook.addEvent(event);
        event.setNotebook(notebook);
        return notebook;
    }

    public void addNotebook(int userId, String json) throws JsonProcessingException {
        User user = userDao.getById(userId);
        Notebook notebook = ObjectMapperUtil.extractEntity(json, "notebook", Notebook.class);
        user.addNotebook(notebook);
        userDao.update(user);
    }

    public void updateEventDetails(String json, int eventId) {
        try {
            Event eventWithUpdates = ObjectMapperUtil.extractEntity(json, "event", Event.class);
            eventDao.updateWithMerge(eventDao.getById(eventId), eventWithUpdates);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public Event setEntityRelationships(String json) throws IOException {
        Map<String, Object> map = extractEntitiesFromJson(json);
        EntityHelper entityHelper = new EntityHelper(map);
        Event event = entityHelper.get("event", Event.class);

        Goal goal = entityHelper.get("goal", Goal.class);
        event.setGoal(goal);
        goal.setEvent(event);

        Note note = entityHelper.get("note", Note.class);
        event.setNote(note);
        note.setEvent(event);

        Reflection reflection = entityHelper.get("reflection", Reflection.class);
        event.setReflection(reflection);
        reflection.setEvent(event);

        Detail detail = entityHelper.get("detail", Detail.class);
        event.addDetail(detail);

        detail.addLocation(entityHelper.get("location", Location.class));
        List<Performer> performers = entityHelper.getList("performers", Performer.class);
        for (Performer performer : performers) {
            detail.addPerformer(performer);
        }


        return event;
    }


    public Notebook getNotebookById(int notebookId) {
        return notebookDao.getById(notebookId);
    }

    public User getUserById(int userId) {
        return userDao.getById(userId);
    }

    public Map<String, Object> extractEntitiesFromJson(String json) throws IOException {
        Map<String, Class<?>> classMap = Map.of(
                "notebook", Notebook.class,
                "event", Event.class,
                "goal", Goal.class,
                "detail", Detail.class,
                "location", Location.class,
                "performers", Performer.class,
                "note", Note.class,
                "reflection", Reflection.class
        );

        Map<String, Object> entities = new HashMap<>();
        for (Map.Entry<String, Class<?>> entry : classMap.entrySet()) {
            if (entry.getKey().equals("performers")) {
                entities.put(entry.getKey(), ObjectMapperUtil.extractEntityList(json, entry.getKey(), entry.getValue()));
            } else {
                entities.put(entry.getKey(), ObjectMapperUtil.extractEntity(json, entry.getKey(), entry.getValue()));
            }
        }
        return entities;
    }



}
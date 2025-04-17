package com.eventvisualizer.util;

import com.eventvisualizer.entity.Detail;
import com.eventvisualizer.entity.Event;
import com.eventvisualizer.entity.Goal;
import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.persistence.GenericDao;
import com.eventvisualizer.service.EventService;
import com.eventvisualizer.service.GoalService;
import com.eventvisualizer.service.NotebookService;

public class AppContext {

    public static void initialize() {
        System.out.println("in app config");
        // DAOs
        GenericDao<Event> eventDao = new GenericDao<>(Event.class);
        GenericDao<Notebook> notebookDao = new GenericDao<>(Notebook.class);
        GenericDao<Detail> detailDao = new GenericDao<>(Detail.class);
        GenericDao<Goal> goalDao = new GenericDao<>(Goal.class);
        // ... other DAOs

        // Services
        NotebookService notebookService = new NotebookService(notebookDao);
        EventService eventService = new EventService(eventDao, notebookService);
        GoalService goalService = new GoalService(goalDao);
        // ... other services

        // Register services
        ServiceRegistry.register(GoalService.class, goalService);
        ServiceRegistry.register(EventService.class, eventService);
        ServiceRegistry.register(NotebookService.class, notebookService);
        // ... other service registrations
    }
}
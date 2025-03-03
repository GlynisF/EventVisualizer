package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Event;
import com.eventvisualizer.entity.Goal;
import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Event dao test.
 */
class EventDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Event dao.
     */
    GenericDao<Event> eventDao;
    /**
     * The Notebook dao.
     */
    GenericDao<Notebook> notebookDao;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    void setUp() throws Exception {
        eventDao = new GenericDao<>(Event.class);
        notebookDao = new GenericDao<>(Notebook.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info(database);
    }

    /**
     * Gets all events success.
     */
    @Test
    void getAllEventsSuccess() {
        List<Event> events = eventDao.getAll();
        assertNotNull(events);
        logger.info(events);
    }

    /**
     * Gets event by id success.
     */
    @Test
    void getEventByIdSuccess() {
        Event event = eventDao.getById(1);
        assertNotNull(event);
        logger.info(event);
    }

    /**
     * Update event success.
     */
    @Test
    void updateEventSuccess() {
        Event event = eventDao.getById(4);
        assertNotNull(event);
        assertEquals(event.getEventName(), "Rhythm Rebellion");
        event.setEventName("Updated Event Name");
        assertTrue(event.getEventName().equals("Updated Event Name"));
        eventDao.update(event);
        logger.info(event);
    }

    /**
     * Delete event success.
     */
    @Test
    void deleteEventSuccess() {
        Event event = eventDao.getById(3);
        assertNotNull(event);
        eventDao.delete(event);
        Event deletedEvent = eventDao.getById(3);
        assertNull(deletedEvent);
    }

    /**
     * Insert event success.
     */
    @Test
    void insertEventSuccess() {
        Event event = new Event("New Event Inserted");

        Notebook eventParent = notebookDao.getById(7);
        assertNotNull(eventParent);
        assertEquals("High Noon Show", eventParent.getTitle());

        event.setNotebook(eventParent);

        Event insertedEvent = eventDao.insert(event);
        assertNotNull(insertedEvent);

        assertEquals(7, insertedEvent.getNotebook().getId());
    }

    /**
     * Remove notebook from event success.
     */
    @Test
    void removeEventFromNotebookSuccess() {
        Notebook notebookToRemove = notebookDao.getById(8);
        assertNotNull(notebookToRemove);

        notebookDao.delete(notebookToRemove);
        Notebook removedNotebook = notebookDao.getById(8);
        assertNull(removedNotebook);
        assertFalse(notebookDao.getAll().contains(removedNotebook));

        Event event = eventDao.getById(20);
        assertNull(event);

    }

    /**
     * Sets notebook for event success.
     */
    @Test
    void addEventToNotebookSuccess() {
        Event event = new Event("New Event for Setting Notebook");
        event.setNotebook(notebookDao.getById(5));
        assertNotNull(event.getNotebook());

        assertEquals("November Shows", event.getNotebook().getTitle());
        Event insertedEvent = eventDao.insert(event);

        Notebook notebookContainingEvent = notebookDao.getById(5);
        assertTrue(notebookContainingEvent.getEvents().contains(insertedEvent));

        Notebook notebookAddingEvent = notebookDao.getById(3);
        assertEquals("Gamma Ray Event", notebookAddingEvent.getTitle());
        event.setNotebook(null);
        notebookAddingEvent.addEvent(event);
        notebookDao.update(notebookAddingEvent);

        assertTrue(notebookAddingEvent.getEvents().contains(event));

    }


    @Test
    void addGoalToEventSuccess() {
        Event event = new Event("New Event for Setting Goal");
        Event insertedEvent = eventDao.insert(event);
        assertNotNull(event);


        Goal goal = new Goal("This is a description for a new goal");
        insertedEvent.setGoal(goal);
        goal.setEvent(event);

        GenericDao<Goal> goalDao = new GenericDao<>(Goal.class);
        Goal insertedGoal = goalDao.insert(goal);

        assertNotNull(insertedGoal);

        assertTrue(insertedEvent.getGoal().equals(insertedGoal));
        assertTrue(insertedGoal.getEvent().equals(insertedEvent));
        assertTrue(insertedGoal.getEvent().getId() == 37);

        logger.info(insertedEvent);
        logger.info(insertedGoal);
    }

    @Test
    void removeGoalFromEventSuccess() {
        Event event = eventDao.getById(14);
        assertNotNull(event);
        assertTrue(event.getEventName().equals("Bass Cathedral"));

        GenericDao<Goal> goalDao = new GenericDao<>(Goal.class);
        Goal goalToRemove = goalDao.getById(13);
        assertNotNull(goalToRemove);
        assertTrue(goalToRemove.getEvent().equals(event));
        assertTrue(event.getGoal().equals(goalToRemove));

        goalDao.delete(goalToRemove);
        assertFalse(goalDao.getAll().contains(goalToRemove));


    }

}
package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Event;
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
    void removeNotebookFromEventSuccess() {
        Notebook notebookToRemove = notebookDao.getById(8);
        assertNotNull(notebookToRemove);

        Event event = eventDao.getById(20);
        assertNotNull(event);

        assertTrue(notebookToRemove.getEvents().contains(event));

        event.setNotebook(null);
        eventDao.update(event);
        logger.info(event);

        Event updatedEvent = eventDao.getById(20);
        logger.info(updatedEvent);
    }

    /**
     * Sets notebook for event success.
     */
    @Test
    void setNotebookForEventSuccess() {
        Event event = new Event("New Event for Setting Notebook");
        event.setNotebook(notebookDao.getById(5));
        assertNotNull(event.getNotebook());
        assertEquals("November Shows", event.getNotebook().getTitle());
        Event insertedEvent = eventDao.insert(event);

        Notebook setNotebookForEvent = notebookDao.getById(5);
        assertTrue(setNotebookForEvent.getEvents().contains(insertedEvent));
    }

}
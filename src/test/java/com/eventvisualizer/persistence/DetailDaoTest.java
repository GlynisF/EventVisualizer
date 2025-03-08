package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Detail;
import com.eventvisualizer.entity.Event;
import com.eventvisualizer.entity.Performer;
import com.eventvisualizer.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Detail dao test.
 */
class DetailDaoTest {

    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * The Event dao.
     */
    GenericDao<Detail> detailDao;
    /**
     * The Notebook dao.
     */
    GenericDao<Event> eventDao;

    /**
     * Sets up.
     *
     * @throws Exception the exception
     */
    @BeforeEach
    void setUp() throws Exception {
        detailDao = new GenericDao<>(Detail.class);
        eventDao = new GenericDao<>(Event.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info(database);
    }

    /**
     * Gets all details success.
     */
    @Test
    void getAllDetailsSuccess() {
        List<Detail> details = detailDao.getAll();
        assertFalse(details.isEmpty());
        logger.info(details);
    }

    /**
     * Delete detail success.
     */
    @Test
    void deleteDetailSuccess() {
        Detail detailToDelete = detailDao.getById(33);
        assertNotNull(detailToDelete);

        String description = "Past and future sounds merge into an unforgettable sonic experience.";
        assertTrue(description.equals(detailToDelete.getDescription()));

        detailDao.delete(detailToDelete);
        assertNull(detailDao.getById(33));
    }

    /**
     * Insert detail success.
     */
    @Test
    void insertDetailSuccess() {
        String description = "Sounds from the underground";
        Detail newDetail = new Detail(LocalDate.parse("2025-01-01"), LocalTime.parse("21:00"), LocalTime.parse("02:00"), description);
        Event event = eventDao.getById(15);
        event.addDetail(newDetail);

        Detail insertedDetail = detailDao.insert(newDetail);
        assertNotNull(insertedDetail);

        assertTrue(insertedDetail.getDescription().equals(description));
        logger.info(insertedDetail);

    }

    /**
     * Update detail success.
     */
    @Test
    void updateDetailSuccess() {
        Detail detailToUpdate = detailDao.getById(7);
        assertNotNull(detailDao);
        assertTrue(detailToUpdate.getDescription().equals("A glowing spectacle of sound and light in an immersive rave atmosphere."));

        String updatedDescription = ("This is an updated description.");
        detailToUpdate.setDescription(updatedDescription);
        detailDao.update(detailToUpdate);

        Detail updatedDetail = detailDao.getById(7);
        assertNotNull(updatedDetail);
        assertEquals("This is an updated description.", updatedDetail.getDescription());
        logger.info(updatedDetail);

    }

    /**
     * Add event with detail success.
     */
    @Test
    void addEventWithDetailSuccess() {
        String description = ("Description for adding an event to a detail");

        Event eventWithDetail = new Event("New Event with Detail");

        Detail newDetail = new Detail(LocalDate.parse("2025-06-11"), LocalTime.parse("22:30"),
                LocalTime.parse("03:00"), description);

        eventWithDetail.addDetail(newDetail);
        eventDao.insert(eventWithDetail);

        List<Detail> detailList = detailDao.findByPropertyEqual("description", description);
        assertFalse(detailList.isEmpty());
        int id = detailList.get(0).getId();
        logger.info(detailList);

        Detail insertedDetail = detailDao.getById(id);
        assertNotNull(insertedDetail);

        Set<Detail> details = eventWithDetail.getDetails();
        assertNotNull(details);
        logger.info(details);

        assertTrue(eventWithDetail.getDetails().contains(insertedDetail));

    }

    /**
     * Remove event with detail success.
     */
    @Test
    void removeEventWithDetailSuccess() {
        Event eventToDelete = eventDao.getById(10);
        assertNotNull(eventToDelete);
        assertTrue(eventToDelete.getEventName().equals("Afterdark Sessions"));

        Detail detailOfEvent = detailDao.getById(10);
        assertNotNull(detailOfEvent);
        assertTrue(eventToDelete.getDetails().contains(detailOfEvent));

        eventDao.delete(eventToDelete);
        assertNull(eventDao.getById(10));
        assertNull(detailDao.getById(10));
    }

    /**
     * Add performer to detail success.
     */
    @Test
    void addPerformerSuccess() {
        GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);
        Detail detailToAdd = detailDao.getById(1);
        assertNotNull(detailToAdd);

        Performer performerToAdd = new Performer("Joe Jackson", "DJ JK", "DJJJ@email.com", BigDecimal.valueOf(500.00));
        performerDao.insert(performerToAdd);

        assertTrue(performerToAdd.getId() > 0);

        detailToAdd.addPerformer(performerToAdd);
        detailDao.update(detailToAdd);

        Performer insertedPerformer = performerDao.getById(performerToAdd.getId());
        assertNotNull(insertedPerformer);

        Set<Performer> performers = detailToAdd.getPerformers();
        assertNotNull(performers);

        assertTrue(performers.contains(insertedPerformer));
        assertTrue(performerDao.getAll().contains(insertedPerformer));

    }

    /**
     * Remove performer from detail success.
     */
    @Test
    void removePerformerSuccess() {
    GenericDao<Performer> performerDao = new GenericDao<>(Performer.class);

    Detail detailContainingPerformer = detailDao.getById(7);
    assertNotNull(detailContainingPerformer);

    Performer performerToRemove = performerDao.getById(14);
    assertNotNull(performerToRemove);

    assertTrue(detailContainingPerformer.getPerformers().contains(performerToRemove));

    detailContainingPerformer.removePerformer(performerToRemove);

    detailDao.update(detailContainingPerformer);

    assertFalse(detailContainingPerformer.getPerformers().contains(performerToRemove));


    }

}
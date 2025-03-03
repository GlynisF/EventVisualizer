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
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The type Performer dao test.
 */
class PerformerDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * The Performer dao.
     */
    GenericDao<Performer> performerDao;
    /**
     * The Detail dao.
     */
    GenericDao<Detail> detailDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        performerDao= new GenericDao<>(Performer.class);
        detailDao = new GenericDao<>(Detail.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info(database);

    }

    /**
     * Gets all s performers success.
     */
    @Test
    void getAllSPerformersSuccess() {
        List<Performer> performers = performerDao.getAll();
        assertNotNull(performers);
        logger.info(performers);

    }

    /**
     * Insert performer success.
     */
    @Test
    void insertPerformerSuccess() {
        Performer performer = new Performer("Glynis Fisher", "Dj Glynis", "djglynis@email.com", BigDecimal.valueOf(200.50));
        performerDao.insert(performer);
        assertNotNull(performerDao.getById(performer.getId()));
        int id = performer.getId();

        Performer insertedPerformer = performerDao.getById(id);
        assertNotNull(insertedPerformer);
        assertTrue(performer.equals(insertedPerformer));
    }

    /**
     * Update performer success.
     */
    @Test
    void updatePerformerSuccess() {
        Performer performerToUpdate = performerDao.getById(1);
        assertNotNull(performerToUpdate);
        assertTrue(performerToUpdate.getMoniker().equals("DJ Alice"));

        performerToUpdate.setMoniker("Glynis Fisher");
        performerDao.update(performerToUpdate);

        Performer updatedPerformer = performerDao.getById(1);
        assertNotNull(updatedPerformer);
        assertTrue(updatedPerformer.getMoniker().equals("Glynis Fisher"));
        assertTrue(performerToUpdate.equals(updatedPerformer));
    }

    /**
     * Delete performer success.
     */
    @Test
    void deletePerformerSuccess() {
        Performer performerToDelete = performerDao.getById(15);
        assertNotNull(performerToDelete);
        assertTrue(performerToDelete.getFullName().equals("Omar Platinum"));
        performerDao.delete(performerToDelete);
        Performer deletedPerformer = performerDao.getById(15);
        assertNull(deletedPerformer);

        List<Performer> performers = performerDao.getAll();
        assertTrue(!performers.contains(performerToDelete));
    }

    /**
     * Sets event success.
     */
    @Test
    void setEventSuccess() {
        GenericDao<Event> eventDao = new GenericDao<>(Event.class);

        Detail detail = detailDao.getById(15);
        assertNotNull(detail);

        Event eventContainingDetail = eventDao.getById(15);
        assertNotNull(eventContainingDetail);

        assertTrue(eventContainingDetail.getDetails().contains(detail));

        Event eventToSetForDetail = eventDao.getById(11);

        detail.setEvent(eventToSetForDetail);
        eventDao.update(eventToSetForDetail);
        detailDao.update(detail);

        Detail updatedDetail = detailDao.getById(15);
        assertNotNull(updatedDetail);

        assertTrue(updatedDetail.equals(detail));

        assertTrue(!updatedDetail.getEvent().equals(eventContainingDetail));
        assertTrue(updatedDetail.getEvent().getId() == 11);


    }

    /**
     * Remove event success.
     */
    @Test
    void removeEventSuccess() {
        Detail detail = detailDao.getById(26);
        assertNotNull(detail);

        GenericDao<Event> eventDao = new GenericDao<>(Event.class);
        Event eventToDelete = eventDao.getById(26);
        assertNotNull(eventToDelete);

        assertTrue(detail.getEvent().getId() == 26);

        detail.setEvent(null);
        eventDao.delete(eventToDelete);
        assertNull(eventDao.getById(26));

        detailDao.update(detail);
        assertNull(detail.getEvent());

        logger.info(detail);
    }

    /**
     * Add detail to performer success.
     */
    @Test
    void addDetailToPerformerSuccess() {
        Detail detailToAdd = detailDao.getById(1);
        assertNotNull(detailToAdd);

        Performer performerToAdd = new Performer("Joe Jackson", "DJ JK", "DJJJ@email.com", BigDecimal.valueOf(500.00));
        performerDao.insert(performerToAdd);

        detailToAdd.addPerformer(performerToAdd);
        detailDao.update(detailToAdd);

        assertTrue(performerToAdd.getId() > 0);

        Performer insertedPerformer = performerDao.getById(performerToAdd.getId());
        assertNotNull(insertedPerformer);

        assertTrue(detailToAdd.getPerformers().contains(insertedPerformer));

        assertTrue(performerDao.getAll().contains(performerToAdd));
    }

    /**
     * Delete detail from performer success.
     */
    @Test
    void deleteDetailFromPerformerSuccess() {
        Detail detailToDelete = detailDao.getById(2);
        assertNotNull(detailToDelete);

        Performer performerBelongingToDetail1 = performerDao.getById(4);
        assertNotNull(performerBelongingToDetail1);

        Performer performerBelongingToDetail2 = performerDao.getById(3);
        assertNotNull(performerBelongingToDetail2);

        Set<Performer> performers = detailToDelete.getPerformers();
        assertNotNull(performers);

        assertTrue(performers.contains(performerBelongingToDetail1));
        assertTrue(performers.contains(performerBelongingToDetail2));

        detailDao.delete(detailToDelete);
        assertNull(detailDao.getById(2));

        assertNull(performerDao.getById(4));
        assertNull(performerDao.getById(3));

    }
}
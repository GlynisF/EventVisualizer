package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Detail;
import com.eventvisualizer.entity.Event;
import com.eventvisualizer.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    @Test
    void getAll() {
        List<Detail> details = detailDao.getAll();
        assertFalse(details.isEmpty());
        logger.info(details);
    }

    @Test
    void delete() {
        Detail detailToDelete = detailDao.getById(33);
        assertNotNull(detailToDelete);

        String description = "Past and future sounds merge into an unforgettable sonic experience.";
        assertTrue(description.equals(detailToDelete.getDescription()));

        detailDao.delete(detailToDelete);
        assertNull(detailDao.getById(33));
    }

    @Test
    void insert() {
        String description = "Sounds from the underground";
        Detail newDetail = new Detail(LocalDate.parse("2025-01-01"), LocalTime.parse("21:00"), LocalTime.parse("02:00"),
                description);
        Event event = eventDao.getById(15);
        event.addDetail(newDetail);

        Detail insertedDetail = detailDao.insert(newDetail);
        assertNotNull(insertedDetail);

        assertTrue(insertedDetail.getDescription().equals(description));
        logger.info(insertedDetail);

    }

    @Test
    void update() {
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
}
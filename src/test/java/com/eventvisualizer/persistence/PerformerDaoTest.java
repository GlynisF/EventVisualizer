package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Detail;
import com.eventvisualizer.entity.Performer;
import com.eventvisualizer.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class PerformerDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());

    GenericDao<Performer> performerDao;
    GenericDao<Detail> detailDao;

    @BeforeEach
    void setUp() {
        performerDao= new GenericDao<>(Performer.class);
        detailDao = new GenericDao<>(Detail.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info(database);

    }

    @Test
    void getAllSPerformersSuccess() {
        List<Performer> performers = performerDao.getAll();
        assertNotNull(performers);
        logger.info(performers);

    }
}
package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.eventvisualizer.test.util.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Dao service test.
 */
class DaoServiceTest {
    private final ObjectMapper mapper = new ObjectMapper();
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final Map<String, Class<?>> entityClassMap = new HashMap<>();
    private DaoService daoService;

    /**
     * Sets up requirements to run before each test.
     */
    @BeforeEach
    void setUp() {
        daoService = new DaoService();
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        logger.info(database);

    }

    /**
     * Gets dao service entity by id success.
     */
    @Test
    void getDaoServiceEntityByIdSuccess() {
        String entityName = "users";
        int id = 5;
        User user = daoService.getEntityById(entityName, id);
        assertNotNull(user);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User comparedUser = userDao.getById(5);
        assertNotNull(comparedUser);
        assertTrue(comparedUser.equals(user));
    }

    /**
     * Insert dao service entity success.
     */
    @Test
    void insertDaoServiceEntitySuccess() {
        String entityName = "users";
        Map<String, Object> userMap = Map.of(
                "firstName", "Glynis",
                "lastName", "Fisher",
                "username", "gfisher2",
                "password", "student",
                "dateOfBirth", LocalDate.parse("1992-08-11")
        );
        User insertedUser = daoService.insertNewEntity(entityName, userMap);
        assertNotNull(insertedUser);
        int id = insertedUser.getId();
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User comparedUser = userDao.getById(id);
        assertNotNull(comparedUser);
        assertTrue(comparedUser.equals(insertedUser));
    }

    /**
     * Delete dao service entity success.
     */
    @Test
    void deleteDaoServiceEntitySuccess() {
        String entityName = "notebooks";
        int id = 3;
        GenericDao<Notebook> notebookDao = new GenericDao<>(Notebook.class);
        Notebook notebook = notebookDao.getById(id);
        assertNotNull(notebook);
        daoService.deleteEntity(entityName, id);
        assertNull(notebookDao.getById(id));
        Notebook comparedNotebook = notebookDao.getById(id);
        assertNull(comparedNotebook);
    }


    /**
     * Entity not found exception thrown.
     *
     * @throws EntityNotFoundException the entity not found exception
     */
    @Test
    void entityNotFoundExceptionThrown() throws EntityNotFoundException {
        Map<String, Object> userMap = Map.of(
                "firstName", "Glynis",
                "lastName", "Fisher",
                "username", "gfisher2",
                "password", "student",
                "dateOfBirth", LocalDate.parse("1992-08-11")
        );
        Exception exception = assertThrows(EntityNotFoundException.class, () ->
        {daoService.insertNewEntity("user", userMap);
        });

        String expectedMessage = "Entity name not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
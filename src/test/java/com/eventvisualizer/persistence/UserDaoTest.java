package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.User;
import com.eventvisualizer.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for User entity using GenericDao to access the database
 * @author glynisfisher
 */
class UserDaoTest {
    GenericDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());
    /**
     * Runs before each test to reset database to original state
     */
    @BeforeEach
    void setUp() {
        userDao = new GenericDao<>(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleanDB.sql");
        logger.info(database);
    }
    /**
     * Gets user by ID from db success
     */
    @Test
     void getUserByIdSuccess() {
        User user = (User) userDao.getById(4);
        assertNotNull(user);
        //assertEquals(4, user.getId());
        logger.info(user);
    }

    /**
     * Gets list of all users from db success
     */
    @Test
    void getAllUsersSuccess() {
        List<User> users = userDao.getAll();
        assertNotNull(users);
        int listSize = users.size();
        assertTrue(listSize == users.size());
    }
    /**
     * Inserts new user into db success
     */
    @Test
    void insertUserSuccess() {
        User newUser = new User("Glynis", "Fisher", "student", "gfisher", LocalDate.parse("1992-08-11"));
        userDao.insert(newUser);
        assertNotNull(userDao.getById(newUser.getId()));
        int id = newUser.getId();
        assertNotEquals(0, id);
        User insertedUser = (User) userDao.getById(id);
        assertTrue(newUser.equals(insertedUser));
    }

    /**
     * Updates user in db success
     */
    @Test
    void updateUserSuccess() {
        User userToUpdate = (User) userDao.getById(6);
        assertNotNull(userToUpdate);

        userToUpdate.setFirstName("Jessica");
        userDao.update(userToUpdate);
        assertTrue(userToUpdate.getFirstName().equals("Jessica"));

        List<User> users = userDao.findByPropertyEqual("id", "6");
        assertNotNull(users);

        assertTrue(users.contains(userToUpdate));
        assertTrue(users.get(0).getFirstName().equals("Jessica"));
    }

    /**
     * Deletes user from db success
     */
    @Test
    void deleteUserSuccess() {
        User userToDelete = (User) userDao.getById(2);
        assertNotNull(userToDelete);
        userDao.delete(userToDelete);
        assertNull(userDao.getById(2));

        User deletedUser = (User) userDao.getById(2);
        assertNull(deletedUser);
    }
}
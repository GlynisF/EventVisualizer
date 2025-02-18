package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.eventvisualizer.test.util.Database;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for User entity using GenericDao to access the database
 *
 * @author glynisfisher
 */
class UserDaoTest {
    /**
     * The User dao.
     */
    GenericDao<User> userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Runs before each test to reset database to original state
     */
    @BeforeEach
    void setUp() {
        userDao = new GenericDao<User>(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info(database);
    }

    /**
     * Gets user by ID from db success
     */
    @Test
    void getUserByIdSuccess() {
        User user = (User) userDao.getById(4);
        assertNotNull(user);
        assertTrue("Karen".equals(user.getFirstName()));
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
        logger.info(insertedUser);
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

    /**
     * Find by property equal success.
     */
    @Test
    void findByPropertyEqualSuccess() {
        String searchTerm = "Dawn";
        List<User> users = userDao.findByPropertyEqual("firstName", searchTerm);
        assertNotNull(users);
        assertTrue(searchTerm.equals(users.get(0).getFirstName()));
        User user = (User) userDao.getById(6);
        assertNotNull(user);
        assertTrue(user.equals(users.get(0)));
    }

    /**
     * Find by property map equal success.
     */
    @Test
    void findByPropertyMapEqualSuccess() {
        Map<String, Object> map = new HashMap<>();
        List<User> users = userDao.getAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);

        map.put("firstName", users.get(0).getFirstName());
        map.put("lastName", users.get(0).getLastName());
        map.put("id", users.get(0).getId());

        List<User> userList = userDao.findByPropertyMapEqual(map);

        assertNotNull(userList);
        assertTrue(userList.size() > 0);
        assertTrue("Joe".equals(userList.get(0).getFirstName()));

    }

    @Test
    void addNotebookToUserSuccess() {
        GenericDao<Notebook> notebookDao = new GenericDao<Notebook>(Notebook.class);
        Notebook notebookToAddToUser = new Notebook("Adding Notebook to User");

        User userNotebookOwner = userDao.getById(4);
        assertNotNull(userNotebookOwner);

        userNotebookOwner.addNotebook(notebookToAddToUser);
        notebookToAddToUser.setUser(userNotebookOwner);
        Notebook insertedNotebook = notebookDao.insert(notebookToAddToUser);
        assertNotNull(insertedNotebook);

        List<Notebook> notebooks = userNotebookOwner.getNotebooks();
        assertTrue(notebooks.contains(notebookToAddToUser));

        logger.info(notebooks);
    }

    /**
     * Remove notebook from user success.
     */
    @Test
    void removeNotebookFromUserSuccess() {
        GenericDao<Notebook> notebookDao = new GenericDao<Notebook>(Notebook.class);
        Notebook notebookToDelete = notebookDao.getById(4);
        assertNotNull(notebookToDelete);
        assertTrue(notebookToDelete.getTitle().equals("Surprise Party"));
        logger.info(notebookToDelete);

        User user = notebookToDelete.getUser();
        assertNotNull(user);

        user.removeNotebook(notebookToDelete);
        notebookDao.update(notebookToDelete);

        List<Notebook> notebooks = user.getNotebooks();
        assertTrue(!notebooks.contains(notebookToDelete));

        assertNull(notebookToDelete.getUser());

        logger.info(notebooks);
        logger.info(notebookToDelete);
    }

    /**
     * Entity not found exception success.
     *
     * @throws EntityNotFoundException the entity not found exception
     */
    @Test
    void entityNotFoundExceptionSuccess() throws EntityNotFoundException {
        User user = userDao.getById(25);
        assertNull(user);
        logger.info(user);
    }

    /**
     * Exception thrown success.
     *
     * @throws Exception the exception
     */
    @Test
    void exceptionThrownSuccess() throws Exception {
        List<User> users = userDao.findByPropertyEqual("Glyniss", "first_name");
        assertNull(users);
        User user = userDao.getById(30);
        assertNull(user);
    }

}
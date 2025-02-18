package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.eventvisualizer.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Notebook dao test.
 */
class NotebookDaoTest {


    private final Logger logger = LogManager.getLogger(this.getClass());
    private GenericDao<Notebook> notebookDao;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        notebookDao = new GenericDao<>(Notebook.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
        logger.info(database);
    }

    /**
     * Gets all notebooks success.
     */
    @Test
    void getAllNotebooksSuccess() {
        List<Notebook> notebooks = notebookDao.getAll();
        assertNotNull(notebooks);
        assertTrue(notebooks.size() > 0);
    }

    /**
     * Gets notebook by id success.
     */
    @Test
    void getNotebookByIdSuccess() {
        Notebook notebook = notebookDao.getById(1);
        assertNotNull(notebook);
        assertTrue("October Events".equals(notebook.getTitle()));
    }

    /**
     * Insert notebook success.
     */
    @Test
    void insertNotebookSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(3);

        Notebook newNotebook = new Notebook("New Notebook");
        user.addNotebook(newNotebook);
        newNotebook.setUser(user);

        Notebook insertedNotebook = notebookDao.insert(newNotebook);
        assertNotNull(insertedNotebook);

        int id = insertedNotebook.getId();


        assertNotNull(insertedNotebook);
        //assertTrue(newNotebook.equals(insertedNotebook));
        //assertTrue("New Notebook".equals(newNotebook.getTitle()));
    }

    /**
     * Update notebook success.
     */
    @Test
    void updateNotebookSuccess() {
        Notebook notebookToUpdate = notebookDao.getById(1);
        assertNotNull(notebookToUpdate);
        notebookToUpdate.setTitle("Updated Title");
        notebookDao.update(notebookToUpdate);
        Notebook updatedNotebook = notebookDao.getById(1);
        assertTrue("Updated Title".equals(updatedNotebook.getTitle()));
    }

    /**
     * Delete notebook success.
     */
    @Test
    void deleteNotebookSuccess() {
        Notebook notebookToDelete = notebookDao.getById(12);
        assertNotNull(notebookToDelete);
        assertTrue("Valentine's Day Show".equals(notebookToDelete.getTitle()));
        notebookDao.delete(notebookToDelete);
        assertNull(notebookDao.getById(12));
    }

    /**
     * Find notebook by property equal success.
     */
    @Test
    void findNotebookByPropertyEqualSuccess() {
        String title = "Birthday Party";

        Notebook notebookToCompare = notebookDao.getById(2);
        assertNotNull(notebookToCompare);

        List<Notebook> foundNotebook = notebookDao.findByPropertyEqual("title", title);
        assertNotNull(foundNotebook);

        assertTrue(foundNotebook.get(0).getUser().getFirstName().equals("Fred"));
        assertTrue(notebookToCompare.getTitle().equals(foundNotebook.get(0).getTitle()));
    }


    /**
     * Find notebook by property map equal success.
     */
    @Test
    void findNotebookByPropertyMapEqualSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(5);
        assertNotNull(user);

        Map<String, Object> map = new HashMap();
        map.put("title", "Sylvee Show");
        map.put("user", user);

        List<Notebook> notebooks = notebookDao.findByPropertyMapEqual(map);
        assertFalse(notebooks.isEmpty());

        assertTrue(notebooks.get(0).getUser().getFirstName().equals("Dianne"));
        assertEquals(11, notebooks.get(0).getId());
        logger.info(notebooks);

    }

    /**
     * Add user to notebook success.
     */
    @Test
    void addUserToNotebookSuccess() {
        GenericDao<User> userDao = new GenericDao<User>(User.class);
        User userToAdd = userDao.getById(6);
        assertNotNull(userToAdd);

        Notebook notebook = new Notebook("Adding user to notebook");
        notebook.setUser(userToAdd);

        userToAdd.addNotebook(notebook);
        userDao.update(userToAdd);

        User updatedUser = userDao.getById(6);
        assertTrue(userToAdd.equals(updatedUser));

        List<Notebook> updatedNotebooks = notebookDao.findByPropertyEqual("title", "Adding user to notebook");
        assertTrue(!updatedNotebooks.isEmpty());
        assertTrue(updatedNotebooks.get(0).getUser().equals(updatedUser));

        logger.info(updatedNotebooks);
    }

    /**
     * Remove user from notebook success.
     */
    @Test
    void removeUserFromNotebookSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);

        User userToRemove = userDao.getById(3);
        assertNotNull(userToRemove);

        Notebook notebookContainingUser = notebookDao.getById(3);
        assertNotNull(notebookContainingUser);

        List<Notebook> notebooks = userToRemove.getNotebooks();
        assertTrue(notebooks.contains(notebookContainingUser));

        userToRemove.removeNotebook(notebookContainingUser);
        userDao.update(userToRemove);

        assertNull(notebookContainingUser.getUser());

        assertFalse(userToRemove.getNotebooks().contains(notebookContainingUser));

    }


}
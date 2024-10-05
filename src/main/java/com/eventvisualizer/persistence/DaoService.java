package com.eventvisualizer.persistence;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * The type Dao service.
 * Service layer for GenericDao and REST API communication.
 *
 * @author glynisfisher
 */
public class DaoService {

    private final ObjectMapper mapper;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final Map<String, Class<?>> entityClassMap = new HashMap<>();

    /**
     * Instantiates a new Dao service.
     */
    public DaoService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        // Populate entity map with entity names and corresponding classes
        entityClassMap.put("users", User.class);
        entityClassMap.put("notebooks", Notebook.class);
        // Add other entities as needed
    }

    /**
     * Gets entity from the database by id.
     *
     * @param <T>        the type parameter
     * @param entityName the entity name
     * @param id         the id
     * @return the entity by id
     * @throws EntityNotFoundException problem retrieving entity
     */
    public <T> T getEntityById(String entityName, Integer id) {
        GenericDao<T> genericDao = getEntityDao(entityName);
        T entity = genericDao.getById(id);
        if (entity == null) {
            logger.error("Entity {} with ID {} not found", entityName, id);
            throw new EntityNotFoundException(entityName);
        }
        return entity;
    }

    /**
     * Inserts new entity into the database.
     *
     * @param <T>        the type parameter
     * @param entityName the entity name
     * @param entityData the entity data
     * @return the inserted entity
     */
// Generic method to insert a new entity from provided data
    public <T> T insertNewEntity(String entityName, Map<String, Object> entityData) {
        GenericDao<T> genericDao = getEntityDao(entityName);
        Class<T> entityClass = getEntityClass(entityName);
        T mappedEntity = mapper.convertValue(entityData, entityClass);
        T newEntity = genericDao.insert(mappedEntity);

        if (newEntity == null) {
            logger.error("Failed to insert entity {}: {}", entityName, entityData);
            throw new EntityNotFoundException(entityName);
        }
        return newEntity;
    }

    /**
     * Deletes entity from the database.
     *
     * @param <T>        the type parameter
     * @param entityName the entity name
     * @param id         the id
     */
    public <T> void deleteEntity(String entityName, Integer id) {
        GenericDao<T> genericDao = getEntityDao(entityName);
        T entity = genericDao.getById(id);

        if (entity == null) {
            logger.error("Entity {} with ID {} not found for deletion", entityName, id);
            throw new EntityNotFoundException(entityName);
        }

        genericDao.delete(entity);
        logger.info("Entity {} with ID {} successfully deleted", entityName, id);
    }

    /**
     * Helper method to retrieve the GenericDao based off the entity name.
     * @param entityName
     * @param entityName the entity's name
     * @return the entity's class name
     */
    private <T> GenericDao<T> getEntityDao(String entityName) {
        Class<T> entityClass = getEntityClass(entityName);
        return new GenericDao<>(entityClass);
    }

    /**
     * Helper method to retrieve the entity class from the entity name
     *
     * @param entityName the entity's name
     * @return the entity's class name
     */
    @SuppressWarnings("unchecked")
    private <T> Class<T> getEntityClass(String entityName) {
        Class<?> entityClass = entityClassMap.get(entityName.toLowerCase());
        if (entityClass == null) {
            logger.error("Entity name not found: {}", entityName);
            throw new EntityNotFoundException("Entity name not found: " + entityName);
        }
        return (Class<T>) entityClass;
    }
}
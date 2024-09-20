package com.eventvisualizer.persistence;


import com.eventvisualizer.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

/**
 * The type Dao service.
 * Service layer for GenericDao and REST api communication.
 * @Author glynisfisher
 */
public class DaoService {

    private ObjectMapper mapper;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public DaoService() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public <T> Object getEntityById(String entityName, Integer id) {
        GenericDao<T> genericDao = getEntityDaoFromPathParam(entityName);
        Object entity = genericDao.getById(id);
        if (entity == null) {
            throw new EntityNotFoundException(entityName);
        }
        return entity;
    }

    public <T> T insertNewEntity(String entityName, Map<String, Object> entityData) {
        GenericDao<T> genericDao = getEntityDaoFromPathParam(entityName);
        if (genericDao == null) {
            throw new EntityNotFoundException(entityName);
        }
        Class<T> entityClass = (Class<T>) getEntityClassFromPathParam(entityName);
        T mappedEntity = mapper.convertValue(entityData, entityClass);
        T newEntity = (T) genericDao.insert(mappedEntity);
        if (newEntity == null) {
            throw new EntityNotFoundException(entityName);
        }
        return mappedEntity;
    }

    public <T> void deleteEntity(String entityName, Integer id) {
        boolean isDeleted = false;
        Class<T> entityClass = (Class<T>) getEntityClassFromPathParam(entityName);
        GenericDao<T> genericDao = new GenericDao<>(entityClass);
        Object entity = (T) genericDao.getById(id);
        logger.info(entity);

        genericDao.delete((T) entity);


    }


    public <T> GenericDao<T> getEntityDaoFromPathParam(String entityName) {
        if("users".equalsIgnoreCase(entityName)) {
            return (GenericDao<T>) new GenericDao<>(User.class);
        } else {
            logger.warn("Entity name was not found. {} ", entityName);
            throw new EntityNotFoundException("Entity name was not found.");
        }
    }

    /**
     * Gets entity class by name.
     *
     * @param className name of entity
     * @return instance of entity class.
     */
    @SneakyThrows
    private Class<?> getEntityClassFromPathParam(String className) {
        switch (className.toLowerCase()) {
            case "users":
                return User.class;
            default:
                throw new IllegalAccessException("Error. No match was found for entity" + className);
        }
    }

}
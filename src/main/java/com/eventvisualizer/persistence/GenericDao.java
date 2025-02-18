package com.eventvisualizer.persistence;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericDao<T> {

    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public GenericDao() {
    }

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = (Class<T>) type;
    }

    /**
     * Gets all entities
     *
     * @return the all entities
     */
    public List<T> getAll() {
        List<T> list = new ArrayList<>();

        try (Session session = getSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            list = session.createSelectionQuery(query).getResultList();
            return list;
        } catch (Exception e) {
            logger.error("There was an error retrieving all entities : {} ", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Gets an entity by id
     * @param id entity id to search by
     * @return entity
     */
    public <T> T getById(int id) {
        try (Session session = getSession()) {
            return (T) session.get(type, id);
        } catch (EntityNotFoundException e) {
            logger.error("Entity with the ID {} was not found: {} ", id, e.getMessage(), e);
        } catch (Exception e) {
            logger.error("There was a problem: {} ", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.remove(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                logger.error("Problem deleting entity: {} ", e.getMessage(), e);
            }
        }
    }

    /**
     * Inserts the entity.
     *
     * @param entity entity to be inserted
     */
    public <T> T insert(T entity) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.persist(entity);
                transaction.commit();
                return entity;
            } catch (Exception e) {
                transaction.rollback();
                logger.error("Error inserting entity: {} ", e.getMessage(), e);
            }
        }
        return null;
    }

    /**
     * Inserts or updates the entity.
     *
     * @param entity entity to be inserted/saved
     */
    public void update(T entity) {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.merge(entity);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                logger.error("Error updating entity: {} ", e.getMessage(), e);
            }
        }
    }

    /**
     * Finds entities by one of its properties.
     * sample usage: findByPropertyEqual("lastname", "Curry")
     * @param propertyName the property name.
     * @param value the value by which to find.
     * @return the list of all entities found matching the criteria
     */
    public List<T> findByPropertyEqual(String propertyName, Object value) {
        try (Session session = getSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            query.select(root).where(builder.equal(root.get(propertyName), value));
            return session.createSelectionQuery(query).getResultList();
        } catch (Exception e) {
            logger.error("Error finding entities by property: {} ", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Finds entities by multiple properties.
     * Inspired by https://stackoverflow.com/questions/11138118/really-dynamic-jpa-criteriabuilder

     * @param propertyMap property and value pairs
     * @return entities with properties equal to those passed in the map
     *
     *
     */
    public List<T> findByPropertyMapEqual(Map<String, Object> propertyMap) {
        try (Session session = getSession()) {
            HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);
            List<Predicate> predicates = new ArrayList<>();

            for (Map.Entry entry : propertyMap.entrySet()) {
                predicates.add(builder.equal(root.get((String) entry.getKey()), entry.getValue()));
            }
            query.select(root).where(builder.and(predicates.toArray(new Predicate[0])));
            return session.createSelectionQuery(query).getResultList();
        } catch (Exception e) {
            logger.error("Error finding entities by property map: {} ", e.getMessage(), e);
        }
        return null;
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}
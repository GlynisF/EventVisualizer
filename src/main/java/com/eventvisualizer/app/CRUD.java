package com.eventvisualizer.app;

import com.eventvisualizer.entity.User;
import com.eventvisualizer.persistence.DaoService;
import com.eventvisualizer.persistence.GenericDao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;


@Path("/api")
public class CRUD {

    private final ObjectMapper mapper;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private DaoService daoService;

    public CRUD() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        daoService = new DaoService();
    }

    @GET
    @Path("/{entityType}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntity(@PathParam("entityType") String entityType, @PathParam("id") Integer id) {
        Object entity = daoService.getEntityById(entityType, id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(200).entity(entity).build();

    }

    @POST
    @Path("/add={entityType}")
    @Produces(MediaType.APPLICATION_JSON)
    public <T> Response addEntity(@PathParam("entityType") String entityType, Map<String, Object> entityData) {
        T newEntity = daoService.insertNewEntity(entityType, entityData);
        logger.info(newEntity.toString());
        logger.info(entityData);
        if (newEntity == null) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
        return Response.status(Response.Status.CREATED).entity(newEntity).build();
    }

    @DELETE
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    @Path("/delete={entityType}/{id}")
    public Response removeEntity(@PathParam("entityType") String entityType, @PathParam("id") Integer id) {
        daoService.deleteEntity(entityType, id);
        logger.info(entityType);

        return Response.ok("The entity was removed successfully.").build();

    }




    @SuppressWarnings("unchecked")
    public <T> GenericDao<T> getEntityDao(String entityName) {
        if("users".equals(entityName)) {
            return (GenericDao<T>) new GenericDao<>(User.class);
        } else {
            logger.warn("entity name was not found. {} ", entityName);
            throw new EntityNotFoundException("entity name was not found.");
        }
    }

    private Class<?> getEntityType(String entityName) {
        switch (entityName) {
            case "users":
                return User.class;
            default:
                return null;
        }
    }

}
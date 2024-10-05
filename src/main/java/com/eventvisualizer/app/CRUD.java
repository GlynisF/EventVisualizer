package com.eventvisualizer.app;

import com.eventvisualizer.persistence.DaoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * The type Crud.
 */
@Path("/api")
public class CRUD {

    private final ObjectMapper mapper;
    private final Logger logger = LogManager.getLogger(this.getClass());
    private final DaoService daoService;

    /**
     * Instantiates a new Crud.
     */
    public CRUD() {
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        daoService = new DaoService();
    }

    /**
     * Gets entity.
     *
     * @param entityType the entity type
     * @param id         the id
     * @return the response containing the entity retrieved by id
     * @throws EntityNotFoundException problem retrieving entity
     * @throws JsonProcessingException problem parsing or writing JSON
     */
    @GET
    @Path("/{entityType}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntity(@PathParam("entityType") String entityType, @PathParam("id") Integer id) throws JsonProcessingException{
        Object entity = null;
        try {
            entity = daoService.getEntityById(entityType, id);
            if (entity == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            String json = mapper.writeValueAsString(entity);
            return Response.ok(json).type(MediaType.APPLICATION_JSON).build();
        } catch (EntityNotFoundException e) {
            logger.error("Entity not found: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found: " + e.getMessage()).build();
        } catch (JsonProcessingException e) {
            logger.error("JsonProcessingException: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid JSON input: " + e.getMessage()).build();
        }
    }

    /**
     * Add entity response.
     *
     * @param <T>        the type parameter
     * @param entityType the entity type
     * @param entityData the entity data
     * @return the response
     */
    @POST
    @Path("/{entityType}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public <T> Response addEntity(@PathParam("entityType") String entityType, Map<String, Object> entityData) {
        try {
            T newEntity = daoService.insertNewEntity(entityType, entityData);
            logger.info("Entity created: {}", newEntity);
            return Response.status(Response.Status.CREATED).entity(newEntity).build();
        } catch (EntityNotFoundException e) {
            logger.error("Failed to add entity: {}", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Remove entity response.
     *
     * @param entityType the entity type
     * @param id         the id
     * @return the response
     */
    @DELETE
    @Path("/{entityType}/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
    public Response removeEntity(@PathParam("entityType") String entityType, @PathParam("id") Integer id) {
        try {
            daoService.deleteEntity(entityType, id);
            logger.info("Entity {} with ID {} removed successfully.", entityType, id);
            return Response.ok("The entity was removed successfully.").build();
        } catch (EntityNotFoundException e) {
            logger.error("Failed to remove entity: {}", e.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }
}
package com.eventvisualizer.app;

import com.eventvisualizer.entity.Event;
import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.eventvisualizer.service.CrudService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Path("/service")
public class CrudResource {

    private final Logger logger = (Logger) LogManager.getLogger(this.getClass());

    //private final ExecutorService executorService = Executors.newFixedThreadPool(5);
    private final CrudService service;

    public CrudResource() {
        service = new CrudService();
    }

    public CrudResource(CrudService service) {
        this.service = service;
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new/{entityType}/{id}")
    public Response newUserNotebookAndEvent(@PathParam("id") int id,
                                            @PathParam("entityType") String type,
                                            String json) {
        System.out.println(json);
        if (json == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("Message", "data is null."))
                    .build();
        }
        try {
            if ("user".equals(type)) {
                handleNewNotebookForUser(id, json);
            } else if ("notebook".equals(type)) {
                handleEventForNotebook(id, json);
            } else {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(Map.of("Message", "Unknown entity type: " + type))
                        .build();
            }
            return Response.ok().entity(Map.of("message", "Event was successfully added")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("Error", e.getMessage()))
                    .build();
        }
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add-notebook/{userId}")
    public Response addNotebook(@PathParam("userId") int userId, String json) {
        logger.info(json);
        if (json == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Notebook returned null."))
                    .build();
        }
        try {
            service.addNotebook(userId, json);
            return Response.status(Response.Status.CREATED).entity(Map.of("message", "Notebook added")).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("message", "Error processing notebook.")).build();
        }

    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getAllNotebooks/{userId}")
    public Response getAllEvents(@PathParam("userId") int userId) {
        Set<Notebook> notebooks = service.getUserNotebooks(userId);
        if (notebooks == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("Error: ", "Notebooks not found.")).build();
        }
        return Response.ok().entity(notebooks).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/update/{eventId}")
    public Response updateEvent(@PathParam("eventId") int eventId, String json) {
        System.out.println(json);
        if (json == null || json.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("message", "Invalid input data: JSON payload is missing or empty."))
                    .build();
        }
        service.updateEvent(json, eventId);
        return Response.ok().entity((Event) service.getEventDao().getById(eventId)).build();

    }

    private void handleNewNotebookForUser(int userId, String json) throws IOException {
        User user = service.getUserDao().getById(userId);
        if (user != null) {
            Notebook notebook = service.addNewEventAndNotebook(json, user);
            service.getNotebookDao().insert(notebook);
        }
    }

    private void handleEventForNotebook(int notebookId, String json) throws IOException {
        Notebook notebook = service.addEventToExistingNotebook(json, notebookId);
        service.getNotebookDao().update(notebook);
    }

}
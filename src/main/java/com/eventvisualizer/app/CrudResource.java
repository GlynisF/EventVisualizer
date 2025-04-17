package com.eventvisualizer.app;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.eventvisualizer.service.CrudService;
import com.eventvisualizer.service.NotebookService;
import com.eventvisualizer.util.ServiceRegistry;
import com.fasterxml.jackson.core.JsonProcessingException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.Set;

@Path("/service")
public class CrudResource {

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
    @Path("/add")
    public Response addNewEvent(@QueryParam("operation") String operation,
                                @QueryParam("userId") int userId, String json) {
        NotebookService notebookService = ServiceRegistry.get(NotebookService.class);
        System.out.println(json);
        if (json == null || json.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", "data is null.")).build();
        }
        try {
            Notebook notebook = notebookService.notebookMapper(json);
            CrudService crud = new CrudService();

            System.out.println(notebook);
            return Response.ok().entity(Map.of("success", "Notebook successfully added.")).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Map.of("error", e.getMessage())).build();
        }
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/new/{entityType}/{id}")
    public Response newUserNotebookAndEvent(@PathParam("id") int id,
                                            @PathParam("entityType") String type,
                                            String json) {
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

    private void handleNewNotebookForUser(int userId, String json) throws JsonProcessingException {
        User user = service.getUserDao().getById(userId);
        if (user != null) {
            Notebook notebook = service.addNewEventAndNotebook(json, user);
            service.getNotebookDao().insert(notebook);
        }
    }

    private void handleEventForNotebook(int notebookId, String json) throws JsonProcessingException {
        Notebook notebook = service.addEventToExistingNotebook(json, notebookId);
        service.getNotebookDao().update(notebook);
    }

}
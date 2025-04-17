package com.eventvisualizer.util;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ResponseUtil {

    public static Response success(Object data) {
        return Response.ok(data, MediaType.APPLICATION_JSON).build();
    }

    public static Response created(Object data) {
        return Response.status(Response.Status.CREATED)
                .entity(data)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response noContent() {
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    public static Response badRequest(String message) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse("Bad Request", message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response notFound(String message) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse("Not Found", message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response serverError(String message) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("Internal Server Error", message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response conflict(String message) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse("Conflict", message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response unauthorized(String message) {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ErrorResponse("Unauthorized", message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public static Response forbidden(String message) {
        return Response.status(Response.Status.FORBIDDEN)
                .entity(new ErrorResponse("Forbidden", message))
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
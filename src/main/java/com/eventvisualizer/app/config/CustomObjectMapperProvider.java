package com.eventvisualizer.app.config;

import com.eventvisualizer.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.Produces;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Provider
@Produces("application/json")
public class CustomObjectMapperProvider implements ContextResolver<ObjectMapper> {

    private final ObjectMapper mapper = ObjectMapperUtil.getMapper();

    @Override
    public ObjectMapper getContext(Class<?> type) {
        return mapper;
    }
}
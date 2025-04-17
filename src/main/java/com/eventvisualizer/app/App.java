package com.eventvisualizer.app;

import com.eventvisualizer.app.config.CustomObjectMapperProvider;
import com.eventvisualizer.util.PropertiesLoader;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;


@ApplicationPath("/app")
public class App extends Application implements PropertiesLoader {



    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CustomCorsFilter.class);
        classes.add(AutocompleteResource.class);
        classes.add(CustomObjectMapperProvider.class);
        classes.add(CrudResource.class);
        return classes;
    }




}
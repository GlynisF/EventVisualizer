package com.eventvisualizer.app;

import com.eventvisualizer.entity.User;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/app")
public class App extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(User.class);
        classes.add(CRUD.class);
        return classes;
    }
}

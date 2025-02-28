package com.eventvisualizer.controller;


import com.eventvisualizer.util.PropertiesLoader;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

@WebServlet (
        name = "applicationStartup",
        urlPatterns = {"/event-visualizer-startup"},
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {
    private Properties properties;

    @Override
    public void init() {
        System.out.println("In the startup class");

    }
}

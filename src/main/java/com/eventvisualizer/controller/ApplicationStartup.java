package com.eventvisualizer.controller;


import com.eventvisualizer.util.AppContext;
import com.eventvisualizer.util.PropertiesLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.Properties;

@WebServlet (
        name = "applicationStartup",
        urlPatterns = {"/event-visualizer-startup"},
        loadOnStartup = 1
)
public class ApplicationStartup extends HttpServlet implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());

    @Override
    public void init() throws ServletException {
        System.out.println("In the startup class");
        try {
            ServletContext context = getServletContext();
            Properties properties = new Properties(loadProperties("/cognito.properties"));
            context.setAttribute("cognito.properties", properties);
            logger.info("Properties loaded: {}", properties);
            AppContext.initialize();
        } catch (Exception e) {
            logger.error("Error loading properties: {}", e.getMessage(), e);
            throw new ServletException("Failed to load properties", e);
        }
    }

}
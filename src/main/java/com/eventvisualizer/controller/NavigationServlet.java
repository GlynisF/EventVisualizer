package com.eventvisualizer.controller;

import com.eventvisualizer.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "navigationServlet",
        urlPatterns = {"/home"}
)
public class NavigationServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer counter = (Integer) session.getAttribute("visitCount");
        String welcomeMessage = (String) session.getAttribute("welcomeMessage");
        User user = (User) session.getAttribute("user");

        if (counter == null) {
            counter = 1;
            welcomeMessage = "Welcome back, " + user.getUsername();
             session.setAttribute("visitCount", counter);
             session.setAttribute("welcomeMessage", welcomeMessage);
        } else {
            counter++;
            welcomeMessage = user.getUsername() + "'s Visualizer";
            session.setAttribute("visitCount", counter);
            session.setAttribute("welcomeMessage", welcomeMessage);
        }

        if (req.getRequestURI().endsWith("/home")) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/homepage.jsp");
            dispatcher.forward(req, resp);
        }
    }

}

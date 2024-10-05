package com.eventvisualizer.controller;

import com.eventvisualizer.entity.Notebook;
import com.eventvisualizer.entity.User;
import com.eventvisualizer.persistence.GenericDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/my-events")
public class NavigationServlet extends HttpServlet {

    //TODO user is retrieved by hard-coded value. Update method to pass ID in as param.
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(1);
        req.setAttribute("user", user);
        List<Notebook> notebooks = user.getNotebooks();
        String title = "My Events";
        req.setAttribute("title", title);
        req.setAttribute("notebooks", notebooks);
        RequestDispatcher dispatcher = req.getRequestDispatcher("my-events.jsp");
        dispatcher.forward(req, resp);
    }
}
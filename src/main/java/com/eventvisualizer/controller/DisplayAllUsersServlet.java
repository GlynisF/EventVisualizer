package com.eventvisualizer.controller;

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

@WebServlet (
    urlPatterns = {"/results"}
)
public class DisplayAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        List<User> users = userDao.getAll();
        req.setAttribute("users", userDao.getAll());
        System.out.println(users);

        RequestDispatcher dispatcher = req.getRequestDispatcher("results.jsp");
        dispatcher.forward(req, resp);

    }
}

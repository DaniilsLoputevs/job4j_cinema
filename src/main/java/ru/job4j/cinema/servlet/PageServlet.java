package ru.job4j.cinema.servlet;

import ru.job4j.cinema.ahelptools.ConslLog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet()
public class PageServlet extends HttpServlet {

    /**
     * prepare & get Payment.html.
     * <p>
     * redirect to page.
     * <p>
     * page : String - new page for load.
     * <p>
     * goto: new page.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");

        ConslLog.log("###  ###");


        ConslLog.log("###  ###");

        req.getRequestDispatcher("payment.html").forward(req, resp);
    }
}

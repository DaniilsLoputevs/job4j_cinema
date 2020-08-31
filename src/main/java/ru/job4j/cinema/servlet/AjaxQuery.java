package ru.job4j.cinema.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.cinema.ahelptools.ConslLog;
import ru.job4j.cinema.model.Seat;
import ru.job4j.cinema.store.DbStoreSeat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * url-pattern: /hall.do
 */
public class AjaxQuery extends HttpServlet {

    /**
     * get all hall's seats. (should in future: by hall id.)
     * <p>
     * should be but not know:
     * hall id : from front.
     * <p>
     * goto: NONE - ajax script
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setContentType("text/json");

        var seatList = DbStoreSeat.instOf().getAll();
        try (var writer = new PrintWriter(resp.getOutputStream())) {

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, seatList);

            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * upd seat in hall.
     * <p>
     * seat : String - seat info in String format.
     * <p>
     * goto: NONE - ajax script
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var arr = req.getParameter("seat").split("&");
        var hall = Integer.parseInt(takeValue(arr[2]));
        var row = Integer.parseInt(takeValue(arr[0]));
        var column = Integer.parseInt(takeValue(arr[1]));
        var id = Integer.parseInt(takeValue(arr[3]));

        ConslLog.log("arr", Arrays.toString(arr));
        ConslLog.log("hall", hall);
        ConslLog.log("row", row);
        ConslLog.log("column", column);
        ConslLog.log("id", id);
        ConslLog.log("name", req.getParameter("name"));
        ConslLog.log("phone", req.getParameter("phone"));

        DbStoreSeat.instOf().save(new Seat(id, row, column, hall, true));
    }

    private String takeValue(String string) {
        return string.split("=")[1];
    }
}

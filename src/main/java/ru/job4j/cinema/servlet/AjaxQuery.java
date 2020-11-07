package ru.job4j.cinema.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cinema.model.Seat;
import ru.job4j.cinema.store.DbStoreSeat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(name = "HallServlet", value = "/hall.get")
public class AjaxQuery extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AjaxQuery.class);

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
        String[] arr = req.getParameter("seat").split("&");

        int hall = Integer.parseInt(takeValue(arr[2]));
        int row = Integer.parseInt(takeValue(arr[0]));
        int column = Integer.parseInt(takeValue(arr[1]));
        int id = Integer.parseInt(takeValue(arr[3]));
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");


        LOG.info("arr: {}", Arrays.toString(arr));
        LOG.info("hall: {}", hall);
        LOG.info("row: {}", row);
        LOG.info("column: {}", column);
        LOG.info("id: {}", id);
        LOG.info("name: {}", name);
        LOG.info("phone: {}", phone);

        DbStoreSeat.instOf().save(new Seat(id, row, column, hall, true));
    }

    private String takeValue(String string) {
        return string.split("=")[1];
    }
}

package cybersoft.java18.backend.gamedoanso.servlet;

import cybersoft.java18.backend.gamedoanso.jdbc.PostgresqlConnection;
import cybersoft.java18.backend.gamedoanso.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(urlPatterns = {UrlUtils.HEALTH}, name = "healthServlet")
public class HealthServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Connection mySQLConnection = PostgresqlConnection.getConnection()) {
            resp.getWriter().print("up");
        } catch (Exception e) {
            resp.getWriter().print("down");
        }
    }
}

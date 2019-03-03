package it.sevenbits.SimpleServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SimpleServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write(
                "<html> " +
                        "<div style=\"width: 150px; margin: 150px auto 0 auto; \"> " +
                            "<p style=\"font-size: 24px;\"> Hello, Gaziz. </p> " +
                        "</div> " +
                    "</html>"
        );
    }
}

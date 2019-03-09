package it.sevenbits.ServiceHTTP.servlets;

import it.sevenbits.ServiceHTTP.session.SessionMap;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] stringSessionID = request.getCookies();
        if (stringSessionID.length == 0) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String userName = stringSessionID[0].getValue();
        response.getWriter().write("Current UserName is " + userName);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");

        if (userName == null || userName.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UUID sessionID = UUID.randomUUID();
        SessionMap.getInstance().addUser(sessionID, userName);
        response.addCookie(new Cookie(sessionID.toString(), userName));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

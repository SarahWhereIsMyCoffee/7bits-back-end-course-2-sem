package it.sevenbits.ServiceHTTP.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.sevenbits.ServiceHTTP.session.SessionMap;
import it.sevenbits.ServiceHTTP.taskmanager.Task;
import it.sevenbits.ServiceHTTP.taskmanager.TaskManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SingleTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionID = request.getParameter("Authorization");

        if (sessionID == null
                || sessionID.equals("")
                || SessionMap.getInstance().getUserName(UUID.fromString(sessionID)) == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Task task = TaskManager.getInstance().get(UUID.fromString(sessionID));

        if (task == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", task.getId().toString());
        jsonObject.addProperty("value", task.getMessage());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionID = request.getParameter("Authorization");

        if (SessionMap.getInstance().getUserName(UUID.fromString(sessionID)) == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        Task task = TaskManager.getInstance().get(UUID.fromString(sessionID));

        if (task == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", task.getId().toString());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonObject));

            TaskManager.getInstance().delete(UUID.fromString(sessionID));
            SessionMap.getInstance().deleteUser(UUID.fromString(sessionID));
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

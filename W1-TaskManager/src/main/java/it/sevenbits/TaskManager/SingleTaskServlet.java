package it.sevenbits.TaskManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import it.sevenbits.TaskManager.manager.Task;
import it.sevenbits.TaskManager.manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class SingleTaskServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidString = request.getParameter("id");
        UUID id = UUID.fromString(uuidString);
        Task task = TaskManager.getInstance().get(id);

        if (task == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("id", task.getId().toString());
            jsonObject.addProperty("value", task.getMessage());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonObject));
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuidString = request.getParameter("id");
        UUID id = UUID.fromString(uuidString);
        Task task = TaskManager.getInstance().get(id);

        if (task == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            Gson gson = new GsonBuilder().create();
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("id", task.getId().toString());

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonObject));
            TaskManager.getInstance().delete(id);
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}

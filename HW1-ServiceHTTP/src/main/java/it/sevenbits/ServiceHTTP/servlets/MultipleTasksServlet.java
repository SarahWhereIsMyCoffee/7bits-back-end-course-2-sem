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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MultipleTasksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskMessage = request.getParameter("Message");
        String userName = request.getParameter("UserName");

        if (taskMessage == null
                || taskMessage.equals("")
                || userName == null
                || userName.equals("")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        UUID sessionID = UUID.randomUUID();
        TaskManager.getInstance().put(sessionID, taskMessage);
        Task currentTask = TaskManager.getInstance().get(sessionID);

        SessionMap.getInstance().addUser(sessionID, userName);

        String value = currentTask.getMessage();
        Gson gson = new GsonBuilder().create();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", sessionID.toString());
        jsonObject.addProperty("value", value);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObject));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sessionID = request.getParameter("Authorization");

        if (sessionID == null
                || sessionID.equals("")
                || SessionMap.getInstance().getUserName(UUID.fromString(sessionID)) == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        HashMap<UUID, Task> taskMap = TaskManager.getInstance().getTaskList();
        Gson gson = new GsonBuilder().create();
        ArrayList<JsonObject> jsonObjectList = new ArrayList<>();

        taskMap.forEach((id, task) -> {
            JsonObject currentJsonObject = new JsonObject();
            currentJsonObject.addProperty("id", task.getId().toString());
            currentJsonObject.addProperty("value", task.getMessage());

            jsonObjectList.add(currentJsonObject);
        });

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(gson.toJson(jsonObjectList));
        response.setStatus(HttpServletResponse.SC_OK);
    }
}

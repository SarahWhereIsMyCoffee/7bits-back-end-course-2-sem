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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class MultipleTasksServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String taskMessage = request.getParameter("message");

        if (taskMessage == null || taskMessage.equals("")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            UUID taskID = UUID.randomUUID();
            TaskManager.getInstance().put(taskID, taskMessage);

            Task currentTask = TaskManager.getInstance().get(taskID);
            String value = currentTask.getMessage();
            Gson gson = new GsonBuilder().create();

            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", taskID.toString());
            jsonObject.addProperty("value", value);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(jsonObject));
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

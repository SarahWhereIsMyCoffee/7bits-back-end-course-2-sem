package it.sevenbits.ServiceHTTP.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionMap {
    private Map<UUID, String> usersMap = new HashMap<>();
    private static SessionMap sessionMap;

    public static SessionMap getInstance() {
        if (sessionMap == null) {
            sessionMap = new SessionMap();
        }

        return sessionMap;
    }

    public void addUser(UUID sessionID, String userName) {
        usersMap.put(sessionID, userName);
    }

    public String getUserName(UUID sessionID) {
        return usersMap.get(sessionID);
    }

    public void deleteUser(UUID sessionID) {
        usersMap.remove(sessionID);
    }

    private SessionMap() {}
}

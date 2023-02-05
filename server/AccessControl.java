package server;

import java.util.HashMap;
import java.util.Map;

public class AccessControl {
    private Map<String, UserData> users;

    public AccessControl() {
        users = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        UserData userData = new UserData(username, password);
        users.put(username, userData);
    }

    public boolean checkAccess(String username, String password) {
        UserData userData = users.get(username);
        if (userData != null) {
            return userData.getPassword().equals(password);
        }
        return false;
    }

    private class UserData {
        private String username;
        private String password;

        public UserData(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}


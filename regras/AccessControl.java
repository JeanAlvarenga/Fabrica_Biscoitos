/**
 * Classe responsável por controlar o acesso dos clientes.
 * @author Jean P. Alvarenga
 * @version 5.0
 */

import java.util.HashMap;
import java.util.Map;

public class AccessControl {
    private Map<String, UserData> users;

    public AccessControl() {
        users = new HashMap<>(); // HashMap is not thread-safe!
    }

    /**
     * Metodo responsavel por cadastrar os clientes.
     * @param username
     * @param password
     */
    public void registerUser(String username, String password) {
        UserData userData = new UserData(username, password);
        users.put(username, userData);
    }

    /**
     * Metodo responsavel por verificar se o cliente esta cadastrado.
     * @param username
     * @param password
     * @return
     */
    public boolean checkAccess(String username, String password) {
        UserData userData = users.get(username);
        if (userData != null) {
            return userData.getPassword().equals(password);
        }
        return false;
    }

    private class UserData {
        //private String username;
        private String password;

        public UserData(String username, String password) {
            //this.username = username;
            this.password = password;
        }
        public String getPassword() {
            return password;
        }
    }
}


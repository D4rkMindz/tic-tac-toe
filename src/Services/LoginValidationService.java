package Services;

import Config.Settings;

import java.util.Hashtable;

public class LoginValidationService {
    private static boolean loggedIn;
    public boolean error;

    public boolean isLoginValid(String username, String password) {
        ConnectionService connectionService = new ConnectionService();
        Hashtable<String, String> parameters = new Hashtable<>();
        //add key-value pair to Hashtable
        parameters.put("username", username);
        parameters.put("password", password);
        try {
            String response = connectionService.call(Settings.loginUrl,  parameters);
            return response.equalsIgnoreCase("ok");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}

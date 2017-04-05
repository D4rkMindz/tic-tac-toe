package service;

import config.Settings;

import java.util.Hashtable;

/**
 * Class LoginValidationService.
 * This class is for the login logic responsible.
 */
public class LoginValidationService {
    /**
     * Class variables
     */
    public static boolean loggedIn = false;

    /**
     * Validate login. This function calls the defined URL and sends the login data to it. If the login is valid, the
     * response must be "ok". Otherwise there will be no access to the StartGui
     *
     * @param username String with username
     * @param password String with password
     * @return true boolean if login data is valid
     */
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
}

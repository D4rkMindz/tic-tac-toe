package service;

import config.Settings;

import java.util.Hashtable;

public class RegisterService {
    /**
     * Error variable
     */
    public static String error;
    public static String success;

    /**
     * Validate Registration.
     * <p>
     * This function validates the user input. If the user already exist in the database, it will return false and set
     * an errormessage into the error variable.
     *
     * @param username  String with username
     * @param email     String with emailaddress
     * @param password  String with password
     * @param password2 String with second password
     * @return boolean true if user is successfully inserted into database
     */
    public boolean isRegistrationValid(String username, String email, String password, String password2) {
        if (username.length() <= 3) {
            this.setError("Username too short");
            return false;
        }
        if (email.length() <= 3) {
            this.setError("Email too short");
            return false;
        }
        if (!password.equals(password2)) {
            this.setError("Passwords aren't equal");
            return false;
        }
        if (password.length() <= 3 || password2.length() <= 3) {
            this.setError("Passwords too short");
            return false;
        }
        ConnectionService connectionService = new ConnectionService();
        Hashtable<String, String> parameters = new Hashtable<String, String>();
        //add key-value pair to Hashtable
        parameters.put("username", username);
        parameters.put("email", email);
        parameters.put("password1", password);
        parameters.put("password2", password2);
        try {
            String response = connectionService.call(Settings.registerUrl, parameters);
            if (response.equalsIgnoreCase("ok")) {
                this.setSuccess("User created");
                this.setError("");
                return true;
            } else if (response.equalsIgnoreCase("userExists")) {
                this.setSuccess("");
                this.setError("User already exists");
            } else {
                this.setSuccess("");
                this.setError("Invalid credentials");
                return false;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * Set Errormessage into error variable.
     *
     * @param err with errormessage
     */
    private void setError(String err) {
        error = err;
    }

    /**
     * Set Successmessage into error variable.
     *
     * @param succ with successmessage
     */
    private void setSuccess(String succ) {
        success = succ;
    }
}

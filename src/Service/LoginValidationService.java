package Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLConnection;
import java.net.HttpURLConnection;

public class LoginValidationService {
    public static boolean loggedIn;
    public static boolean error;

    public boolean isLoginValid(String username, String password, boolean useProxy, String url) {
        ConnectionService connectionService = new ConnectionService();
        try {
            HttpURLConnection connection = connectionService.initUrlConnection(useProxy, url);
            PrintStream printStream = connectionService.initPrintStream(connection);

            printStream.print("username=" + username);
            printStream.print("&password=" + password);
            connection.getInputStream();

            BufferedReader reader = null;
            Object message = connection.getHeaderFields();
            Object code = connection.getResponseCode();
            if (connection.getResponseCode() == 200) {
                reader = new BufferedReader(new
                        InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new
                        InputStreamReader(connection.getErrorStream()));
            }
            String line = null;
            String check = "notOk";
            while ((line = reader.readLine()) != null) {
//                System.out.print(line);
                check = line;
            }
            if (check.equalsIgnoreCase("ok")) {
                this.setError(true);
                loggedIn = true;
                return true;
            } else {
                this.setError(false);
                loggedIn = false;
                return false;
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void setError(boolean err) {
        error = err;
    }

    public boolean isLoggedIn() {
        return this.loggedIn;
    }
}

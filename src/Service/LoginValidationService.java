package Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLConnection;

public class LoginValidationService {
    public boolean loggedIn;
    public static boolean error;

    public boolean isLoginValid(String username, String password) {
        ConnectionService connectionService = new ConnectionService();
        try {
            URLConnection connection = connectionService.initUrlConnection(false);
            PrintStream printStream = connectionService.initPrintStream(connection);

            printStream.print("username=" + username);
            printStream.print("&password=" + password);
            connection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            String check = "notOk";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                check = line;
            }
            if (check.equalsIgnoreCase("ok")){
                this.setError(false);
                return true;
            } else {
                this.setError(true);
                return false;
            }


        } catch (Exception ex){
            ex.printStackTrace();
        }
        return true;
    }

    private void setError(boolean err){
        error = err;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}

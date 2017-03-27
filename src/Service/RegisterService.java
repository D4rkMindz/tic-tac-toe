package Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLConnection;

public class RegisterService {

    public static String error;

    public boolean isRegistrationValid(String username, String email, String password, String password2, boolean useProxy, String url){
        ConnectionService connectionService = new ConnectionService();
        try {
            URLConnection connection = connectionService.initUrlConnection(useProxy, url);
            PrintStream printStream = connectionService.initPrintStream(connection);

            printStream.print("username=" + username);
            printStream.print("&email=" + email);
            printStream.print("&password1=" + password);
            printStream.print("&password2=" + password2);
            connection.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            String check = "notOk";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                check = line;
            }
            if (check.equalsIgnoreCase("ok")){
                this.setError("ok");
                return true;
            } else if(check.equalsIgnoreCase("userExists")){
                this.setError("userExists");
            } else {
                this.setError("notOk");
                return false;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    private void setError(String err){
        error = err;
    }
}

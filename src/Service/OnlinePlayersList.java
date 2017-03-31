package Service;

import env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLConnection;
import

public class OnlinePlayersList {
    public String[] getOnlinePlayers(){
        ConnectionService connectionService = new ConnectionService();
        Environment env = new Environment();
        ProxyService proxyService = new ProxyService();

        String url = env.playersUrl;

        try {
            URLConnection connection = connectionService.initUrlConnection(proxyService.useProxy, url);
            PrintStream printStream = connectionService.initPrintStream(connection);
            printStream.print("get");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = null;
            String check = "notOk";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                check = line;
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        String[] stringy = new String[9];
        return stringy;
    }
}

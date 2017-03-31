package Service;

import com.sun.javafx.runtime.SystemProperties;
import env.Environment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URLConnection;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.MapType;
import org.json.*;

public class OnlinePlayersList {
    public Map data;

    public void getOnlinePlayers() {
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
            String check = "";
            while ((line = in.readLine()) != null) {
                System.out.println(line);
                check = line;
            }
            JSONObject jsonObject = new JSONObject(check);
            JSONObject string = jsonObject.getJSONObject("players");

            //copied from http://stackoverflow.com/questions/13916086/jackson-recursive-parsing-into-mapstring-object?answertab=votes#tab-top
            ObjectMapper mapper = new ObjectMapper();
            MapType type = mapper.getTypeFactory().constructMapType(
                    Map.class, String.class, String[].class);
            this.data = mapper.readValue(check, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

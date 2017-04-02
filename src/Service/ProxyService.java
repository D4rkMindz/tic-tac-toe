package Service;

public class ProxyService {
    public static boolean useProxy = false;
    public static String ip = "172.20.10.11", username = "bjoern.pfoster", password = "Ulmi244313!";
    public static int port = 3128;

    public void setUseProxy(boolean use) {
        useProxy = use;
    }

    public boolean getUseProxy() {
        return useProxy;
    }

    public void setSettings(String ipaddr, String usernameIn, String passwordIn, int portIn) {
        ip = ipaddr;
        username = usernameIn;
        password = passwordIn;
        port = portIn;
    }

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

}

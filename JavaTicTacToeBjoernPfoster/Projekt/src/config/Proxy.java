package config;

/**
 * Class Proxy.
 * Proxysettings are going to be saved here.
 */
public class Proxy {
    /**
     * Proxy settings are going to be saved here. They will be accessed via Settings.proxy.{variable}
     */
    public boolean useProxy = false;
    public String ip = "";
    public String username = "";
    public String password = "";
    public int port = 0;
}

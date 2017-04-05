package config;

/**
 * Class Settings.
 * Settings are going to be accessed via this class.
 */
public class Settings {
    /**
     * Class variables
     */
    public static String loginUrl = "", registerUrl = "";
    public static Proxy proxy = new Proxy();

    /**
     * Initialize variables for different environments. This function should be used once. In my case, it is used in the
     * @see start.MainController#start function.
     * @param env String with the Definition of the Environment ("dev" for development environment and "prod" for pro-
     *            ductive environment. But it won't make any difference, if you write "pord" instead of "prod". Even an
     *            empty String would initialize the productive environment).
     */
    public static void Init(String env) {
        if (env.equalsIgnoreCase("dev")) {
            loginUrl = "http://localhost/tic-tac-toe-server/login.php";
            registerUrl = "http://localhost/tic-tac-toe-server/register.php";
        } else {
            loginUrl = "http://files.d4rkmindz.ch/login.php";
            registerUrl = "http://files.d4rkmindz.ch/register.php";
        }
    }
}

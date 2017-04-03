package Config;

/**
 * Class Settings
 */
public class Settings {
    public static String loginUrl = "", registerUrl = "";
    public static Proxy proxy = new Proxy();

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

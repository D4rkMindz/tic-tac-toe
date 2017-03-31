package Service;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;


public class ConnectionService {
    private String password = "";

    public void setProxyPassword(String pass) {
        password = pass;
    }

    public URLConnection    initUrlConnection(boolean useProxy, String url) throws IOException {
        URLConnection con = null;
//        String url = "http://files.d4rkmindz.ch/tictactoe.php";


        try {

            if (useProxy) {
                // Copied from http://stackoverflow.com/questions/1432961/how-do-i-make-httpurlconnection-use-a-proxy
                Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.20.10.11", 3128));
                con = new URL(url).openConnection(proxy);

                Authenticator authenticator = new Authenticator() {

                    public PasswordAuthentication getPasswordAuthentication() {
                        return (new PasswordAuthentication("bjoern.pfoster",
                                "Ulmi244313!".toCharArray()));
                    }
                };
                Authenticator.setDefault(authenticator);
            } else {
                con = new URL(url).openConnection();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return con;

    }

    public PrintStream initPrintStream(URLConnection con) throws IOException {
        PrintStream ps = null;
        try {
            // open a connection to the site
            // activate the output
            con.setDoOutput(true);
            ps = new PrintStream(con.getOutputStream());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void closeUrlConnection(URLConnection con, PrintStream ps) throws IOException {
        try {
            // we have to get the input stream in order to actually send the request
            con.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            // close the print stream
            ps.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

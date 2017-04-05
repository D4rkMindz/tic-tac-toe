package service;

import config.Settings;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.*;
import java.net.URLEncoder;

/**
 * Class ConnectionService.
 * This class is for connections to the Server responsible.
 */
public class ConnectionService {
    /**
     * Initialize URL connection.
     * <p>
     * This function is used to connect to an URL. It will return a URLConnection Object. After this function you need
     * to use the initPrintStream() function.
     *
     * @param url String, URL you want to connect
     * @return con URLConnection
     * @throws IOException on failure
     */
    private HttpURLConnection initUrlConnection(String url) throws IOException {
        URLConnection con = null;
        HttpURLConnection connection = null;
        try {
            if (Settings.proxy.useProxy) {
                // Copied from http://stackoverflow.com/questions/1432961/how-do-i-make-httpurlconnection-use-a-proxy
                java.net.Proxy proxy = new java.net.Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress(Settings.proxy.ip, Settings.proxy.port));
                con = new URL(url).openConnection(proxy);

                Authenticator authenticator = new Authenticator() {

                    public PasswordAuthentication getPasswordAuthentication() {
                        return (new PasswordAuthentication(Settings.proxy.username,
                                Settings.proxy.password.toCharArray()));
                    }
                };
                Authenticator.setDefault(authenticator);
            } else {
                con = new URL(url).openConnection();
            }
            connection = (HttpURLConnection) con;
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Initialize Print Stream.
     * <p>
     * After you called the initUrlConnection() function, you need to use the initPrintStream() function. You can use
     * the internal .print() function on the returned object. The .print() function is used to send data via HTTP-POST
     * to the Server
     *
     * @param con URLConnection, connection from initUrlConnection()
     * @return ps PrintStream
     * @throws IOException on failure
     */
    private PrintStream initPrintStream(URLConnection con) throws IOException {
        PrintStream ps = null;
        try {
            // open a connection to the site
            // activate the output
            con.setDoOutput(true);
            ps = new PrintStream(con.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }

    /**
     * Call URL.
     * <p>
     * This function sends a HTTP-POST request with the handed over parameters to the inserted URL
     *
     * @param url    String with the URL
     * @param params Hashtable<String, String> with parameters
     * @return result String with response
     */
    public String call(String url, Hashtable<String, String> params) {
        ConnectionService connectionService = new ConnectionService();
        try {
            //Setup parameters for HTTP-POST request
            HttpURLConnection connection = connectionService.initUrlConnection(url);
            //Define HTTP-POST
            connection.setRequestMethod("POST");
            //Set request Property
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

            //Create PrintStream
            PrintStream printStream = connectionService.initPrintStream(connection);

            //Create String to send the data to the server successfully
            String postParams = this.getPostString(params);

            //Send it to the server
            printStream.print(postParams);

//            //Receive response
//            connection.getInputStream();

            //Define the reader
            BufferedReader reader;

            //For debugging.
            //Object message = connection.getHeaderFields();
            //Object code = connection.getResponseCode();

            //Setup InputStream only if request was successful (HTTP-Status-Code 200 - OK), otherwise setup ErrorStream
            if (connection.getResponseCode() == 200) {
                reader = new BufferedReader(new
                        InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new
                        InputStreamReader(connection.getErrorStream()));
            }

            //String for the response
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                //Concatenate every responseline to the existing String
                response.append(inputLine);
            }
            //Close all connections
            reader.close();
            printStream.close();
            connection.disconnect();

            //Parse response to String
            String result = response.toString();
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //In case of an error
        return "";
    }

    /**
     * Format POST-Parametes.
     * <p>
     * The handed over parametes are going to be formatted to be sent to the Server via the URL
     *
     * @param arguments Map<String, String> with the parameters to format
     * @return sj formatted as String
     */
    private String getPostString(Map<String, String> arguments) {
        StringJoiner sj = new StringJoiner("&");

        //Other, possible way to format the String
        //Set<String> keys = params.keySet();
        //for (String key : keys) {
        ////System.out.println("Value of " + key + " is: " + value);
        //}

        try {
            for (Map.Entry<String, String> entry : arguments.entrySet()) {
                sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "="
                        + URLEncoder.encode(entry.getValue(), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sj.toString();
    }
}

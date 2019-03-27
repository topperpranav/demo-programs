package oauth2;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import oauth2.CelloParkAuth;

/**
 * This class demonstrates the client implementation of OAuth2.0 The basic idea
 * is to get the bearer token and then pass the body as normal
 * httpurlconnection.
 *
 * It is important to note that when access token is to be fetched, then no
 * authorisation should take place. The params are passed as form params, as
 * specified by cello park
 *
 * @author Pranav
 */
public class OAuth2 {

    static String ACCESS_TOKEN_URL = "https://uat.vpark.com.au/dcc/api/token";
    static String USERNAME = "senbos";
    static String PASSWORD = "Sensen@2019";
    static String POST_URL = "https://uat.vpark.com.au/dcc/api/lpr";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String bearerToken = getAccessToken(ACCESS_TOKEN_URL, USERNAME, PASSWORD);
        postAlertsData(POST_URL, bearerToken);
    }

    /**
     * Gets the access token for subsequent requests.
     *
     * @param urlString
     * @param username
     * @param password
     * @return
     * @throws MalformedURLException
     * @throws IOException
     */
    public static String getAccessToken(String urlString, String username, String password) throws MalformedURLException, IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //Timeout set to 5 seconds
        connection.setConnectTimeout(5000);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        //Headers are added by adding the request property
        //Note that for th oauth 2.0 requests the username and password are not 
        //passed as headers but in this case as for arguments. Only the content type is passed as the header.
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String charset = "UTF-8";
        String s = "grant_type=" + URLEncoder.encode("password", charset);
        s += "&username=" + URLEncoder.encode(USERNAME, charset);
        s += "&password=" + URLEncoder.encode(PASSWORD, charset);

        connection.setFixedLengthStreamingMode(s.getBytes().length);
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        System.out.println("Payload is: " + s);
        out.print(s);
        out.close();

        connection.connect();
        System.out.println(connection.getInputStream());
        //read the inputstream and print it String result; 
        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result2 = bis.read();
        System.out.println("Count of response msg :" + result2);
        while (result2 != -1) {
            buf.write((byte) result2);
            result2 = bis.read();
        }
        //System.out.println("result is: " + buf.toString());
        //return buf.toString();
        Gson gson = new Gson();
        CelloParkAuth cparkAuth = gson.fromJson(buf.toString(), CelloParkAuth.class);
        System.out.println("grant_type: " + cparkAuth.getAccessToken());
        return null;
    }

    /**
     * Code for posting alerts data
     *
     * @param urlString
     * @param accessToken
     * @throws MalformedURLException
     * @throws ProtocolException
     * @throws IOException
     */
    public static void postAlertsData(String urlString, String accessToken) throws MalformedURLException, ProtocolException, IOException {
        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        connection.setRequestProperty("Content-Type", "application/json");
        //Timeout set to 5 seconds
        connection.setConnectTimeout(5000);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        //This is hardoced sample alerts data
        String s = " {\"event\":\"28222\",\"read\":\"28222\",\"result\":0,\"licencePlate\":\"W4U8O\",\"user\":\"sensenadmin\",\"timestamp\":\"2017-05-16T17:55:24.256+05:30\",\"location\":\"ZONE ID 12012\",\"gps\":\"estimate\",\"images\":[{\"type\":\"source\"},{\"type\":\"plate\"}]}";
        connection.setFixedLengthStreamingMode(s.getBytes().length);
        PrintWriter out = new PrintWriter(connection.getOutputStream());
        System.out.println("Payload is: " + s);
        out.print(s);
        out.close();

        connection.connect();
        System.out.println(connection.getInputStream());
        //read the inputstream and print it String result; 
        BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        int result2 = bis.read();
        System.out.println("Count of response msg :" + result2);
        while (result2 != -1) {
            buf.write((byte) result2);
            result2 = bis.read();
        }
        System.out.println("result is: " + buf.toString());
    }

}

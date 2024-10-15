import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
//        SwingUtilities.invokeLater(GUI::new);

        DbConnector dbConnector = new DbConnector();
        Connection connection = null;
        try {
            // Connect to MySQL server
            connection = dbConnector.connect(null);
            // Manage database setup and insertion
            DatabaseManager dbManager = new DatabaseManager(connection);
            // This will only create the database and insert default data if the database does not exist
            dbManager.setupDatabase();

            connection = dbConnector.connect("bdsm");
            DBInsertUpdateDelete crud = new DBInsertUpdateDelete(connection);

            new LoginManager();


        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            dbConnector.close();
        }
    }

    public static String urlparser(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            // Construct the base URL by getting the protocol, host
            String baseUrl = url.getProtocol() + "://" + url.getHost();
            return baseUrl + "/";
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            return null; // or return a default value
        }
    }

    public static String nameExtracter(String pageUrl) {
        try {
            URL url = new URL(pageUrl);
            String host =  url.getHost();

            if (host.startsWith("www.")) {
                host = host.substring(4);
            }
            // Remove ".com"
            int dotIndex = host.indexOf(".");
            if (dotIndex != -1) {
                host = host.substring(0, dotIndex);
            }
            return host;
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
            return null;
        }
    }

}

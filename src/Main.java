import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
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
//            DbSelect select = new DbSelect(connection);
//            List<String> pages = select.selectPages(); // Get list of page names
//
//            for (String pageName : pages) {
//                System.out.println(pageName); // Print each page name
//            }



        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL Error: " + e.getMessage());
        } finally {
            dbConnector.close();
        }
    }



}

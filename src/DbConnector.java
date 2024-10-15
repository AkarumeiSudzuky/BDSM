import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private Connection connection;

    public DbConnector() {
        this.connection = null;
    }

    public Connection connect(String dbName) throws SQLException, ClassNotFoundException {
        // Register JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Open connection to MySQL server
        System.out.println("Connecting to database...");
        String url = dbName == null ? "jdbc:mysql://localhost:3306/" : "jdbc:mysql://localhost:3306/" + dbName;
        connection = DriverManager.getConnection(url, "root", "");
        System.out.println("Connected to MySQL server");

        return connection;
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

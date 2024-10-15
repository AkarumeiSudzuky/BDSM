import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCreation {
    private Connection connection;

    public DbCreation(Connection connection) {
        this.connection = connection;
    }

    // Check if the database exists
    public boolean doesDatabaseExist() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SHOW DATABASES LIKE 'bdsm'");
            return resultSet.next();  // Return true if the database exists
        }
    }

    public void createDatabase() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            System.out.println("Creating database...");
            String sql = "CREATE DATABASE IF NOT EXISTS bdsm";
            stmt.executeUpdate(sql);
            System.out.println("Database created or already exists.");
        }
    }

    public void createTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            // Create `user` table
            String sql = "CREATE TABLE IF NOT EXISTS user ("
                    + "user_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "username VARCHAR(16) NOT NULL, "
                    + "password VARCHAR(32) NOT NULL"
                    + ")";
            stmt.executeUpdate(sql);

            // Create `block_list` table
            sql = "CREATE TABLE IF NOT EXISTS block_list ("
                    + "list_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "user_user_id INT NOT NULL, "
                    + "FOREIGN KEY (user_user_id) REFERENCES user(user_id)"
                    + ")";
            stmt.executeUpdate(sql);

            // Create `webpage` table
            sql = "CREATE TABLE IF NOT EXISTS webpage ("
                    + "page_id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(255) NOT NULL, "
                    + "url VARCHAR(255) NOT NULL"+")";
            stmt.executeUpdate(sql);

            sql = "CREATE TABLE IF NOT EXISTS block (" +
                    "    blockTime INT NOT NULL," +
                    "    emergency TINYINT(1) NOT NULL DEFAULT 0," +
                    "    block_list_id INT," +
                    "    FOREIGN KEY (block_list_id) REFERENCES block_list(list_id)," +
                    "    webpage_id INT," +
                    "    FOREIGN KEY (webpage_id) REFERENCES webpage(page_id)" +
                    ")";
            stmt.executeUpdate(sql);
        }
    }
}

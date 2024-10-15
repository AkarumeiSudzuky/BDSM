import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
    private Connection connection;

    public DatabaseManager(Connection connection) {
        this.connection = connection;
    }

    public void setupDatabase() throws SQLException, ClassNotFoundException {
        DbCreation dbCreation = new DbCreation(connection);

        // Create the database if it doesn't exist
        if (!dbCreation.doesDatabaseExist()) {
            System.out.println("First time running the program. Creating database and tables...");
            dbCreation.createDatabase();

            // Reconnect to the newly created database
            connection.close();
            DbConnector dbConnector = new DbConnector();
            connection = dbConnector.connect("bdsm");

            dbCreation = new DbCreation(connection);
            dbCreation.createTables();

            // Insert default data
            DefaultInsert defInsert = new DefaultInsert(connection);
            defInsert.insert();
        } else {
            System.out.println("Database already exists. Skipping setup.");
        }
    }
}

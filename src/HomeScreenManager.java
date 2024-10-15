import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

class HomeScreenManager {
    private Connection connection; // Database connection
    private DbSelect dbSelect;
    public HomeScreenManager() {
        DbConnector dbConnector = new DbConnector();
        try {
            this.connection = dbConnector.connect("bdsm"); // Initialize the instance variable
            dbSelect = new DbSelect(this.connection); // Use the initialized connection
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
        loadData();
    }

    private void loadData() {
        DbSelect dbSelect = new DbSelect(connection); // Use the DbSelect class to fetch data

        try {
            List<List<String>> pageDetails = dbSelect.selectPages(); // Fetch page details from DB

            // Extracting data into separate lists
            List<String> names = pageDetails.get(0);
            List<Integer> limits = pageDetails.get(1).stream().map(Integer::parseInt).toList(); // Convert String limits to Integer
            List<Boolean> emergencyStatuses = pageDetails.get(2).stream().map(status -> status.equals("Allowed")).toList(); // Convert to Boolean

            // Open HomeScreen with the loaded data
            new HomeScreen(names, limits, emergencyStatuses); // Pass data to HomeScreen
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception (could show a message dialog)
        }
    }
}

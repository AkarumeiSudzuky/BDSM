import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbSelect {
    private Connection connection;

    public DbSelect(Connection connection) {
        this.connection = connection;
    }

    //Select user data
    public boolean selectUser(String name, String password) throws SQLException {

        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // True if user exists, false otherwise
        }
    }
    public List<List<String>> selectPages() throws SQLException {
        String query = "SELECT webpage.name, block.blockTime, block.emergency " +
                "FROM webpage " +
                "INNER JOIN block ON webpage.page_id = block.webpage_id " +
                "INNER JOIN block_list ON block.block_list_id = block_list.list_id";

        List<String> names = new ArrayList<>();
        List<String> blockTimes = new ArrayList<>();
        List<String> emergencyStatuses = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Iterate through the ResultSet and collect page names and additional details
            while (rs.next()) {
                String name = rs.getString("name"); // Get the name from the webpage
                String blockTime = rs.getString("blockTime"); // Get blockTime from block
                boolean emergency = rs.getBoolean("emergency"); // Get emergency status from block

                // Convert emergency status to human-readable format
                String emergencyStatus = emergency ? "Allowed" : "Not Allowed";

                // Add details to respective lists
                names.add(name);
                blockTimes.add(blockTime);
                emergencyStatuses.add(emergencyStatus);
            }
        }

        // Return a list containing the three lists
        List<List<String>> result = new ArrayList<>();
        result.add(names);
        result.add(blockTimes);
        result.add(emergencyStatuses);
        return result;
    }

}

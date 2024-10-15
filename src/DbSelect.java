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
    public List<String> selectPages() throws SQLException {
        String query = "SELECT name FROM webpage"
                + " INNER JOIN block ON webpage.page_id = block.webpage_id"
                + " INNER JOIN block_list ON block.block_list_id = block_list.list_id";

        List<String> pageNames = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            // Iterate through the ResultSet and collect page names
            while (rs.next()) {
                pageNames.add(rs.getString("name")); // Assuming the column is called 'name'
            }
        }
        return pageNames;
    }
}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}

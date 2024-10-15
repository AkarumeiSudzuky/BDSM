import java.sql.Connection;
import java.sql.SQLException;

public class LoginManager {
    private LoginPage loginPage;
    private DbSelect dbSelect;

    public LoginManager() {
        DbConnector dbConnector = new DbConnector();
        try {
            Connection connection = dbConnector.connect("bdsm");
            dbSelect = new DbSelect(connection);
            loginPage = new LoginPage(this); // Pass itself to LoginPage for interaction
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void authenticate(String username, String password) {
        try {
            boolean isAuthenticated = dbSelect.selectUser(username, password);
            if (isAuthenticated) {
                // Go to HomeScreenManager instead of HomeScreen
                loginPage.dispose(); // Close the LoginPage
                new HomeScreenManager(); // Open HomeScreenManager
            } else {
                loginPage.showMessage("Invalid username or password.", true); // true for error
            }
        } catch (SQLException e) {
            loginPage.showMessage("Error during authentication: " + e.getMessage(), true);
        }
    }
}

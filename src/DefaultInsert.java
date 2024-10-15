import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultInsert {
    private Connection connection;

    public DefaultInsert(Connection connection) {
        this.connection = connection;
    }

    public void insert() throws SQLException {
        Statement stmt = null;
        try {
            stmt = connection.createStatement();

            // Insert into user
            String sql = "INSERT INTO user (username, password) VALUES ('default', 'password')";
            stmt.executeUpdate(sql);

            // Insert into block_list
            sql = "INSERT INTO block_list (user_user_id) VALUES (1)";
            stmt.executeUpdate(sql);

            // Insert into webpage
            sql = "INSERT INTO webpage (name, url) VALUES " +
                    "('YouTube', 'https://www.youtube.com/'), " +
                    "('Instagram', 'https://www.instagram.com/'), " +
                    "('TikTok', 'https://www.tiktok.com/'), " +
                    "('Tumblr', 'https://www.tumblr.com/'), " +
                    "('Pinterest', 'https://www.pinterest.com/');";
            stmt.executeUpdate(sql);

            //Insert into block
            sql = "INSERT INTO block (blockTime,emergency,block_list_id, webpage_id)VALUES "+
                    "(0, 0,1,1), " +
                    "(0, 0,1,2), " +
                    "(0, 0,1,3), " +
                    "(0, 0,1,4), " +
                    "(0, 0,1,5);";
            stmt.executeUpdate(sql);
            System.out.println("Inserted default data into tables.");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}

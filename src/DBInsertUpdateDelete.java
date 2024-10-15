import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBInsertUpdateDelete {
    private Connection connection;

    public DBInsertUpdateDelete(Connection connection) {
        this.connection = connection;
    }

    //Insert functions
    public void insert_user(String username, String password) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("INSERT INTO user (username, password) VALUES ('%s', '%s')", username, password);
            stmt.executeUpdate(sql);
        }
    }
    public void insert_list(Integer username_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("INSERT INTO block_list (user_user_id) VALUES (%d)", username_id);
            stmt.executeUpdate(sql);
        }
    }
    public void insert_webpage(String name, String url) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("INSERT INTO webpage (name, url) VALUES ('%s', '%s')", name, url);
            stmt.executeUpdate(sql);
        }
    }
    public void insert_block(Integer blockTime, Integer webpage_id, Integer block_list_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("INSERT INTO block (blockTime, block_list_id, webpage_id) VALUES (%d, %d, %d)", blockTime, block_list_id, webpage_id);
            stmt.executeUpdate(sql);
        }
    }

    //Delete functions
    public void delete_user(Integer user_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("DELETE FROM user WHERE user_id = '%d'", user_id);
            stmt.executeUpdate(sql);
        }
    }
    public void delete_list(Integer list_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("DELETE FROM block_list WHERE list_id = '%d'", list_id);
            stmt.executeUpdate(sql);
        }
    }
    public void delete_webpage(Integer webpage_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("DELETE FROM webpage WHERE webpage_id = '%d'", webpage_id);
            stmt.executeUpdate(sql);
        }
    }
    public void delete_block(Integer block_list_id, Integer webpage_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("DELETE FROM block_list WHERE block_list_id = '%d' and webpage_id = '%d'", block_list_id, webpage_id);
        }
    }

    //Update functions
    public void update_blockTime(Integer block_time, Integer page_id, Integer block_list_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("UPDATE block SET blockTime = '%d' WHERE webpage_id = '%d' AND block_list_id = '%d'", block_time, page_id, block_list_id);
            stmt.executeUpdate(sql);
        }
    }

    public void update_emergency(Boolean emergency, Integer page_id, Integer block_list_id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String sql = String.format("UPDATE block SET emergency = '%d' WHERE webpage_id = '%d' AND block_list_id = '%d'", emergency, page_id, block_list_id);
            stmt.executeUpdate(sql);
        }
    }

}

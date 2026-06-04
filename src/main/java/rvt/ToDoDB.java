package rvt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToDoDB {
    private static final String DB_URL = "jdbc:sqlite:todo.db";

    public ToDoDB() {
        initSchema();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private void initSchema() {
        String sql = "CREATE TABLE IF NOT EXISTS todo ("
                   + "id INTEGER PRIMARY KEY, "
                   + "task TEXT NOT NULL) STRICT";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException("Schema init failed: " + e.getMessage());
        }
    }

    
    public void add(String task) {
        String sql = "INSERT INTO todo (task) VALUES (?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Insert failed: " + e.getMessage());
        }
    }

    
    public List<String> findAll() {
        List<String> tasks = new ArrayList<>();
        String sql = "SELECT id, task FROM todo";
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tasks.add(rs.getInt("id") + ": " + rs.getString("task"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        return tasks;
    }

   
    public void removeById(int id) {
        String sql = "DELETE FROM todo WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Delete failed: " + e.getMessage());
        }
    }
}

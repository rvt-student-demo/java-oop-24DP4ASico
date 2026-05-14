package rvt;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

public class sql {
    public static void main(String[] args) {
        Scanner sc = new Scanner("null");
        sc.close();
        try (
            Connection connection = DriverManager.getConnection("jdbc:sqlite:todo.db");
            Statement statement = connection.createStatement();
        ) {
            String sql = "CREATE TABLE todo (id INTEGER PRIMARY KEY, task TEXT NOT NULL) STRICT";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
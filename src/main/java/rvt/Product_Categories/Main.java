package rvt.Product_Categories;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Connection conn = DatabaseConnection.connect();
        Scanner sc = new Scanner(System.in);

        createTables(conn);
        while(true) {
            System.out.println("1 - Pievienot kategoriju");
            System.out.println("2 - Pievienot produktu" );
            System.out.println("3 - Parādīt kategorijas");
            System.out.println("4 - Parādīt produktus");
            System.out.println("5 - Meklēt produktu pēc kategorijas");
            System.out.println("6 - Dzēst produktu");
            System.out.println("0 - Iziet");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> addCategory(conn, sc);
                case 2 -> addProduct(conn, sc);
                case 3 -> showCategories(conn);
                case 4 -> showProducts(conn);
                case 5 -> searchProductsByCategory(conn, sc);
                case 6 -> deleteProduct(conn, sc);
                case 0 -> {
                    try {
                        if (conn != null && !conn.isClosed()) conn.close();
                    } catch (SQLException e) {
                        System.out.println("Error closing connection: " + e.getMessage());
                    }
                    System.out.println("Programma beidzas. Uz redzēšanos!");
                    return;
                }
                default -> System.out.println("Nederīga izvēle. Mēģiniet vēlreiz.");
            }
        }
    }

    private static void createTables(Connection conn) {
        try (Statement st = conn.createStatement()) {
            st.execute("""
                CREATE TABLE IF NOT EXISTS categories (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL
                );
            """);
            st.execute("""
                CREATE TABLE IF NOT EXISTS products (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    price REAL NOT NULL,
                    category_id INTEGER,
                    FOREIGN KEY (category_id) REFERENCES categories(id)
                );
            """);
        } catch (SQLException e) {
            System.out.println("Error creating tables: " + e.getMessage());
        }
    }

    private static void addCategory(Connection conn, Scanner sc) {
        System.out.print("Ievadi kategorijas nosaukumu: ");
        String name = sc.nextLine();
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO categories(name) VALUES(?)")) {
            ps.setString(1, name);
            ps.executeUpdate();
            System.out.println("Kategorija pievienota!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addProduct(Connection conn, Scanner sc) {
        System.out.print("Ievadi produkta nosaukumu: ");
        String name = sc.nextLine();
        System.out.print("Ievadi produkta cenu: ");
        double price = sc.nextDouble();
        sc.nextLine();
        System.out.print("Ievadi kategorijas ID: ");
        int categoryId = sc.nextInt();
        sc.nextLine(); 

        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO products(name, price, category_id) VALUES(?, ?, ?)")) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setInt(3, categoryId);
            ps.executeUpdate();
            System.out.println("Produkts pievienots!");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showCategories(Connection conn){
        try( PreparedStatement ps = conn.prepareStatement("SELECT * FROM categories");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " - " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void showProducts(Connection conn) {
        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT p.id, p.name, p.price, c.name AS category
            FROM products p
            JOIN categories c ON p.category_id = c.id
        """);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.printf("%d | %s | %.2f | %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getDouble("price"), rs.getString("category"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void searchProductsByCategory(Connection conn, Scanner sc) {
        System.out.print("Ievadi kategorijas ID: ");
        int categoryId = sc.nextInt();
        sc.nextLine();

        try (PreparedStatement ps = conn.prepareStatement("""
            SELECT p.id, p.name, p.price
            FROM products p
            WHERE p.category_id = ?
        """)) {
            ps.setInt(1, categoryId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%d | %s | %.2f%n",
                            rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void deleteProduct(Connection conn, Scanner sc) {
        System.out.print("Ievadi dzēšamā produkta ID: ");
        int productId = sc.nextInt();
        sc.nextLine();

        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM products WHERE id = ?")) {
            ps.setInt(1, productId);
            int rowsAffected = ps.executeUpdate();  
            if (rowsAffected > 0) {
                System.out.println("Produkts dzēsts!");
            } else {
                System.out.println("Produkts ar ID " + productId + " nav atrasts.");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


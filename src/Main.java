import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/ProductTable";
        String username = "root";
        String password = "";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement()) {
            createProductTable(stmt);
            displayProducts(stmt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createProductTable(Statement stmt) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Product (" +
                "  id INT," +
                "  name VARCHAR(255)," +
                "  price_per_unit DOUBLE," +
                "  active_for_sell BOOLEAN)";
        stmt.executeUpdate(createTableQuery);
        System.out.println("Table 'Product' created successfully.");
    }

    private static void displayProducts(Statement stmt) throws SQLException {
        String selectQuery = "SELECT * FROM Product";
        try (ResultSet rs = stmt.executeQuery(selectQuery)) {
            System.out.println("id\tname\tprice_per_unit\tactive_for_sell");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price_per_unit = rs.getDouble("price_per_unit");
                boolean active_for_sell = rs.getBoolean("active_for_sell");
                System.out.printf("%d\t%s\t%.2f\t%b%n", id, name, price_per_unit, active_for_sell);
            }
        }
    }
}
import java.sql.*;

class mysqlCon {
    public static void main(String args[]) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_data?user=root")) {
            if (conn != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Failed");
            }
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from authors"); // Changed to Student table
            while (rs.next())
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3)); // Adjusted for Student table columns

            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
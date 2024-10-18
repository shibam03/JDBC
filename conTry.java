import java.sql.*;
import java.util.*;
public class conTry{
	static Scanner in = new Scanner(System.in);
	public static void main(String args[]){
        Connection conn = connectDatabase();
		//tasks with the connection
		if(conn!=null){
			displayValues(conn);
			insertValues(conn);
			displayValues(conn);
			closeConnection(conn);
		}
		else{
			System.out.println("There is no database connected.");
		}
    }
    public static Connection connectDatabase(){
		Connection conn = null;
        try{
			conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/library_data?user=root");
            if (conn != null) {
				System.out.println("Preparing to connect to database.....");
				Thread.sleep(1000);
                System.out.println("Connected to database");
            } else {
				System.out.println("Preparing to connect to database");
				Thread.sleep(2000);
                System.out.println("Failed connecting to database");
            }
			return conn;
        }
		catch(Exception e){
			System.out.println(e);
			return null;
		}
    }
	public static void closeConnection(Connection conn){
		System.out.println("Preparing to close the connection.....");
		//closure method
		try{
			if(conn!=null){
				conn.close();
				Thread.sleep(1000);
				System.out.println("Connection closed succesfully");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	public static void displayValues(Connection conn){
		//display values
		System.out.println();
		System.out.println("The values are");
		System.out.println("--------------------------------------------------------------------");
		try{
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from authors"); // Changed to Student table
			while (rs.next()){
				System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
				Thread.sleep(650);
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
		System.out.println("--------------------------------------------------------------------");
		System.out.println();
	}
	
	public static void insertValues(Connection conn){
		try{
			System.out.println("Preparing to insert....");
			Thread.sleep(1000);
			if(conn!=null){
				System.out.println("Enter Author ID:");
				String AID = in.nextLine();
				System.out.println("Enter Author name:");
				String name = in.nextLine();
				System.out.println("Enter Author age:");
				int age = in.nextInt();
				
				String query = "INSERT INTO authors (AID, NAME, AGE) VALUES (?, ?, ?)";
				
				PreparedStatement pstmt = conn.prepareStatement(query);
				pstmt.setString(1, AID);
				pstmt.setString(2, name);
				pstmt.setInt(3, age);
				
				int rowsAffected = pstmt.executeUpdate(); // Execute the update!
                System.out.println(rowsAffected + " row(s) inserted.");
            
				
				System.out.println("Values inserted...");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
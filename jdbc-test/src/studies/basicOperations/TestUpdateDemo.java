package studies.basicOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Basic exemplo for operation update for jdbc mysql
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestUpdateDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sql = "update employees set email='john.doe@code.com' where last_name='Doe' and first_name='John'";

		try {
			// Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// Create a statement
			stmt = con.createStatement();

			// Call helper method to display the employee's information
			System.out.println("BEFORE THE UPDATE...");
			displayEmployee(con, "John", "Doe");

			// UPDATE the employee
			System.out.println("\nEXECUTING THE UPDATE FOR: John Doe\n");

			int rowsAffected = stmt.executeUpdate(sql);

			// Call helper method to display the employee's information
			System.out.println("AFTER THE UPDATE...");
			displayEmployee(con, "John", "Doe");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}

			if (stmt != null) {
				stmt.close();
			}

			if (rs != null) {
				rs.close();
			}
		}
	}

	private static void displayEmployee(Connection con, String first, String last) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from employees where first_name = ? and last_name = ?";

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, first);
			ps.setString(2, last);

			// now execute the query
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			ps.close();
		}
	}
}

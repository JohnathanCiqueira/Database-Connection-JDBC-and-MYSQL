package studies.basicOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestDeleteDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		try {
			// Get a connection to database
			con = DriverManager.getConnection(dbUrl, user, pass);

			// Create a statement
			stmt = con.createStatement();

			// Call helper method to display the employee's information
			System.out.println("BEFORE THE DELETE...");
			displayEmployee(con, "John", "Doe");

			// DELETE the employee
			System.out.println("\nDELETING THE EMPLOYEE: John Doe\n");

			String sql = "delete from employees " + "where last_name='Doe' and first_name='John'";

			int linhasAfetadas = stmt.executeUpdate(sql);

			// Call helper method to display the employee's information
			System.out.println("AFTER THE DELETE...");
			displayEmployee(con, "John", "Doe");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

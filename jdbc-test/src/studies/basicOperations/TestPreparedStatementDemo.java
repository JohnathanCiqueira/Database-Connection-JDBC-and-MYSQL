package studies.basicOperations;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestPreparedStatementDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String senha = "root";

		String sql = "select * from employees where salary > ? and department=?";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, senha);

			// 2. Prepare statement
			ps = con.prepareStatement(sql);

			// 3. Set the parameters
			ps.setDouble(1, 80000);
			ps.setString(2, "Legal");

			// 4. Execute SQL query
			rs = ps.executeQuery();

			// 5. Display the result set
			display(ps, rs);
			
			// Reuse the prepared statement: salary > 25000, department = HR
			
			System.out.println("\n\nReuse the prepared statement: salary > 25000, department = HR");
			
			// 6. Set the parameters
			ps.setDouble(1, 25000);
			ps.setString(2, "HR");
			
			// 7. Execute SQL query
			rs = ps.executeQuery();
			
			// 8. Display the result set
			display(ps, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void display(PreparedStatement ps, ResultSet rs) throws SQLException {

		try {
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}

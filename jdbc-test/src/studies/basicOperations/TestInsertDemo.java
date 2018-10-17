package studies.basicOperations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestInsertDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String dbUrl = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(dbUrl, user, pass);

			// 2. Create a statement
			stmt = con.createStatement();

			// 3. Insert a new employee
			System.out.println("Inserting a new employee to database\n");

			String sql = "insert into employees " + "(last_name, first_name, email, department, salary) " + "values "
					+ "('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)";

			int linhasAfetadas = stmt.executeUpdate(sql);

			// 4. Verify this by getting a list of employees
			rs = stmt.executeQuery("select * from employees order by last_name");

			// 5. Process the result set
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getShort("first_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
	}
}

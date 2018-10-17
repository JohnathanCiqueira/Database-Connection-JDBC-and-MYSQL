package studies.callingStoredProcedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Test IN OUT with Result Set
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestInOutResultSetDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		CallableStatement cs = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sql = "{call get_employees_for_department(?)}";

		String theDepartment = "Engineering";

		try {
			// Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// Prepare the stored procedure call
			cs = con.prepareCall(sql);

			// Set the parameter
			cs.setString(1, theDepartment);

			// Call stored procedure
			System.out.println("Calling stored procedure. get_employees_for_department('" + theDepartment + "')");
			cs.execute();
			System.out.println("Finished calling stored procedure.\n");

			// Get the result set
			rs = cs.getResultSet();

			// Display the result set
			display(rs);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			cs.close();
			rs.close();
		}

	}

	private static void display(ResultSet rs) throws SQLException {

		while (rs.next()) {
			System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name") + " - " + rs.getString("salary"));
		}
	}

}

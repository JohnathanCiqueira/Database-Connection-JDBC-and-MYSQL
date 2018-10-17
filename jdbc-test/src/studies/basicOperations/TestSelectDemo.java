package studies.basicOperations;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Basic exemplo for operation select for jdbc mysql
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestSelectDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sql = "select * from employees";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// 2. Create a statement
			stmt = con.createStatement();

			// 3. Execute SQL query
			rs = stmt.executeQuery(sql);

			// 4. Process the result set
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}

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
}

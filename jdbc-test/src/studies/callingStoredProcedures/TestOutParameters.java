package studies.callingStoredProcedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Test calling stored procedure with OUT parameters
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestOutParameters {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		CallableStatement cs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String theDepartment = "Engineering";

		String sql = "{call get_count_for_department(?, ?)}";

		try {
			// Get a conneection to database
			con = DriverManager.getConnection(url, user, pass);
			
			// Prepare the stored procedure call
			cs = con.prepareCall(sql);
			
			// Set the parameters
			cs.setString(1, theDepartment);
			cs.registerOutParameter(2, Types.INTEGER);
			
			
			// Call stored procedure
			System.out.println("Calling stored procedure. get_count_for_department('" + theDepartment + "', ?)");
			cs.execute();
			System.out.println("Finished calling stored procedure");
			
			// Get the value of the OUT parameter
			int theCount = cs.getInt(2);
			
			System.out.println("\nThe count = " + theCount);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

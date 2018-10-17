package studies.callingStoredProcedures;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Test IN OUT
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */
public class TestInOutDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		CallableStatement cs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String theDepartment = "Engineering";

		String sql = "{call greet_the_department(?)}";

		try {
			// Get a connection to database
			con = DriverManager.getConnection(url, user, pass);
			
			// Prepare the stored procedure call
			cs = con.prepareCall(sql);
			
			// Set the parameters
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.setString(1, theDepartment);
			
			// Call stored procedure
			System.out.println("Calling stored procedure. greet_the_department('" + theDepartment +"')");
			cs.execute();
			System.out.println("Finished calling stored procedure");
			
			// Get the value of the INOUT parameter
			String theResult = cs.getString(1);
			
			System.out.println("\nThe result = " + theResult);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

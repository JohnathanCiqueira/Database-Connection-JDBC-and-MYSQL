package studies.callingStoredProcedures;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Test calling stored procedure with IN parameters
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestCallStoreProcedure {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		CallableStatement cs = null;
		
		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";
		
		String sql = "{call increase_salaries_for_department(?, ?)}";
		
		try {
			// Get a connection to database
			con = DriverManager.getConnection(url, user, pass);
			
			String theDepartment = "Engineering";
			int theIncreaseAmount = 10000;
			
			// Show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			showSalaries(con , theDepartment);
			
			// Prepare the store procedure call
			cs = con.prepareCall(sql);
			
			// Set the parameters
			cs.setString(1, theDepartment);
			cs.setDouble(2, theIncreaseAmount);
			
			// Call stored procedure
			System.out.println("\n\nCalling stored procedure. increase_salaries_for_department('" + theDepartment + "', "+ theIncreaseAmount +")");
			cs.execute();
			System.out.println("Finished calling stored procedure");
			
			// Show salaries AFTER
			System.out.println("\n\nSalaries AFTER\n");
			showSalaries(con, theDepartment);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			cs.close();
		}
	}

	private static void showSalaries(Connection con, String theDepartment) throws SQLException{

		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "Select * from employees where department = ?";
		
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1, theDepartment);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name") + " - " + rs.getString("department"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ps.close();
			rs.close();
		}
		
		
	}

}

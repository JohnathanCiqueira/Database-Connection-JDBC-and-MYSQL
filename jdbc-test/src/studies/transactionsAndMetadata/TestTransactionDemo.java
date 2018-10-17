package studies.transactionsAndMetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Test Transaction JDBC
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */
public class TestTransactionDemo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement st = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sqlDelete = "delete from employees where department='HR'";
		String sqlUpdate = "update employees set salary = 300000 where department='Engineering'";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// Turn off auto commit
			con.setAutoCommit(false);

			// Show salaries BEFORE
			System.out.println("Salaries BEFORE\n");
			showSalaries(con, "HR");
			showSalaries(con, "Engineering");

			// Transaction Step 1: Delete all HR employees
			st = con.createStatement();
			st.executeUpdate(sqlDelete);

			// Transaction Step 2: Set salaries to 300000 for all Engineerging
			// employees
			st.executeUpdate(sqlUpdate);

			System.out.println("\n>> Transaction steps are ready. \n");

			// Ask user if it is okay to save
			boolean ok = askUserIfOkToSave();

			// So if it's OK then we will commit the data in the database and we'll
			// sprint out transaction committed
			if (ok) {
				// store in database
				con.commit();
				System.out.println("\n>> Transaction COMMITTED.\n");
			} else {
				// discard
				con.rollback();
				System.out.println("\n>> Transaction ROLLED BACK. \n");
			}

			// Show salaries AFTER
			System.out.println("Salaries AFTER\n");
			showSalaries(con, "HR");
			showSalaries(con, "Engineering");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			st.close();
		}

	}

	// Helper method
	private static boolean askUserIfOkToSave() {
		// And then we're going to prompt the user if it's OK to save.
		// So this is just a simple help or math that that's just going to repeat
		// user input if the user enters
		// Yes it's going to return true if the user knows anything else like no
		// return false

		Scanner sc = new Scanner(System.in);

		System.out.println("\nIs it okay to save? yes/no:");
		String aux = sc.nextLine();
		
		sc.close();

		if (aux.equals("yes")) {
			return true;
		} else {
			return false;
		}

	}

	private static void showSalaries(Connection con, String department) throws SQLException {

		ResultSet rs = null;
		PreparedStatement ps = null;
		String sql = "select * from employees where department = ?";

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, department);

			rs = ps.executeQuery();
			
			if(department.equals("HR")) {
				System.out.println("Show Salaries for Department: HR");
			}else {
				System.out.println("\nShow Salaries for Departmente: Engineering");
			}

			while (rs.next()) {
				System.out.println(
						rs.getString("last_name") + ", " + rs.getString("first_name") + " - " + rs.getString("salary"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

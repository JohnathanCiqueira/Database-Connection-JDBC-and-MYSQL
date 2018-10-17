package studies.transactionsAndMetadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Test MetaData Basic Info
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */
public class TestMetaDataBasicInfo {

	public static void main(String[] args) throws SQLException {

		Connection con = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);
			
			// 2. Get metadata
			DatabaseMetaData databaseMetaData = con.getMetaData();
			
			// 3. Display info about database
			System.out.println("Product name: " + databaseMetaData.getDatabaseProductName());
			System.out.println("Product version: " + databaseMetaData.getDatabaseProductVersion());
			System.out.println();
			
			// 4. Display info about JDBC Driver
			System.out.println("JDBC Driver name: " + databaseMetaData.getDriverName());
			System.out.println("JDBC Driver version: " + databaseMetaData.getDriverVersion());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con);
		}

	}

	private static void close(Connection con) {

		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

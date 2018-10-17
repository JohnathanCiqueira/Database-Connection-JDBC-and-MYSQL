package studies.databaseConfiguration;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Test connecting to a database from a Properties file
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */
public class ConnectionPropertiesFile {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			// 1. Load the properties file
			Properties props = new Properties();
			props.load(new FileInputStream("demo.properties"));

			// 2. Read the props
			String user = props.getProperty("user");
			String pass = props.getProperty("password");
			String url = props.getProperty("dburl");

			System.out.println("Connecting to database...");
			System.out.println("Database URL: " + url);
			System.out.println("User: " + user);

			// 3. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			System.out.println("\nConnection sucessful!\n");

			String sql = "select * from employees";

			st = con.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("last_name") + ", " + rs.getString("first_name"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}

			if (st != null) {
				st.close();
			}

			if (rs != null) {
				rs.close();
			}
		}
	}

}

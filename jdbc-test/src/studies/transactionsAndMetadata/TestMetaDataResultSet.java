package studies.transactionsAndMetadata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test reading ResultSet MetaData
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */
public class TestMetaDataResultSet {

	public static void main(String[] args) throws SQLException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sql = "select id, last_name, first_name, salary from employees";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// 2. Get result set metadata
			st = con.createStatement();
			rs = st.executeQuery(sql);

			// 3. get result set metadata
			ResultSetMetaData rsMetaData = rs.getMetaData();

			// 4. Display info
			int columnCount = rsMetaData.getColumnCount();
			System.out.println("Column count: " + columnCount + "\n");

			for (int column = 1; column <= columnCount; column++) {
				System.out.println("Column name: " + rsMetaData.getColumnName(column));
				System.out.println("Column type name: " + rsMetaData.getColumnTypeName(column));
				System.out.println("Is Nullable: " + rsMetaData.isNullable(column));
				System.out.println("Is Auto Increment: " + rsMetaData.isAutoIncrement(column) + "\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			st.close();
			rs.close();
		}
	}

}

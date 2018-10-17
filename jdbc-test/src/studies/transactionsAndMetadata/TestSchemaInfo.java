package studies.transactionsAndMetadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Test Schema Info
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */
public class TestSchemaInfo {

	public static void main(String[] args) throws SQLException {

		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		Connection con = null;
		ResultSet rs = null;

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);
			
			// 2. Get metadata
			DatabaseMetaData databaseMetaData = con.getMetaData();
			
			// 3. get list of tables
			System.out.println("List of Tables");
			System.out.println("--------------");
			
			rs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern, types);
			
			while(rs.next()) {
				System.out.println(rs.getString("TABLE_NAME"));
			}
			
			// 4. Get list of columns
			System.out.println("\n\nList of Columns");
			System.out.println("--------------");
			
			rs = databaseMetaData.getColumns(catalog, schemaPattern, "employees", columnNamePattern);
			
			while (rs.next()) {
				System.out.println(rs.getString("COLUMN_NAME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			rs.close();
		}

	}

}

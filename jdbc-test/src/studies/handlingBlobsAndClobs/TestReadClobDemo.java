package studies.handlingBlobsAndClobs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Test Read in Clob
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestReadClobDemo {

	public static void main(String[] args) throws SQLException, IOException {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		Reader input = null;
		FileWriter output = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sql = "select resume from employees where email='john.doe@foo.com'";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// 2. Execute statement
			st = con.createStatement();
			rs = st.executeQuery(sql);

			// 3. Set up a handle to the file
			File file = new File("resume_from_db.txt");
			output = new FileWriter(file);

			if (rs.next()) {
				input = rs.getCharacterStream("resume");
				System.out.println("Reading resume from database...");
				System.out.println(sql);

				int theChar;
				while ((theChar = input.read()) > 0) {
					output.write(theChar);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closes(con, st, rs, input, output);
		}
	}

	private static void closes(Connection con, Statement st, ResultSet rs, Reader input, FileWriter output)
			throws SQLException, IOException {

		if (con != null) {
			con.close();
		}

		if (st != null) {
			st.close();
		}

		if (rs != null) {
			rs.close();
		}

		if (input != null) {
			input.close();
		}

		if (output != null) {
			output.close();
		}
	}

}

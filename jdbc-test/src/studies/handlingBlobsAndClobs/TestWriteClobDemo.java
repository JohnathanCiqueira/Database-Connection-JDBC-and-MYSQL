package studies.handlingBlobsAndClobs;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Test Write in Clob
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestWriteClobDemo {

	public static void main(String[] args) throws SQLException, IOException {

		Connection con = null;
		PreparedStatement ps = null;

		FileReader input = null;

		String url = "jdbc:mysql://localhost:3306/demo?useSSL=false";
		String user = "root";
		String pass = "root";

		String sql = "update employees set resume = ? where email = 'john.doe@foo.com'";

		try {
			// 1. Get a connection to database
			con = DriverManager.getConnection(url, user, pass);

			// 2. Prepare statement
			ps = con.prepareStatement(sql);

			// 3. Set parameter for resume file name
			File file = new File("sample_resume.txt");
			input = new FileReader(file);
			ps.setCharacterStream(1, input);

			System.out.println("Reading inpu file: " + file.getAbsolutePath());

			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + file);
			System.out.println(sql);

			ps.executeUpdate();

			System.out.println("\nCompleted successfully!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closes(con, ps, input);
		}

	}

	private static void closes(Connection con, PreparedStatement ps, FileReader input) throws SQLException {

		try {

			if (con != null) {
				con.close();
			}

			if (ps != null) {
				ps.close();
			}

			if (input != null) {
				input.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

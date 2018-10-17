package studies.handlingBlobsAndClobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Test Write in Blob
 * 
 * @author jciqueira https://github.com/JohnathanCiqueira/
 */

public class TestWriteBlobDemo {

	public static void main(String[] args) throws SQLException, IOException {

		Connection con = null;
		PreparedStatement ps = null;
		FileInputStream input = null;

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
			File theFile = new File("sample_resume.pdf");
			input = new FileInputStream(theFile);
			ps.setBinaryStream(1, input);
			
			System.out.println("Reading input file: " + theFile.getAbsolutePath());
			
			// 4. Execute statement
			System.out.println("\nStoring resume in database: " + theFile);
			System.out.println(sql);
			
			ps.executeUpdate();
			
			System.out.println("\nCompleted sucessfully!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
			ps.close();
			input.close();
		}
	}

}

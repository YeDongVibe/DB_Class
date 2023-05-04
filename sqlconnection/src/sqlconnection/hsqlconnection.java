package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class hsqlconnection {

	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "org.h2.Driver";
			String url = "jdbc:h2:tcp://localhost/~/telephone";

			String username = "YeDongVibe";
			String password = "1234";

			Class.forName(driver);

			con = DriverManager.getConnection(url, username, password);

			System.out.println("Connection Succes");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}

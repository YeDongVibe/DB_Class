package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class sqlconnection {
	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";

			String username = "YeDongVibe";
			String password = "1234";

			Class.forName(driver); //class라는 클래스가 있는데 forName이라는 매서드를 호출.mysql에서 쓸수있는 Driver를 load함.

			con = DriverManager.getConnection(url, username, password);

			System.out.println("Connection Succes");
		} catch (Exception e) { // 모든 예외를 받아드린다.
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

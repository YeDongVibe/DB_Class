package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class hdatainsertpre {
	private void insertContactWithPreStatement(Connection con) {

		Statement st = null;
		String[] cates = { "friend", "family", "cowork", "etc" };
		Random rd = new Random();
		int totcnt = 1000000;

		try {
			st = con.createStatement();
			for (int i = 0; i <= totcnt; i++) {
				String NAME = "NAME" + i;
				String CATEGORY = cates[rd.nextInt(4)];
				String ADDRESS = "ADDRESS" + i;
				String WORK = "WORK" + i;
				String BIRTHDAY = String.format("%4d-%02d-%02d", rd.nextInt(1950, 2022), rd.nextInt(1, 13),
						rd.nextInt(1, 29));

				String sql = String.format(
						"insert into contact (CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDAY)"
								+ " values (%d, '%s','%s', '%s', '%s', '%s')",
						i, NAME, CATEGORY, ADDRESS, WORK, BIRTHDAY);
				st.executeUpdate(sql);

				System.out.println(String.format("%.2f:%d/%d", i * 100 / (double) totcnt, i, totcnt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection connectDB() {
		try {
			Class.forName("org.h2.Driver");
			Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "YeDongVibe", "1234");
			System.out.println("데이터베이스가 연결되었습니다.");
			return con;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closeDB(Connection con) {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		hdatainsertpre aa = new hdatainsertpre();

		Connection con = aa.connectDB();
		if (con != null) {
			aa.insertContactWithPreStatement(con);
			aa.closeDB(con);
		}
	}

}

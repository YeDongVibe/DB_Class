package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class hdatainsert {

	private void insertContactWithStatement(Connection con) {

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

	private void insertPhoneWithPreStatement(Connection con) {

		String[] types = { "phnumber", "hnumber", "comnumber", "fax", "etc" };
		Random rd = new Random();
		int totcnt = 1000000;
		
		String sql = "insert into PHONE (CID, SEQ, NUMBER, TYPE) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i = 0; i <= totcnt; i++) {
				int CID = i;
				int SEQ = i;
				int idx = rd.nextInt(5);
				
				String TYPE = types[idx];
				String NUMBER;
				if(idx == 0) 
					NUMBER = String.format("010-%4d-%4d", rd.nextInt(1000, 10000), rd.nextInt(1000, 10000));
				else
					NUMBER = String.format("%d-%4d-%4d",rd.nextInt(100, 1000), rd.nextInt(1000, 10000), rd.nextInt(1000, 10000));
				
				
				ps.setInt(1, CID);
				ps.setInt(2, SEQ);
				ps.setString(3, NUMBER);
				ps.setString(4, TYPE);
				
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "YeDongVibe", "1234");
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
		hdatainsert aa = new hdatainsert();

		Connection con = aa.connectDB();
		if (con != null) {
			//aa.insertContactWithStatement(con);
			aa.insertPhoneWithPreStatement(con);
			aa.closeDB(con);
		}
	}

}

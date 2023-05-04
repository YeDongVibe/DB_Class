package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class hsqlinsertdata {
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;

	private void selectdata () {
		try
		{
			st = con.createStatement();
			rs = st.executeQuery("Select DNO, DNAME, BUDGET from DEPT");

			while(rs.next()) {
				System.out.println(String.format("%s. %s, %d",
						rs.getString("DNO"), rs.getString("DNAME"), rs.getInt("BUDGET")));
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}finally
		{
			try {
				if (rs != null)
					rs.close();
				if (st != null)
					st.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void insertDept(String dno, String dname, int budget) {

		String sql = "insert into dept (dno, dname, budget) values (?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dno);// ?의 순서중 첫번째 ?.
			ps.setString(2, dname);
			ps.setInt(3, budget);
			ps.executeUpdate(); // Int를 return함. 즉 업데이트 된 레코드의 갯수를 return함.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터베이스가 입력되었습니다.");

	}

	private boolean connectDB() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/telephone", "YeDongVibe", "1234");
			System.out.println("데이터베이스가 연결되었습니다.");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private void closeDB() {
		try {
			con.close();
			System.out.println("데이터베이스가 닫혔습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		hsqlinsertdata tt = new hsqlinsertdata();
//		for (int i = 0; i < 100; i++) {
//			if (tt.connectDB()) {
//				tt.insertDept("D" + i, "D" + i, i);
//				tt.closeDB();
//			}
//		}
		
		if(tt.connectDB()) {
			tt.selectdata();
			tt.closeDB();
		}

	}

}

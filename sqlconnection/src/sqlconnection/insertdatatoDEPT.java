package sqlconnection;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//Statement : 완결된 query 문장을 실행할 때
//PreparedStatement : 변수로 값을 추가할 수 있는 query문을 실행할 때

//select => executeQuery : PreparedStatement, Statement
//insert/delete/update => executeUpdate : PreparedStatement, Statement

public class insertdatatoDEPT {
	Connection con = null;

	// Statement, executeUPdate 사용.
	private void insertDeptStatement(String dno, String dname, int budget) {
		// String sql = String.format("insert into dept (dno, dname, budget) values
		// ('%s', '%s', '%d')", dno, dname, budget);

		try {
			Statement st = con.createStatement();
			int cnt = st.executeUpdate(String.format(String
					.format("insert into dept (dno, dname, budget) values ('%s', '%s', '%d')", dno, dname, budget)));

			System.out.println("데이터베이스가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// PreparedStatement, executeUpdate사용.
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "YeDongVibe", "1234");
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

	private void deleteDepttriggerStatement(int from, int to) {
		try {
			Statement st = con.createStatement();
			int cnt = st.executeUpdate(
					String.format(String.format("delete from depttrigger where %d <= id and id <= %d", from, to)));

			System.out.println("데이터가" + cnt + "개가 삭제되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteDepttriggePrepared(int from, int to) {
		String sql = "delete from depttrigger where ? <= id and id <= ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, from);// ?의 순서중 첫번째 ?.
			ps.setInt(2, to);
			ps.executeUpdate(); // Int를 return함. 즉 업데이트 된 레코드의 갯수를 return함.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("데이터가 삭제되었습니다.");

	}

	public static void main(String[] args) {
		insertdatatoDEPT tt = new insertdatatoDEPT();
		if (tt.connectDB()) {
			// tt.insertDept("d10", "dname", 123);
			// tt.insertDeptStatement("d11", "dname", 556);
			// tt.deleteDepttriggerStatement(1, 10);
			tt.deleteDepttriggePrepared(11, 12);
			tt.closeDB();
		}

	}

}

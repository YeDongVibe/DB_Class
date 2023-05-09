package ddd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

class QueryExe {
	int num;
	String text;

	QueryExe(int num, String text) {
		this.num = num;
		this.text = text;
	}

	int getNum() {
		return num;
	}

	String getText() {
		return text;
	}
}

public class DDUlove {

	private Connection connectDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "YeDongVibe", "1234");
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

	public static void exe01(Connection con) {

		Statement st;
		String sql = "Select jname from j where city = 'London';";
		String sql2 = "Select sname from s where sno in select sno from spj where jno ='j1';";

		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSet rs2 = st.executeQuery(sql2);

			while (rs.next()) {
				System.out.println(String.format("%s", rs.getString("jname")));
				
			}
			while (rs2.next()) {
				System.out.println(String.format("%s", rs2.getString("sname")));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("-".repeat(80));

	}

	public static void main(String[] args) {

		DDUlove tt = new DDUlove();

		Connection con = tt.connectDB();

		ArrayList<QueryExe> list = new ArrayList<>();

		list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라"));
		list.add(new QueryExe(2, "문제2"));

		Scanner sc = new Scanner(System.in);

		while (true) {

			for (QueryExe qe : list) {
				System.out.println(String.format("%d. %s", qe.getNum(), qe.getText()));
			}
			System.out.print("선택<0:exit>: ");
			int sel = sc.nextInt();
			if (sel == 0)
				break;

			switch (sel) {
			case 1:
				exe01(con);
				break;
			case 2:
				exe01(con);
				break;
			default:
				System.out.println("제대로 선택하도록....");
			}

		}
		System.out.println("종료합니다.");
		sc.close();
		tt.closeDB(con);

	}

}
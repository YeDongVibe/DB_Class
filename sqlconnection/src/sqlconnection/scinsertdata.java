package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class scinsertdata {

	Connection con = null;

	private void insertDB(int CID, String NAME, String CATEGORY, String ADDRESS, String WORK, String BIRTHDAY) {

		String sql = "insert into CONTACT (CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDAY) values (?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, CID);
			ps.setString(2, NAME);
			ps.setString(3, CATEGORY);
			ps.setString(4, ADDRESS);
			ps.setString(5, WORK);
			ps.setString(6, BIRTHDAY);

			ps.executeUpdate();
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
		scinsertdata dd = new scinsertdata();
		Scanner sc = new Scanner(System.in);
		System.out.println("데이터를 입력하세요 : ");

		if (!dd.connectDB())
			return;

		while (true) {
			System.out.print("CID : ");
			int CID = sc.nextInt();
			if (CID < 0)
				break;

			System.out.print("Name : ");
			String NAME = sc.next();

			String CATEGORY;
			while (true) {
				System.out.print("Cate : (1.friend, 2.cowork, 3.family, 4.etc, 0.exit");
				int icate = sc.nextInt();
				if (icate == 0) {
					sc.close();
					return;
				} else if (icate == 1)
					CATEGORY = "friend";
				else if (icate == 2)
					CATEGORY = "cowokr";
				else if (icate == 3)
					CATEGORY = "family";
				else if (icate == 4)
					CATEGORY = "etc";
				else
					continue;
				break;
			}
			System.out.print("Addr : ");
			String ADDRESS = sc.next();
			System.out.print("Work : ");
			String WORK = sc.next();
			System.out.print("Birth : ");
			String BIRTHDAY = sc.next();

			dd.insertDB(CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDAY);
			//System.out.println(String.format("CID:%d,Name:%s,Category:%s,Address:%s,Work:%s,BirthDay:%s", CID, NAME, CATEGORY, ADDRESS, WORK, BIRTHDAY));
		}
		System.out.println("작업을 종료합니다.");
		sc.close();
		dd.closeDB();
	}
}

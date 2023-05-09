package subject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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

public class test01 {

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

	public static void exe01(Connection con, String sql) {
		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			while (rs.next()) {
				int columnCnt = rsmd.getColumnCount();
				for(int i = 1; i<=columnCnt; i++) {
					System.out.print(rs.getString(rsmd.getColumnName(i)) + " ");
				
				}
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		System.out.println("-".repeat(80));

	}

	public static void main(String[] args) {
		
		String sql = "";

		test01 tt = new test01();
		Connection con = tt.connectDB();

		ArrayList<QueryExe> list = new ArrayList<>();

		list.add(new QueryExe(1, "London에 있는 프로젝트 이름을 찾아라"));
		list.add(new QueryExe(2, "프로젝트 j1에 참여하는 공급자의 이름을 찾아라"));
		list.add(new QueryExe(3, "공급 수량이 300 이상 750 이하인 모든 공급의 sno, pno, qty를 찾아라"));
		list.add(new QueryExe(4, "부품의 color와 city의 모든 쌍ㅇㄹ 찾아라. 같은 쌍은 한 번만 검색되어야 한다."));
		list.add(new QueryExe(5, "같은 도시에 있는 공급자, 부품, 프로젝트의 모든 sno, pnom jno쌍을 찾아라. 찾아진 sno, pno, jno의 city 들은 같은 것이 없어야 한다."));
		list.add(new QueryExe(6, "같은 도시에 있지 않는 공급자, 부품, 프로젝트의 sno, pno, jno을 찾아라. 찾아진 sno, pno, jno의 city 들은 같은 것이 없어야 한다."));
		list.add(new QueryExe(7, "London에 있는 공급자에 의해 공급된 부품의 번호, 이름을 찾아라."));
		list.add(new QueryExe(8, "London에 있는 공급자가 London의 프로젝트에 공급한 부품의 부품 번호와 이름을 찾아라."));
		list.add(new QueryExe(9, "한 도시에 있는 공급자가 다른 도시에 있는 프로젝트에 공급할 때 공급자 도시, 프로젝트 도시 쌍을 모두 구하라."));
		list.add(new QueryExe(10, " 한 도시에 있는 공급자가 같은 도시에 있는 프로젝트에 공급하는 부품의 부품 번호와 이름을 찾아라."));
		list.add(new QueryExe(11, "같은 도시에 있지 않은, 적어도 한 명 이상의 공급자들에 의해 공급되고 있는 프로젝트의 번호와 이름을 찾아라"));
		list.add(new QueryExe(13, "공급자 S1이 공급한 프로젝트 개수를 찾아라."));
		list.add(new QueryExe(14, "공급자 s1이 공급한 부품 DI의 전체 수량을 찾아라."));
		list.add(new QueryExe(15, "프로젝트에 공급된 각 부품에 대하여, 각 부족 번호, 공급된 프로젝트 번호, 자 프로젝트에 공급된 수량을 찾아라."));
		list.add(new QueryExe(16, "any 프로젝트에 공급된 부품의 수량 평균이 350 이상인 부품의 번호를 찾아라. 부품이 여러프로젝트에 공급 되는데 공급된 부품 수량의 평균(부품 수량을 프로젝트 개수로 나눈)이 350 이상 되는 것을 말한다."));


		Scanner sc = new Scanner(System.in);
		while (true) {

			for (QueryExe qe : list) {
				System.out.println(String.format("%d. %s", qe.getNum(), qe.getText()));
			}

			System.out.println("선택 <0 : EXIT> : ");
			int sel = sc.nextInt();
			if (sel == 0)
				break;

			switch (sel) {
			case 1:
				sql = "select distinct jno,jname from j where city = 'London';";
				exe01(con, sql);
				break;
			case 2:
				sql = "select distinct sname from s where sno in (select sno from spj where jno ='j1');";
				exe01(con,sql);
				break;
			case 3:
				sql = "select distinct sno, pno, qty from spj where 300<= qty<= 750 ;";
				exe01(con,sql);
				break;
			case 4:
				sql = "select distinct distinct color, city from p;";
				exe01(con,sql);
				break;
			case 5:
				sql = "select distinct sno, pno, jno from s,p,j where s.city = p.city and p.city = j.city;";
				exe01(con,sql);
				break;
			case 6:
				sql = "select distinct sno, pno, jno from s,p,j where s.city != p.city or p.city != j.city;";
				exe01(con,sql);
				break;
			case 7:
				sql = "select distinct pno, pname from p where pno in (select pno from spj, s where (spj.sno = s.sno and city = 'london')); ";
				exe01(con,sql);
				break;
			case 8:
				sql = "select distinct p.pno, p.pname from p,s,spj,j where(s.sno = spj.sno and j.jno = spj.jno and p.pno = spj.pno and s.city = 'london' and j.city = 'london');";
				exe01(con,sql);
				break;
			case 9:
				sql = "select distinct s.city, j.city from s,j,spj where s.city != j.city and spj.sno = s.sno and spj.jno = j.jno;";
				exe01(con,sql);
				break;
			case 10:
				sql = "select distinct s.city, j.city from s,j,spj where s.city = j.city and spj.sno = s.sno and spj.jno = j.jno;";
				exe01(con,sql);
				break;
			case 11:
				sql = "select distinct j.jno, j.jname from s, j, spj where s.city != j.city and spj.sno = s.sno and spj.jno = j.jno;";
				exe01(con,sql);
				break;
			case 13:
				sql = "select distinct count(spj.sno) from spj;";
				exe01(con,sql);
				break;
			case 14:
				sql = "select distinct count(*) from spj where spj.sno='p1';";
				exe01(con,sql);
				break;
			case 15:
				sql = "select distinct sum(qty) from spj where pno = 'P1' and sno = 'S1';";
				exe01(con,sql);
				break;
			case 16:
				sql = "select distinct pno, jno,sum(qty) from spj group by pno, jno;";
				exe01(con,sql);
				break;

			default:
				System.out.println("제대로 선택하시오.");
			}


		}
		sc.close();
		tt.closeDB(con);
	}

}

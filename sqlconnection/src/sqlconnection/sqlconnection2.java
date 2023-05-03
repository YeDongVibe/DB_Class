package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlconnection2 {

	public static void main(String[] args) {
		Connection con  = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/warehouse", "YeDongVibe", "1234");
			
			st = con.createStatement();//statement라는 클래스의 객체를 만들어서 st라는 참조변수에 할당함.
			rs = st.executeQuery("Select sno, pno, jno, qty from spj order by sno");//executeQuery라는 매서드러 데이터베이스에 select~~라는 query를 날림.
			
			while(rs.next()) {//rs라는 참조변수가 결과값을 저장함. //cursor processing하는 듯한 역할
				System.out.println(String.format("%s. %s, %s, %d", //String.format이라는 것을 통해 String형태의 값을 댕겨옴. //%s : 문자열, %c : 문자 1개, %d : int, %l : long, %f : float
						rs.getString("sno"), rs.getString("pno"), rs.getString("jno"), rs.getInt("qty")));
				//get~를 통해 각 필드명을 입력하여 데이터를 가져옴. 
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (rs != null) rs.close(); //null이 아니면 닫아라.
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}  

	}

}

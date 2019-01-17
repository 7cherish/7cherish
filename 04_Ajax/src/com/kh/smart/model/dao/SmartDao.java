package com.kh.smart.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.smart.model.vo.Smart;

public class SmartDao {
	private Connection conn;
	private ResultSet rset;
	private PreparedStatement pstmt;
	
	private String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	private String db_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userId = "web";
	private String password = "web";
	
	private String query = "";
	
	
	public int insertSmart(String pname, int amount) {
		conn = null;
		pstmt = null;
		int result = 0;
		query = "insert into smartphone values(?,?,default)";
		
		
		try {
			// 1. 클래스등록확인
			Class.forName(jdbc_driver);
			// System.out.println("드라이버 로딩 성공..");
			
			// 2. Connection 객체 생성
			conn = DriverManager.getConnection(db_url, userId, password);
			// System.out.println("DB연결 성공..");
			
			// 3. Statement 객체 생성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pname);
			pstmt.setInt(2, amount);

			
			// 4. 실행
			result = pstmt.executeUpdate(); // int값(result)를 리턴
			
			if(result > 0) {
				conn.commit();
				System.out.println("주문 완료!");
			}
			else {
				conn.rollback();
				System.out.println("주문 실패!");	
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<Smart> getTop5() {
		List<Smart> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from (select pname,sum(amount) as total from smartphone group by pname order by total desc) where rownum<6"; 
				
		
		try {
			//1.클래스 등록 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//여기서 에러나면 class가 없다는 거니까 ojdbc.jar파일 확인
			//2.Connection 객체 생성
			conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "web", "web");
			
			//3.statement객체 생성
			pstmt = conn.prepareStatement(query);
			
			//4.실행
			rset = pstmt.executeQuery();
			
			//5.list에 담기
			list = new ArrayList<>();
			while(rset.next()) {
				Smart smart = new Smart();  // String pname, int amount,  (date pdate)
											// String pname, int total ,  (int rank)
				smart.setPname(rset.getString("pname")); 
				smart.setAmount(rset.getInt("total"));
				//data 값을 따로 지정하지않는다 > default값이 들어가게됨
				
				list.add(smart);
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;

	}

	public List<com.kh.smart.model.vo.Smart> selectRecent5() {
		List<Smart> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null; // 
		// String query = "select * from( select rownum as rnum, v.* from( select * from smartphone order by pdate desc ) v) v where rnum between 1 and 5";
		String query = "select * from (select * from smartphone order by pdate desc) where ROWNUM < 6";
		
		try {
			//1.클래스 등록 확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//여기서 에러나면 class가 없다는 거니까 ojdbc.jar파일 확인
			//2.Connection 객체 생성
			conn  = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "web", "web");
			
			//3.statement객체 생성
			pstmt = conn.prepareStatement(query);
			
			//4.실행
			rset = pstmt.executeQuery();
			
			//5.list에 담기
			list = new ArrayList<>();
			
			while(rset.next()) {
				Smart smart = new Smart(); //smart 객체를 선언만 해둔 상태.
				smart.setPname(rset.getString("pname")); //smart 객체의 pname 변수에, rset의 pname 값을 담는다.
				smart.setAmount(rset.getInt("amount"));
				smart.setPdate(rset.getDate("pdate"));
								
				list.add(smart);
			}

			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
		

	}

}

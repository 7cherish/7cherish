package common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Singletone패턴으로 다음 업무를 수행함.
 * 0. 드라이버클래스 등록(jdbc 4.0이하일때만 반드시 작성) 
 * 1.DB연결 : Connection객체생성(url, user, password) DB로 가는 통행권을 만든것임 
 * 2. 트랜잭션처리 : 
 *					 EX) //트랜잭션처리 
 * 						if(result>0) { 
 * 						conn.commit(); 
 * 						} else { 
 * 								conn.rollback(); } 
 * 3. 연결해제 : 자원반납 (역순으로 할 것)
 * 
 * 싱글톤패턴은?
 * 클래스에 대한 객체가 프로그램 구동내내 단 하나만 생성되어 작동되게 한다.
 * static자원을 활용하는걸로 해보겠습니다.
 * 
 *
 */

public class JDBCTemplate {
	
	public static Connection getConnection() {
		
		Connection conn = null;

		try {
			Properties prop = new Properties();
//			prop.load(new FileReader("resources/driver.properties"));
			
			String fileName = JDBCTemplate.class.getResource("/driver.properties")
												.getPath();
			prop.load(new FileReader(fileName));
			
			String driver = prop.getProperty("driver");
			String url = prop.getProperty("url");
			String user = prop.getProperty("user");
			String password = prop.getProperty("password");
		
			
			//0. 드라이버 등록
			Class.forName(driver);
			
			//1. Connection객체생성
			conn = DriverManager.getConnection(url, user, password);
			
			//Java Application에서 트랜잭션을 제어하겠다는 뜻이다.
			conn.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}
	
	public static void close(Connection conn) {
		try {
			//conn이 null이 아니고 닫히지 않았을때만 커넥션객체를 실행하도록 하겠습니다.
			if(conn!=null && !conn.isClosed())
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt) {
		
		try {
			if(stmt!=null && !stmt.isClosed())
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void close(ResultSet rset) {
		try {
			if(rset!=null && !rset.isClosed())
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed())
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(Connection conn) {
		try {
			if(conn!=null && !conn.isClosed())
			conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

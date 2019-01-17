package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AjaxDao {

	public List<String> selectByName(String srchName) {
		List<String> list = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from classmate where name like ?";

		try {
			// 1. 클래스등록확인
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 여기서 에러나면 class드라이버가 없다는 뜻 : ojdbc 파일 확인할것

			// 2. Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "web", "web");
			
			// 3. Statement 객체 생성
			pstmt = conn.prepareStatement(query);
			// 미완성된 쿼리
			pstmt.setString(1, "%" + srchName + "%");
			
			// 4. 실행
			rset = pstmt.executeQuery();
			
			// 5. list에 담기
			list = new ArrayList<>();
			while(rset.next()) {
				// 다음 행이 있으면 실행
				list.add(rset.getString("name"));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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

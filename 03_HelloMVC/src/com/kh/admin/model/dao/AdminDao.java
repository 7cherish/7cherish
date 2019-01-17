package com.kh.admin.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.*;

import com.kh.member.model.vo.Member;

public class AdminDao {
	private Properties prop = new Properties();

	public AdminDao() {
		String fileName = AdminDao.class.getResource("/sql/admin/admin-query.properties").getPath(); // 이 경로에 있는 url객체를
																										// 가져온다.
																										// getPath() :
																										// 절대 경로를 문자열로
																										// 가져온다.
		try {
			prop.load(new FileReader(fileName)); // 인자로 파일 경로 전달
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Member> selectMemberList(Connection conn, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberListByPaging");

		try {
			// 1. 쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			// startRnum, endRnum
			// numPerPage = 5, cPage = 1인 경우, s=1, e=5
			// numPerPage = 5, cPage = 2인 경우, s=6, e=10
			// numPerPage = 5, cPage = 3인 경우, s=11, e=15
			int startRnum = (cPage - 1) * numPerPage + 1;
			int endRnum = cPage * numPerPage;

			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);

			// 2. 쿼리객체 실행
			rset = pstmt.executeQuery();

			// 3. 실행결과 list에 담기
			list = new ArrayList<>();
			// 여러 행의 결과인데 하나 행의 정보는 Member에 전달돼야함.
			// 다음 행이 있으면 true, 없으면 false
			// 행의 길이만큼 반복될 것임
			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));

				list.add(m);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 (생성 역순으로)
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<Member> selectMemberByMemberId(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberIdPaging");

		try {
			// 1. 쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			
			int startRnum = (cPage - 1) * numPerPage + 1;
			int endRnum = cPage * numPerPage;
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, startRnum);
			pstmt.setInt(3, endRnum);

			// 2. 쿼리객체 실행
			rset = pstmt.executeQuery();

			// 3. 실행결과 list에 담기
			list = new ArrayList<>();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 (생성 역순으로)
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<Member> selectMemberByMemberName(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByMemberNamePaging");

		try {
			// 1. 쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			
			int startRnum = (cPage - 1) * numPerPage + 1;
			int endRnum = cPage * numPerPage;
			
			pstmt.setString(1, "%"+searchKeyword+"%");
			pstmt.setInt(2, startRnum);
			pstmt.setInt(3, endRnum);

			// 2. 쿼리객체 실행
			rset = pstmt.executeQuery();

			// 3. 실행결과 list에 담기
			list = new ArrayList<>();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 (생성 역순으로)
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public List<Member> selectMemberByGender(Connection conn, String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberByGenderPaging");

		try {
			// 1. 쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			
			int startRnum = (cPage - 1) * numPerPage + 1;
			int endRnum = cPage * numPerPage;
			
			pstmt.setString(1, searchKeyword);
			pstmt.setInt(2, startRnum);
			pstmt.setInt(3, endRnum);

			// 2. 쿼리객체 실행
			rset = pstmt.executeQuery();

			// 3. 실행결과 list에 담기
			list = new ArrayList<>();
			while (rset.next()) {
				Member m = new Member();
				m.setMemberId(rset.getString("memberid"));
				m.setPassword(rset.getString("password"));
				m.setMemberName(rset.getString("membername"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));

				list.add(m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 (생성 역순으로)
			close(rset);
			close(pstmt);
		}

		return list;
	}

	public int selectMemberCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCount");

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int selectMemberCountByMemberId(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByMemberId");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int selectMemberCountByMemberName(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByMemberName");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+searchKeyword+"%");
			rset = pstmt.executeQuery();
			if (rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}

	public int selectMemberCountByGender(Connection conn, String searchKeyword) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectMemberCountByGender");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, searchKeyword);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				totalContent = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return totalContent;
	}
}

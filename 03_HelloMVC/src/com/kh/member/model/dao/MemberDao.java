package com.kh.member.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import static com.kh.common.JDBCTemplate.*;

import com.kh.member.model.exception.MemberException;
import com.kh.member.model.vo.Member;

public class MemberDao {
	Properties prop = new Properties();

	public MemberDao() {
		String fileName = MemberDao.class.getResource("/sql/member/member-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int loginCheck(Connection conn, Member m) {
		int result = -1;
		PreparedStatement pstmt = null;
		// select문에서만 사용
		ResultSet rset = null;
		String query = prop.getProperty("loginCheck");

		try {
			// 1. Statement객체 생성 및 미완성쿼리문 완성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMemberId());

			// 2. 쿼리 실행
			rset = pstmt.executeQuery();

			// 3. 결과 변수 result에 담기
			if (rset.next()) {
				result = rset.getInt("login_check");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 자원반납
			close(rset);
			close(pstmt);
		}

		return result;
	}

	public Member selectOne(Connection conn, String memberId) 
		throws MemberException {
		Member memberLoggedIn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");

		try {
			// 1. Statement객체 생성 및 미완성쿼리문 완성
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);

			// 2. 쿼리 실행
			rset = pstmt.executeQuery();

			// 3. 결과 변수 result에 담기
			while (rset.next()) {
				memberLoggedIn = new Member();
				memberLoggedIn.setMemberId(rset.getString("MEMBERID"));
				memberLoggedIn.setPassword(rset.getString("PASSWORD"));
				memberLoggedIn.setMemberName(rset.getString("MEMBERNAME"));
				memberLoggedIn.setGender(rset.getString("GENDER"));
				memberLoggedIn.setAge(rset.getInt("AGE"));
				memberLoggedIn.setEmail(rset.getString("EMAIL"));
				memberLoggedIn.setPhone(rset.getString("PHONE"));
				memberLoggedIn.setAddress(rset.getString("ADDRESS"));
				memberLoggedIn.setHobby(rset.getString("HOBBY"));
				memberLoggedIn.setEnrollDate(rset.getDate("ENROLLDATE"));
			}

		} catch (Exception e) {
			throw new MemberException("selectOne메소드 에러!", e);
		} finally {
			// 4. 자원반납
			close(rset);
			close(pstmt);
		}
		return memberLoggedIn;
	}

	public int insertMemberLogger(Connection conn, String memberId, String status, String ip) {
		// 직접적으로 DB에 넣기
		// insert, delete, update는 int형으로 리턴
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMemberLogger");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			pstmt.setString(2, status);
			pstmt.setString(3, ip);

			// DB에 가서 쿼리문 실행, 성공하면 1, 실패하면 0
			// 쿼리문 실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int insertMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getMemberId());
			pstmt.setString(2, m.getPassword());
			pstmt.setString(3, m.getMemberName());
			pstmt.setString(4, m.getGender());
			pstmt.setInt(5, m.getAge());
			pstmt.setString(6, m.getEmail());
			pstmt.setString(7, m.getPhone());
			pstmt.setString(8, m.getAddress());
			pstmt.setString(9, m.getHobby());

			// 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updateMember(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateMember");
		try {
			pstmt = conn.prepareStatement(query);
			// 쿼리문 물음표 순서에 맞게 작성
			pstmt.setString(1, m.getMemberName());
			/* pstmt.setString(2, m.getPassword()); */
			pstmt.setInt(2, m.getAge());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getAddress());
			pstmt.setString(6, m.getGender());
			pstmt.setString(7, m.getHobby());
			pstmt.setString(8, m.getMemberId());
			// 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int deleteMember(Connection conn, String memberId) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteMember");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int updatePassword(Connection conn, Member m) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updatePassword");
		try {
			// 1. 쿼리객체준비끝
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getPassword());
			pstmt.setString(2, m.getMemberId());

			// 2. 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

}

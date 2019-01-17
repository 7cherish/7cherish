package com.kh.member.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.exception.MemberException;
import com.kh.member.model.vo.Member;

public class MemberService {
	// 로그인 관련 상수
	public static final int LOGIN_OK = 1;
	public static final int WRONG_PASSWORD = 0;
	public static final int ID_NOT_EXIST = -1;
	
	public int loginCheck(Member m) {
		int result = ID_NOT_EXIST;
		Connection conn = getConnection();
		
		result = new MemberDao().loginCheck(conn, m);
		
		// 자원반납
		close(conn);
		
		return result;
	}

	public Member selectOne(String memberId) throws MemberException {
		Member memberLoggedIn = null;
		Connection conn = getConnection();
		
		memberLoggedIn = new MemberDao().selectOne(conn, memberId);
		
		// 자원반납
		close(conn);
		
		return memberLoggedIn;
	}

	public int insertMemberLogger(String memberId, String status, String ip) {
		int result = 0;
		Connection conn = getConnection();
		
		result = new MemberDao().insertMemberLogger(conn, memberId, status, ip);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		return result;
	
	}

	public int insertMember(Member m) {
		int result = 0;
		Connection conn = getConnection();
		
		result = new MemberDao().insertMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}

	public int updateMember(Member m) {
		int result = 0;
		Connection conn = getConnection();
		
		result = new MemberDao().updateMember(conn, m);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}

	public int deleteMember(String memberId) {
		int result = 0;
		Connection conn = getConnection();
		
		result = new MemberDao().deleteMember(conn, memberId);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}

	public int updatePassword(Member m) {
		int result = 0;
		Connection conn = getConnection();
		
		result = new MemberDao().updatePassword(conn, m);
		
		// DML문 실행시 반드시 트랜잭션 처리
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		// 자원반납
		close(conn);
		
		return result;
	}
}

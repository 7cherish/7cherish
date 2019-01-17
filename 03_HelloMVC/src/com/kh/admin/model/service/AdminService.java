package com.kh.admin.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.admin.model.dao.AdminDao;
import com.kh.member.model.vo.Member;

public class AdminService {
	
//	public List<Member> selectMemberList() {
//		List<Member> list = null;
//		Connection conn = getConnection();
////		list = new AdminDao().selectMemberList(conn);
//		// select는 DQL이기 때문에 트랜잭션처리할 필요가 없다 (commit, rollback)
//		close(conn);
//		return list;
//	}
//
//	public List<Member> selectMemberByMemberId(String searchKeyword) {
//		List<Member> list = null;
//		Connection conn = getConnection();
////		list = new AdminDao().selectMemberByMemberId(conn, searchKeyword);
//		close(conn);
//		return list;
//	}
//
//	public List<Member> selectMemberByMemberName(String searchKeyword) {
//		List<Member> list = null;
//		Connection conn = getConnection();
////		list = new AdminDao().selectMemberByMemberName(conn, searchKeyword);
//		close(conn);
//		return list;
//	}
//
//	public List<Member> selectMemberByGender(String searchKeyword) {
//		List<Member> list = null;
//		Connection conn = getConnection();
////		list = new AdminDao().selectMemberByGender(conn, searchKeyword);
//		close(conn);
//		return list;
//	}
	
	/**
	 * 페이징처리용 회원조회
	 * @param cPage
	 * @param numPerPage
	 * @return
	 */
	public List<Member> selectMemberList(int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectMemberList(conn, cPage, numPerPage);
		// select는 DQL이기 때문에 트랜잭션처리할 필요가 없다 (commit, rollback)
		close(conn);
		return list;
	}
	
	public List<Member> selectMemberByMemberId(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectMemberByMemberId(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByMemberName(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectMemberByMemberName(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}

	public List<Member> selectMemberByGender(String searchKeyword, int cPage, int numPerPage) {
		List<Member> list = null;
		Connection conn = getConnection();
		list = new AdminDao().selectMemberByGender(conn, searchKeyword, cPage, numPerPage);
		close(conn);
		return list;
	}
	
	public int selectMemberCount() {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCount(conn);
		close(conn);
		return totalContent;
	}

	public int selectMemberCountByMemberId(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByMemberId(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectMemberCountByMemberName(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByMemberName(conn, searchKeyword);
		close(conn);
		return totalContent;
	}

	public int selectMemberCountByGender(String searchKeyword) {
		Connection conn = getConnection();
		int totalContent = new AdminDao().selectMemberCountByGender(conn, searchKeyword);
		close(conn);
		return totalContent;
	}
	
}

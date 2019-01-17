package member.model.service;

import java.sql.Connection;

import common.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

public class MemberService {
	public int insertMember(Member m) {
		int result = 0;
		// 1. 커넥션 객체 생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2. dao에 요청 및 결과 받기
		result = new MemberDao().insertMember(conn, m);
		
		// 3. 결과에 따른 트랜잭션 처리
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// 4. 자원반납
		JDBCTemplate.close(conn);
		
		return result;
	}
}

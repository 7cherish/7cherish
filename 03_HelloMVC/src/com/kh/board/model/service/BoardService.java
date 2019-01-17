package com.kh.board.model.service;

import static com.kh.common.JDBCTemplate.close;
import static com.kh.common.JDBCTemplate.commit;
import static com.kh.common.JDBCTemplate.getConnection;
import static com.kh.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

public class BoardService {

	public List<Board> selectBoardList(int cPage, int numPerPage) {
		List<Board> bList = null;
		Connection conn = getConnection();
		bList = new BoardDao().selectBoardList(conn, cPage, numPerPage);
		close(conn);
		return bList;
	}

	public int selectBoardCount() {
		Connection conn = getConnection();
		int totalContent = new BoardDao().selectBoardCount(conn);
		close(conn);
		return totalContent;
	}

	public int insertBoard(Board b) {

		Connection conn = getConnection();

		int boardNo = new BoardDao().insertBoard(conn, b);
		// 하나의 커넥션안에서 트랜잭션처리가 된다.
		if (boardNo > 0) {
			// 최근 게시글 번호를 가져오기 요청
			boardNo = new BoardDao().getLastSeq(conn);
			// seq.nextval(다음값을 가져오는 속성), seq.currval(현재값을 가져오는 속성)
			commit(conn);
		} else {
			rollback(conn);
		}

		return boardNo;
	}

	public Board selectOne(int boardNo) {
		Board b = null;
		Connection conn = getConnection();

		b = new BoardDao().selectOne(conn, boardNo);

		// 자원반납
		close(conn);

		return b;
	}

	public int increaseReadCount(int boardNo) {
		Connection conn = getConnection();
		int result = new BoardDao().increaseReadCount(conn, boardNo);

		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		// 자원반납
		close(conn);

		return result;
	}

	public int deleteBoard(int boardNo) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().deleteBoard(conn, boardNo);

		// DML이므로 트랜잭션처리
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		// 자원반납
		close(conn);

		return result;
	}

	public int updateBoard(Board b) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().updateBoard(conn, b);

		if (result > 0) {
			commit(conn);
		}

		close(conn);
		return result;
	}

	public int boardCommentInsert(BoardComment boardComment) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().boardCommentInsert(conn, boardComment);
		
		// DML이므로 트랜잭션처리
		if (result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		// 자원반납
		close(conn);
		
		return result;
	}

	public List<BoardComment> selectCommentList(int boardNo) {
		List<BoardComment> commentList = null;
		Connection conn = getConnection();
		commentList = new BoardDao().selectCommentList(conn, boardNo);
		// 단순 조회이기 때문에 트랜잭션처리 불필요
		close(conn);
		
		return commentList;
	}

	public int boardCommentDelete(int boardCommentNo, int boardCommentLevel) {
		int result = 0;
		Connection conn = getConnection();
		result = new BoardDao().boardCommentDelete(conn, boardCommentNo, boardCommentLevel);
		
		// DML이므로 트랜잭션처리
		if(result > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		// 자원반납
		close(conn);
		
		return result;
	}

}

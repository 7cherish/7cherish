package com.kh.board.model.dao;

import static com.kh.common.JDBCTemplate.close;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.BoardComment;

public class BoardDao {
	private Properties prop = new Properties();

	public BoardDao() {
		String fileName = BoardDao.class.getResource("/sql/board/board-query.properties").getPath();

		try {
			prop.load(new FileReader(fileName)); // 인자로 파일 경로 전달
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Board> selectBoardList(Connection conn, int cPage, int numPerPage) {
		List<Board> bList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardListByPaging");

		try {
			// 1. 쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			int startRnum = (cPage - 1) * numPerPage + 1;
			int endRnum = cPage * numPerPage;

			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);

			// 2. 쿼리객체 실행
			rset = pstmt.executeQuery();

			// 3. 실행결과 bList에 담기
			bList = new ArrayList<>();

			while (rset.next()) {
				Board b = new Board();
				b.setBoardNo(rset.getInt("board_No"));
				b.setBoardTitle(rset.getString("board_Title"));
				b.setBoardWriter(rset.getString("board_Writer"));
				b.setBoardContent(rset.getString("board_content"));
				b.setOriginalFileName(rset.getString("board_original_FileName"));
				b.setOriginalFileName(rset.getString("board_renamed_FileName"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setReadCount(rset.getInt("board_readCount"));
				b.setBoardCommentCnt(rset.getInt("board_comment_cnt"));

				bList.add(b);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 (생성 역순으로)
			close(rset);
			close(pstmt);
		}

		return bList;
	}

	public int selectBoardCount(Connection conn) {
		PreparedStatement pstmt = null;
		int totalContent = 0;
		ResultSet rset = null;
		String query = prop.getProperty("selectBoardCount");

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

	public int insertBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoard");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getOriginalFileName());
			pstmt.setString(5, b.getRenamedFileName());

			// 실행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;

	}

	public Board selectOne(Connection conn, int boardNo) {
		Board b = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectOne");

		try {
			// 1. Statement객체 생성 및 미완성쿼리문 완성
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			// 2. 쿼리 실행
			rset = pstmt.executeQuery();

			// 3. 결과 변수 result에 담기
			while (rset.next()) {
				b = new Board();
				b.setBoardNo(rset.getInt("board_no"));
				b.setBoardTitle(rset.getString("board_title"));
				b.setBoardContent(rset.getString("board_content"));
				b.setBoardWriter(rset.getString("board_writer"));
				b.setOriginalFileName(rset.getString("board_original_filename"));
				b.setRenamedFileName(rset.getString("board_renamed_filename"));
				b.setBoardDate(rset.getDate("board_date"));
				b.setReadCount(rset.getInt("board_readcount"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4. 자원반납
			close(rset);
			close(pstmt);
		}
		return b;
	}

	public int increaseReadCount(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("increaseReadCount");
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int getLastSeq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int currVal = 0;
		String query = prop.getProperty("selectLastSeq");
		try {
			// 쿼리 연결
			pstmt = conn.prepareStatement(query);

			// 실행
			rset = pstmt.executeQuery();

			if (rset.next()) {
				currVal = rset.getInt("currval");
			}

			System.out.println("board_currVal@BoardDao=" + currVal);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return currVal;
	}

	public int deleteBoard(Connection conn, int boardNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("deleteBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납
			close(pstmt);
		}

		return result;
	}

	public int updateBoard(Connection conn, Board b) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("updateBoard");

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardWriter());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getOriginalFileName());
			pstmt.setString(5, b.getRenamedFileName());
			pstmt.setInt(6, b.getBoardNo());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}

		return result;
	}

	public int boardCommentInsert(Connection conn, BoardComment boardComment) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = prop.getProperty("insertBoardComment");
		try {
			// 미완성쿼리문을 가지고 객체생성.
			pstmt = conn.prepareStatement(query);
			// 쿼리문미완성

			pstmt.setInt(1, boardComment.getBoardCommentLevel());
	        pstmt.setString(2, boardComment.getBoardCommentWriter());
	        pstmt.setString(3, boardComment.getBoardCommentContent());
	        pstmt.setInt(4, boardComment.getBoardRef());
	        pstmt.setObject(5, boardComment.getBoardCommentRef()==0?null:boardComment.getBoardCommentRef());

			// (중요)
			// 댓글인 경우 boardCommentRef에는 null값이 들어가야 하는데
			// setInt는 null을 허용하지 않으므로, setInt 대신 setObject
			// 혹은 setNull메소드를 사용할 수 있다.
			// pstmt.setObject(5, boardComment.getBoardCommentRef()==0?:);
			// if(b.getBoardCommentRef() == 0) {
			// pstmt.setNull(5, 0);
			// }else {
			// pstmt.setInt(5, b.getBoardCommentRef());
			// }

			// 쿼리문실행 : 완성된 쿼리를 가지고 있는 pstmt실행(파라미터 없음)
			// DML은 executeUpdate()
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);

		}

		return result;
	}

	public List<BoardComment> selectCommentList(Connection conn, int boardNo) {
		List<BoardComment> commentList = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectCommentList");

		try {
			// 1. Statement객체
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);

			// 2. 실행
			rset = pstmt.executeQuery();

			// 3. list객체 담기
			commentList = new ArrayList<>();
			while (rset.next()) {
				BoardComment bc = new BoardComment();
				bc.setBoardCommentNo(rset.getInt("board_comment_no"));
				bc.setBoardCommentLevel(rset.getInt("board_comment_level"));
				bc.setBoardCommentWriter(rset.getString("board_comment_writer"));
				bc.setBoardCommentContent(rset.getString("board_comment_content"));
				bc.setBoardRef(rset.getInt("board_ref"));
				bc.setBoardCommentRef(rset.getInt("board_comment_ref"));
				bc.setBoardCommentDate(rset.getDate("board_comment_date"));

				// list에 추가
				commentList.add(bc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return commentList;
	}

	public int boardCommentDelete(Connection conn, int boardCommentNo, int boardCommentLevel) {
		int result = 0;
		PreparedStatement pstmt = null;

		try {
//			// 레벨1 댓글 삭제시 board_comment_content를 "삭제된 댓글입니다." 업데이트
//			if (boardCommentLevel == 1) {
//				pstmt = conn.prepareStatement(prop.getProperty("boardCommentDelete1"));
//				pstmt.setString(1, "-------------------- 삭제된 댓글입니다. --------------------");
//				pstmt.setInt(2, boardCommentNo);
//				result = pstmt.executeUpdate();
//			} else {
				// 레벨2 댓글 삭제시 
				pstmt = conn.prepareStatement(prop.getProperty("boardCommentDelete2"));
				pstmt.setInt(1, boardCommentNo);
				result = pstmt.executeUpdate();
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}
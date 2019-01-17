package com.kh.board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int boardNo; // 글번호
	private String boardTitle; // 글제목
	private String boardWriter; // 글쓴이
	private String boardContent; // 내용
	private String originalFileName; // 원첨부파일
	private String renamedFileName; // 이름중복되지않게 바꿔준 첨부파일
	private Date boardDate; // 작성일
	private int readCount; // 조회수
	
	private int boardCommentCnt; // 댓글수 필드 추가

	public Board() {
	}

	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, String originalFileName,
			String renamedFileName, Date boardDate, int readCount, int boardCommentCnt) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.originalFileName = originalFileName;
		this.renamedFileName = renamedFileName;
		this.boardDate = boardDate;
		this.readCount = readCount;
		this.boardCommentCnt = boardCommentCnt;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardWriter() {
		return boardWriter;
	}

	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getRenamedFileName() {
		return renamedFileName;
	}

	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}

	public Date getBoardDate() {
		return boardDate;
	}

	public void setBoardDate(Date boardDate) {
		this.boardDate = boardDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public int getBoardCommentCnt() {
		return boardCommentCnt;
	}
	
	public void setBoardCommentCnt(int boardCommentCnt) {
		this.boardCommentCnt = boardCommentCnt;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardContent + "\t" + originalFileName + "\t"
				+ renamedFileName + "\t" + boardDate + "\t" + readCount;
	}

}

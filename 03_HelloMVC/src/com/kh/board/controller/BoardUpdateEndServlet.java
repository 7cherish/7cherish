package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.josephoconnell.html.HTMLInputFilter;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class BoardUpdateEndServlet
 */
@WebServlet("/board/boardUpdateEnd")
public class BoardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. enctype = multipart/form=data 체크
		// 파일업로드할때 계속 쓰기 때문에 filter 만들어주면 용이하다
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판 수정오류");
			request.setAttribute("loc", "/");
			String view = "/WEB-INF/views/common/msg.jsp";
			request.getRequestDispatcher(view)
				   .forward(request, response);
			return;
		}
		
		// 1. MultipartRequest객체생성
		// a. saveDirectory
		String saveDirectory = getServletContext().getRealPath("/upload/board/");
		// b. maxPostSize
		int maxPostSize = 1024 * 1024 * 10; // 10MB
		// c. 인코딩 : UTF-8
		String enc = "UTF-8";
		// d. 파일Rename정책
		FileRenamePolicy frp = new MyFileRenamePolicy();		
		
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, enc, frp);
		
		// 2. MultipartRequest객체로부터 파라미터핸들링
		int boardNo = Integer.parseInt(multiReq.getParameter("boardNo"));
		String boardTitle = multiReq.getParameter("title");
		String boardWriter = multiReq.getParameter("writer");
		String boardContent = multiReq.getParameter("content");
		
		// 새로 첨부한 파일이 있다고 가정
		String renamedFileName = multiReq.getFilesystemName("up_file");
		String originalFileName = multiReq.getOriginalFileName("up_file");
		
		// 이전 파일 삭제용
		String oldFile = multiReq.getParameter("old_renamed_file");
		
		// 테스트용 파일객체
		// 파일이 있나 없나?
		// 업로드한 파일이 없다면 f = null
		File f = multiReq.getFile("up_file");
		// 1. 업로드한 파일이 있는 경우
		if(f!=null && f.length() > 0) {
			// 기존파일 삭제처리
			File delFile = new File(saveDirectory + oldFile);
			// delFile삭제
			delFile.delete();
		}
		// 2. 첨부파일 삭제가 체크된 경우 (체크했다면 on, 언체크드 null)
		else if(multiReq.getParameter("del_file") != null) {
			// 기존파일 삭제처리
			File delFile = new File(saveDirectory + oldFile);
			// delFile삭제
			delFile.delete();
		}
		// 3. 첨부파일 없는 경우
		else {
			// 기존값 유지
			originalFileName = multiReq.getParameter("old_original_file");
			renamedFileName = multiReq.getParameter("old_renamed_file");
		}
		
		// 실제 업무로직 요청하기 위한 보드객체 생성
		Board b = new Board();
		b.setBoardNo(boardNo);
		b.setBoardTitle(boardTitle);
		b.setBoardWriter(boardWriter);
//		b.setBoardContent(boardContent);
		// xss 방어코드
		b.setBoardContent(new HTMLInputFilter().filter(boardContent));
		b.setOriginalFileName(originalFileName);
		b.setRenamedFileName(renamedFileName);
		
		// 3. 업무로직 요청
		int result = new BoardService().updateBoard(b);
		
		// 4. view단 처리
		String view = "/WEB-INF/views/common/msg.jsp";
		String msg = "";
		String loc = "/board/boardView?boardNo=" + boardNo;
		
		if(result > 0) {
			msg = "게시물 수정 성공!";
		}
		else {
			msg = "게시물 수정 실패!";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view)
			   .forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

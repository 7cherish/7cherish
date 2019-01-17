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

/**
 * Servlet implementation class BoardFormEndServlet
 */
@WebServlet("/board/boardFormEnd")
public class BoardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 0. 유효성 타입 enctype으로 보냈는지 안 보냈는지 확인
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "게시판작성오류![form:enctype]");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
				   .forward(request, response);
			return; // 더 실행되지 않도록 return처리
		}
		
		// 1. FileUpload처리 request => MultipartRequest객체 사용(cos.jar가 제공하는것)
		// a. saveDirectory (실제 파일이 저장될 디렉토리) : /upload/board/ 여기에 파일 저장할 것임
		// 파일업로드 디렉토리의 절대경로 가져오기
		// getServletContext()는 세션보다 생명줄이 길다.
		// os별로 파일경로에 대한 구분자가 다르다. / 는 리눅스 맥에서 사용하는 구분자이고,
		// 윈도우에서는 \ 역슬래시를 구분자로 사용한다.
		// File.separator는 os에 맞게 사용해준다.
		String root = getServletContext().getRealPath("/");
		String saveDirectory = root + "upload" + File.separator + "board";
		System.out.printf("[saveDirectory = %s]\n", saveDirectory);
		
		// b. maxPostSize : 파일최대크기 
		// 크기를 제한하지 않으면 사용자가 큰 파일을 올려서 서버용량을 다 차지할 수 있기 때문에
		// 보통 10MB로 지정한다.
		// 1kb * 1kb * 10
		int maxPostSize = 1024 * 1024 * 10;
		
		// c. 파일Rename정책객체 : DefaultRenamePolicy(클래스이름)를 상속한 사용자 정의객체
		
		
		// MultipartRequest객체 생성
		MultipartRequest multiReq = new MultipartRequest(request, saveDirectory, maxPostSize, "UTF-8", new MyFileRenamePolicy());
		
		
		// 2. parameterHandling
		// (중요) MultipartRequest객체를 생성하면, 
		// 기존 request객체로부터 파라미터값을 가져올 수 없다.
		// DB컬럼, VO컬럼, FORM데이터 NAME을 맞춰서 작성한다.
		// 여기서는 강사님이 달리 만들어서 다르다.
		String boardTitle = multiReq.getParameter("title");        
		String boardWriter = multiReq.getParameter("writer");       
		String boardContent = multiReq.getParameter("content");  
		
		// xss(Cross-Site Scripting) 공격 대비코드
//		boardContent = boardContent.replaceAll("<", "&lt;")
//								   .replaceAll(">", "&gt;");
		
		// html_filter라이브러리 사용 (공격 대비)
		boardContent = new HTMLInputFilter().filter(boardContent);
		
		String originalFileName = multiReq.getOriginalFileName("up_file"); // 곽경국.jpg  
		String renamedFileName = multiReq.getFilesystemName("up_file"); // 20181220_163230567_34436.jpg
		
		Board b = new Board();
		b.setBoardTitle(boardTitle);
		b.setBoardWriter(boardWriter);
		b.setBoardContent(boardContent);
		b.setOriginalFileName(originalFileName);
		b.setRenamedFileName(renamedFileName);
		
		System.out.printf("[%s]\n", b);
		
		// 3. businessLogic
		// 처리결과를 리턴 => 직전 입력된 게시글 번호를 리턴
		int result = new BoardService().insertBoard(b);
		
		// 4. view단 처리
		String msg = "";
		String loc = "/board/boardView?boardNo=" + result;
		String view = "/WEB-INF/views/common/msg.jsp";
		
		
		if(result > 0) {
			msg = "게시글 등록 완료!";
		}
		else {
			msg = "게시글 등록 실패";
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

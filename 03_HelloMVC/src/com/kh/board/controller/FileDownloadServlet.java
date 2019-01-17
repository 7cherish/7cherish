package com.kh.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/board/fileDownload")
public class FileDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 파라미터 핸들링
		String rName = request.getParameter("rName");
		String oName = request.getParameter("oName");
		System.out.printf("[rName=%s, oName=%s]\n", rName, oName);
		
		// 2. 파일입출력준비
		String saveDirectory = getServletContext().getRealPath("/");
		System.out.println("saveDirectory=" + saveDirectory);
		
		// 입력스트림
		File f = new File(saveDirectory + "upload/board/" + rName);
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		// 출력스트림
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos); // 성능향상을 위함
		
		// 전송 전에 전송할 파일명 작성
		String resFileName = "";
		
		// 요청 브라우저에 따른 분기 : IE
		// IE10 이전 버전, 중간에 정책을 바꿔서 IE10 이후 버전은 MSIE 키워드를 빼버렸다.
		// IE10 이후 버전은 Trident
		boolean isMSIE = request.getHeader("user-agent").indexOf("MSIE") != -1
				   || request.getHeader("user-agent").indexOf("Trident") != -1;
		
		if(isMSIE) {
			// UTF-8 인코딩 처리를 명시적으로 해준다.
			resFileName = URLEncoder.encode(oName, "UTF-8");
			// +로 처리된 공백을 다시 한번 %20로 치환해준다.
			resFileName = resFileName.replaceAll("\\+", "%20");	
		}
		else {
			// oName을 바이트배열로 받아서 UTF-8로 바꿔주고,
			// 톰캣 기본 인코딩 사양인 8859-1로 스트림을 만들게요.
			resFileName = new String(oName.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		
		
		// 여기서는 잘 안 나와도 클라이언트한테만 잘 나오면 된다.
		System.out.println("resFileName="+resFileName); 
		
		// 3. 파일전송
		// 이진데이터 전송을 위한 MIME타입설정
		response.setContentType("application/octet-stream");
		// 내가 파일 보낼테니까 지정된 파일명으로 저장하라는 키워드 (saveAs같은것)
		response.setHeader("Content-Disposition", "attachment;filename=" + resFileName);
		
		// 파일쓰기 작업
		// 실제 파일에서 읽어서 출력스트림에 계속 2바이트 단위로 쓴다.
		int read = -1;
		while((read=bis.read()) != -1) {
			bos.write(read);
		}
		bos.close();
		bis.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

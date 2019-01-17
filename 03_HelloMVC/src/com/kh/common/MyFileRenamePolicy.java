package com.kh.common;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * FileRename처리해주는 이유
 * 1. 중복된 파일 덮어쓰기 방지
 * 2. 한글/특수문자가 섞인 파일명의 에러방지
 *  -> 사용자가 업로드한 원래 파일명은 DB에 보관한다.
 * 
 * 곽경국.jpg -> 20181220_163230567_34436.jpg
 * 
 * originalFileName : 곽경국.jpg
 * renameFileName : 20181220_163230567_34436.jpg
 *
 */
public class MyFileRenamePolicy implements FileRenamePolicy {

	@Override
	// 오리지널 파일을 받아서 새로운 파일로 바꿔주는 것
	public File rename(File oFile) {
		File rFile = null;
		// 현재 시각 가져오기 yyyyMMdd_HHmmssSSS
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
		int rndNum = (int) (Math.random() * 1000); // 0~999 뒤에 붙일 난수

		// 확장자. 파일 확장자가 그대로 유지되게 하기 위해서
		String oFileName = oFile.getName(); // 파일명 가져오기
		String ext = ""; // 확장자 담을 변수
		int dot = oFileName.lastIndexOf('.');
		if (dot > -1) {
			ext = oFileName.substring(dot);
		}
		
		// 새 파일명 생성
		String rFileName = sdf.format(new Date()) + "_" + rndNum + ext; // 현재 날짜를 만들어서 전달
		
		// renamed파일객체생성
		// 부모디렉토리, renamedFileName
		// getParent() : 원래 파일 있던 곳의 부모 디렉토리를 알 수 있다.
		rFile = new File(oFile.getParent(), rFileName);
		
		System.out.printf("[rFileName=%s]\n", rFile.getName());
		
		try {
			// 실제파일생성
			rFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return rFile;
	}

}

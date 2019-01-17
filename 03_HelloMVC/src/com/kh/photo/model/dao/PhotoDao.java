package com.kh.photo.model.dao;

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

import com.kh.photo.model.vo.Photo;

import static com.kh.common.JDBCTemplate.*;

public class PhotoDao {
	
	private Properties prop = new Properties();
	
	public PhotoDao() {
		String path = "/sql/photo/photo-query.properties";
		String fileName = PhotoDao.class.getResource(path).getPath(); // 프로퍼티즈 파일 읽어오기
		
		try {
			prop.load(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectPhotoCount(Connection conn) {
		int cnt = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPhotoCount");
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			// 하나의 행이므로 while문 대신 if문 사용
			if(rset.next()) {
				cnt = rset.getInt("cnt");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return cnt;
	}

	/**
	 * 1page : 1 ~ 5
	 * 2page : 6 ~ 10
	 * 3page : 11 ~ 15
	 * ...
	 * 
	 * startrownum : 
	 * endrownum : 
	 * @param conn
	 * @param cPage
	 * @param numPerPage
	 * @return
	 */
	public List<Photo> selectPhotoMore(Connection conn, int cPage, int numPerPage) {
		List<Photo> list = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = prop.getProperty("selectPhotoMore");
		
		try {
			// 1. 쿼리객체 준비
			pstmt = conn.prepareStatement(query);
			int startRnum = (cPage - 1) * numPerPage + 1;
			int endRnum = cPage * numPerPage;
			
			pstmt.setInt(1, startRnum);
			pstmt.setInt(2, endRnum);
			
			// 2. 쿼리객체 실행
			rset = pstmt.executeQuery();
			// 3. 실행결과 list에 담기
			list = new ArrayList<>();
			
			while(rset.next()) {
				Photo p = new Photo();
				p.setPhotoNo(rset.getInt("photo_no"));
				p.setPhotoWriter(rset.getString("photo_writer"));
				p.setPhotoContent(rset.getString("photo_content"));
				p.setOriginaFileName(rset.getString("photo_original_filename"));
				p.setRenamedFileName(rset.getString("photo_renamed_filename"));
				p.setPhotoDate(rset.getDate("photo_date"));
				p.setReadCount(rset.getInt("photo_readcount"));
				
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}

}

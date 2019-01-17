package com.kh.photo.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.photo.model.dao.PhotoDao;
import com.kh.photo.model.vo.Photo;

public class PhotoService {
	public int selectPhotoCount() {
		int cnt = 0;
		Connection conn = getConnection();
		cnt = new PhotoDao().selectPhotoCount(conn);
		close(conn);
		
		return cnt;
	}

	public List<Photo> selectPhotoMore(int cPage, int numPerPage) {
		List<Photo> list = null;
		Connection conn = getConnection();
		list = new PhotoDao().selectPhotoMore(conn, cPage, numPerPage);
		close(conn);
		return list;
	}
}

package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Map<String, String>> search0(SqlSession session) {
		List<Map<String, String>> list = session.selectList("emp.search0");
		System.out.println("list@List<Map<String, String>> search0@EmpDaoImpl = " + list);
		
		return list;
	}

	@Override
	public List<Map<String, String>> search1(SqlSession session, Map<String, String> map) {
		List<Map<String, String>> list = session.selectList("emp.search1", map);
		System.out.println("list@List<Map<String, String>> search1@EmpDaoImpl = " + list);
		
		return list;
	}

	@Override
	public List<Map<String, String>> search2(SqlSession session, Map<String, String> map) {
		List<Map<String, String>> list = session.selectList("emp.search2", map);
		System.out.println("list@List<Map<String, String>> search2@EmpDaoImpl = " + list);
		
		return list;
	}

	@Override
	public List<Map<String, String>> search3(SqlSession session, Map<String, String[]> map) {
		List<Map<String, String>> list = session.selectList("emp.search3", map);
		System.out.println("list@List<Map<String, String>> search3@EmpDaoImpl = " + list);
		
		return list;
	}

	@Override
	public List<Map<String, String>> selectEmpPagedList(SqlSession session, int cPage, int numPerPage) {
		// new RowBounds(int offset, int limit)
		// offset : 건너 뛸 게시물 수
		// cPage(현재 페이지)가 1이라면 -> offset : 0
		// cPage(현재 페이지)가 2이라면 -> offset : numPerPage * 1
		// cPage(현재 페이지)가 3이라면 -> offset : numPerPage * 2
		// limit : numPerPage(보여줄 컨텐츠 수)
		RowBounds rowBounds = new RowBounds((cPage - 1) * numPerPage, numPerPage);
		
		List<Map<String, String>> list = session.selectList("emp.empPagedList", null, rowBounds);
		
		System.out.println("list@List<Map<String, String>> selectEmpPagedList@EmpDaoImpl = " + list);
		
		return list;
	}

}

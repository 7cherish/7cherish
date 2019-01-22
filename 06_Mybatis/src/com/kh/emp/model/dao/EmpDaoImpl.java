package com.kh.emp.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public class EmpDaoImpl implements EmpDao {

	@Override
	public List<Map<String, String>> search0(SqlSession session) {
		List<Map<String, String>> list = session.selectList("emp.empList");
		System.out.println("mapList@selectList@StudentDao = " + list);
		
		return list;
	}

}

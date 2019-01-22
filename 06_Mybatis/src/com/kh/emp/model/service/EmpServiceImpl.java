package com.kh.emp.model.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.kh.common.SqlSessionTemplate;
import com.kh.emp.model.dao.EmpDao;
import com.kh.emp.model.dao.EmpDaoImpl;

public class EmpServiceImpl implements EmpService {
	EmpDao empDao = new EmpDaoImpl();

	@Override
	public List<Map<String, String>> search0() {
		SqlSession session = SqlSessionTemplate.getSqlSession();
		
		List<Map<String, String>> list = empDao.search0(session);
		
		// 사용한 세션 반납
		session.close();
		
		return list;
	}

}

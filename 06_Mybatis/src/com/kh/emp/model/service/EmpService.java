package com.kh.emp.model.service;

import java.util.List;
import java.util.Map;

public interface EmpService {

	List<Map<String, String>> search0();

	List<Map<String, String>> search1(Map<String, String> map);

	List<Map<String, String>> search2(Map<String, String> map);

	List<Map<String, String>> search3(Map<String, String[]> map);

	List<Map<String, String>> selectEmpPagedList(int cPage, int numPerPage);

}

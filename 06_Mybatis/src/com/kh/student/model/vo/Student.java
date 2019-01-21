package com.kh.student.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Student implements Serializable {
	
	
	/**
	 * id값 지정
	 */
	private static final long serialVersionUID = 1L;

	private int studentNo;
	private String studentName;
	private String studentTel;
	private String studentEmail;
	private String studentAddr;
	private Date regDate;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int studentNo, String studentName, String studentTel, String studentEmail, String studentAddr, Date regDate) {
		super();
		this.studentNo = studentNo;
		this.studentName = studentName;
		this.studentTel = studentTel;
		this.studentEmail = studentEmail;
		this.studentAddr = studentAddr;
		this.regDate = regDate;
	}

	public int getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(int studentNo) {
		this.studentNo = studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName2) {
		this.studentName = studentName2;
	}

	public String getStudentTel() {
		return studentTel;
	}

	public void setStudentTel(String studentTel) {
		this.studentTel = studentTel;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}

	public String getStudentAddr() {
		return studentAddr;
	}

	public void setStudentAddr(String studentAddr) {
		this.studentAddr = studentAddr;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "[studentNo=" + studentNo + ", studentName=" + studentName + ", studentTel=" + studentTel
				+ ", studentEmail=" + studentEmail + ", studentAddr=" + studentAddr + ", regDate=" + regDate + "]";
	}
	
	
	

}

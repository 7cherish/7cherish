package com.kh.smart.model.vo;

import java.sql.Date;

public class Smart {
	private String pname;
	private int amount;
	private Date pdate; // SimpleformatDate : date 값을 사용자가 지정한 형태로 리턴해줌(데이터타입은 String)
	
	public Smart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Smart(String pname, int amount, Date pdate) {
		super();
		this.pname = pname;
		this.amount = amount;
		this.pdate = pdate;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	@Override
	public String toString() {
		return "Smart [pname=" + pname + ", amount=" + amount + ", pdate=" + pdate + "]";
	}
	
}

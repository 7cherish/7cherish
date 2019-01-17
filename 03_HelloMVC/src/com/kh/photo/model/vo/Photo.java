package com.kh.photo.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Photo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int photoNo;
	private String photoWriter;
	private String photoContent;
	private String originaFileName;
	private String renamedFileName;
	private Date photoDate;
	private int readCount;
	
	
	
	public Photo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Photo(int photoNo, String photoWriter, String photoContent, String originaFileName, String renamedFileName,
			Date photoDate, int readCount) {
		super();
		this.photoNo = photoNo;
		this.photoWriter = photoWriter;
		this.photoContent = photoContent;
		this.originaFileName = originaFileName;
		this.renamedFileName = renamedFileName;
		this.photoDate = photoDate;
		this.readCount = readCount;
	}
	
	

	public int getPhotoNo() {
		return photoNo;
	}


	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}


	public String getPhotoWriter() {
		return photoWriter;
	}


	public void setPhotoWriter(String photoWriter) {
		this.photoWriter = photoWriter;
	}


	public String getPhotoContent() {
		return photoContent;
	}


	public void setPhotoContent(String photoContent) {
		this.photoContent = photoContent;
	}


	public String getOriginaFileName() {
		return originaFileName;
	}


	public void setOriginaFileName(String originaFileName) {
		this.originaFileName = originaFileName;
	}


	public String getRenamedFileName() {
		return renamedFileName;
	}


	public void setRenamedFileName(String renamedFileName) {
		this.renamedFileName = renamedFileName;
	}


	public Date getPhotoDate() {
		return photoDate;
	}


	public void setPhotoDate(Date photoDate) {
		this.photoDate = photoDate;
	}


	public int getReadCount() {
		return readCount;
	}


	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Photo [photoNo=" + photoNo + ", photoWriter=" + photoWriter + ", photoContent=" + photoContent
				+ ", originaFileName=" + originaFileName + ", renamedFileName=" + renamedFileName + ", photoDate="
				+ photoDate + ", readCount=" + readCount + "]";
	}
	
	
	
	
	
}

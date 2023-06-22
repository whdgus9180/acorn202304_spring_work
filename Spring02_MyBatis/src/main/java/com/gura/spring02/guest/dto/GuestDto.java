package com.gura.spring02.guest.dto;

import java.util.List;

public class GuestDto {
	private int Num;
	private String Writer;
	private String Content;
	private String pwd;
	private String regdate;
	
	
	public GuestDto() {
		
	}


	public GuestDto(int num, String writer, String content, String pwd, String regdate) {
		super();
		Num = num;
		Writer = writer;
		Content = content;
		this.pwd = pwd;
		this.regdate = regdate;
	}


	public int getNum() {
		return Num;
	}


	public void setNum(int num) {
		Num = num;
	}


	public String getWriter() {
		return Writer;
	}


	public void setWriter(String writer) {
		Writer = writer;
	}


	public String getContent() {
		return Content;
	}


	public void setContent(String content) {
		Content = content;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getRegdate() {
		return regdate;
	}


	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}


	
}

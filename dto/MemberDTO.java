package dto;

import java.sql.Date;

public class MemberDTO {
	
	private int mno ;
	private String writer ;
	private String id ;
	private String pw ;
	private Date mdate ;
	
	public int getMno() {
		return mno;
	}
	public String getWriter() {
		return writer;
	}
	public String getId() {
		return id;
	}
	public String getPw() {
		return pw;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}


	
	
	

	
	
}

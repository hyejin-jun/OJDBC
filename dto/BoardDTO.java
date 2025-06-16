package dto;


import java.sql.Date;

public class BoardDTO {
	
	private int bno ;
	private String writer;
	private String title ;
	private String detail ;
	private Date bdate;
	
	
	public int getBno() {
		return bno;
	}
	public String getWriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public String getDetail() {
		return detail;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	
	
	

}

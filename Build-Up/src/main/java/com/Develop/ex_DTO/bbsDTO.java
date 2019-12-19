package com.Develop.ex_DTO;

import java.sql.Date;

public class bbsDTO {
	
	private String username; // 유저이름
	private String nickname; // 표시될 닉네임
	private String btitle; //글 제목
	private String bcontent; // 글 내용
	
	private String bnum; //게시물 번호
	private String bhit; //조회수
	private String breply; // 게시물에 쓴 댓글
	
	private Date bdate; // 글 작성 시간
	
	private String postimage;// 게시물 배경이미지
	private String bimage; // 게시물 내 이미지
	
	private String keyword;
	private String titleVal;
	private String contentVal;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBcontent() {
		return bcontent;
	}

	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}

	public String getBnum() {
		return bnum;
	}

	public void setBnum(String bnum) {
		this.bnum = bnum;
	}

	public String getBhit() {
		return bhit;
	}

	public void setBhit(String bhit) {
		this.bhit = bhit;
	}

	public String getBreply() {
		return breply;
	}

	public void setBreply(String breply) {
		this.breply = breply;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	
	
	
	
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitleVal() {
		return titleVal;
	}

	public void setTitleVal(String titleVal) {
		this.titleVal = titleVal;
	}

	public String getContentVal() {
		return contentVal;
	}

	public void setContentVal(String contentVal) {
		this.contentVal = contentVal;
	}

	public String getPostimage() {
		return postimage;
	}

	public void setPostimage(String postimage) {
		this.postimage = postimage;
	}

	public String getBimage() {
		return bimage;
	}

	public void setBimage(String bimage) {
		this.bimage = bimage;
	}


	
	
	

	
	
	
}

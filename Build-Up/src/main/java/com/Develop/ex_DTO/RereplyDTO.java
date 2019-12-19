package com.Develop.ex_DTO;

public class RereplyDTO {
	
	private String username;
	private String nickname;
	private String cnum; //댓글 번호 <밑에 대댓글이 달려야 하기 때문>
	private String recomment;
	private String groupnum;
	
	
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
	public String getCnum() {
		return cnum;
	}
	public void setCnum(String cnum) {
		this.cnum = cnum;
	}
	public String getRecomment() {
		return recomment;
	}
	public void setRecomment(String recomment) {
		this.recomment = recomment;
	}
	public String getGroupnum() {
		return groupnum;
	}
	public void setGroupnum(String groupnum) {
		this.groupnum = groupnum;
	}
	
	

}

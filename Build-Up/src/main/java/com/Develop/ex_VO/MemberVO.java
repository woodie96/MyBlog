package com.Develop.ex_VO;

import java.util.Date;
import java.util.List;

public class MemberVO {
	
	private String userid;
	private String userpw;
	private String userName;
	private String nickname;
	private String email;
	private String enabled;

	private Date regdate;
	private List<AuthVO> authList; // 리스트 형식이라 여러가지 권한을 가질 수 있게 만들었음!
	
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public List<AuthVO> getAuthList() {
		return authList;
	}
	public void setAuthList(List<AuthVO> authList) {
		this.authList = authList;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	


	
	
}

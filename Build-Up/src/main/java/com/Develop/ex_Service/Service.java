package com.Develop.ex_Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Develop.ex_DAO.DAO;
import com.Develop.ex_DTO.DTO;
import com.Develop.ex_DTO.ReplyDTO;
import com.Develop.ex_DTO.RereplyDTO;
import com.Develop.ex_DTO.bbsDTO;
import com.Develop.ex_Paging.Paging;

public class Service {
	
	
	@Autowired
	private DAO dao;
	
	@Autowired
	private BCryptPasswordEncoder pwen;
	
	public void Confirm(DTO dto) {
		System.out.println("####### 입력 값 확인 #######");
		String userid = dto.getUserid();
		System.out.println("사용자 아이디: "+userid);
		
		String username = dto.getUsername();
		System.out.println("사용자 이름: "+username);
		
		String nickname = dto.getNickname();
		System.out.println("사용자 닉네임: "+nickname);
		
		dto.setUserpw(pwen.encode(dto.getUserpw()));
		String userpw = dto.getUserpw();
		System.out.println("사용자 패스워드: "+userpw);
		
		String authority = dto.getAuthority();
		System.out.println("사용자 권한: "+authority);
		
		String email = dto.getEmail();
		System.out.println("사용자  이메일: "+email);
		System.out.println("##### 입력 값 확인 마무리 #####");
		
		dao.joinConfirm(userid, username, nickname, userpw, email);
		dao.joinAuth(userid, authority);
		
	}
	
	public void writeBoard(String username,String nickname,String btitle,String bcontent,String postimage,String bimage) {
		
		System.out.println("===== service date check =====");
		System.out.println("이름: "+username);
		System.out.println("닉네임: "+nickname);
		System.out.println("제목: "+btitle);
		System.out.println("내용: "+bcontent);
		System.out.println("배경이미지: "+postimage);
		System.out.println("일반이미지: "+bimage);
		
		dao.writeBoard(username, nickname, btitle, bcontent, postimage, bimage);
		
	}
	
	public ArrayList<bbsDTO> selectList() {
		
		ArrayList<bbsDTO> dtos =  dao.selectList();
		
		return dtos;
	}
	
	//페이징
	public ArrayList<bbsDTO> PageList(int pNum, int at){
		
		Paging p = new Paging();
		
		int pageNum = p.PageNumer(pNum, at); // 현재 페이지를 나타냄
		int amount = p.PageAmount(pNum, at); // 한 페이지당 몇개를 보여줄지 결정
		
		ArrayList<bbsDTO> dto = dao.pageList(pageNum, amount);
		
		return dto;
	}
	
	//DB에 저장된 게시물 총 개수 찾는 기능
	public int PostCount() {
		
		int Count = dao.PostCount();
		
		return Count;
		
	}
	
	//게시물 삭제
	
	public void DelPost(String bnum) {
		
		dao.DelPost(bnum);
		
	}
	
	public bbsDTO getPost(String bnum) {
		
		System.out.println("Service실행 @ 게시물 번호 확인: "+bnum);
		
		bbsDTO dto = dao.getPost(bnum);
		
		return dto;
		
	}
	
	public void PostModify(String bnum, String btitle, String bcontent) {
		
		System.out.println("Service 실행 @");
		System.out.println("게시물 번호: "+bnum);
		System.out.println("게시물 제목: "+btitle);
		System.out.println("게시물 내용: "+bcontent);
		
		dao.PostModify(bnum, btitle, bcontent);
		
	}
	// 댓글
	public void reply(String nickname, String username, String rnum, String rcomment) {
		
		System.out.println("Service 실행 @");
		System.out.println("닉네임: "+nickname);
		System.out.println("댓글 작성자: "+username);
		System.out.println("해당 게시물 번호: "+rnum);
		System.out.println("댓글 내용: "+rcomment);
		
		dao.reply(nickname, username, rnum, rcomment);
		
	}
	// 대댓글
	public void saveRecomment(String cnum, String username, String nickname, String recomment, String groupnum) {
		
		System.out.println("Service 실행  @");
		System.out.println("댓글 번호: "+ cnum);
		System.out.println("대댓 작성자: "+username);
		System.out.println("대댓 작성자 닉네임: "+nickname);
		System.out.println("대댓 내용: "+recomment);
		
		dao.saveRecomment(cnum, username, nickname, recomment, groupnum);
	}
	
	//댓글 가져오는 서비스
	public ArrayList<ReplyDTO> selectReply(String bnum) {
		
		System.out.println("Service 실행  @");
		System.out.println("게시물 번호 및 댓글 번호: "+ bnum);
		
		ArrayList<ReplyDTO> rdto = dao.selectReply(bnum);
		
		return rdto;
	}
	
	// 대댓글 가져오는 서비스
	public ArrayList<RereplyDTO> RecommentSelect(String groupnum){
		
		System.out.println("Service 실행 @");
		System.out.println("대댓글 조회를 위한 그룹 넘버: "+groupnum);
		
		ArrayList<RereplyDTO> dtos = dao.RecommentSelect(groupnum);
		
		return dtos;
	}
	
	//제목으로 검색
	public ArrayList<bbsDTO> searchTitle(String value) {
		
		System.out.println("Service 실행 @");
		System.out.println("검색어: "+value);
		
		ArrayList<bbsDTO> dto = dao.searchTitle(value);
		
		return dto;
	}
	
	//내용으로 검색
	public ArrayList<bbsDTO> searchContent(String value) {
		
		System.out.println("Service 실행 @");
		System.out.println("검색어: "+value);
		
		ArrayList<bbsDTO> dto = dao.searchContent(value);
		
		return dto;
	}
	
	public String idchecker(String userid) {
		
		int chNum = dao.idCheck(userid);
		
		String result = null;
		
		if(chNum >= 1 ) {
			result = "error";
		}else if(chNum == 0) {
			result = "ok";
		}
		
		return result;
	}
	
	public String nicknamech(String nickname) {
		
		int nickCh = dao.nicknameCheck(nickname);
		
		String data = null;
		
		if(nickCh >= 1) {
			data = "1";
		}else if(nickCh == 0) {
			data = "0";
		}
		
		return data;
		
	}

}

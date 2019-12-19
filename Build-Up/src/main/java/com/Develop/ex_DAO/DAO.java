package com.Develop.ex_DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.Develop.ex_DTO.ReplyDTO;
import com.Develop.ex_DTO.RereplyDTO;
import com.Develop.ex_DTO.bbsDTO;
import com.Develop.ex_DTO.searchDTO;

public class DAO implements IDAO{
	
	//@Autowired //따로 빈 등록하지 않아도 알아서 연결해줌 개꿀
	private IDAO idao;
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입 1-1(개인정보 입력 등)
	@Override
	public void joinConfirm(String userid, String username, String nickname, String userpw, String email) {
		
		
		idao = sqlSession.getMapper(IDAO.class);
		
		idao.joinConfirm(userid, username, nickname, userpw, email);
	}
	
	//회원가입 1-2(외래키로서 부모에 입력된 값(유저 아이디)를 받아 권한까지 함께 저장)
	@Override
	public void joinAuth(String userid, String authority) {
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.joinAuth(userid, authority);
		
	}
	
	//글쓰기
	@Override
	public void writeBoard(String username, String nickname, String btitle, String bcontent, String postimage, String bimage) {
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.writeBoard(username, nickname, btitle, bcontent, postimage, bimage);
		
	}
	
	//DB에 저장된 전체 게시물 가져옴
	@Override
	public ArrayList<bbsDTO> selectList() {
		// TODO Auto-generated method stub
	
		idao = sqlSession.getMapper(IDAO.class);
		ArrayList<bbsDTO> dtos = idao.selectList();
		
		return dtos;
		
	}
	
	//페이징
	@Override
	public ArrayList<bbsDTO> pageList(int pageNum, int amount) {
		// TODO Auto-generated method stub
		
		idao = sqlSession.getMapper(IDAO.class);
		ArrayList<bbsDTO> dto = idao.pageList(pageNum, amount);
		
		return dto;
	}
	
	//게시물 총 개수
	public int PostCount() {
		
		idao = sqlSession.getMapper(IDAO.class); //idao에 매퍼의 값을 받을 수 있도록 등록? 해주고
		
		int Count = idao.PostCount(); // idao에서 실행값 저장
		
		return Count;
	}
	
	//게시물 번호를 이용한 게시물 삭제.
	@Override
	public void DelPost(String bnum) {
		
		idao = sqlSession.getMapper(IDAO.class);
		
		idao.DelPost(bnum);
		
		
	}
	
	//게시물 번호를 통해 게시물 가져오는 실행
	@Override
	public bbsDTO getPost(String bnum) {
		
		idao = sqlSession.getMapper(IDAO.class);
		bbsDTO dto = idao.getPost(bnum);
		
		System.out.println("DAO 데이터 확인@ 게시물 번호: "+bnum);
		
		return dto;
	}
	
	@Override
	public void PostModify(String bnum, String btitle, String bcontent) {
		
		System.out.println("DAO 실행  @");
		System.out.println("게시물 번호: "+bnum);
		System.out.println("게시물 제목: "+btitle);
		System.out.println("게시물 내용: "+bcontent);
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.PostModify(bnum, btitle, bcontent);
		
	}
	
	@Override
	public void reply(String nickname, String username, String rnum, String rcomment) {
		
		System.out.println("DAO 실행  @");
		System.out.println("닉네임: "+nickname);
		System.out.println("댓글 작성자: "+username);
		System.out.println("해당 게시물 번호: "+rnum);
		System.out.println("댓글 내용: "+rcomment);
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.reply(nickname, username, rnum, rcomment);
		
	}
	
	@Override
	public ArrayList<ReplyDTO> selectReply(String bnum) {
		
		System.out.println("DAO 실행  @");
		System.out.println("게시물 번호 및 댓글 번호: "+ bnum);
		
		idao = sqlSession.getMapper(IDAO.class);
		
		ArrayList<ReplyDTO> rdto = idao.selectReply(bnum);
		
		return rdto;
	}
	
	@Override
	public void saveRecomment(String cnum, String username, String nickname, String recomment, String groupnum) {
		
		System.out.println("DAO 실행  @");
		System.out.println("댓글 번호: "+ cnum);
		System.out.println("대댓 작성자: "+username);
		System.out.println("대댓 작성자 닉네임: "+nickname);
		System.out.println("대댓 내용: "+recomment);
		System.out.println("대댓 그룹: "+groupnum);
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.saveRecomment(cnum, username, nickname, recomment, groupnum);
	}
	
	//그룹 번호를 통한 조회로 대댓글 찾아오기.
	@Override
	public ArrayList<RereplyDTO> RecommentSelect(String groupnum) {
		
		System.out.println("DAO 실행 @");
		System.out.println("대댓글 그룹 번호: "+groupnum);
		
		idao = sqlSession.getMapper(IDAO.class);
		ArrayList<RereplyDTO> dtos = idao.RecommentSelect(groupnum);
		
		return dtos;
	}
	
	// 검색 서비스(제목) 
	@Override
	public ArrayList<bbsDTO> searchTitle(String value) {
		
		System.out.println("DAO 실행 @");
		System.out.println("키워드: "+value);
		
		idao = sqlSession.getMapper(IDAO.class);
		
		ArrayList<bbsDTO> sDto = idao.searchTitle(value);
		System.out.println("제목으로 찾은 값: "+ sDto == null);
		return sDto;
	}
	
	//검색 서비스 (내용)
	@Override
	public ArrayList<bbsDTO> searchContent(String value) {
		
		System.out.println("DAO 실행 @");
		System.out.println("키워드: "+value);
		
		idao = sqlSession.getMapper(IDAO.class);
		
		ArrayList<bbsDTO> dto = idao.searchContent(value);
		
		return dto;
	}
	
	@Override
	public int idCheck(String userid) {
		
		idao = sqlSession.getMapper(IDAO.class);
		
		
		int idch = idao.idCheck(userid); 
		
		return idch;
	}
	
	@Override
	public int nicknameCheck(String nickname) {
		
		idao = sqlSession.getMapper(IDAO.class);
		
		int nickch = idao.nicknameCheck(nickname);
		
		return nickch;
	}

}

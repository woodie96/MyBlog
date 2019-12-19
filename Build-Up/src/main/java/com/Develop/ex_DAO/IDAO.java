package com.Develop.ex_DAO;

import java.util.ArrayList;

import com.Develop.ex_DTO.ReplyDTO;
import com.Develop.ex_DTO.RereplyDTO;
import com.Develop.ex_DTO.bbsDTO;

public interface IDAO {

	public void joinConfirm(String userid, String username, String nickname, String userpw, String email);
	public void joinAuth(String userid, String authority);
	public void writeBoard(String username, String nickname, String btitle, String bcontent, String postimage, String bimage);
	
	//페이징 없이 게시물 가져오기
	public ArrayList<bbsDTO> selectList();
	
	//페이징으로 게시물 가져오기
	public ArrayList<bbsDTO> pageList(int pageNum, int amount);
	public int PostCount();
	
	//게시물 삭제
	public void DelPost(String bnum);
	
	public bbsDTO getPost(String bnum);
	public void PostModify(String bnum, String btitle, String bcontent);
	public void reply(String nickname, String username, String rnum, String rcomment);
	public ArrayList<ReplyDTO> selectReply(String bnum);
	public ArrayList<RereplyDTO> RecommentSelect(String groupnum);
	
	public void saveRecomment(String cnum, String username, String nickname, String recomment, String groupnum);
	
	//검색어 (제목)으로 찾기
	public ArrayList<bbsDTO> searchTitle(String value);
	//검색어 (내용)으로 찾기
	public ArrayList<bbsDTO> searchContent(String value);
	
	//ajax 아이디 체크
	public int idCheck(String userid);
	//ajax 닉네임체크
	public int nicknameCheck(String nickname);
	
//	@Select("SELECT sysdate FROM dual") //이걸쓰면 xml에 따로 쓰지 않아도되지만 복잡한것을 이렇게 쓰면 오히려 유지보수에 어려움
//	public String getTime();
}

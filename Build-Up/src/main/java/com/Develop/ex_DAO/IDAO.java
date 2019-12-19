package com.Develop.ex_DAO;

import java.util.ArrayList;

import com.Develop.ex_DTO.ReplyDTO;
import com.Develop.ex_DTO.RereplyDTO;
import com.Develop.ex_DTO.bbsDTO;

public interface IDAO {

	public void joinConfirm(String userid, String username, String nickname, String userpw, String email);
	public void joinAuth(String userid, String authority);
	public void writeBoard(String username, String nickname, String btitle, String bcontent, String postimage, String bimage);
	
	//����¡ ���� �Խù� ��������
	public ArrayList<bbsDTO> selectList();
	
	//����¡���� �Խù� ��������
	public ArrayList<bbsDTO> pageList(int pageNum, int amount);
	public int PostCount();
	
	//�Խù� ����
	public void DelPost(String bnum);
	
	public bbsDTO getPost(String bnum);
	public void PostModify(String bnum, String btitle, String bcontent);
	public void reply(String nickname, String username, String rnum, String rcomment);
	public ArrayList<ReplyDTO> selectReply(String bnum);
	public ArrayList<RereplyDTO> RecommentSelect(String groupnum);
	
	public void saveRecomment(String cnum, String username, String nickname, String recomment, String groupnum);
	
	//�˻��� (����)���� ã��
	public ArrayList<bbsDTO> searchTitle(String value);
	//�˻��� (����)���� ã��
	public ArrayList<bbsDTO> searchContent(String value);
	
	//ajax ���̵� üũ
	public int idCheck(String userid);
	//ajax �г���üũ
	public int nicknameCheck(String nickname);
	
//	@Select("SELECT sysdate FROM dual") //�̰ɾ��� xml�� ���� ���� �ʾƵ������� �����Ѱ��� �̷��� ���� ������ ���������� �����
//	public String getTime();
}

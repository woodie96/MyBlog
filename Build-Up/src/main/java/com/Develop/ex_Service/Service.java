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
		System.out.println("####### �Է� �� Ȯ�� #######");
		String userid = dto.getUserid();
		System.out.println("����� ���̵�: "+userid);
		
		String username = dto.getUsername();
		System.out.println("����� �̸�: "+username);
		
		String nickname = dto.getNickname();
		System.out.println("����� �г���: "+nickname);
		
		dto.setUserpw(pwen.encode(dto.getUserpw()));
		String userpw = dto.getUserpw();
		System.out.println("����� �н�����: "+userpw);
		
		String authority = dto.getAuthority();
		System.out.println("����� ����: "+authority);
		
		String email = dto.getEmail();
		System.out.println("�����  �̸���: "+email);
		System.out.println("##### �Է� �� Ȯ�� ������ #####");
		
		dao.joinConfirm(userid, username, nickname, userpw, email);
		dao.joinAuth(userid, authority);
		
	}
	
	public void writeBoard(String username,String nickname,String btitle,String bcontent,String postimage,String bimage) {
		
		System.out.println("===== service date check =====");
		System.out.println("�̸�: "+username);
		System.out.println("�г���: "+nickname);
		System.out.println("����: "+btitle);
		System.out.println("����: "+bcontent);
		System.out.println("����̹���: "+postimage);
		System.out.println("�Ϲ��̹���: "+bimage);
		
		dao.writeBoard(username, nickname, btitle, bcontent, postimage, bimage);
		
	}
	
	public ArrayList<bbsDTO> selectList() {
		
		ArrayList<bbsDTO> dtos =  dao.selectList();
		
		return dtos;
	}
	
	//����¡
	public ArrayList<bbsDTO> PageList(int pNum, int at){
		
		Paging p = new Paging();
		
		int pageNum = p.PageNumer(pNum, at); // ���� �������� ��Ÿ��
		int amount = p.PageAmount(pNum, at); // �� �������� ��� �������� ����
		
		ArrayList<bbsDTO> dto = dao.pageList(pageNum, amount);
		
		return dto;
	}
	
	//DB�� ����� �Խù� �� ���� ã�� ���
	public int PostCount() {
		
		int Count = dao.PostCount();
		
		return Count;
		
	}
	
	//�Խù� ����
	
	public void DelPost(String bnum) {
		
		dao.DelPost(bnum);
		
	}
	
	public bbsDTO getPost(String bnum) {
		
		System.out.println("Service���� @ �Խù� ��ȣ Ȯ��: "+bnum);
		
		bbsDTO dto = dao.getPost(bnum);
		
		return dto;
		
	}
	
	public void PostModify(String bnum, String btitle, String bcontent) {
		
		System.out.println("Service ���� @");
		System.out.println("�Խù� ��ȣ: "+bnum);
		System.out.println("�Խù� ����: "+btitle);
		System.out.println("�Խù� ����: "+bcontent);
		
		dao.PostModify(bnum, btitle, bcontent);
		
	}
	// ���
	public void reply(String nickname, String username, String rnum, String rcomment) {
		
		System.out.println("Service ���� @");
		System.out.println("�г���: "+nickname);
		System.out.println("��� �ۼ���: "+username);
		System.out.println("�ش� �Խù� ��ȣ: "+rnum);
		System.out.println("��� ����: "+rcomment);
		
		dao.reply(nickname, username, rnum, rcomment);
		
	}
	// ����
	public void saveRecomment(String cnum, String username, String nickname, String recomment, String groupnum) {
		
		System.out.println("Service ����  @");
		System.out.println("��� ��ȣ: "+ cnum);
		System.out.println("��� �ۼ���: "+username);
		System.out.println("��� �ۼ��� �г���: "+nickname);
		System.out.println("��� ����: "+recomment);
		
		dao.saveRecomment(cnum, username, nickname, recomment, groupnum);
	}
	
	//��� �������� ����
	public ArrayList<ReplyDTO> selectReply(String bnum) {
		
		System.out.println("Service ����  @");
		System.out.println("�Խù� ��ȣ �� ��� ��ȣ: "+ bnum);
		
		ArrayList<ReplyDTO> rdto = dao.selectReply(bnum);
		
		return rdto;
	}
	
	// ���� �������� ����
	public ArrayList<RereplyDTO> RecommentSelect(String groupnum){
		
		System.out.println("Service ���� @");
		System.out.println("���� ��ȸ�� ���� �׷� �ѹ�: "+groupnum);
		
		ArrayList<RereplyDTO> dtos = dao.RecommentSelect(groupnum);
		
		return dtos;
	}
	
	//�������� �˻�
	public ArrayList<bbsDTO> searchTitle(String value) {
		
		System.out.println("Service ���� @");
		System.out.println("�˻���: "+value);
		
		ArrayList<bbsDTO> dto = dao.searchTitle(value);
		
		return dto;
	}
	
	//�������� �˻�
	public ArrayList<bbsDTO> searchContent(String value) {
		
		System.out.println("Service ���� @");
		System.out.println("�˻���: "+value);
		
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

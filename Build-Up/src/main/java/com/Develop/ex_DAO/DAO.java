package com.Develop.ex_DAO;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.Develop.ex_DTO.ReplyDTO;
import com.Develop.ex_DTO.RereplyDTO;
import com.Develop.ex_DTO.bbsDTO;
import com.Develop.ex_DTO.searchDTO;

public class DAO implements IDAO{
	
	//@Autowired //���� �� ������� �ʾƵ� �˾Ƽ� �������� ����
	private IDAO idao;
	
	
	@Autowired
	private SqlSession sqlSession;
	
	//ȸ������ 1-1(�������� �Է� ��)
	@Override
	public void joinConfirm(String userid, String username, String nickname, String userpw, String email) {
		
		
		idao = sqlSession.getMapper(IDAO.class);
		
		idao.joinConfirm(userid, username, nickname, userpw, email);
	}
	
	//ȸ������ 1-2(�ܷ�Ű�μ� �θ� �Էµ� ��(���� ���̵�)�� �޾� ���ѱ��� �Բ� ����)
	@Override
	public void joinAuth(String userid, String authority) {
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.joinAuth(userid, authority);
		
	}
	
	//�۾���
	@Override
	public void writeBoard(String username, String nickname, String btitle, String bcontent, String postimage, String bimage) {
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.writeBoard(username, nickname, btitle, bcontent, postimage, bimage);
		
	}
	
	//DB�� ����� ��ü �Խù� ������
	@Override
	public ArrayList<bbsDTO> selectList() {
		// TODO Auto-generated method stub
	
		idao = sqlSession.getMapper(IDAO.class);
		ArrayList<bbsDTO> dtos = idao.selectList();
		
		return dtos;
		
	}
	
	//����¡
	@Override
	public ArrayList<bbsDTO> pageList(int pageNum, int amount) {
		// TODO Auto-generated method stub
		
		idao = sqlSession.getMapper(IDAO.class);
		ArrayList<bbsDTO> dto = idao.pageList(pageNum, amount);
		
		return dto;
	}
	
	//�Խù� �� ����
	public int PostCount() {
		
		idao = sqlSession.getMapper(IDAO.class); //idao�� ������ ���� ���� �� �ֵ��� ���? ���ְ�
		
		int Count = idao.PostCount(); // idao���� ���ప ����
		
		return Count;
	}
	
	//�Խù� ��ȣ�� �̿��� �Խù� ����.
	@Override
	public void DelPost(String bnum) {
		
		idao = sqlSession.getMapper(IDAO.class);
		
		idao.DelPost(bnum);
		
		
	}
	
	//�Խù� ��ȣ�� ���� �Խù� �������� ����
	@Override
	public bbsDTO getPost(String bnum) {
		
		idao = sqlSession.getMapper(IDAO.class);
		bbsDTO dto = idao.getPost(bnum);
		
		System.out.println("DAO ������ Ȯ��@ �Խù� ��ȣ: "+bnum);
		
		return dto;
	}
	
	@Override
	public void PostModify(String bnum, String btitle, String bcontent) {
		
		System.out.println("DAO ����  @");
		System.out.println("�Խù� ��ȣ: "+bnum);
		System.out.println("�Խù� ����: "+btitle);
		System.out.println("�Խù� ����: "+bcontent);
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.PostModify(bnum, btitle, bcontent);
		
	}
	
	@Override
	public void reply(String nickname, String username, String rnum, String rcomment) {
		
		System.out.println("DAO ����  @");
		System.out.println("�г���: "+nickname);
		System.out.println("��� �ۼ���: "+username);
		System.out.println("�ش� �Խù� ��ȣ: "+rnum);
		System.out.println("��� ����: "+rcomment);
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.reply(nickname, username, rnum, rcomment);
		
	}
	
	@Override
	public ArrayList<ReplyDTO> selectReply(String bnum) {
		
		System.out.println("DAO ����  @");
		System.out.println("�Խù� ��ȣ �� ��� ��ȣ: "+ bnum);
		
		idao = sqlSession.getMapper(IDAO.class);
		
		ArrayList<ReplyDTO> rdto = idao.selectReply(bnum);
		
		return rdto;
	}
	
	@Override
	public void saveRecomment(String cnum, String username, String nickname, String recomment, String groupnum) {
		
		System.out.println("DAO ����  @");
		System.out.println("��� ��ȣ: "+ cnum);
		System.out.println("��� �ۼ���: "+username);
		System.out.println("��� �ۼ��� �г���: "+nickname);
		System.out.println("��� ����: "+recomment);
		System.out.println("��� �׷�: "+groupnum);
		
		idao = sqlSession.getMapper(IDAO.class);
		idao.saveRecomment(cnum, username, nickname, recomment, groupnum);
	}
	
	//�׷� ��ȣ�� ���� ��ȸ�� ���� ã�ƿ���.
	@Override
	public ArrayList<RereplyDTO> RecommentSelect(String groupnum) {
		
		System.out.println("DAO ���� @");
		System.out.println("���� �׷� ��ȣ: "+groupnum);
		
		idao = sqlSession.getMapper(IDAO.class);
		ArrayList<RereplyDTO> dtos = idao.RecommentSelect(groupnum);
		
		return dtos;
	}
	
	// �˻� ����(����) 
	@Override
	public ArrayList<bbsDTO> searchTitle(String value) {
		
		System.out.println("DAO ���� @");
		System.out.println("Ű����: "+value);
		
		idao = sqlSession.getMapper(IDAO.class);
		
		ArrayList<bbsDTO> sDto = idao.searchTitle(value);
		System.out.println("�������� ã�� ��: "+ sDto == null);
		return sDto;
	}
	
	//�˻� ���� (����)
	@Override
	public ArrayList<bbsDTO> searchContent(String value) {
		
		System.out.println("DAO ���� @");
		System.out.println("Ű����: "+value);
		
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

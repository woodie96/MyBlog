package com.Develop.ex_Paging;

public class Paging {
	
	public int PageNumer(int pageNum, int amount) {
		
		int param1 = pageNum * amount; // ó�� ������ �� pageNum�� �⺻ ���� 1�� �־��� amount�� 5
									   // pageNum�� 2��� 2 * 5 = 10�� �ȴ�.
		return param1;
	}
	
	public int PageAmount(int pageNum, int amount) {
		
		int param2 = ( pageNum - 1 ) * amount;
		// param2 = ( 1 - 1 ) * 5 = 0 --> 1��° ���������� ������ �Խù� ���ڴ� 70 ~ 65 �� 5���̴�.
		// ������ pageNum�� DESC�� �Խù��� �����ͼ�(���� �ֱٿ� ���� �����ֱ� ����) ���������� 5���� �����´�.
		// �� ���¿��� amount�� 0�̸� 5 > 0  -> 0���� 5���� �����´�.
		return param2;
	}
	
	//����¡ ���������� ���ϱ�
	public int startPage(int pageNum, int amount) {
		
		int startPage = ( (pageNum - 1) / amount ) * amount + 1;
		
		//((1 - 1) / 5 ) * 5 + 1 = 1; ������ ��ȣ�� 5������ 1�� ����������
		//((2 - 1) / 5 ) * 5 + 1 = 1;
		// �ڹٴ� ���� ��ȯ�ɶ� int������ ��ȯ�Ǳ� ������  0.2�� ��ȯ�ǵ� 0���� ���
		// �ᱹ 1 ~ 5���� ������ ��µ��� �ʱ⶧���� startpage ���� 1�̉�
				
		//((6 - 1) / 5 ) * 5 + 1 = 5; 
		//((7 - 1) / 5 ) * 5 + 1 = 6;
		
		return startPage;
	}
	
	//����¡ �� ������ ���ϱ�
	public int endPage(int startPage, int amount) {
		
		int endPage =  startPage + amount  - 1;
		//endpage =  1 + 5 - 1 = 5
		//endpage =  6 + 5 - 1 = 10  
		//endpage =  11 + 5 - 1 = 15
		
		return endPage;
	}
	
	// ��¥ ������ ������ ���ϱ�
	public int realPage(int Count, int amount) {
		
		int realPage = (int) (Math.ceil((Count * 1.0) / amount));
		System.out.println("realPageCount: "+Count);
		//Math.ceil�� double�� �̱⶧���� �տ� Int�� �ٿ��־�� int�� ������ ���� �� ����
		// ��ȣ�� ���־�� ����� ���������� ��
		
		return realPage;
	}

}

package com.Develop.ex_Paging;

public class Paging {
	
	public int PageNumer(int pageNum, int amount) {
		
		int param1 = pageNum * amount; // 처음 시작할 때 pageNum은 기본 값은 1이 주어짐 amount는 5
									   // pageNum이 2라면 2 * 5 = 10이 된다.
		return param1;
	}
	
	public int PageAmount(int pageNum, int amount) {
		
		int param2 = ( pageNum - 1 ) * amount;
		// param2 = ( 1 - 1 ) * 5 = 0 --> 1번째 페이지에서 가져올 게시물 숫자는 70 ~ 65 즉 5개이다.
		// 이유는 pageNum이 DESC로 게시물을 가져와서(가장 최근에 쓴걸 보여주기 위함) 위에서부터 5개를 가져온다.
		// 그 상태에서 amount가 0이면 5 > 0  -> 0부터 5까지 가져온다.
		return param2;
	}
	
	//페이징 시작페이지 구하기
	public int startPage(int pageNum, int amount) {
		
		int startPage = ( (pageNum - 1) / amount ) * amount + 1;
		
		//((1 - 1) / 5 ) * 5 + 1 = 1; 페이지 번호가 5까지는 1이 시작페이지
		//((2 - 1) / 5 ) * 5 + 1 = 1;
		// 자바는 값이 반환될때 int형으로 반환되기 때문에  0.2가 반환되도 0으로 출력됌
		// 결국 1 ~ 5까지 정수가 출력되지 않기때문에 startpage 값이 1이됌
				
		//((6 - 1) / 5 ) * 5 + 1 = 5; 
		//((7 - 1) / 5 ) * 5 + 1 = 6;
		
		return startPage;
	}
	
	//페이징 끝 페이지 구하기
	public int endPage(int startPage, int amount) {
		
		int endPage =  startPage + amount  - 1;
		//endpage =  1 + 5 - 1 = 5
		//endpage =  6 + 5 - 1 = 10  
		//endpage =  11 + 5 - 1 = 15
		
		return endPage;
	}
	
	// 진짜 마지막 페이지 구하기
	public int realPage(int Count, int amount) {
		
		int realPage = (int) (Math.ceil((Count * 1.0) / amount));
		System.out.println("realPageCount: "+Count);
		//Math.ceil은 double형 이기때문에 앞에 Int를 붙여주어야 int형 변수에 담을 수 있음
		// 괄호를 해주어야 계산이 순차적으로 됌
		
		return realPage;
	}

}

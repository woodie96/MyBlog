package com.Develop.ex_BuildUp;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.UUID;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Develop.ex_DTO.DTO;
import com.Develop.ex_DTO.PagingDTO;
import com.Develop.ex_DTO.ReplyDTO;
import com.Develop.ex_DTO.RereplyDTO;
import com.Develop.ex_DTO.bbsDTO;
import com.Develop.ex_Paging.Paging;
import com.Develop.ex_Service.Service;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	
	@Autowired
	private Service service;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	//메인 홈 페이지
	@GetMapping("/")
	public String home(Model model, PagingDTO pDTO) {
		
		Paging p = new Paging();
		
		int pageNum = 0; // 디폴트
		int amount = 5; // 페이지에서 보여줄 게시물 갯수. 디폴트

		
		if(pDTO.getPageNum() == null) {
			
			pageNum = 1; // 페이지 넘버는 기본값 1
			
		}else if(pDTO.getPageNum() != null) {
			
			pageNum = Integer.parseInt(pDTO.getPageNum());
			
		}
		
		int startPage = p.startPage(pageNum, amount);

		int endPage = p.endPage(startPage, amount);
		
		//service.PostCount() = 게시물 총 페이지 의미함
		int realPage = p.realPage(service.PostCount(), amount);
		
			
		
		
		if(endPage > realPage) {
			endPage = realPage;
			//endpage가 필요한 이상만큼 커진다면 제어하기 위해 필요한 if문
			//realpage는 전체 게시물 개수와 보여줄 게시물 개수를 계산해 넣은 값이기 때문에 정확함
		}
		
		

		// 게시물 가져옴
		//ArrayList<bbsDTO> dtos = service.selectList();
		
		//페이징으로 게시물 가져옴
		ArrayList<bbsDTO> dto = service.PageList(pageNum, amount);
		
		
		
		
		//model.addAttribute("list", dtos);
		model.addAttribute("list", dto);
		model.addAttribute("realEnd", realPage);
		model.addAttribute("startpage", startPage);
		model.addAttribute("endpage", endPage);
		return "/Boardlist";
	}
	
	//회원가입 페이지로 이동
	@GetMapping("join")
	public void joinpage() {
		
	}
	
	//회원가입 로직
	@PostMapping("joinConfirm")
	public String Confirm(DTO dto, Model model) {
		
		if(dto.getUserpw().equals(dto.getUserpwch())) {
			service.Confirm(dto);
		}else {
			System.out.println("비밀번호가 일치하지 않습니다!");
			return "redirect:/join";
		}
		
		model.addAttribute("msg",1);
		return "redirect:/";
	}
	
	//Spring security 로그인 페이지 이동
	@GetMapping("LoginPage")
	public String loginPageGo(String error, Model model) {
		
		if(error != null) {
			model.addAttribute("error", -1);
		}
		
		return "/LoginPage";
	}
	
	
	@GetMapping("/AboutPage")
	public void AboutPage() {
		
	}
	
	//접근권한제어 페이지
	@GetMapping("/errorpage")
	public String errorpage() {
		
		System.out.println("Access Denied");
		
		return"errorpage";
	}
	
	//게시물 보기 페이지
	@GetMapping("Listview")
	public String listview(HttpServletRequest request, Model model) {
		
		// post Number = 게시물 번호가 ex) 4이면 댓글 및 대댓글의 그룹 넘버가 4인것만 추출
		String bnum = request.getParameter("bnum");
		System.out.println("게시물 번호: "+bnum);
		System.out.println("가져올 댓글 그룹 번호: "+bnum);
		System.out.println("가져올 대댓글 그룹 번호: "+bnum);
		
		//게시물 가져오기
		bbsDTO dto = service.getPost(bnum);
		
		//댓글 가져오기
		ArrayList<ReplyDTO> rdto = service.selectReply(bnum); 
		
		//대댓글 가져오기
		ArrayList<RereplyDTO> dtos = service.RecommentSelect(bnum); // bnum(게시물 번호) = groupnum
		
		
		model.addAttribute("post", dto);
		model.addAttribute("reply", rdto);
		model.addAttribute("reComment", dtos);
		
		return "/Listview";
	}
	
	
	//게시물 수정 페이지 이동
	@GetMapping("PostModifyPage")
	public String modify(bbsDTO dto, Model model) {
		
		//service.PostModify(dto.getBnum(), dto.getBtitle(), dto.getBcontent());
		System.out.println("========================== 시작");
		
		String bnum = dto.getBnum();
		//해당 게시글 번호로 글 가져오기 기능을 글 삭제 부분에서 한번 더 이용.
		
		model.addAttribute("modify", service.getPost(bnum));
		
		return "/PostModifyPage";
	}
	
	//게시물 수정 로직
	@PostMapping("ModifyPost")
	public String modifyPost(bbsDTO dto) {
		
		System.out.println("========================== 시작");
		System.out.println(dto.getBnum());
		System.out.println(dto.getBtitle());
		System.out.println(dto.getBcontent());
		
		service.PostModify(dto.getBnum(), dto.getBtitle(), dto.getBcontent());
		
		return "redirect:/";
	}
	
	//게시물 삭제 로직
	@GetMapping("Delpost")
	public String DelPost(bbsDTO dto) {
		
		service.DelPost(dto.getBnum());
		
		
		return "redirect:/";
	}
	
	//글 쓰기 페이지로 이동
	@GetMapping("/writeview")
	public void writeview() {
		
	}
	
	@GetMapping("/PostWritePage")
	public void postWritePage() {
		
	}
	
	//게시물 작성 하는 기능
	@PostMapping("PostWrite")
	public String writego(bbsDTO bDto, MultipartFile[] upfile) {
		
		String uploadUri = "D:\\upload";
		
		
		String username = bDto.getUsername();
		String nickname = bDto.getNickname();
		String btitle = bDto.getBtitle();
		String bcontent = bDto.getBcontent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "gt;").replaceAll("\n", "<br>");
		String postimage;
		String bimage;
		
		UUID uid = UUID.randomUUID(); // 랜덤하게 UUID를 만드는 메서드
		ArrayList<String> image = new ArrayList<String>(); // 두개의 이미지를 받기 위해 만든 것.
		
		for(MultipartFile files : upfile) { // 이미지 최대 두개.
			
			//이미지 파일 확장자 먼저 체크.
			if(files.getOriginalFilename().endsWith("jpg") || files.getOriginalFilename().endsWith("jpeg") || files.getOriginalFilename().endsWith("png") ||files.getOriginalFilename().endsWith("gif")) {
				System.out.println("업로드파일 이미지 확인 완료!");
			}else {
				System.out.println("이미지 파일이 아닌 다른 파일이 확인되었습니다.");
				return "redirect:/";
			}
			
			String imageName = uid.toString()+"_"+files.getOriginalFilename(); // 랜덤uid를 붙인 파일의 오리지널 이름을 저장
			
			image.add(imageName);
			
			File file = new File(uploadUri, imageName); // 해당 Uri에 업로드하기 위해서 trnasferTo 메서드를 사용하는데 매개변수 값을 File 로 받기 때문에 File 변수를 선언 후 Uri와 이미지 이름을 매개변수로 준다.
			
			try {
				files.transferTo(file); 
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		//for문으로 List에 저장한 이미지 이름을 적절한 변수에 저장
		postimage = image.get(0);
		bimage = image.get(1);
		
		System.out.println("=================");
		System.out.println("이름: "+username);
		System.out.println("닉네임: "+nickname);
		System.out.println("제목: "+btitle);
		System.out.println("내용: "+bcontent);
		System.out.println("포스트 배경 이미지: "+postimage);
		System.out.println("일반 이미지: "+bimage);
		
		service.writeBoard(username,nickname,btitle,bcontent,postimage, bimage);
		
		return "redirect:/";
	}
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		
		Resource resource = new FileSystemResource("D:\\upload\\"+ fileName); //D:\\upload 에 \\가 추가되는 이유는 이미지가 저장된 공간의 파일을 다운로드하기 때문에 \\를 추가해줘야함
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; fileName="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1")); 
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}
	
	
	// 검색 하는 로직
	@PostMapping("search")
	public String search(HttpServletRequest rq, RedirectAttributes at, Model model) {
		
		String value = rq.getParameter("SearchValue");
		
		System.out.println("검색: "+value);
		
		ArrayList<bbsDTO> dtoT = service.searchTitle(value); // 제목으로 검색한 결과를 저장
		
		
		System.out.println(dtoT.isEmpty() == false); // dtoT가  비어있는게 거짓이라면(반환값이 있다면) true -> dtoT != null 와 같음
		
		if(dtoT.isEmpty() == false) { //dtoT의 값이 null이 아니라면
			
			at.addFlashAttribute("searchValue", dtoT); //dtoT의 값이 존재하기 때문에 전송.
			System.out.println("작동알림: dtoT 반환");
			
		}else if(dtoT.isEmpty()) { // 하지만 만약에 dtoT가 비어있다면
			
			ArrayList<bbsDTO> dtoC = service.searchContent(value);// dtoC를 만들고
			
			if(dtoC.isEmpty() == false) { //  만약 dtoC의 값이 null이 아니라면
				
				at.addFlashAttribute("searchValue", dtoC); // dtoC의 값이 존재하기 때문에 전송.
				System.out.println("작동알림: dtoC반환");
				
			}else if(dtoC.isEmpty()) { //dtoC의 값이 비어있다면 에러를 전송.
				
				at.addFlashAttribute("error", "e");
				System.out.println("작동알림: error 반환");
			}
		}
		
		return "redirect:/";
	}
	
	//댓글 달기.
	@PostMapping("reply")
	@ResponseBody
	public void reply(HttpServletRequest rq) throws UnsupportedEncodingException {
		
	   String username = rq.getParameter("username");
	   String nickname = rq.getParameter("nickname");
	   String rnum = rq.getParameter("rnum");
	   String rcomment = rq.getParameter("rcomment");
	   
	   System.out.println("Ajax 알림@");
	   System.out.println("작성자: "+username);
	   System.out.println("닉네임: "+nickname);
	   System.out.println("댓글 번호: "+rnum);
	   System.out.println("댓글 내용: "+rcomment);
		
	   service.reply(nickname, username, rnum, rcomment);
	}
	
	//댓글 저장
	@PostMapping("saveRecomment")
	@ResponseBody
	public void SaveRecomment(HttpServletRequest rq, RereplyDTO reDTO) {
		
		System.out.println("Ajax 알림 @");
		
		String reNum = rq.getParameter("replynum");
		String reName = rq.getParameter("replyname");
		String reNickname = rq.getParameter("replynickname");
		String rePostnum = rq.getParameter("replypostnum");
		String reComment = rq.getParameter("replycomment");
		
		System.out.println("부모댓글 번호: "+reNum); // 해당 게시물 안에 있는 부모 댓글 번호
		System.out.println("게시물 번호: "+reName);// 대댓글을 저장하기 위한 게시물 번호
		System.out.println("작성자 이름: "+reNickname); //유저 이름
		System.out.println("작성자 닉네임: "+rePostnum);// 닉네임
		System.out.println("대댓글 내용: "+ reComment);// 대댓글 내용
		
		service.saveRecomment(reNum, reName, reNickname, reComment, rePostnum);
		
	}

	@PostMapping("idcheck")
	@ResponseBody
	public String IDcheck(HttpServletRequest rq) {
		
		String userid = rq.getParameter("userid");
		
		String result = service.idchecker(userid);
		
		return result;
	}
	
	@PostMapping("nicknamech")
	@ResponseBody
	public String Nicknamech(HttpServletRequest rq) {
		
		String nickname = rq.getParameter("nickname");
		
		String checker = service.nicknamech(nickname);
		
		return checker;
		
	}
	
		  
		
	
	
	
	
}

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
	
	//���� Ȩ ������
	@GetMapping("/")
	public String home(Model model, PagingDTO pDTO) {
		
		Paging p = new Paging();
		
		int pageNum = 0; // ����Ʈ
		int amount = 5; // ���������� ������ �Խù� ����. ����Ʈ

		
		if(pDTO.getPageNum() == null) {
			
			pageNum = 1; // ������ �ѹ��� �⺻�� 1
			
		}else if(pDTO.getPageNum() != null) {
			
			pageNum = Integer.parseInt(pDTO.getPageNum());
			
		}
		
		int startPage = p.startPage(pageNum, amount);

		int endPage = p.endPage(startPage, amount);
		
		//service.PostCount() = �Խù� �� ������ �ǹ���
		int realPage = p.realPage(service.PostCount(), amount);
		
			
		
		
		if(endPage > realPage) {
			endPage = realPage;
			//endpage�� �ʿ��� �̻�ŭ Ŀ���ٸ� �����ϱ� ���� �ʿ��� if��
			//realpage�� ��ü �Խù� ������ ������ �Խù� ������ ����� ���� ���̱� ������ ��Ȯ��
		}
		
		

		// �Խù� ������
		//ArrayList<bbsDTO> dtos = service.selectList();
		
		//����¡���� �Խù� ������
		ArrayList<bbsDTO> dto = service.PageList(pageNum, amount);
		
		
		
		
		//model.addAttribute("list", dtos);
		model.addAttribute("list", dto);
		model.addAttribute("realEnd", realPage);
		model.addAttribute("startpage", startPage);
		model.addAttribute("endpage", endPage);
		return "/Boardlist";
	}
	
	//ȸ������ �������� �̵�
	@GetMapping("join")
	public void joinpage() {
		
	}
	
	//ȸ������ ����
	@PostMapping("joinConfirm")
	public String Confirm(DTO dto, Model model) {
		
		if(dto.getUserpw().equals(dto.getUserpwch())) {
			service.Confirm(dto);
		}else {
			System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�!");
			return "redirect:/join";
		}
		
		model.addAttribute("msg",1);
		return "redirect:/";
	}
	
	//Spring security �α��� ������ �̵�
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
	
	//���ٱ������� ������
	@GetMapping("/errorpage")
	public String errorpage() {
		
		System.out.println("Access Denied");
		
		return"errorpage";
	}
	
	//�Խù� ���� ������
	@GetMapping("Listview")
	public String listview(HttpServletRequest request, Model model) {
		
		// post Number = �Խù� ��ȣ�� ex) 4�̸� ��� �� ������ �׷� �ѹ��� 4�ΰ͸� ����
		String bnum = request.getParameter("bnum");
		System.out.println("�Խù� ��ȣ: "+bnum);
		System.out.println("������ ��� �׷� ��ȣ: "+bnum);
		System.out.println("������ ���� �׷� ��ȣ: "+bnum);
		
		//�Խù� ��������
		bbsDTO dto = service.getPost(bnum);
		
		//��� ��������
		ArrayList<ReplyDTO> rdto = service.selectReply(bnum); 
		
		//���� ��������
		ArrayList<RereplyDTO> dtos = service.RecommentSelect(bnum); // bnum(�Խù� ��ȣ) = groupnum
		
		
		model.addAttribute("post", dto);
		model.addAttribute("reply", rdto);
		model.addAttribute("reComment", dtos);
		
		return "/Listview";
	}
	
	
	//�Խù� ���� ������ �̵�
	@GetMapping("PostModifyPage")
	public String modify(bbsDTO dto, Model model) {
		
		//service.PostModify(dto.getBnum(), dto.getBtitle(), dto.getBcontent());
		System.out.println("========================== ����");
		
		String bnum = dto.getBnum();
		//�ش� �Խñ� ��ȣ�� �� �������� ����� �� ���� �κп��� �ѹ� �� �̿�.
		
		model.addAttribute("modify", service.getPost(bnum));
		
		return "/PostModifyPage";
	}
	
	//�Խù� ���� ����
	@PostMapping("ModifyPost")
	public String modifyPost(bbsDTO dto) {
		
		System.out.println("========================== ����");
		System.out.println(dto.getBnum());
		System.out.println(dto.getBtitle());
		System.out.println(dto.getBcontent());
		
		service.PostModify(dto.getBnum(), dto.getBtitle(), dto.getBcontent());
		
		return "redirect:/";
	}
	
	//�Խù� ���� ����
	@GetMapping("Delpost")
	public String DelPost(bbsDTO dto) {
		
		service.DelPost(dto.getBnum());
		
		
		return "redirect:/";
	}
	
	//�� ���� �������� �̵�
	@GetMapping("/writeview")
	public void writeview() {
		
	}
	
	@GetMapping("/PostWritePage")
	public void postWritePage() {
		
	}
	
	//�Խù� �ۼ� �ϴ� ���
	@PostMapping("PostWrite")
	public String writego(bbsDTO bDto, MultipartFile[] upfile) {
		
		String uploadUri = "D:\\upload";
		
		
		String username = bDto.getUsername();
		String nickname = bDto.getNickname();
		String btitle = bDto.getBtitle();
		String bcontent = bDto.getBcontent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "gt;").replaceAll("\n", "<br>");
		String postimage;
		String bimage;
		
		UUID uid = UUID.randomUUID(); // �����ϰ� UUID�� ����� �޼���
		ArrayList<String> image = new ArrayList<String>(); // �ΰ��� �̹����� �ޱ� ���� ���� ��.
		
		for(MultipartFile files : upfile) { // �̹��� �ִ� �ΰ�.
			
			//�̹��� ���� Ȯ���� ���� üũ.
			if(files.getOriginalFilename().endsWith("jpg") || files.getOriginalFilename().endsWith("jpeg") || files.getOriginalFilename().endsWith("png") ||files.getOriginalFilename().endsWith("gif")) {
				System.out.println("���ε����� �̹��� Ȯ�� �Ϸ�!");
			}else {
				System.out.println("�̹��� ������ �ƴ� �ٸ� ������ Ȯ�εǾ����ϴ�.");
				return "redirect:/";
			}
			
			String imageName = uid.toString()+"_"+files.getOriginalFilename(); // ����uid�� ���� ������ �������� �̸��� ����
			
			image.add(imageName);
			
			File file = new File(uploadUri, imageName); // �ش� Uri�� ���ε��ϱ� ���ؼ� trnasferTo �޼��带 ����ϴµ� �Ű����� ���� File �� �ޱ� ������ File ������ ���� �� Uri�� �̹��� �̸��� �Ű������� �ش�.
			
			try {
				files.transferTo(file); 
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		//for������ List�� ������ �̹��� �̸��� ������ ������ ����
		postimage = image.get(0);
		bimage = image.get(1);
		
		System.out.println("=================");
		System.out.println("�̸�: "+username);
		System.out.println("�г���: "+nickname);
		System.out.println("����: "+btitle);
		System.out.println("����: "+bcontent);
		System.out.println("����Ʈ ��� �̹���: "+postimage);
		System.out.println("�Ϲ� �̹���: "+bimage);
		
		service.writeBoard(username,nickname,btitle,bcontent,postimage, bimage);
		
		return "redirect:/";
	}
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName){
		
		Resource resource = new FileSystemResource("D:\\upload\\"+ fileName); //D:\\upload �� \\�� �߰��Ǵ� ������ �̹����� ����� ������ ������ �ٿ�ε��ϱ� ������ \\�� �߰��������
		
		String resourceName = resource.getFilename();
		
		HttpHeaders headers = new HttpHeaders();
		
		try {
			headers.add("Content-Disposition", "attachment; fileName="+new String(resourceName.getBytes("UTF-8"),"ISO-8859-1")); 
		} catch (Exception e) {
			// TODO: handle exception
		}

		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
		
	}
	
	
	// �˻� �ϴ� ����
	@PostMapping("search")
	public String search(HttpServletRequest rq, RedirectAttributes at, Model model) {
		
		String value = rq.getParameter("SearchValue");
		
		System.out.println("�˻�: "+value);
		
		ArrayList<bbsDTO> dtoT = service.searchTitle(value); // �������� �˻��� ����� ����
		
		
		System.out.println(dtoT.isEmpty() == false); // dtoT��  ����ִ°� �����̶��(��ȯ���� �ִٸ�) true -> dtoT != null �� ����
		
		if(dtoT.isEmpty() == false) { //dtoT�� ���� null�� �ƴ϶��
			
			at.addFlashAttribute("searchValue", dtoT); //dtoT�� ���� �����ϱ� ������ ����.
			System.out.println("�۵��˸�: dtoT ��ȯ");
			
		}else if(dtoT.isEmpty()) { // ������ ���࿡ dtoT�� ����ִٸ�
			
			ArrayList<bbsDTO> dtoC = service.searchContent(value);// dtoC�� �����
			
			if(dtoC.isEmpty() == false) { //  ���� dtoC�� ���� null�� �ƴ϶��
				
				at.addFlashAttribute("searchValue", dtoC); // dtoC�� ���� �����ϱ� ������ ����.
				System.out.println("�۵��˸�: dtoC��ȯ");
				
			}else if(dtoC.isEmpty()) { //dtoC�� ���� ����ִٸ� ������ ����.
				
				at.addFlashAttribute("error", "e");
				System.out.println("�۵��˸�: error ��ȯ");
			}
		}
		
		return "redirect:/";
	}
	
	//��� �ޱ�.
	@PostMapping("reply")
	@ResponseBody
	public void reply(HttpServletRequest rq) throws UnsupportedEncodingException {
		
	   String username = rq.getParameter("username");
	   String nickname = rq.getParameter("nickname");
	   String rnum = rq.getParameter("rnum");
	   String rcomment = rq.getParameter("rcomment");
	   
	   System.out.println("Ajax �˸�@");
	   System.out.println("�ۼ���: "+username);
	   System.out.println("�г���: "+nickname);
	   System.out.println("��� ��ȣ: "+rnum);
	   System.out.println("��� ����: "+rcomment);
		
	   service.reply(nickname, username, rnum, rcomment);
	}
	
	//��� ����
	@PostMapping("saveRecomment")
	@ResponseBody
	public void SaveRecomment(HttpServletRequest rq, RereplyDTO reDTO) {
		
		System.out.println("Ajax �˸� @");
		
		String reNum = rq.getParameter("replynum");
		String reName = rq.getParameter("replyname");
		String reNickname = rq.getParameter("replynickname");
		String rePostnum = rq.getParameter("replypostnum");
		String reComment = rq.getParameter("replycomment");
		
		System.out.println("�θ��� ��ȣ: "+reNum); // �ش� �Խù� �ȿ� �ִ� �θ� ��� ��ȣ
		System.out.println("�Խù� ��ȣ: "+reName);// ������ �����ϱ� ���� �Խù� ��ȣ
		System.out.println("�ۼ��� �̸�: "+reNickname); //���� �̸�
		System.out.println("�ۼ��� �г���: "+rePostnum);// �г���
		System.out.println("���� ����: "+ reComment);// ���� ����
		
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

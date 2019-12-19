package CustomHandler;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.Develop.ex_VO.MemberVO;

public class CustomUser extends User{ // UserDetails�� ������ User Ŭ������ ��ӹ޾Ƽ� ó���ϸ� �ȴ�.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MemberVO member;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority>authorities, String nickname) {
		super(username, password, authorities);
		
	}

	public CustomUser(MemberVO vo) {
		
		super(vo.getUserid(), vo.getUserpw(), vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuthority())).collect(Collectors.toList()));
		
		//this.member = vo;
		setMember(vo);
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}
	
	
}

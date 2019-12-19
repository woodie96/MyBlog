package CustomHandler;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Develop.ex_DAO.MemberMapper;
import com.Develop.ex_VO.MemberVO;

public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private SqlSession sqlSession;
	
	private MemberMapper mapper;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	//����� ����Ϸ��� myBatis�� ȸ��ó�� �� ����(MemberVO)�� UserDetails�� ��ȯ�ؾ� �Ѵ�.	
		
		System.out.println("userName: "+userName);
		
		mapper = sqlSession.getMapper(MemberMapper.class);
		
		MemberVO vo = mapper.read(userName);
		
		
		System.out.println("VO üũ: "+vo);
		System.out.println(vo.getUserName());
	
		
		return vo == null ? null : new CustomUser(vo) ; //���� Ÿ���� UserDetails
		
	}
	
}

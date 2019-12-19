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
	//제대로 사용하려면 myBatis로 회원처리 한 정보(MemberVO)를 UserDetails로 변환해야 한다.	
		
		System.out.println("userName: "+userName);
		
		mapper = sqlSession.getMapper(MemberMapper.class);
		
		MemberVO vo = mapper.read(userName);
		
		
		System.out.println("VO 체크: "+vo);
		System.out.println(vo.getUserName());
	
		
		return vo == null ? null : new CustomUser(vo) ; //리턴 타입은 UserDetails
		
	}
	
}

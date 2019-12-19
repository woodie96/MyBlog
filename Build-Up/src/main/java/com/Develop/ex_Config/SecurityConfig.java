package com.Develop.ex_Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import CustomHandler.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DriverManagerDataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder pwen;
	
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		
		auth.userDetailsService(customUserDetailsService()).passwordEncoder(pwen);
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select userid, userpw, enabled from member where userid = ?")
			.authoritiesByUsernameQuery("select userid, authority from authorities where userid = ?");
		
//		String password = passwordEncoder().encode("1234");
//
//		auth.inMemoryAuthentication()
//			.withUser("admin").password(password).roles("ADMIN");
//		auth.inMemoryAuthentication()
//			.withUser("member").password(password).roles("MEMBER");
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/Boradlist").permitAll();
		http.authorizeRequests().antMatchers("/join").permitAll();
		http.authorizeRequests().antMatchers("/LoginPage").permitAll();
		http.authorizeRequests().antMatchers("/PostModifyPage").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/PostWritePage").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/writeview").access("hasRole('ROLE_ADMIN')");
		http.authorizeRequests().antMatchers("/AboutPage").access("hasRole('ROLE_ADMIN')");
		
		//��� �޶� csrf ����ǰ� ����
		http.csrf().ignoringAntMatchers("/reply");
		http.csrf().ignoringAntMatchers("/saveRecomment");
		http.csrf().ignoringAntMatchers("/idcheck");
		http.csrf().ignoringAntMatchers("/nicknamech");
		
		http.exceptionHandling().accessDeniedPage("/errorpage");
		
		http.logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true).deleteCookies("remember-me", "JSESSION_ID");
		
		http.formLogin().loginPage("/LoginPage").loginProcessingUrl("/login");
		http.exceptionHandling().accessDeniedPage("/errorpage");
		
		//�̺κ� �߰�������� ������ ��ť��Ƽ csrf��������� �ѱ� �ȱ���
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		http.addFilterBefore(filter,CsrfFilter.class);

		
		
		
		//http.csrf().disable();//�׽�Ʈ �Ҷ��� �𽺿��̺�
		
	}
	
}

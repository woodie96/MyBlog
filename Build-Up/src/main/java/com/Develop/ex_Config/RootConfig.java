package com.Develop.ex_Config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.Develop.ex_DAO.DAO;
import com.Develop.ex_Service.Service;

import CustomHandler.CustomUserDetailsService;

@MapperScan(basePackages = {"com.Develop.ex_DAO"}) //@Select를 사용하기 위해선 존재해야됌
@Configuration
public class RootConfig { //RootConfig.xml과 같은 역할
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DAO dao() {
		return new DAO();
	}
	
	@Bean 
	public Service service() {
		return new Service();
	}
	
	@Bean
	public CustomUserDetailsService customUserDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		dataSource.setUsername("woody");
		dataSource.setPassword("root");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
		bean.setDataSource(dataSource());
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/Develop/ex_Mapper/*.xml"));
		return (SqlSessionFactory) bean.getObject();
	}
	
	@Bean
	public SqlSession sqlSession() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
}

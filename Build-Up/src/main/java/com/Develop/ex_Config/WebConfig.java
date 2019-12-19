package com.Develop.ex_Config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{ //wec.xml과 같음,
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class, SecurityConfig.class};
	}
	
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class [] {ServletConfig.class};
	}
	
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	//필터링
	@Override
	protected Filter[] getServletFilters() { // 한글깨짐에 대한 필터추가 단계
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		// .setEncoding()에 값이 입력되고 .setForceEncoding()이 true이면 인코딩 값을 강제로 바꾸어준다.
		return new Filter[] {filter};
	}
	
	//파일 업로드 설정
	@Override
	protected void customizeRegistration(Dynamic registration) {
		
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
		MultipartConfigElement multipartConfig = new MultipartConfigElement("D:\\upload", 20971520, 41943040, 209715200);
		registration.setMultipartConfig(multipartConfig);
		
	}
	
}

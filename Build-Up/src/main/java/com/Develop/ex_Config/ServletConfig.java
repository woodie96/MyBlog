package com.Develop.ex_Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@ComponentScan(basePackages = {"com.Develop.ex_BuildUp"}) // 컨트롤러 위치 찾기.
@EnableWebMvc //Spring Mvc를 구성할때 필요한 Bean설정들을 자동으로 구성해줌
public class ServletConfig implements WebMvcConfigurer{ //Servlet-context.xml과 같음

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		registry.viewResolver(resolver);
	}
	
	@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
			// 리소스 파일에 접근할때 사용할 uri 					//리소스에 접근할때.						
			registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
			registry.addResourceHandler("/font/**").addResourceLocations("/resources/font/");
			registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
			registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
			registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
			
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:/upload/");
		}
	
	//파일 업로드에 필요한 MultipartResolver를  빈으로 추가해줘야한다고 함.
	@Bean
	public MultipartResolver multipartResolver() {
		
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
		
	}
	
	
	
	
	
	
	
	
	
}

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


@ComponentScan(basePackages = {"com.Develop.ex_BuildUp"}) // ��Ʈ�ѷ� ��ġ ã��.
@EnableWebMvc //Spring Mvc�� �����Ҷ� �ʿ��� Bean�������� �ڵ����� ��������
public class ServletConfig implements WebMvcConfigurer{ //Servlet-context.xml�� ����

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
			// ���ҽ� ���Ͽ� �����Ҷ� ����� uri 					//���ҽ��� �����Ҷ�.						
			registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
			registry.addResourceHandler("/font/**").addResourceLocations("/resources/font/");
			registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
			registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
			registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
			
			registry.addResourceHandler("/upload/**").addResourceLocations("file:///D:/upload/");
		}
	
	//���� ���ε忡 �ʿ��� MultipartResolver��  ������ �߰�������Ѵٰ� ��.
	@Bean
	public MultipartResolver multipartResolver() {
		
		StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
		return resolver;
		
	}
	
	
	
	
	
	
	
	
	
}

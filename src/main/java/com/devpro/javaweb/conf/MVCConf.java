package com.devpro.javaweb.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

//1. Báo cho spring-mvc biết đây là file cấu hình
//2. Báo cho spring biết đây là 1 BEAN
@Configuration
public class MVCConf implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		
		//ERROR: http://localhost:8080/css/styles.css 404
		//bất cứ request nào có dạng: /css/... sẽ vào folder src/main/resources/css
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
		
		//ERROR: http://localhost:8080/js/scripts.js 404
		//bất cứ request nào có dạng: /js/... sẽ vào folder src/main/resources/js
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
		
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
		registry.addResourceHandler("/upload/**").addResourceLocations("file:" + "E:/Java_web/upload/");		
		
	}
	// spring-mvc sẽ call hàm này
	// kết quả của hàm sẽ là 1 Bean và sẽ được lưu trong SrpingContainer
	@Bean
	public ViewResolver viewResolver() {

		// Class InternalResourceViewResolver implements ViewResolver
		InternalResourceViewResolver bean = new InternalResourceViewResolver();

		// thiết lập view engine
		bean.setViewClass(JstlView.class);

		// Đường dẫn thư mực gốc chứa Views.
		bean.setPrefix("/WEB-INF/views/");

		// Tên đuôi của View
		bean.setSuffix(".jsp");

		return bean;

	}
	
}

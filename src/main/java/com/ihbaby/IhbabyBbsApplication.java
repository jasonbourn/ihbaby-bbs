package com.ihbaby;

import com.ihbaby.config.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@SpringBootApplication
@EnableCaching
public class IhbabyBbsApplication {
	@Autowired
	private Environment env;
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		JwtFilter jwtFilter =new JwtFilter();
		jwtFilter.setEnvironment(env);
		registrationBean.setFilter(jwtFilter);
		registrationBean.addUrlPatterns("/app/*");

		return registrationBean;
	}
	public static void main(String[] args) {
		SpringApplication.run(IhbabyBbsApplication.class, args);
	}
}

package com.ihbaby.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import java.util.Locale;

@Configuration
public class BeanFactoryConfig {
	
	
	/** 国际化配置 **/
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource regMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames(new String[] { "classpath:messages/message", "classpath:messages/form" });
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean(name = "localeResolver")
	public FixedLocaleResolver localeResolver() {
		FixedLocaleResolver fix = new FixedLocaleResolver();
		fix.setDefaultLocale(Locale.CHINA);
		return fix;
	}
	
	@Bean(name = "validator")
	public LocalValidatorFactoryBean regValidator(ReloadableResourceBundleMessageSource messageSource) {
		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
		validator.setProviderClass(HibernateValidator.class);
		validator.setValidationMessageSource(messageSource);
		return validator;
	}

}

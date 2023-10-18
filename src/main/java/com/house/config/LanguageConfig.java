package com.house.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LanguageConfig {
    @Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver slr=new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.US);
		return slr;
	}

	@Bean
	public static ResourceBundleMessageSource messageSource(){
		ResourceBundleMessageSource rbm=new ResourceBundleMessageSource();
		rbm.setBasename("messages");
		rbm.setDefaultEncoding("utf-8");
		rbm.setUseCodeAsDefaultMessage(true);
		return rbm;
	}   
}

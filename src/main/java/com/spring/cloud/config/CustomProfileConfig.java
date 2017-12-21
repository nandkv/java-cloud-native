package com.spring.cloud.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.Bean;


public class CustomProfileConfig {
	
	@Bean
	public static BeanFactoryPostProcessor configurableEnvironmentProcessor() {
	    return new ConfigurableEnvironmentProcessor();
	}
	
}

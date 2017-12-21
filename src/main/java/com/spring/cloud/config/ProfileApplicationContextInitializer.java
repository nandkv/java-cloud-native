package com.spring.cloud.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class ProfileApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		try {
			String instance = getHostInstance();
			ConfigurableEnvironment env = applicationContext.getEnvironment();
			if ("private".equals(instance)){
				env.setActiveProfiles("private");
			} else {
				env.setActiveProfiles("public");
			}
		} catch (Exception e){
			System.out.println("Exception occurred:" + e);
		}
		
	}

	private String getHostInstance() throws IOException{
		Properties props = new Properties();
		InputStream input = new ClassPathResource("config.properties").getInputStream();
		props.load(input);
		return props.getProperty("instance");
	}

}

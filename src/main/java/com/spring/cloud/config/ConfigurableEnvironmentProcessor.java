package com.spring.cloud.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.env.ConfigurableEnvironment;

public class ConfigurableEnvironmentProcessor implements BeanFactoryPostProcessor{
	
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

		try {
			Properties prop = new Properties();
			InputStream input = getClass().getResourceAsStream("config.properties");
			prop.load(input);
			ConfigurableEnvironment env = beanFactory.getBean(ConfigurableEnvironment.class);
			if (prop.containsKey("message")){
				env.addActiveProfile("dev");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

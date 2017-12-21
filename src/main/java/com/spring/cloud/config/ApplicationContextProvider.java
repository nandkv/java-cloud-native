package com.spring.cloud.config;

import java.util.Arrays;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationListener<ContextRefreshedEvent> {

	private AnnotationConfigApplicationContext ctx;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		ctx = new AnnotationConfigApplicationContext();
		String[] profiles = ctx.getEnvironment().getActiveProfiles();
		if (!Arrays.asList(profiles).contains("dev")) {
			ctx.getEnvironment().addActiveProfile("dev");
			System.out.println("Added dev profile");
			ctx.refresh();
		} else {
			System.out.println("Profile dev already present");
		}
	}
}

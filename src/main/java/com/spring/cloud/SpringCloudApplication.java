package com.spring.cloud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import com.spring.cloud.config.ProfileApplicationContextInitializer;

@SpringBootApplication
public class SpringCloudApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SpringCloudApplication.class)
				.initializers(new ProfileApplicationContextInitializer()).run(args);
	}
}

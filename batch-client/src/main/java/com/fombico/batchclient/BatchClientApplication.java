package com.fombico.batchclient;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@EnableScheduling
@EnableOAuth2Client
@SpringBootApplication
public class BatchClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchClientApplication.class, args);
	}

	@Bean
	public OAuth2RestOperations restOperations(@Qualifier("oauth2ClientContext") OAuth2ClientContext oauth2ClientContext,
											   OAuth2ProtectedResourceDetails details) {
		return new OAuth2RestTemplate(details, oauth2ClientContext);
	}
}

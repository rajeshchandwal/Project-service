package com.teksAcademy.springDemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

import com.teksAcademy.springDemo.propertiesConfig.PropertiesConfig;

import reactor.core.publisher.*;



@Configuration
public class WebClientConfiguration {

	
	private final PropertiesConfig propertiesConfig;	
	
	@Autowired
	public WebClientConfiguration(PropertiesConfig propertiesConfig) {
		
		this.propertiesConfig = propertiesConfig;
	}

   @Bean
	public WebClient webClientCreation(WebClient.Builder webClientBuilder) {
		
		return webClientBuilder.filter(setJWT())
				.baseUrl(propertiesConfig.getWebClientBaseUrl()).build();
	}
   
   private ExchangeFilterFunction setJWT() {
       return ExchangeFilterFunction.ofRequestProcessor((clientRequest) -> {
           ClientRequest authorizedRequest = ClientRequest.from(clientRequest).header("AUTHORIZATION","bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJyYWplc2giLCJpYXQiOjE3NDM1MDI2NTh9.M8UOUvLo8bqT4Agypm4snFPFru1MABfVi8oCLx1OEK8k9ZvnX2sg_QdEz1dV2jJH").build();
           return Mono.just(authorizedRequest);
       });
   }
}

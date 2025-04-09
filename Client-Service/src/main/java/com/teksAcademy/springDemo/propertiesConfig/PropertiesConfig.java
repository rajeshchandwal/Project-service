package com.teksAcademy.springDemo.propertiesConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class PropertiesConfig {
	private String webClientBaseUrl;

	public String getWebClientBaseUrl() {
		return webClientBaseUrl;
	}

	@Value("${webClient.baseUrl}")
	public void setWebClientBaseUrl(String webClientBaseUrl) {
		this.webClientBaseUrl = webClientBaseUrl;
	}
	
	

}

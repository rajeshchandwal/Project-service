package com.example.api_gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.boot.logging.LoggingSystemFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

public class LoggingFilter implements GlobalFilter{
	
private Logger    logger = LoggerFactory.getLogger(LoggingSystemFactory.class);
	
	//inforation about request present in exchange
	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.info("path of the request received -> {}", exchange.getRequest().getPath());
		System.out.println("API gateway Call");
		return chain.filter(exchange);
	}

}

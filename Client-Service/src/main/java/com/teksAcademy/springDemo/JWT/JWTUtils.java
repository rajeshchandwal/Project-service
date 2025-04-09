package com.teksAcademy.springDemo.JWT;



import java.security.Key;
import java.util.Base64.Decoder;
import java.util.Date;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class JWTUtils {
	private static final Logger logger = LoggerFactory.getLogger(JWTUtils.class);
	
	@Value("${spring.app.jwtSecrete}")
	private String jwtSecret;
	
	@Value("${spring.app.jwtExpirationMns}")
	private String jwtExpirationMns;
	
	public String getJwtFromHeader(HttpServletRequest request) {
		System.out.println("inside getJwtFromHeader");
		String bearerToken = request.getHeader("Authorization");
		System.out.println("bearerToken "+bearerToken);
		logger.debug("Authorization Header: {}", bearerToken);
		if(bearerToken != null && bearerToken.startsWith("bearer ")) {
			return bearerToken.substring(7); //remove bearer prefix
		}
		return null;
	}
	
	public String generateTokenFromUserName(UserDetails userDetails) {
		String userName = userDetails.getUsername();
		return Jwts.builder()
				.setSubject(userName)
				.setIssuedAt(new Date())
				//.setExpiration(new Date((new Date()).getTime()+jwtExpirationMns))
				.signWith(key())
				.compact();
		
	}
	
	public String getUserNameFromJwtToken(String token) {
		
		
		return Jwts.parser()
				  .setSigningKey(key())
				  .parseClaimsJws(token)
				  .getBody()
				  .getSubject();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode("ddfdsfdsfdffdvd26352653653vv2378687vgcfgcgcfcgfgfcfcfxfdxgffgfytfcgfcgcgfcg"));
	}
	
	public boolean validateJwtToken(String authToken) {
		
		try {
			Jwts.parserBuilder()
	        .setSigningKey(key())
	        .build()
	        .parseClaimsJws(authToken);
			return true;
			
		}catch(MalformedJwtException e) {
			logger.error("Invalid Jwt token: {}",e.getMessage());
		}catch(IllegalArgumentException e) {
			logger.error("Invalid Jwt token: {}",e.getMessage());
		}
		return false;
		
	}
	

}

package com.teksAcademy.springDemo.response;

import java.util.HashMap;
import java.util.Map;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;


public class ResponseHandler {
	
	public static ResponseEntity<Object> responseBuilder(String message
			,HttpStatusCode httpStatus,Object objResponse){
		
		Map<String, Object> response = new HashMap<>();
		
		response.put("message", message);
		response.put("status", httpStatus);
		response.put("data", objResponse);
		return new ResponseEntity<Object>(response,httpStatus);
		
	}

}

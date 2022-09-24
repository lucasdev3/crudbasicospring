package com.lucasdev3.crudbasicospring.responsesmodels;

import java.util.Objects;

import org.springframework.http.HttpStatus;

public class ResponseModel {
	
	private Integer statusCode;
	
	private HttpStatus message;
	
	private Object contentBodyResponse;

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public HttpStatus getMessage() {
		return message;
	}

	public void setMessage(HttpStatus message) {
		this.message = message;
	}

	public Object getContentBodyResponse() {
		return contentBodyResponse;
	}

	public void setContentBodyResponse(Object contentBodyResponse) {
		this.contentBodyResponse = contentBodyResponse;
	}

	@Override
	public int hashCode() {
		return Objects.hash(contentBodyResponse, message, statusCode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseModel other = (ResponseModel) obj;
		return Objects.equals(contentBodyResponse, other.contentBodyResponse) && Objects.equals(message, other.message)
				&& Objects.equals(statusCode, other.statusCode);
	}
	
	

}

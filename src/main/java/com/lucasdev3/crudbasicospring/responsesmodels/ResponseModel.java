package com.lucasdev3.crudbasicospring.responsesmodels;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

public class ResponseModel implements Serializable {
	public static final long serialVersionUID = 1L;
	
	private Integer statusCode;
	
	private HttpStatus message;
	
	private Object contentBodyResponse = null;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResponseModel that = (ResponseModel) o;

		if (!Objects.equals(statusCode, that.statusCode)) return false;
		if (message != that.message) return false;
		return Objects.equals(contentBodyResponse, that.contentBodyResponse);
	}

	@Override
	public String toString() {
		return "ResponseModel{" +
				"statusCode=" + statusCode +
				", message=" + message +
				", contentBodyResponse=" + contentBodyResponse +
				'}';
	}
}

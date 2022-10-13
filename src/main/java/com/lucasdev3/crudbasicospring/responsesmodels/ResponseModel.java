package com.lucasdev3.crudbasicospring.responsesmodels;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.Objects;

public class ResponseModel implements Serializable {
	public static final long serialVersionUID = 1L;

	private HttpStatus message;
	
	private Object contentBodyResponse = null;

	private String responseDescription;

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

	public String getResponseDescription() {
		return responseDescription;
	}

	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ResponseModel that = (ResponseModel) o;

		if (message != that.message) return false;
		if (!Objects.equals(contentBodyResponse, that.contentBodyResponse))
			return false;
		return Objects.equals(responseDescription, that.responseDescription);
	}

	@Override
	public int hashCode() {
		int result = message != null ? message.hashCode() : 0;
		result = 31 * result + (contentBodyResponse != null ? contentBodyResponse.hashCode() : 0);
		result = 31 * result + (responseDescription != null ? responseDescription.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ResponseModel{" +
				"message=" + message +
				", contentBodyResponse=" + contentBodyResponse +
				", responseDescription='" + responseDescription + '\'' +
				'}';
	}
}

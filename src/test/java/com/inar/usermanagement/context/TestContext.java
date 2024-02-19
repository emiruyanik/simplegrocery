package com.inar.usermanagement.context;

import io.restassured.response.Response;

public class TestContext {

	private Response response;

	public void setResponse(Response response) {
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}

}

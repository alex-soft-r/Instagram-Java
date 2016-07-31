package com.sola.instagram.io;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.InputStream;


public class GetMethod extends APIMethod {
	
	
	public GetMethod() {
		super();
		this.type = "GET";
	}
	
	public GetMethod(String methodUri) {
		super(methodUri);
	}	
	
	@Override
	protected InputStream performRequest() {
		Response response;
		InputStream stream = null;
		try {
			Request request = new Request.Builder()
				.url(this.methodUri)
				.build();
			response = client.newCall(request).execute();
			stream = response.body().byteStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}
}

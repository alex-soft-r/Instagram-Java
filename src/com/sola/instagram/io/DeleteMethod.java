package com.sola.instagram.io;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.InputStream;

public class DeleteMethod extends APIMethod {
	
	public DeleteMethod() {
		super();
	}
	
	public DeleteMethod(String methodUri) {
		super(methodUri);
		this.type = "DELETE";
	}		
	
	@Override
	protected InputStream performRequest() throws Exception {
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

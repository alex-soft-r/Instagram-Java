package com.sola.instagram.io;

import com.squareup.okhttp.*;

import java.io.InputStream;
import java.util.Map;


public class PostMethod extends APIMethod {
	Map<String, Object> postParameters;
//	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	//application/x-www-form-urlencoded
//	public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

	public PostMethod() {
		super();
		this.type = "POST";
	}

	public PostMethod(String methodUri) {
		super(methodUri);
		this.type = "POST";
	}

	@Override
	protected InputStream performRequest() {
		Response response;
		FormEncodingBuilder formBody = new FormEncodingBuilder();
		for (Map.Entry<String, Object> arg : getPostParameters().entrySet()) {
			formBody.add(arg.getKey(), arg.getValue().toString());
		}
		RequestBody requestBody = formBody.build();
		InputStream stream = null;
		try {
			Request request = new Request.Builder()
					.url(this.methodUri)
					.post(requestBody)
					.build();
			response = client.newCall(request).execute();
			stream = response.body().byteStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stream;
	}

	
	public Map<String, Object> getPostParameters() {
		return postParameters;
	}


	public PostMethod setPostParameters(Map<String, Object> postParameters) {
		this.postParameters = postParameters;
		return this;
	}

}

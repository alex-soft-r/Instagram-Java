package com.sola.instagram.io;


import com.squareup.okhttp.OkHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;

public abstract class APIMethod {
	String methodUri;
	String type;
	String accessToken;
	static String proxyAddress;
	static int proxyPort;
	OkHttpClient client;

	abstract protected InputStream performRequest() throws Exception;
	
	public APIMethod() {
		client = new OkHttpClient();
		if(APIMethod.hasProxy()) {
			System.out.println("using proxy -> " + APIMethod.proxyAddress + ":" + APIMethod.proxyPort);
//			HttpHost proxy = new HttpHost(APIMethod.proxyAddress, APIMethod.proxyPort, "http");
//			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			client.setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(APIMethod.proxyAddress, APIMethod.proxyPort)));
		}
	}
	
	public APIMethod(String methodUri) {
		this();
		setMethodURI(methodUri);
	}
	
	public static void setProxy(String proxyAddress, int proxyPort) {
		APIMethod.proxyAddress = proxyAddress;
		APIMethod.proxyPort    = proxyPort;
	}	

	public static void removeProxy() {
		APIMethod.proxyAddress = null;
	}	
	
	public RequestResponse call() throws Exception {
		System.out.println(this.methodUri);
		StringBuilder sb  = new StringBuilder();
		InputStream response = performRequest();
		BufferedReader rd = new BufferedReader(new InputStreamReader(response));
		String chunk;
		while ((chunk = rd.readLine()) != null) {
			sb.append(chunk);
		}
		if(response!=null)
			response.close();
		return new RequestResponse(sb.toString());
	}
	
	public static Boolean hasProxy() {
		return proxyAddress != null;
	}
	
	public String getType() {
		return type;
	}

	public String getMethodUri() {
		return methodUri;
	}

	public APIMethod setMethodURI(String methodURI) {
		this.methodUri = methodURI;
		return this;
	}
}
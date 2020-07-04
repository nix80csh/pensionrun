package com.pensionrun.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class EmailValidUtil {
	public static final String emailApi_key = "5728086d813f05728086d8142c5728086d814685728086d814a35728086d814e6";

	public static String checkValid(String email) {
		Unirest.setHttpClient(createHttpClient_AcceptsUntrustedCerts());
		HttpResponse<JsonNode> response;
		try {
			response = Unirest.get("https://captainverify.com/api/v1/verify?email=" + email + "&timeout=3&language=ko")
					.header("X-CaptainVerify-Key", emailApi_key).header("Accept", "application/json").asJson();
			String status = (String) response.getBody().getObject().get("status");
			Boolean success = (Boolean) response.getBody().getObject().get("success");
			Boolean role = (Boolean) response.getBody().getObject().get("role");
			Boolean risky = (Boolean) response.getBody().getObject().get("risky");
			String resultCode = "";			
			if (success == true && status.equals("valid") && role == false && risky == false) {
				resultCode = "S";
			} else {
				resultCode = "F";
			}
			return resultCode;
		} catch (UnirestException e) {
			return null;
		}		
	}

	private static HttpClient createHttpClient_AcceptsUntrustedCerts() {
		HttpClientBuilder b = HttpClientBuilder.create();
		SSLContext sslContext;
		try {
			sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
					return true;
				}
			}).build();

			b.setSslcontext(sslContext);
			X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.getSocketFactory())
					.register("https", sslSocketFactory).build();
			PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			b.setConnectionManager(connMgr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpClient client = b.build();
		return client;
	}

}

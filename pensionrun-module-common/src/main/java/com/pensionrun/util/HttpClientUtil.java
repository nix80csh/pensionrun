package com.pensionrun.util;

import java.util.List;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static String sendHttpClientPost(String url,List <NameValuePair> param) {
		// TODO Auto-generated method stub
		String response="";
		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
            HttpPost httpost=new HttpPost(url);
            httpost.setEntity(new UrlEncodedFormEntity(param, Consts.UTF_8));
			response= EntityUtils.toString(httpclient.execute(httpost).getEntity());
		} catch (Exception e) {
		}
		return response;
	}
	public static String sendHttpClientGet(String url) {
		// TODO Auto-generated method stub
		String response="";
		HttpClient httpclient = HttpClientBuilder.create().build();
		try {
            HttpGet httpost=new HttpGet(url);
			response= EntityUtils.toString(httpclient.execute(httpost).getEntity());
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			logger.warn("msg",e);
		}
		return response;
	}
}

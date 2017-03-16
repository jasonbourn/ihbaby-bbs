package com.ihbaby.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class HttpClientUtils {
	
	
	protected static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
	
	/**
	 * 连接超时时间
	 */
	public static final int CONNECTION_TIMEOUT_MS = 360000;
	
	/**
	 * 读取数据超时时间
	 */
	public static final int SO_TIMEOUT_MS = 360000;
	
	private static final CloseableHttpClient httpClient;
	
	public static final String CHARSET = "UTF-8";
	
	static {
		RequestConfig config = RequestConfig.custom().setConnectTimeout(CONNECTION_TIMEOUT_MS).setSocketTimeout(SO_TIMEOUT_MS).build();
		httpClient = HttpClientBuilder.create().setDefaultRequestConfig(config).build();
	}
	
	public static String doGet(String url, Map<String, String> params) {
		return doGet(url, params, CHARSET);
	}
	
	public static String doPost(String url, Map<String, String> params) {
		return doPost(url, params, CHARSET);
	}
	
	/**
	 * HTTP Get 获取内容
	 * 
	 * @param url 请求的url地址 ?之前的地址
	 * @param params 请求的参数
	 * @param charset 编码格式
	 * @return 页面内容
	 */
	public static String doGet(String url, Map<String, String> params, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
				for (Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
				url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
			}
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response = httpClient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpGet.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			logger.error("doGet error, url : {}, params : {}", url, params);
		}
		return null;
	}
	
	/**
	 * HTTP Post 获取内容
	 * 
	 * @param url 请求的url地址 ?之前的地址
	 * @param params 请求的参数
	 * @param charset 编码格式
	 * @return 页面内容
	 */
	public static String doPost(String url, Map<String, String> params, String charset) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		try {
			List<NameValuePair> pairs = null;
			if (params != null && !params.isEmpty()) {
				pairs = new ArrayList<NameValuePair>(params.size());
				for (Entry<String, String> entry : params.entrySet()) {
					String value = entry.getValue();
					if (value != null) {
						pairs.add(new BasicNameValuePair(entry.getKey(), value));
					}
				}
			}
			HttpPost httpPost = new HttpPost(url);
			if (pairs != null && pairs.size() > 0) {
				httpPost.setEntity(new UrlEncodedFormEntity(pairs, CHARSET));
			}
			System.out.println(httpPost.toString());
			CloseableHttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				httpPost.abort();
				throw new RuntimeException("HttpClient,error status code :" + statusCode);
			}
			HttpEntity entity = response.getEntity();
			String result = null;
			if (entity != null) {
				result = EntityUtils.toString(entity, "utf-8");
			}
			EntityUtils.consume(entity);
			response.close();
			return result;
		} catch (Exception e) {
			logger.error("doPost error, url : {}, params : {}", url, params);
		}
		return null;
	}
}

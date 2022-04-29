package com.soft.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.soft.config.CommonConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Http接口请求
 *
 * @author xuhf
 */
public class HttpClientUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

	/**
	 * JSON格式请求
	 *
	 * @param httpClient
	 * @param url
	 * @param json
	 * @return
	 */
	public static JSONObject doPostJson(CloseableHttpClient httpClient, String url, JSONObject json) {
		return doPostJson(httpClient, url, json, null);
	}

	public static JSONObject doPostJson(CloseableHttpClient httpClient, String url, JSONObject json, Map<String, String> headers) {
		CloseableHttpResponse r = null;
		HttpEntity entity = null;
		try {
			// 设置参数
			StringEntity s = new StringEntity(json.toJSONString(), CommonConfig.CHARSET);
			s.setContentEncoding(CommonConfig.CHARSET);
			s.setContentType("application/json");
			HttpPost post = new HttpPost(url);
			post.setEntity(s);
			post.addHeader("content-type", "application/json;CHARSET=UTF-8");
			if (headers != null) {
				for (String key : headers.keySet()) {
					post.addHeader(key, headers.get(key));
				}
			}
			r = httpClient.execute(post);
			int statusCode = r.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				entity = r.getEntity();
				String response = EntityUtils.toString(entity, CommonConfig.CHARSET);
				return JSONObject.parseObject(response);
			} else {
				logger.error("url : " + url + ", parameters : " + json + ", response statusCode : " + statusCode);
				return null;
			}
		} catch (Exception e) {
			logger.error("url : " + url + ", parameters : " + json, e);
		} finally {
			try {
				EntityUtils.consume(entity);
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				logger.error("url : " + url + ", parameters : " + json, e);
			}
		}
		return null;
	}

	/**
	 * form表单格式
	 * <p>
	 * 以form表单的形式提交
	 *
	 * @param httpClient
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JSONObject doPostForm(CloseableHttpClient httpClient, String url, Map<String, Object> parameters) {
		CloseableHttpResponse r = null;
		HttpEntity entity = null;
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		for (String k : parameters.keySet()) {
			NameValuePair p = new BasicNameValuePair(k, (String) parameters.get(k));
			params.add(p);
		}
		try {
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			post.setHeader("content-type", "application/x-www-form-urlencoded; charset=utf-8");
			post.setHeader("charset", "utf-8");
			r = httpClient.execute(post);
			int statusCode = r.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				entity = r.getEntity();
				String response = EntityUtils.toString(entity, CommonConfig.CHARSET);
				return JSONObject.parseObject(response);
			} else {
				logger.error("url : " + url + ", parameters : " + parameters + ", response statusCode : " + statusCode);
				return null;
			}
		} catch (Exception e) {
			logger.error("url : " + url + ", parameters : " + parameters, e);
		} finally {
			try {
				EntityUtils.consume(entity);
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				logger.error("url : " + url + ", parameters : " + parameters, e);
			}
		}
		return null;
	}


	/**
	 * Get请求
	 *
	 * @param httpClient
	 * @param url
	 * @param parameters
	 * @return
	 */
	public static JSONObject doGet(CloseableHttpClient httpClient, String url, Map<String, Object> parameters) {
		Map<String, String> headers = Maps.newHashMap();
		headers.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
		return doGet(httpClient, url, parameters, headers);
	}

	/**
	 * 特定Get请求
	 *
	 * @param httpClient
	 * @param url
	 * @param parameters
	 * @param headers
	 * @return
	 */
	public static JSONObject doGet(CloseableHttpClient httpClient, String url, Map<String, Object> parameters,
								   Map<String, String> headers) {
		CloseableHttpResponse r = null;
		HttpEntity entity = null;
		List<String> list = Lists.newArrayList();
		for (String key : parameters.keySet()) {
			list.add(key + "=" + parameters.get(key));
		}
		try {
			HttpGet get = new HttpGet(url + "?" + Joiner.on("&").join(list));
			if (headers != null) {
				for (String key : headers.keySet()) {
					get.setHeader(key, headers.get(key));
				}
			}
			r = httpClient.execute(get);
			int statusCode = r.getStatusLine().getStatusCode();
			if (HttpStatus.SC_OK == statusCode) {
				entity = r.getEntity();
				String response = EntityUtils.toString(entity, CommonConfig.CHARSET);
				return JSONObject.parseObject(response);
			} else {
				logger.error("请求地址 : " + url + ", 参数 : " + parameters + "消息头 : " + headers
						+ ", HTTP响应状态码 : " + statusCode);
				return null;
			}
		} catch (Exception e) {
			logger.error("请求地址 : " + url + ", 参数 : " + parameters + ", 消息头 : " + headers, e);
		} finally {
			try {
				EntityUtils.consume(entity);
				if (r != null) {
					r.close();
				}
			} catch (IOException e) {
				logger.error("请求地址 : " + url + ", 参数 : " + parameters + ", 消息头 : " + headers, e);
			}
		}
		return null;
	}
}

package com.james.tvplay.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * 解析URL的工具类
 * 
 * @author james
 *
 */
@SuppressWarnings("deprecation")
public class HttpUtil {

	// 下载JSON的工具方法
	public static String loadJsonFromNet(String url, String method) throws Exception {
		String str = null;

		// 创建一个HttpClient对象
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request = null;
		if ("get".equalsIgnoreCase(method)) {// 如果是get请求
			// 创建一个get请求
			request = new HttpGet(url);
		} else if ("post".equalsIgnoreCase(method)) {// 如果是post请求
			request = new HttpPost(url);
		}

		// 执行请求
		HttpResponse response = client.execute(request);
		// 2xx:标示请求成功;3xx:重定向;4xx:客户端错误;5xx:服务器端错误
		if (response.getStatusLine().getStatusCode() == 200) {
			// 得到一个网络实体实体.
			HttpEntity entity = response.getEntity();
			// 如果要返回流
			// InputStream content = entity.getContent();
			str = EntityUtils.toString(entity);
		}

		return str;

	}

	// 下载图片的工具方法
	public static byte[] loadImgFromNet(String url, String method) throws Exception {
		// 创建一个HttpClient对象
		HttpClient client = new DefaultHttpClient();
		HttpUriRequest request = null;
		if ("get".equalsIgnoreCase(method)) {// 如果是get请求
			// 创建一个get请求
			request = new HttpGet(url);
		} else if ("post".equalsIgnoreCase(method)) {// 如果是post请求
			request = new HttpPost(url);
		}

		// 执行请求
		HttpResponse response = client.execute(request);
		// 2xx:标示请求成功;3xx:重定向;4xx:客户端错误;5xx:服务器端错误
		if (response.getStatusLine().getStatusCode() == 200) {
			// 得到一个网络实体实体.
			HttpEntity entity = response.getEntity();
			// 如果要返回流
			// InputStream content = entity.getContent();
			return EntityUtils.toByteArray(entity);
		}

		return null;

	}

}

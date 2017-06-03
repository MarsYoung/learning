package com.marsyoung.crawler;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


@SuppressWarnings("deprecation")
public class HttpUtils {
	private static final Log log =LogFactory.getLog(HttpUtils.class);
	private static final int TIME_OUT = 180 * 1000; // 设置的http请求的超时时间
	private static final String ENCODING = "UTF-8"; // 默认的响应内容文本的编码格式
	public static boolean LOCAL_PROXY = false;
	/**
	 * 得到一个URL的响应内容文本
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static String getResponseTextFromURL(String url, String encoding)
			throws ClientProtocolException, IOException {
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT);
		log.info(url);
		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);
		String result=EntityUtils.toString(response.getEntity(), encoding);
		log.info(result);
		return result;
	}

	public static String getResponseTextFromURL(String url)
			throws ClientProtocolException, IOException {
		return getResponseTextFromURL(url, ENCODING);
	}

	/**
	 * 得到一个URL的响应二进制数据
	 * 
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static byte[] getResponseBytesFromURL(String url)
			throws IllegalStateException, IOException {
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT);

		HttpGet get = new HttpGet(url);

		HttpResponse response = client.execute(get);

		return IOUtils.toByteArray(response.getEntity().getContent());
	}

	/**
	 * 获取一个URL表示的静态资源的大小，单位为字节
	 * 
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	public static int getURLResourceSize(String url)
			throws ClientProtocolException, IOException {
		final int TIME_OUT = 60 * 1000; // 设置的http请求的超时时间
		@SuppressWarnings("resource")
		HttpClient client = new DefaultHttpClient();
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, TIME_OUT);
		HttpConnectionParams.setSoTimeout(params, TIME_OUT);

		HttpHead head = new HttpHead(url);
		HttpResponse response = client.execute(head);
		return Integer.parseInt(response.getFirstHeader("Content-Length")
				.getValue());
	}
	
	/**
	 * URL编码
	 */
	public static String urlEncode(String url) {
		try {
			System.out.println(url);
			url = java.net.URLEncoder.encode(url, "UTF-8");
			url = url.replaceAll("%2F", "/");
			url = url.replaceAll("%3A", ":");
			url = url.replaceAll("\\+", "%20");
			System.out.println(url);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return url;
	}

	
	public static String get(String url, Map<String, String> header) throws IOException {
		HttpClient client = new DefaultHttpClient();
		if (LOCAL_PROXY) {
			HttpUtils.setHttpClientProxy(client, new HttpHost("localhost", 9977));
		}
		HttpUtils.setHttpClientTimeout(client, TIME_OUT);
		HttpGet get = new HttpGet(url);
		HttpUtils.setHeader(get, header);
		try {
			return HttpUtils.getReturnStr(client, get, null);
		} finally {
			HttpUtils.close(client);
		}
	}
	
	
	public static String genGetUrl(String url, Map<String, String> param) throws ParseException, IOException {
		if (param == null) {
			return url;
		}
		return url + "?" + HttpUtils.genGetParam(param, "UTF-8");
	}

	public static String get(String url, Map<String, String> header, Map<String, String> param) throws IOException {
		HttpClient client = new DefaultHttpClient();
		if (LOCAL_PROXY) {
			HttpUtils.setHttpClientProxy(client, new HttpHost("localhost", 9977));
		}
		HttpUtils.setHttpClientTimeout(client, TIME_OUT);
		HttpGet get = new HttpGet(genGetUrl(url, param));
		HttpUtils.setHeader(get, header);
		try {
			return HttpUtils.getReturnStr(client, get, null);
		} finally {
			HttpUtils.close(client);
		}
	}

	public static String post(String url, Map<String, String> header, Map<String, String> params) throws IOException {
		HttpClient client = new DefaultHttpClient();
		if (LOCAL_PROXY) {
			HttpUtils.setHttpClientProxy(client, new HttpHost("localhost", 9977));
		}
		HttpUtils.setHttpClientTimeout(client, TIME_OUT);
		HttpPost post = new HttpPost(url);
		HttpUtils.setHeader(post, header);
		HttpUtils.setPostEntity(post, params, "UTF-8");
		try {
			return HttpUtils.getReturnStr(client, post, null);
		} finally {
			HttpUtils.close(client);
		}
	}

	public static String postStringEntity(String url, Map<String, String> header, String params) throws IOException {
		HttpClient client = new DefaultHttpClient();
		if (LOCAL_PROXY) {
			HttpUtils.setHttpClientProxy(client, new HttpHost("localhost", 9977));
		}
		HttpUtils.setHttpClientTimeout(client, TIME_OUT);
		HttpPost post = new HttpPost(url);
		HttpUtils.setHeader(post, header);
		post.setEntity(new StringEntity(params, "UTF-8"));
		try {
			return HttpUtils.getReturnStr(client, post, null);
		} finally {
			HttpUtils.close(client);
		}
	}

	/**
	 * 设置连接和发送的超时时间
	 * 
	 * @param client
	 */
	public static void setHttpClientTimeout(HttpClient client) {
		setHttpClientTimeout(client, TIME_OUT);
	}

	public static void setHttpClientTimeout(HttpClient client, int timeout) {
		HttpParams params = client.getParams();
		HttpConnectionParams.setConnectionTimeout(params, timeout * 1000);
		HttpConnectionParams.setSoTimeout(params, timeout * 1000);
	}

	/**
	 * 插入通用header
	 * 
	 * @param httpRequestBase
	 */
	public static void setCommonHeader(HttpRequestBase httpRequestBase) {
		httpRequestBase.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpRequestBase.setHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
		httpRequestBase.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
		httpRequestBase.setHeader("Cache-Control", "no-cache");
		httpRequestBase.setHeader("Connection", "keep-alive");
		httpRequestBase
				.setHeader("User-Agent",
						"Mozilla/5.0 (xp NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1");
	}

	/**
	 * 插入header
	 * 
	 * @param httpRequestBase
	 * @param referer
	 * @param origin
	 * @param host
	 * @param contentType
	 */
	public static void setHeader(HttpRequestBase httpRequestBase, String referer, String origin, String host,
			String contentType) {
		setCommonHeader(httpRequestBase);
		if (StringUtils.isNotBlank(referer)) {
			httpRequestBase.setHeader("Referer", referer);
		}
		if (StringUtils.isNotBlank(origin)) {
			httpRequestBase.setHeader("Origin", origin);
		}
		if (StringUtils.isNotBlank(host)) {
			httpRequestBase.setHeader("Host", host);
		}
		if (StringUtils.isNotBlank(contentType)) {
			httpRequestBase.setHeader("Content-type", contentType);
		}
	}

	/**
	 * 
	 * @param httpRequestBase
	 * @param headerMap
	 */
	public static void setHeaderWithCommon(HttpRequestBase httpRequestBase, Map<String, String> headerMap) {
		setCommonHeader(httpRequestBase);
		if (headerMap != null) {
			for (Entry<String, String> headerEntry : headerMap.entrySet()) {
				httpRequestBase.setHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
	}

	/**
	 * 
	 * @param httpRequestBase
	 * @param headerMap
	 * @throws UnsupportedEncodingException
	 */
	public static void setHeader(HttpRequestBase httpRequestBase, Map<String, String> headerMap)
			throws UnsupportedEncodingException {
		if (headerMap != null) {
			for (Entry<String, String> headerEntry : headerMap.entrySet()) {
				httpRequestBase.setHeader(headerEntry.getKey(), headerEntry.getValue());
			}
		}
	}

	public static String getReturnStr(HttpClient client, HttpRequestBase httpRequestBase, String encoding)
			throws IOException {
		try {
			HttpResponse response = client.execute(httpRequestBase);
			// 获取header中的编码
			String charset = "";
			if (response.getLastHeader("Content-Type") != null
					&& response.getLastHeader("Content-Type").toString().contains("charset")) {
				String contentType = response.getLastHeader("Content-Type").getValue();
				charset = contentType.substring(contentType.indexOf("charset=") + 8, contentType.length());
			}
			// 如果获取失败则用默认编码
			if (StringUtils.isBlank(charset)) {
				if (StringUtils.isBlank(encoding)) {
					charset = "UTF-8";
				} else {
					charset = encoding;
				}
			}
			// 判断是否使用压缩
			String contentEncoding = "";
			if (response.getLastHeader("Content-Encoding") != null) {
				contentEncoding = response.getLastHeader("Content-Encoding").getValue();
				if (contentEncoding.indexOf("gzip") != -1) {
					return EntityUtils.toString(new GzipDecompressingEntity(response.getEntity()), charset);
				}
				if (contentEncoding.indexOf("deflate") != -1) {
					return EntityUtils.toString(new DeflateDecompressingEntity(response.getEntity()), charset);
				}
			}
			return EntityUtils.toString(response.getEntity(), charset);
		} finally {
			httpRequestBase.abort();
		}
	}

	public static byte[] getReturnByteArray(HttpClient client, HttpRequestBase httpRequestBase) throws IOException {
		try {
			HttpResponse response = client.execute(httpRequestBase);
			return EntityUtils.toByteArray(response.getEntity());
		} finally {
			httpRequestBase.abort();
		}
	}

	public static void close(HttpClient httpClient) {
		if (httpClient != null && httpClient.getConnectionManager() != null) {
			httpClient.getConnectionManager().shutdown();
			httpClient = null;
		}
	}

	public static void setPostEntity(HttpPost post, Map<String, String> params, String encode)
			throws UnsupportedEncodingException {
		if (params == null || params.size() <= 0) {
			return;
		}
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		post.setEntity(new UrlEncodedFormEntity(nvps, encode));
	}

	public static String genGetParam(Map<String, String> params, String encode) throws ParseException, IOException {
		if (params == null || params.size() <= 0) {
			return "";
		}
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Entry<String, String> entry : params.entrySet()) {
			nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		return EntityUtils.toString(new UrlEncodedFormEntity(nvps, encode));
	}

	/**
	 * 填入代理
	 * 
	 * @param client
	 * @param proxyPO
	 */
	public static void setHttpClientProxy(HttpClient client, HttpHost proxy) {
		if (proxy != null) {
			client.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,
					new HttpHost(proxy.getHostName(), proxy.getPort()));
		}
	}
}

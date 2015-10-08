package com.marsyoung.crawler;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;


@SuppressWarnings("deprecation")
public class UploadFile {
	
	private static Logger log=LoggerFactory.getLogger(UploadFile.class);
	private static UploadFile uploadFile;
	private UploadFile() {
	}
	public static UploadFile getInstance(){
		if(uploadFile==null){
			return new UploadFile();
		}
		return uploadFile;
	}
	
	public HttpClient httpClient;

	
	public HttpClient getHttpClient() {
		return httpClient;
	}
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	/**
	 * 1.获取活动登录状态的httpclient，没有则新建
	 * 2。上传文件
	 * 3.导入
	 * 4.获取导入结果
	 * @param file
	 * @return
	 */
	public Boolean uploadFile(File file){
		try {
			httpClient=getLoginHttpClient();
			log.info("获取活动登录状态的httpclient 成功。");
		} catch (UnsupportedEncodingException e) {
			log.error("获取登录状态的httpclient失败：{}",e);
		}
		
		String fileUrl = null;
		try {
			fileUrl=getTheUplodaFile(file);
			log.info("上传文件成功:{}",fileUrl);
		} catch (UnsupportedEncodingException e) {
			log.error("上传文件失败：{}",e);
		}
		
		try {
			Boolean b=importTheFile(fileUrl);
			if(!b){
				log.error("导入文件失败");
				return b;
			}
		} catch (UnsupportedEncodingException e) {
			log.error("导入文件失败：{}",e);
		}
		
		String result=checkTheResult();
		log.info("导入结果如下:\n{}",result);
		
		return null;
	}
	
	/**4
	 * @return
	 */
	public String checkTheResult(){
		HttpClient client = httpClient;
		HttpGet get = new HttpGet("https://crm.xiaoshouyi.com/json/crm_import/check-import-status.action?belongId=3");
		try {
			String resp= UploadFile.getInstance().getReturnStr(client, get, null);
			if(JSONObject.parseObject(resp).getInteger("status").equals(0)){
				JSONObject data=JSONObject.parseObject(resp).getJSONObject("data");
				log.info("导入结果如下：{}",resp);
				String result="共导入"+data.getJSONObject("uploadStatus").getInteger("totalCount")+"条记录，成功"+data.getJSONObject("uploadStatus").getInteger("succNow")+"条记录。";
				return result;
			}else{
				log.info("导入结果如下：{}",resp);
			}
		} catch(Exception e){
			log.error("导入结果如下：{}",e);
		}
		return null;
	}
	
	
	/**3
	 * @param fileUrl
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public Boolean importTheFile(String fileUrl) throws UnsupportedEncodingException{
		HttpClient client = httpClient;
		HttpPost post = new HttpPost("https://crm.xiaoshouyi.com/json/crm_import/import.action");
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("belongId", "3"));
		nvps.add(new BasicNameValuePair("entityTypeId", "1003588"));
		nvps.add(new BasicNameValuePair("overWrite", "0"));
		nvps.add(new BasicNameValuePair("fileUrl", fileUrl));
		nvps.add(new BasicNameValuePair("code", "GB18030"));
		nvps.add(new BasicNameValuePair("undefined", "/download-imp-model.action?belongId=3"));
		post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
		try {
			String resp= UploadFile.getInstance().getReturnStr(client, post, null);
			if(JSONObject.parseObject(resp).getInteger("status").equals(0)){
				return true;
			}else{
				log.info("导入文件失败：{}",resp);
			}
		} catch(Exception e){
			log.error("导入文件失败：{}",e);
		}
		return false;
	}
	
	
	/**2
	 * @param file
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getTheUplodaFile(File file) throws UnsupportedEncodingException{
		HttpClient client =  httpClient;
		HttpPost post = new HttpPost("https://crm.xiaoshouyi.com/json/global_uploadify/csv-upload.action");
		HttpEntity reqEntity = MultipartEntityBuilder.create()
                .addPart("Filename", new StringBody(file.getName()))
                .addPart("uploadFileType", new StringBody("csv"))
                .addPart("uploadFile", new FileBody(file))
                .addPart("Upload", new StringBody("Submit Query"))
                .build();
		post.setEntity(reqEntity);
		try {
			String resp= UploadFile.getInstance().getReturnStr(client, post, null);
			if(JSONObject.parseObject(resp).getInteger("status").equals(0)){
				return JSONObject.parseObject(resp).getJSONObject("data").getJSONObject("returnParams").getString("fileUrl");
			}else{
				log.info("上传文件失败：{}",resp);
			}
		} catch(Exception e){
			log.error("上传文件失败：{}",e);
		}
		
		return null;
	}
	
	
	/**
	 * 1
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public HttpClient getLoginHttpClient() throws UnsupportedEncodingException{
		if(httpClient==null){
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost("https://crm.xiaoshouyi.com/global/do-login.action");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("loginName", "xxx@taochedao.cn"));
			nvps.add(new BasicNameValuePair("password", "xxx"));
			nvps.add(new BasicNameValuePair("graphCode", ""));
			nvps.add(new BasicNameValuePair("sessionID", ""));
			post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			try {
				String resp= UploadFile.getInstance().getReturnStr(client, post, null);
				if(JSONObject.parseObject(resp).getString("statusText").equals("成功")){
					setHttpClient(client);
					return httpClient;
				}else{
					log.info("获取登录状态的httpclient失败：{}",resp);
				}
			} catch(Exception e){
				log.error("获取登录状态的httpclient失败：{}",e);
			}
		}
		return httpClient;
	}
	
	public String getReturnStr(HttpClient client, HttpRequestBase httpRequestBase, String encoding)
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
	
	public static void main(String[] args) {
		UploadFile.getInstance().uploadFile(new File("C:\\Users\\zhiyuma\\Desktop\\opportunity.csv"));
	}
}

